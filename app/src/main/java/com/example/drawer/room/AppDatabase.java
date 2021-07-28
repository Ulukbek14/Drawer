package com.example.drawer.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.drawer.model.TaskModel;

@Database(entities = TaskModel.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
