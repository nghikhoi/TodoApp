package com.example.todoapp.database;

import java.util.Set;
import java.util.UUID;

public interface IDatabase {

    void init();

    void shutdown();

    Set<UUID> getTodoIds();

    Set<TodoTask> getTodos();

    void save(TodoTask task);

}
