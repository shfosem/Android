package com.example.sampleprogress;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog ;
    private SeekBar seekBar;
    TextView seekBarText;
    private int brightness =50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar =(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        progressBar.setProgress(80);


        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             showProgressDialog(view);

            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialog!=null)
                    dialog.dismiss();
            }
        });


        seekBarText=(TextView) findViewById(R.id.seekBarText);
        Button button3 =(Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout seekBarPanel =  (LinearLayout) findViewById(R.id.seekBarPanel);
                seekBarPanel.setVisibility(View.VISIBLE);
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setBrightness(i);
                seekBarText.setText("시크바의 값 : " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void showProgressDialog(View view) {
        dialog = new ProgressDialog(getApplicationContext());
        dialog.setCancelable(false);
        dialog.setMessage("데이터를 확인하는 중입니다.");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

    }
    class CheckTypesTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog asyncDialog = new ProgressDialog(getApplicationContext());
        @Override
        protected void onPreExecute(){
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("데이터를 확인하는 중입니다");

            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                for(int i=0; i<5; i++)
                    Thread.sleep(500);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            asyncDialog.dismiss();
            super.onPostExecute(result);
        }
    }
    private void setBrightness(int i) {
        if(i<10)
            i=10;
        else if (i>100){
            i=100;
        }
        brightness = i;

        WindowManager.LayoutParams params =getWindow().getAttributes();
        params.screenBrightness=(float) i/100;
        getWindow().setAttributes(params);
    }


}


