package com.example.stickynotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Cursor cursor;
    ArrayList<String> listTitle, listBody, listID;
    ArrayAdapter adapter;
    CustomAdapter customAdapter;
    DBManager dbManager;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sticky Notes");
        ActionBar aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#00a680"));
        aBar.setBackgroundDrawable(cd);
        dbManager = new DBManager(this);
        cursor = dbManager.query(null,null,null,DBManager.colID);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        listTitle = new ArrayList<>();
        listBody = new ArrayList<>();
        listID = new ArrayList<>();
        refresh();
    }

    public void refresh(){
        listTitle.clear();
        listBody.clear();
        if(cursor.moveToFirst()){
            do{
                listTitle.add(cursor.getString(cursor.getColumnIndex(DBManager.colTitle)));
                listBody.add(cursor.getString(cursor.getColumnIndex(DBManager.colBody)));
                listID.add(cursor.getString(cursor.getColumnIndex(DBManager.colID)));
            }while(cursor.moveToNext());
        }

        customAdapter = new CustomAdapter(MainActivity.this, listTitle, listBody, listID);
        recyclerView.setAdapter(customAdapter);

    }

    public void addNewNote(View view) {
        Intent i = new Intent(this, InputNote.class);
        startActivity(i);
        refresh();
    }

}