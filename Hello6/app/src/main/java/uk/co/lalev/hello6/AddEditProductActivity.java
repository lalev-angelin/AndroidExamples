package uk.co.lalev.hello6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddEditProductActivity extends AppCompatActivity {

    private EditText InventoryNumberEdit;
    private EditText NameEdit;
    private EditText PriceEdit;
    private EditText QuantityEdit;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        InventoryNumberEdit = findViewById(R.id.InventoryNumberEdit);
        NameEdit = findViewById(R.id.NameEdit);
        PriceEdit = findViewById(R.id.PriceEdit);
        QuantityEdit = findViewById(R.id.QuantityEdit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        intent = getIntent();
        if (intent.getStringExtra("mode").equals("edit")) {
            InventoryNumberEdit.setText(intent.getStringExtra("invNo"));
            NameEdit.setText(intent.getStringExtra("name"));
            PriceEdit.setText(intent.getStringExtra("price"));
            QuantityEdit.setText(intent.getStringExtra("quantity"));
        }
    }

    public void onSaveButtonClick(View view) {
        ProductDatabase db = Database.get(getApplication());
        Product product = new Product();


        try {
            product.invNo = Long.parseLong(InventoryNumberEdit.getText().toString());
            product.price = Double.parseDouble(PriceEdit.getText().toString());
            product.name = NameEdit.getText().toString();
            product.quantity = Long.parseLong(QuantityEdit.getText().toString());
        } catch (NumberFormatException e) {
           new AlertDialog.Builder(this)
                    .setTitle("Грешка")
                    .setMessage("Грешка при въвеждане на цифрова стойност")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        try {
            if (intent.getStringExtra("mode").equals("addnew")) {
                db.productDAO().insert(product);
            } else {
                db.productDAO().update(product);
            }
        } catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("Грешка")
                    .setMessage("Продукт със същия ключ вече съществува в базата от данни!")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        finish();
    }

    public void onCancelButtonClick(View view) {
        finish();
    }
}