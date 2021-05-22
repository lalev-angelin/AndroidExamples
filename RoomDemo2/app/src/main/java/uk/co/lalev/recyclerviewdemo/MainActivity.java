package uk.co.lalev.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.net.DatagramPacket;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContactDatabase db;


    private class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

        private final DataAdapter dataAdapter;
        private final ContactDatabase contactDatabase;

        public SwipeToDeleteCallback(DataAdapter dataAdapter, ContactDatabase contactDatabase) {
            super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            this.dataAdapter=dataAdapter;
            this.contactDatabase=contactDatabase;
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getPosition();
            Contact contact = dataAdapter.getContacts().get(position);
            db.contactDao().delete(contact);
            View view = findViewById(R.id.mainLayout);
            Snackbar snackbar = Snackbar.make(view, contact.getName()+" e изтрит", Snackbar.LENGTH_LONG);
            snackbar.setAction("Връщане", v->{
               db.contactDao().insert(Arrays.asList(contact));
            });
            snackbar.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(
                getApplicationContext(),
                ContactDatabase.class,
                "uk.co.lalev.roomandrecyclerdemo")
                    .allowMainThreadQueries()
                    .build();

        RecyclerView contactsRecycler = findViewById(R.id.contactsRecycler);
        contactsRecycler.setLayoutManager(new LinearLayoutManager(this));
        final DataAdapter dataAdapter = new DataAdapter(this);
        contactsRecycler.setAdapter(dataAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(dataAdapter, db));
        itemTouchHelper.attachToRecyclerView(contactsRecycler);

        db.contactDao().getAllContacts().observe(this, a->{
            dataAdapter.setContacts(a);
        });
    }

    public void saveContactClick(View view) {
        EditText name = findViewById(R.id.nameEdit);
        EditText phone = findViewById(R.id.phoneEdit);
        EditText email = findViewById(R.id.emailEdit);


        Contact contact = new Contact(name.getText().toString(), email.getText().toString(), phone.getText().toString());
        db.contactDao().insert(Arrays.asList(contact));
    }

    public void clearContactClick(View view) {
        EditText name = findViewById(R.id.nameEdit);
        EditText phone = findViewById(R.id.phoneEdit);
        EditText email = findViewById(R.id.emailEdit);

        name.setText(null);
        phone.setText(null);
        email.setText(null);
    }
}