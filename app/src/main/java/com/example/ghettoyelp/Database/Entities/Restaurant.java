package com.example.ghettoyelp.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ghettoyelp.Database.MainDatabase;

@Entity(tableName = MainDatabase.RESTAURANT_TABLE)
public class Restaurant {
    @PrimaryKey(autoGenerate = true)
    int id;
}
