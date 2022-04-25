package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

import com.example.todoapp.database.DatabaseFactory;
import com.example.todoapp.database.IDatabase;
import com.example.todoapp.database.TodoManager;

public class MainActivity extends AppCompatActivity {

    private TodoManager todoManager;

    public TodoManager getTodoManager() {
        return todoManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final IDatabase sqllite = DatabaseFactory.create(0, this.getApplicationContext());
        final IDatabase firebase = DatabaseFactory.create(1);
        todoManager = new TodoManager(firebase); //Firebase
        todoManager.Initialize();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
