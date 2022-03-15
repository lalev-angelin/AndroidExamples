package uk.co.lalev.hello6;

import android.app.Application;

import androidx.room.Room;

public class Database {
    private static ProductDatabase db = null;

    public static ProductDatabase get(Application application) {
        if (db==null) {
            db = Room.databaseBuilder(
                    application,
                    ProductDatabase.class,
                    "bg.uni-svishtov.bi2018.database")
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }
}
