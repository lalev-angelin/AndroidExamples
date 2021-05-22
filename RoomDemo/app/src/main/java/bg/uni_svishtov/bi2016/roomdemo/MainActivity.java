package bg.uni_svishtov.bi2016.roomdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int START_PRODUCT=1;

    private LinearLayout listLayout;
    private int elementCounter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listLayout = findViewById(R.id.linearLayout);
        elementCounter=0;

        db = Room.databaseBuilder(
                getApplication(),
                AppDatabase.class,
                "bg.uni_svishtov.bi2016.database")
                .allowMainThreadQueries()
                .build();

        loadAllProducts();

    }

    private void loadAllProducts() {
        List<Product> productsList = db.productDao().getAllProducts();
        for (Product p : productsList) {
            addProduct(p.getProductCode(), p.getProductName(), p.getProductDescription());
        }
    }

    private void storeProduct(int productCode, String productName, String productDescription) {
        Product p = new Product(productCode, productName, productDescription);
        db.productDao().insert(p);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, ProductActivity.class);
        startActivityForResult(intent, START_PRODUCT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case START_PRODUCT:
                if (resultCode==RESULT_OK) {
                    try {
                        int productCode = data.getIntExtra("productCode", 0);
                        String productName = data.getStringExtra("productName");
                        String productDescription = data.getStringExtra("productDescription");
                        storeProduct(productCode, productName, productDescription);
                        addProduct(productCode, productName, productDescription);
                    } catch (NullPointerException e) {
                        //Do nothing and return
                        return;
                    }
                }
        }
    }

    private void addProduct(int productCode, String productName, String productDescription) {
        LayoutInflater layoutInflater = getLayoutInflater();
        try {
            LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.list_item,
                    listLayout, false);
            ((TextView)layout.findViewById(R.id.product_code_view)).setText(""+productCode);
            ((TextView)layout.findViewById(R.id.product_name_view)).setText(productName);
            ((TextView)layout.findViewById(R.id.product_description_view)).setText(productDescription);

            if (elementCounter++%2==1) {
                layout.setBackgroundColor(Color.rgb(220, 220, 255));
            }
            listLayout.addView(layout);

        } catch (NullPointerException e) {
            //Do nothing and exit
            return;
        }
    }
}
