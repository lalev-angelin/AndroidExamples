package bg.uni_svishtov.bi2016.roomdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ProductActivity extends AppCompatActivity {

    private EditText productCodeEdit;
    private EditText productNameEdit;
    private EditText productDescriptionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productCodeEdit = findViewById(R.id.product_code_edit);
        productNameEdit = findViewById(R.id.product_name_edit);
        productDescriptionEdit = findViewById(R.id.product_description_edit);
    }

    public void onClick1(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void onClick2(View view) {
        Intent data = new Intent();
        data.putExtra("productCode", Integer.valueOf(productCodeEdit.getText().toString()));
        data.putExtra("productName", productNameEdit.getText().toString());
        data.putExtra("productDescription", productDescriptionEdit.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
}
