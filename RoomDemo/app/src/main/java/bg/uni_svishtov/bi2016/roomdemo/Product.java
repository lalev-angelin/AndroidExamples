package bg.uni_svishtov.bi2016.roomdemo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey
    private int productCode;
    private String productName;
    private String productDescription;

    public Product(int productCode, String productName, String productDescription) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
