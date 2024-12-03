package com.example.ghettoyelp.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ghettoyelp.Database.MainDatabase;

@Entity(tableName = MainDatabase.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;
}
