package com.example.doanvanhung_17059571_h51m08;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDAO  {
    //Insert query
    @Insert(onConflict = REPLACE)
    void insert(User mainData);

    //Delete query
    @Delete
    void delete(User mainData);

    //Delete all query
    @Delete
    void reset(List<User> mainData);

    //Updata query
    @Query("UPDATE user SET text = :sText WHERE ID = :sID")
    void update(int sID, String sText);
    
    //Get all data query
    @Query("SELECT * FROM user")
    List<User> getAll();
}
