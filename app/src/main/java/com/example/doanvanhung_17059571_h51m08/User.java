package com.example.doanvanhung_17059571_h51m08;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User  {
    //create id column
    @PrimaryKey(autoGenerate = true)
    private int ID;

    //Create text column
    @ColumnInfo(name = "text")
    private String text;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
