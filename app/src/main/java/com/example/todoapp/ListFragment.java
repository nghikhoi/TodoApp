package com.example.todoapp;

import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.todoapp.database.TodoTask;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private ListView lvTasks;
    private Button btnCreate;

    private ArrayList<TodoTask> tasks = new ArrayList<>();
    private TodoListViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);

        btnCreate = v.findViewById(R.id.btnCreateTODO);
        lvTasks = v.findViewById(R.id.lvTODOLIST);
        adapter = new TodoListViewAdapter(tasks);
        lvTasks.setAdapter(adapter);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        btnCreate.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, EditFragment.class, null).commit();
        });

        reloadTasks();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadTasks();
    }

    private void reloadTasks() {
        MainActivity activity = (MainActivity) getActivity();
        activity.getTodoManager().getTodos(result -> {
            tasks.clear();
            tasks.addAll(result);
            adapter.notifyDataSetChanged();
        });
    }

}
