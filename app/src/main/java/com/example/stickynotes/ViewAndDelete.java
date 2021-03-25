package com.example.stickynotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ViewAndDelete extends AppCompatActivity {
    String id;
    String titlePass, bodyPass;
    Context context;
    DBManager dbManager;
    EditText tv;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_delete);
        Bundle b = getIntent().getExtras();
        id=b.getString("id");
        titlePass=b.getString("title");
        bodyPass=b.getString("body");
        setTitle(titlePass);

        tv = (EditText) findViewById(R.id.bodyPass);
        tv.setText(bodyPass);
        ActionBar aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#00a680"));
        aBar.setBackgroundDrawable(cd);
        context=this;
        dbManager = new DBManager(context);

        i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }


    public void deleteNote(View view) {
        String[] selectionArgs = new String[]{id};
        dbManager.Delete("ID=?", selectionArgs);
        startActivity(i);
    }

    public void saveChanges(View view) {
        ContentValues values=new ContentValues();
        values.put(DBManager.colBody,id);
        values.put(DBManager.colBody,titlePass);
        values.put(DBManager.colBody,tv.getText().toString());
        String[] selectionArgs = {id.toString()};
        dbManager.Update(values,"ID=?",selectionArgs);
        startActivity(i);
    }
}