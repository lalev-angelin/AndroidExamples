package bg.uni_svishtov.bi2016.alertdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /*
     * Методът onClick1 е свързан с първия бутон чрез декларацията android:onClick на ред
     * 16 в app/res/layout/activity_main.xml. Графичният дизайнер показва това свойство
     * просто като onClick.
     * Свързването води до това, че когато бутонът бъде натиснат, долният метод ще се изпълни.
     */
    public void onClick1(View view) {

        /*
         * AlertDialog е клас, който позволява извеждането на съобщения в прозорец, разположен
         * "над" потребителския интерфейс на дейността. Тъй като конструирането на подобен обект
         * изисква задаване на множество параметри, създаването му става чрез специален
         * клас - фабрика, която следва т.нар. Builder шаблон.
         * Тази фабрика - класът AlertDialog.Builder - позволява попълването на стойности чрез използване на
         * методи от типа setXXXXX. Всеки от тези методи попълва дадена стойност и връща референция към
         * обекта - фабрика. Това позволява "закачането" на методите .setXXX един след друг, което е
         * много по четливо от извикване на класически конструктор с например 12 параметъра.
         * Читателите следва да забележат, че тъй като цялата инициализация на фабриката е един много дълъг
         * израз, точкатата и запетаята се поставя след последния метод. Той се нарича show()
         * и генерира AlertDialog обект, попълнен със зададените параметри, след което го показва на
         * екрана на мобилното устройство.
         *
         */
        new AlertDialog.Builder(this)

                .setTitle("Hello, world")  //Задава заглавие на прозореца
                .setMessage("This is very important message")  //Попъва текста в прозореца

                // Задаването на .setCancelable(false) означава, че от диалоговия прозорец
                // ще може да се излезе само след чукване върху някой от бутоните му.
                // Обратното поведение, което е стойността по подразбиране, се задава със
                // .setCancelable(true) и означава, че от прозореца ще може да се излезе
                // и по други начини. Такъв начин например е чукването извън прозореца.
                // Важно е да се отбележи, че при Android, за разлика от Windows, извеждането на
                // прозорец със .setCancelable(true) не е истински модално. Истински модалното
                // поведение, което се поддържа от Windows, но не и от Android,  би спрябло
                // изпълнението на програмния код докато потребителят даде отговор.
                // Разработчиците на Android са на мнение, че подобно поведение - блокиране на
                // приложението до получаване на отговор - е лоша практика, която пречи на
                // ефективното използване на ограничените процесорни ресурси на мобилното устройство
                // от програмите. Ето защо след извикване на метода .show(), програмният код ще
                // продължи изпълнението си. За да получим резултатите от диалога, ние трябва да
                // имаме колбак методи, които да се извикат от операционната система, когато
                // потребителят най-накрая щракне върху един от бутоните.
                //
                .setCancelable(false)

                // Показва бутон, който представлява положителен отговор на въпроса.
                // Методът взема два параметъра - заглавие на бутона и обект от тип
                // DialogInterface.OnClickListener.
                // Този обект е начин да се зададе колбак метод, който да бъде
                // извикан при натискане на бутона.
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    //Колбак метод, който да се извика при натискане на бутона.
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      TextView text = findViewById(R.id.textView);
                      text.setVisibility(View.VISIBLE);
                      text.setText(getString(R.string.you_have_pressed_ok));
                    }
                })

                // Показва бутон, който представлява отрицателен отговор на въпроса.
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    //Колбак метод, който да се извика при натискане на бутона.
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView text = findViewById(R.id.textView);
                        text.setVisibility(View.VISIBLE);
                        text.setText(getString(R.string.you_have_pressed_cancel));
                    }
                })

                //Показва диалоговия прозорец.
                .show();
    }

    public void onClick2(View view) {
        //Стартира дейност чрез експлицитно намерение.
        //Стартираната в случая дейност е HelloWorldActivity.
        Intent intent = new Intent(this, HelloWorldActivity.class);
        startActivity(intent);
    }

    public void onClick3(View view) {
        //Стартира имейл клиент. Ако на системата има два или повече клиента, се извежда
        //диалог за избор на клиент.
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "lalev@uni-svishtov.bg", null));
        startActivity(Intent.createChooser(emailIntent, "Изпращане на поща чрез"));
    }
}
