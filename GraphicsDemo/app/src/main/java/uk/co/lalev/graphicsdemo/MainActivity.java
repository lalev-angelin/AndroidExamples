package uk.co.lalev.graphicsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = findViewById(R.id.imageView);
        image.setBackgroundResource(R.drawable.animation);
        final AnimationDrawable animationDrawable = (AnimationDrawable)image.getBackground();

        image.setOnClickListener((a)-> {
            animationDrawable.start();
        });
    }
}