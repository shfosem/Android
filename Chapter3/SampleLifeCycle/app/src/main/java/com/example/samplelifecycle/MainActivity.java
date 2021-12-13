package com.example.samplelifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 101;

    EditText nameInput; //= (EditText) findViewById(R.id.nameInput);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"onCreate 호출됨", Toast.LENGTH_LONG).show();
        nameInput =(EditText) findViewById(R.id.nameInput);
    }

    public void buttonOnClicked(View v){
        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
        startActivityForResult(intent,REQUEST_CODE_MENU);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "onStart 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause 호출됨",Toast.LENGTH_LONG).show();
        saveState();
    }

    protected void saveState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", nameInput.getText().toString());
        editor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "onResume 호출됨",Toast.LENGTH_LONG).show();
        restoreState();
    }

    protected void restoreState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if((pref!=null)&& (pref.contains("name"))){
            String name = pref.getString("name", "");
            nameInput.setText(name);
        }

    }

    protected void clearMyPrefs(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

}