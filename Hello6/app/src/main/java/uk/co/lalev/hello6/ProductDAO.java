package uk.co.lalev.hello6;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM product WHERE invNo=:inventoryNumber")
    Product getProduct(int inventoryNumber);

    @Delete
    void delete(Product product);

    @Update
    void update(Product product);

    @Insert
    void insert(Product product);
}
