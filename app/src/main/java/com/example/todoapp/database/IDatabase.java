package com.example.todoapp.database;

import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

public interface IDatabase {

    void init();

    void shutdown();

    void getTodoIds(Consumer<Set<UUID>> callback);

    void getTodos(Consumer<Set<TodoTask>> callback);

    void save(TodoTask task);

}
