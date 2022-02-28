package uk.co.lalev.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1(View view) {
        ((Button) view).setText("I've been clicked!");
    }

    public void onClick2(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Alert, alert, alert!")
                .setMessage("We are live!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((Button)view).setText("Alert has been displayed!");
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}