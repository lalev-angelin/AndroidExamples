package uk.co.lalev.helloworld3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_IMAGE_CAPTURE = 1;
    public static final int RESULT_IMAGE_GET = 2;
    public static final int RESULT_CHOOSE_FILE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnImage1Click(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(intent, RESULT_IMAGE_CAPTURE);
        }
    }

    public void OnImage2Click(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.uni-svishtov.bg"));
        if (intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }

    public void OnImage3Click(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "a.lalev@uni-svishtov.bg");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Относно незнам-какво си");
        intent.putExtra(Intent.EXTRA_TEXT, "Бла бла бла...");
        startActivity(Intent.createChooser(intent, "Choose email client"));
    }

    public void OnImage4Click(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void OnImage5Click(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(intent, RESULT_IMAGE_GET);
        }
    }

    public void OnImage6Click(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "Choose file"), RESULT_CHOOSE_FILE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No file manager present", Toast.LENGTH_SHORT).show();
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
                    ImageView view = findViewById(R.id.cameraImage);
                    view.setImageBitmap(imageBitmap);
                    view.setVisibility(View.VISIBLE);
                };
                break;
            case RESULT_IMAGE_GET: {
                if (resultCode==RESULT_OK) {
                    Uri i = data.getData();
                    ImageView view = findViewById(R.id.cameraImage);
                    view.setImageURI(null);
                    view.setImageURI(i);
                    view.setVisibility(View.VISIBLE);
                }
            }

            case RESULT_CHOOSE_FILE: {
                if (resultCode==RESULT_OK) {
                    Uri i = data.getData();
                    Toast.makeText(this, "You have chosen: "+i.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}