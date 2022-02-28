package uk.co.lalev.hello4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView r = findViewById(R.id.recyclerView);
        r.setLayoutManager(new LinearLayoutManager(this));
        DataAdapter adapter = new DataAdapter(this);
        r.setAdapter(adapter);

        ItemTouchHelper i = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getPosition();
                Client removedClient = Client.clients.remove(position);
                adapter.notifyDataSetChanged();
                Snackbar snackbar = Snackbar.make(findViewById(R.id.mainActivityConstrantLayout), "Deleted "+removedClient.getName(), Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", v->{
                    Client.clients.add(position, removedClient);
                    adapter.notifyDataSetChanged();
                });
                snackbar.show();
            }
        });
        i.attachToRecyclerView(r);

        r.setOnClickListener();
    }

}