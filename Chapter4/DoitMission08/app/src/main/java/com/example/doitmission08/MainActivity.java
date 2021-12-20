package com.example.doitmission08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

   public static final String TAG = "MainActivity";

   WebView webview;
   EditText urlInput;

   ImageView handleButton;
   RelativeLayout urlLayout;

   Animation translateUpsideAnim;
   Animation translateDownsideAnim;

   boolean isUrlInputOpen = false;

   boolean loadRequested = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = (WebView)findViewById(R.id.webview);

        WebSettings webSettings = webview.getSettings();
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);

        webview.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG, "onPageFinished() called.");

                if (loadRequested) {
                    urlLayout.startAnimation(translateUpsideAnim);
                    loadRequested = false;
                }
            }
        });


        urlInput = (EditText) findViewById(R.id.urlInput);
        Button searchButton = (Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlStr = urlInput.getText().toString().trim();
                if(urlStr.length()<1)
                {
                    Toast.makeText(getApplicationContext(),"사이트 주소를 먼저 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!urlStr.startsWith("http://")){
                    urlStr = "http://" + urlStr;
                    urlInput.setText(urlStr);
                }
                loadRequested=true;
                webview.loadUrl(urlStr);
            }
        });

        handleButton = (ImageView) findViewById(R.id.handleButton);
        handleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButton.startAnimation(translateUpsideAnim);
            }
        });

        urlLayout = (RelativeLayout) findViewById(R.id.urlLayout);

        translateUpsideAnim = AnimationUtils.loadAnimation(this,R.anim.translate_upside);
        translateDownsideAnim = AnimationUtils.loadAnimation(this, R.anim.translate_downside);

        SlidingPageAnimationListener animListener =new SlidingPageAnimationListener();
        translateUpsideAnim.setAnimationListener(animListener);
        translateDownsideAnim.setAnimationListener(animListener);

        isUrlInputOpen =true;
    }

    private void showHandleButton(){
        handleButton.setVisibility(View.VISIBLE);
        urlLayout.setVisibility(View.GONE);
        isUrlInputOpen=false;
    }

    private void showUrlInput(){
        handleButton.setVisibility(View.GONE);
        urlLayout.setVisibility(View.VISIBLE);

        isUrlInputOpen =true;
    }
    private class SlidingPageAnimationListener implements Animation.AnimationListener{
        public void onAnimationEnd(Animation animation)
        {
            if(isUrlInputOpen)
            {
                showHandleButton();
            }else{
                showUrlInput();
            }
        }
        public void onAnimationRepeat(Animation animation){}
        public void onAnimationStart(Animation animation){}
    }
}