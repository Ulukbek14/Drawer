package com.example.drawer;

import com.example.drawer.model.TaskModel;

public interface ItemOnClickListener {
    void onItemClick(int position, TaskModel taskModel);
}