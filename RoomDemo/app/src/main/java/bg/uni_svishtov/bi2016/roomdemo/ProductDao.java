package bg.uni_svishtov.bi2016.roomdemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM product")
    List<Product> getAllProducts();

    @Query("SELECT * FROM product WHERE productCode=:productCode")
    Product getProduct(int productCode);

    @Delete
    void delete(Product product);

    @Insert
    void insert(Product product);
}
