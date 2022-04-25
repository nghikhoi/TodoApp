package com.example.todoapp.database;

import android.content.Context;

import com.example.todoapp.database.firebase.FirebaseDatabase;
import com.example.todoapp.database.local.LocalDatabase;

public class DatabaseFactory {

    public static IDatabase create(int code, Object... args) {
        switch (code) {
            case 0:
                return new LocalDatabase((Context) args[0]);
            case 1:
                return new FirebaseDatabase();
            default: return null;
        }
    }

}
