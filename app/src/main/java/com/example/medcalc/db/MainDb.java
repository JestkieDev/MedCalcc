package com.example.medcalc.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Pacient.class}, version = 1)
public abstract class MainDb extends RoomDatabase {

   public abstract PacientDao pacientDao();

   private static MainDb INSTANCE;

    public static MainDb getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),MainDb.class, "PACIENTS")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
