package uk.co.lalev.helloworld2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "bg.uni_svishtov.bi2022.notification.channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Демонстраци на нотификации",
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void onClick1(View view) {
       new AlertDialog.Builder(this)
               .setTitle("Hello, world!")
               .setMessage("This is very, very important")
               .setCancelable(false)
               .setPositiveButton(R.string.ok, (a,b)-> {
                   TextView v = findViewById(R.id.alertView);
                   v.setVisibility(View.VISIBLE);
               })
               .setNegativeButton(R.string.nok, (a,b)-> {
                   TextView v = findViewById(R.id.alertView);
                   v.setVisibility(View.INVISIBLE);
               })
               .show();

    }

    public void onClick2(View view) {
        NotificationCompat.Builder nb = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentText("Уведомително съобщение")
                .setContentTitle("Съобщение")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_baseline_13mp_24);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(1, nb.build());
    }

    public void onClick3(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message", "The quick");
        startActivity(intent);
    }

    public void onClick4(View view) {
        Toast.makeText(this, "Вие натиснахте бутон "+((Button)view).getText(), Toast.LENGTH_LONG).show();
    }

    public void onClick6(View view) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Добро утро!");
        intent.putExtra(AlarmClock.EXTRA_HOUR, 5);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 15);

        if ((intent.resolveActivity(getPackageManager())!=null)) {
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "Няма приложение - часовник???", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}