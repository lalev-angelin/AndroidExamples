package uk.co.lalev.hello6;

import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey
    long invNo;
    String name;
    double price;
    long quantity;
}
