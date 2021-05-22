package bg.uni_svishtov.bi2016.lifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textView);
        text.setText(text.getText()+"\n onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        text.setText(text.getText()+"\n onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        text.setText(text.getText()+"\n onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        text.setText(text.getText()+"\n onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        text.setText(text.getText()+"\n onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        text.setText(text.getText()+"\n onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        text.setText(text.getText()+"\n onResume");
    }
}
