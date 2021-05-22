package uk.co.lalev.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView contactsRecycler = findViewById(R.id.contactsRecycler);
        contactsRecycler.setLayoutManager(new LinearLayoutManager(this));
        contactsRecycler.setAdapter(new DataAdapter(this));
    }
}