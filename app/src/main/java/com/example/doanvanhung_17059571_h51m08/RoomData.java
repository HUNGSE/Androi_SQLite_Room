package com.example.doanvanhung_17059571_h51m08;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class RoomData extends RoomDatabase {
    //Create database instance
    private static RoomData database;

    //Define database name
    private static String DATABASE_NAME = "database.sqlite";

    public synchronized static RoomData getInstance(Context context){
        //Check condition
        if(database ==null){
            //when database is null
            //Initialize database
            database = Room.databaseBuilder( context.getApplicationContext()
                    , RoomData.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //Return database
        return database;
    }
    //Create Dao
    public abstract MainDAO mainDao();

}
