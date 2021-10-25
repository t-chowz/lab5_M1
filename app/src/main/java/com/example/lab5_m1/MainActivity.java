package com.example.lab5_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String usernameKey;

    public void getInfo(View view){
        //1. Get Username and password via Edit Text
        EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        usernameKey = editTextUserName.getText().toString();
        //goToHome(usernameKey);
        //2. add username to shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_m1", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", usernameKey).apply();
        //3. start second activity
        Intent intent = new Intent(this, homePage.class);
//        startActivity(intent);


    }
    public void goToHome(String str){
        Intent intent = new Intent(this, homePage.class);
        intent.putExtra("value", str);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        String usernameKey = "username";

    //TODO:
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_m1", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString(usernameKey, "").equals("")){
            //"username" key exists in shared preferences object
            //Get the name of that user from SharedPreferences using sharedPreferences.getString(user
            sharedPreferences.getString(usernameKey, "");
            //use intent to start the second activity to welcome the user
        } else{
            //SharedPreferences object has no user key set
            //start screen1, that is the main activity
            setContentView(R.layout.activity_main);
        }

    }
}