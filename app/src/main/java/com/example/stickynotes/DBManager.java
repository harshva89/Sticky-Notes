package com.example.stickynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DBManager {
    private SQLiteDatabase sqlDB;
    static final String DBname = "NotesDB";
    static final String tableName = "Notes";
    static final String colTitle = "Title";
    static final String colBody = "Body";
    static final String colID = "ID";
    static final int DBversion=1;
    static final String CreateTable = "Create table if not exists "+tableName+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+colTitle+" text, "+colBody+" text)";

    static class DatabaseHelper extends SQLiteOpenHelper{
        Context context;
        DatabaseHelper(Context context){
            super(context, DBname, null, DBversion);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTable);
            Toast.makeText(context,"DB created",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table IF EXISTS "+tableName);
            onCreate(db);
        }
    }
    public DBManager(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        sqlDB=db.getWritableDatabase();
    }

    public long Insert(ContentValues values){
        long id = sqlDB.insert(tableName,"",values);
        return id;
    }

    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(tableName);
        Cursor cursor = qb.query(sqlDB, projection, selection, selectionArgs, null,null,sortOrder);
        return cursor;
    }

    public void Delete(String selection, String[] SelectionArgs){
        sqlDB.delete(tableName, selection, SelectionArgs);
    }

    public void Update(ContentValues values, String selection, String[] selectionArgs){
        sqlDB.update(tableName, values, selection, selectionArgs);
    }


}
