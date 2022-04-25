package com.example.todoapp.database;

import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

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

    public void getTodoIds(Consumer<Set<UUID>> callback) {
        database.getTodoIds(callback);
    }

    public void getTodos(Consumer<Set<TodoTask>> callback) {
        database.getTodos(callback);
    }

    public void save(TodoTask task) {
        database.save(task);
    }

}
