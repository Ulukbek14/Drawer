package com.example.drawer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.ItemOnClickListener;
import com.example.drawer.R;
import com.example.drawer.model.TaskModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    List<TaskModel> list;
    List<TaskModel> filteredData;
    public ItemOnClickListener itemOnClickListener;
    public boolean isList;

    public TaskAdapter(boolean isList, ItemOnClickListener itemClickListener) {
        this.list = new ArrayList<>();
        this.filteredData = new ArrayList<>();
        this.itemOnClickListener = itemClickListener;
        this.isList = isList;
    }

    public TaskModel getWordAtPosition(int position) {
        return list.get(position);
    }

    public void setList(List<TaskModel> modelList) {
        list.clear();
        list.addAll(modelList);
        notifyDataSetChanged();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<TaskModel> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;

        public TaskViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.item_title);
        }

        public void onBind(TaskModel taskModel) {
            txtTitle.setText(taskModel.getTitle());

            itemView.setOnClickListener(v -> {
                itemOnClickListener.onItemClick(getAdapterPosition(), taskModel);
            });
        }
    }
}