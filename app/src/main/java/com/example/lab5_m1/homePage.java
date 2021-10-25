package com.example.lab5_m1;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class homePage extends AppCompatActivity {
    TextView textView2;

    public static ArrayList<Note> notes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //display welcome message
        textView2 = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();
        //String str = intent.getStringExtra("value");
        //testing sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_m1", Context.MODE_PRIVATE);
        String str = sharedPreferences.getString("username", "");
        textView2.setText("Welcome " + str + "!");
        //Get SQLDatabase Instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        //Initiate the "Notes" class variable using readNotes methods implemented in DBHelper class
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        notes = dbHelper.readNotes(str);
        //create an ArrayList<String> object by iterating over notes object
        ArrayList<String> displayNotes = new ArrayList<>();
        for(Note note: notes){
            displayNotes.add(String.format("Tile:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        //Use listView to display notes on screen
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        //add onItemClickListener for ListView item, a note in our case
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (getApplicationContext(),notes.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
       switch (item.getItemId()){
           case R.id.createNote:
               //switch screens
               System.out.println("create New Note Selected");
               Intent myIntent = new Intent(this, notes.class);
               startActivity(myIntent);
               return true;
           case R.id.logOut:
               //switch screens
               Intent logout = new Intent(this, MainActivity.class);
               SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_m1", Context.MODE_PRIVATE);
               sharedPreferences.edit().remove("username").apply();
               startActivity(logout);
               return true;
           default:
               return super.onContextItemSelected(item);
       }
    }



}