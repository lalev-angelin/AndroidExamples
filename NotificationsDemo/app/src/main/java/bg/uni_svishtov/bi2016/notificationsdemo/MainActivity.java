package bg.uni_svishtov.bi2016.notificationsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Имената на каналите следва да са глобално уникални, поради което
    // те следва да се префиксират с домейн името на организацията.
    public static final String channelId = "bg.uni-svishtov.bi2016.NotificationsDemo";
    public static final String name = "Важни съобщения от СА Ценов";
    public static final String description = "Този канал ще информира потребителя за закъснели студентски такси. Бла Бла.";

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Създаване на канал. Тази операция трябва да се извърши само ако операционната система е
        //Android 8 или по-нова. Без такъв канал, нотификациите на такива нови системи
        //няма да се показват.
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    name,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    /*
     * Показва нотификация в статус лентата на мобилното устройство.
     */
    public void onClick1(View view) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, channelId)
                        //Малка икона, която да се покаже в статус лентата
                        .setSmallIcon(R.drawable.ic_vpn_key_black_24dp)
                        //Заглавие на нотификацията
                        .setContentTitle("Съобщение от СА Ценов")
                        //Текст на нотификацията
                        .setContentText("Вие натиснахте този бугон "+(++counter)+" пъти")
                        //Приоритет. Задава се тук и при създаването на канала
                        //тъй като каналът се задава само за Андроид версия 8 или по-нов.
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // Числото - идентификатор е уникално за нотификацията. Повторното извеждане на
        // нотификация със същия номер променя изведената по-рано нотификация.
        notificationManager.notify(1, builder.build());
    }


    /*
     * Извежда нотификация, която изчезва след 7 секунди.
     */
    public void onClick2(View view) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_vpn_key_black_24dp)
                        .setContentTitle("Съобщение от СА Ценов")
                        .setContentText("Това съобщение ще изчезне само след 7 секунди")
                        .setTimeoutAfter(7*1000)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(2, builder.build());
    }

    /*
     * Извежда нотификация, която реагира на чукване.
     */
    public void onClick3(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_vpn_key_black_24dp)
                .setContentTitle("Съобщение от СА Ценов")
                .setContentText("Щракнете тук")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(3, builder.build());
    }

    /*
     * Извежда тост
     */
    public void onClick4(View view) {
        Toast toast = Toast.makeText(this, "Test", Toast.LENGTH_LONG);
        toast.show();
    }
}
