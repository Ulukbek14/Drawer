package com.example.drawer.model;

import java.io.Serializable;

public class TaskModel implements Serializable {
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskModel(String title){
        this.title = title;
    }
}
