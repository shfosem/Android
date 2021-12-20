package com.example.sampletoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 =(EditText) findViewById(R.id.editText2);
    }

    public void onButton1Clicked(View v){
        try{
            Toast toastView =   Toast.makeText(this,"위치가 바뀐 토스트 메시지입니다.", Toast.LENGTH_LONG);

           /* int xOffset = Integer.parseInt(editText.getText().toString());
            int yOffset = Integer.parseInt(editText2.getText().toString());
*/
            toastView.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0);
            toastView.show();
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    public void onButton2Clicked(View v){
        Snackbar.make(v, "스낵바입니다.", Snackbar.LENGTH_LONG).show();
    }
}