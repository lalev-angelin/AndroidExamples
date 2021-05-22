package bg.uni_svishtov.bi2016.permissionsdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_READ_CALENDAR=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Искаме разрешение при създаване на главната дейност
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CALENDAR)!=PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.READ_CALENDAR}, REQUEST_READ_CALENDAR);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode) {
            case REQUEST_READ_CALENDAR:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    Toast toast = Toast.makeText(this, "Сега имаме разрешение за четене на календара", Toast.LENGTH_LONG);
                    toast.show();
                    break;
                } else {
                    Toast toast = Toast.makeText(this, "Все още нямаме разрешение за четене на календара", Toast.LENGTH_LONG);
                    toast.show();
                    break;
                }
        }
    }
}
