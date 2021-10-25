package com.example.lab5_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class notes extends AppCompatActivity {
    int noteid = -1;
    public static String content;
    public void saveMethod(View view){
        //1. Get editText view and the contents of that
        EditText getNote= (EditText) findViewById(R.id.userNote);
        content = getNote.getText().toString();
        //2. Initialize SQLiteDatabase instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        //3. Initialize DBHelper class
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        //4. Set username in the following variable by fetching it from shared Preferences

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_m1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        //5. Save information to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (homePage.notes.size() + 1);
            dbHelper.saveNotes(username,title, content, date);
        }else{
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        //1. get editText view.
        EditText getNote= (EditText) findViewById(R.id.userNote);
        content = getNote.getText().toString();
        //2. Get Intent
        Intent intent = getIntent();
        //3. Get value of integer "noteid" from intent
        //TODO:
        //4. Initialize class varaible "noteid" with the value from intent
        if(noteid != -1){
            //Display content of note by retrieving "notes" ArrayList in Second Activity
            Note note = homePage.notes.get(noteid);
            String noteContent = note.getContent();
            //use editText.setText() to display the contents of this note on the screen

        }
    }
}