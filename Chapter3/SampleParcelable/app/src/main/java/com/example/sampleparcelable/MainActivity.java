package com.example.sampleparcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public  static final String KEY_SIMPLE_DATA = "data";
    public static final int REQUEST_CODE_MENU = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v)
    {
        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);

        SimpleData data= new SimpleData(100, "Hello Android!") ;
        intent.putExtra(KEY_SIMPLE_DATA,data);

        startActivityForResult(intent,REQUEST_CODE_MENU );
    }
}