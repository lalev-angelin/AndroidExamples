package uk.co.lalev.hello6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ProductDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Database.get(getApplication());

        RecyclerView productsRecycler = findViewById(R.id.product_recycler);
        productsRecycler.setLayoutManager(new LinearLayoutManager(this));

        ProductAdapter productAdapter = new ProductAdapter(this, db);
        productsRecycler.setAdapter(productAdapter);

        db.productDAO().getAllProducts().observe(this, productAdapter::setProducts);

    }

    public void onFabClick(View view) {
        Intent i = new Intent(this, AddEditProductActivity.class);
        i.putExtra("mode", "addnew");
        startActivity(i);
    }
}