package com.example.todoapp.database;

public class TodoManager {

    private final IDatabase database;

    private boolean _init = false;

    public TodoManager(IDatabase database) {
        this.database = database;
    }

    public synchronized void Initialize() {
        if (!_init) {
            database.init();
            _init = true;
        }
    }

}
