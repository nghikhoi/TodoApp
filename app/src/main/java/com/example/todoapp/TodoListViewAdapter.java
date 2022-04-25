package com.example.todoapp;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.todoapp.database.TodoTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TodoListViewAdapter extends BaseAdapter {

    private final ArrayList<TodoTask> listProduct;

    TodoListViewAdapter(ArrayList<TodoTask> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listProduct.get(position).getId().hashCode();
    }

    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.todotask_view, null);
        } else viewProduct = convertView;

        TodoTask todoTask = (TodoTask) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.taskId)).setText(String.format("ID = %s", todoTask.getId().toString()));
        ((TextView) viewProduct.findViewById(R.id.taskTitle)).setText(String.format("Title: %s", todoTask.getTitle()));
        ((TextView) viewProduct.findViewById(R.id.taskDescription)).setText(String.format("Description: %s", todoTask.getDescription()));
        ((TextView) viewProduct.findViewById(R.id.taskTime)).setText(String.format("Time: %s - %s", format.format(todoTask.getStartTime()), format.format(todoTask.getEndTime())));

        return viewProduct;
    }
}
