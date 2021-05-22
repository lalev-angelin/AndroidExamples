package uk.co.lalev.recyclerviewdemo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> getAllContacts();

    @Insert
    void insert(List<Contact> contacts);

    @Delete
    void delete(Contact contact);
}
