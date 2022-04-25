package com.example.todoapp.database.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.todoapp.database.IDatabase;
import com.example.todoapp.database.TodoTask;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LocalDatabase implements IDatabase {

    private static final String DATABASE_NAME = "TodoApp";
    private static final String TABLE_NAME = "TodoList";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_STARTTIME = "startTime";
    private static final String KEY_ENDTIME = "endTime";
    private static final String KEY_DESCRIPTION = "description";

    private DatabaseHandler handler;
    private final Context context;

    public LocalDatabase(Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        handler = new DatabaseHandler(context);
    }

    @Override
    public void shutdown() {
        handler.close();
    }

    @Override
    public Set<UUID> getTodoIds() {
        Set<UUID> result = new HashSet<>();
        SQLiteDatabase db = handler.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT %s from %s", KEY_ID, TABLE_NAME), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            result.add(UUID.fromString(id));
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }

    @Override
    public Set<TodoTask> getTodos() {
        Set<TodoTask> result = new HashSet<>();
        SQLiteDatabase db = handler.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * from %s", TABLE_NAME), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            String title = cursor.getString(1);
            String desc = cursor.getString(2);
            Date start = new Date(cursor.getLong(3));
            Date end = new Date(cursor.getLong(4));
            result.add(new TodoTask(UUID.fromString(id), title, start, end, desc));
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }

    @Override
    public void save(TodoTask task) {
        SQLiteDatabase db = handler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, task.getId().toString());
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_STARTTIME, task.getStartTime().getTime());
        values.put(KEY_ENDTIME, task.getEndTime().getTime());
        db.update(TABLE_NAME, values, null, null);
    }

    private static class DatabaseHandler extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;

        public DatabaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String create_students_table = String.format("CREATE TABLE %s(%s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s UNSIGNED BIG INT, %s UNSIGNED BIG INT)", TABLE_NAME, KEY_ID, KEY_TITLE, KEY_DESCRIPTION, KEY_STARTTIME, KEY_ENDTIME);
            db.execSQL(create_students_table);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String drop_todo_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
            db.execSQL(drop_todo_table);
            onCreate(db);
        }

    }

}
