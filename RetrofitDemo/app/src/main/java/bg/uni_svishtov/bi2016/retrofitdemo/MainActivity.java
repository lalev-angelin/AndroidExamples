package bg.uni_svishtov.bi2016.retrofitdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        /*
         * Долните два реда ще премахнат ограниченията за извършване на мрежови операции в
         * главната нишка на приложението, както и други ограничения.
         * Би следвало тези редове да се използват само за отстраняване на грешки в програмите
         * по време на разработка.
         */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /*
         * Инстанцирането на обект от тип Proxy е нужно само когато се налага да използваме
         * прокси сървър. В такъв случай, трябва да инстанцираме и обект от тип
         * OkHttpClient и да му предадем прокси сървъра.
         * На свой ред OkHttpClient обектът трябва да бъде предаден на билдера на
         * ретрофит, който в противен случай ще създаде собствен обект от този тип, който
         * ще има настройки по подразбиране.
         */

        //Прокси на адрес http://proxy.uni-svishtov.bg:8080/
        java.net.Proxy proxy = new java.net.Proxy(
                java.net.Proxy.Type.HTTP,
                new InetSocketAddress("proxy.uni-svishtov.bg", 8080));
        //OkHttpClient с прокси
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .proxy(proxy)
                .build();
        /* Retrofit обект с предварително създаден клиент
         * В нашия пример създаваме Retrofit клиент, който ще сериализира обектите в JSON,
         * използвайки библиотеката на Google GSON за работа с JSON.
         * За да се използва Retrofit библиотеката по този начин, в build.gradle трябва да
         * се поставят две зависимости към външни библиотеки. Тези библиотеки са
         * com.squareup.retrofit2:converter-gson и com.google.code.gson:gson.
         * Заедно със самата библиотека Retrofit това налага поставянето на три реда
         * в секция dependencies на build.gradle, а именно:
         *   implementation 'com.squareup.retrofit2:converter-gson:2.7.1'
         *   implementation 'com.squareup.retrofit2:converter-gson:2.7.1'
         *   implementation 'com.google.code.gson:gson:2.8.6'
         * Читателите следва да забележат, че това са най-актуалните версии на тези библиотеки
         * които след време ще се променят. Android Studio може да обнови тези версии автоматично
         * ако е инструктирана от разработчика да направи това.
         */
        Retrofit retrofit = new Retrofit.Builder()
                //Задава инициализирания по-горе клиент
                .client(httpClient)
                /*
                 * Сървърът се намира на указания по-долу адрес и порт
                 * Нормално политиката по сигурност на Андроид не позволява
                 * свързването с http сървъри, тъй като това е несигурно.
                 * Това е тестов сървър и той не поддържа https. Ето защо, за да
                 * се осъществи свързването, политиката за сигурност по отношение
                 * на домейна uni-svishtov.bg е променена.
                 * За целта е създаден нов файл @xml/network_security.xml, в който е
                 * указана тази корекция. Задаването на корекцията изисква в елемента
                 * application в манифеста да се укаже атрибут
                 * android:networkSecurityConfig="@xml/network_security.xml"
                */
                .baseUrl("http://bimoodle.uni-svishtov.bg:8080/")
                //Долният ред е необходим за свързване на Retrofit с конвертора към GSON
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Създава нов RestClient от интерфейса, който създадохме в RestClient.java
        RestClient client = retrofit.create(RestClient.class);

        //Подготвя всички параметри на бъдещата REST заявка
        Call<LoginResponse> call = client.authenticate(new LoginRequest("975012", "lalev"));
        Response<LoginResponse> response = null;
        try {
            //Изпълнява заявката
            response = call.execute();
        } catch (IOException e) {
            //Toast toast = Toast.makeText(this, "Грешка при оторизацията пред сървъра", Toast.LENGTH_LONG);
            Toast toast = Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG);
            toast.show();
        }

        if (response.code()!=200) {
            Toast.makeText(this, "Некоректна парола или грешен URL. Код "+response.code(),
                    Toast.LENGTH_LONG).show();
                    return;
        }

        //Toast.makeText(this, response.body().getToken(), Toast.LENGTH_LONG).show();

        Call<List<Mark>> callMarks = client.getMarks("Bearer "+response.body().getToken());
        Response<List<Mark>> responseMarks = null;
        try {
            responseMarks = callMarks.execute();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
            return;
        }

        if (response.code()==200) {
            String message = "";

            for (Mark mark : responseMarks.body()) {
                message = message + mark.getSubject() +"\n";
            }

            new AlertDialog.Builder(this)
                    .setTitle("Data")
                    .setMessage(message)
                    .show();
        }


    }
}
