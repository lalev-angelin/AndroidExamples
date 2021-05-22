package bg.uni_svishtov.bi2016.intentdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_IMAGE_CAPTURE =1;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
    }


    public void onClick1(View view) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Добро утро");
        intent.putExtra(AlarmClock.EXTRA_HOUR, 5);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 45);

        if ((intent.resolveActivity(getPackageManager()) != null)) {
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "Нямаме алармен часовник????", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    public void onClick2(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(intent, RESULT_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case RESULT_IMAGE_CAPTURE:
                if (resultCode==RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap)extras.get("data");
                    image.setImageBitmap(imageBitmap);
                    image.setVisibility(View.VISIBLE);
                }
        }
    }
}
