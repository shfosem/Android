package com.example.challenge03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    ScrollView upScrollView;
    ScrollView downScrollView;
    ImageView upImg;
    ImageView downImg;
    BitmapDrawable bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        upScrollView =(ScrollView)findViewById(R.id.scrollView);
        downScrollView = (ScrollView) findViewById(R.id.scrollable2);
        upImg = (ImageView) findViewById(R.id.imageView4);
        downImg =(ImageView) findViewById(R.id.imageView5);


        Resources res = getResources();
        bitmap=(BitmapDrawable) res.getDrawable(R.drawable.a184);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        upImg.setImageDrawable(bitmap);
        upImg.getLayoutParams().width=bitmapWidth;
        upImg.getLayoutParams().height=bitmapHeight;
    }

    public void onButtonDownClicked(View v)
    {
        deleteUpImg();
        changeDownImg();
    }

    public void onButtonUpClicked(View v)
    {
        deleteDownImg();
        changeUpImg();
    }

    private void changeUpImg() {
        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.a184);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        upImg.setImageDrawable(bitmap);
        upImg.getLayoutParams().width=bitmapWidth;
        upImg.getLayoutParams().height=bitmapHeight;
    }

    private void deleteDownImg() {
        Resources res = getResources();
        bitmap=null;
        downImg.setImageDrawable(bitmap);
    }

    private void changeDownImg() {
        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.a184);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        downImg.setImageDrawable(bitmap);
        downImg.getLayoutParams().width=bitmapWidth;
        downImg.getLayoutParams().height=bitmapHeight;
    }

    private void deleteUpImg() {
        Resources res = getResources();
        bitmap=null;
        upImg.setImageDrawable(bitmap);
    }


}