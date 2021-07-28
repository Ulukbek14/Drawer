package com.example.drawer.room;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.drawer.unitt.PreferenceHelper;

public class MyApp extends Application {

    public static AppDatabase instance = null;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initPrefHelp();
        getInstance();
    }

    private void initPrefHelp() {
        PreferenceHelper preferencesHelper = new PreferenceHelper();
        preferencesHelper.unit(this);
    }

    public static AppDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "task.database")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
}
