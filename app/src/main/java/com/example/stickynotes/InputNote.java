package com.example.stickynotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class InputNote extends AppCompatActivity {
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_note);
        setTitle("New Note");
        ActionBar aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#00a680"));
        aBar.setBackgroundDrawable(cd);
        dbManager=new DBManager(this);
    }

    public void save(View view) {

        EditText title = (EditText) findViewById(R.id.titleName);
        EditText body = (EditText) findViewById(R.id.noteBody);
        ContentValues values=new ContentValues();
        values.put(DBManager.colTitle,title.getText().toString());
        values.put(DBManager.colBody,body.getText().toString());
        long id = dbManager.Insert(values);

        if(id>0)
            Toast.makeText(this,"Note saved",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }

    public void cancel(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }
}