package com.example.todoapp.database.firebase;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.todoapp.database.IDatabase;
import com.example.todoapp.database.TodoTask;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Sets;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FirebaseDatabase implements IDatabase {

    private static final String DATABASE_NAME = "TodoApp";
    private static final String COLLECTION_NAME = "todolist";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_STARTTIME = "startTime";
    private static final String KEY_ENDTIME = "endTime";
    private static final String KEY_DESCRIPTION = "description";

    private FirebaseFirestore db;

    @Override
    public void init() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void shutdown() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void getTodoIds(Consumer<Set<UUID>> callback) {
        db.collection(COLLECTION_NAME)
                .get()
                .addOnCompleteListener(task -> {
                    Log.d(TAG, "b: " + Thread.currentThread().getId());
                    if (task.isSuccessful()) {
                        Set<UUID> result = new HashSet<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            result.add(UUID.fromString(document.getId()));
                            Log.d(TAG, document.getId() + " => " + document.getData());
                        }
                        callback.accept(result);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    private String TAG = "Firebase";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void getTodos(Consumer<Set<TodoTask>> callback) {
        db.collection(COLLECTION_NAME)
                .get()
                .addOnCompleteListener(task -> {
                    Log.d(TAG, "b: " + Thread.currentThread().getId());
                    if (task.isSuccessful()) {
                        Set<TodoTask> result = new HashSet<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String title = document.getString(KEY_TITLE);
                            String desc = document.getString(KEY_TITLE);
                            Date start = new Date(document.getLong(KEY_STARTTIME));
                            Date end = new Date(document.getLong(KEY_ENDTIME));
                            result.add(new TodoTask(UUID.fromString(document.getId()), title, start, end, desc));
                            Log.d(TAG, document.getId() + " => " + document.getData());
                        }
                        callback.accept(result);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    @Override
    public void save(TodoTask task) {
        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put(KEY_TITLE, task.getTitle());
        taskMap.put(KEY_DESCRIPTION, task.getDescription());
        taskMap.put(KEY_STARTTIME, task.getStartTime().getTime());
        taskMap.put(KEY_ENDTIME, task.getEndTime().getTime());
        db.collection(COLLECTION_NAME).document(task.getId().toString()).set(taskMap);
    }
}
