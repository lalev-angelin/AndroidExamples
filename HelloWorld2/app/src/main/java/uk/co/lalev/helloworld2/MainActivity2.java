package uk.co.lalev.helloworld2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    private TextView textView;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);
        Random r = new Random(System.currentTimeMillis());
        i = r.nextInt();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("message")+"/"+i);
    }
}