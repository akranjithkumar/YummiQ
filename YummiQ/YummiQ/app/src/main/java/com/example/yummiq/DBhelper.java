package com.example.yummiq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBhelper extends SQLiteOpenHelper {

    // Database Name & Version
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;


    // SQL Query to Create Table
    private static final String CREATE_TABLE = "CREATE TABLE food(name TEXT,price TEXT,qty TEXT)";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    public Cursor readTableRaw(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS food");
        onCreate(db);
    }

    public void clearTable(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, null, null);
        db.close();
    }

    // Insert Data
    public long insertUser(String name, String price,String qty,Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("qty", qty);

        long result = db.insert("food", null, values);
        if(result != 1){
        }
        return result;
    }

    public void updateItem(String name, String price, String qty,Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("qty", qty);

        // Updating row where id matches
        long d = db.update("food", values, "name = ?", new String[]{String.valueOf(name)});
        if (d != -1) {
        }
        db.close();
    }

    public Cursor updatecount(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM food WHERE name = ?;";
        Cursor cursor =  db.rawQuery(query,new String[]{name});
        return cursor;
    }

    public int getUser(String name,Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM food WHERE name = ?;";
        Cursor cursor =  db.rawQuery(query,new String[]{name});
        return cursor.getCount();
    }

    public void deleteRow(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("food", "name=?", new String[]{String.valueOf(name)});
        db.close();
    }
}

