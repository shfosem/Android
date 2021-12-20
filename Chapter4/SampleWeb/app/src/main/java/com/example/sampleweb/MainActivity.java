package com.example.sampleweb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Handler handler = new Handler();

    //EditText urlInput ;
    Button loadButton;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebBrowserClient());
        webView.addJavascriptInterface(new JavaScriptMethods(), "sample");

        webView.loadUrl("file:///android_asset/sample.html");
      //  Toast.makeText(this,"file:///android_asset/www/sample.html",Toast.LENGTH_SHORT).show();
        final EditText urlInput = (EditText)findViewById(R.id.urlInput);

        loadButton = (Button)findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                webView.loadUrl(urlInput.getText().toString());
                Toast.makeText(getApplicationContext(),urlInput.getText().toString()+" clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public class JavaScriptMethods{
        JavaScriptMethods(){}
        @android.webkit.JavascriptInterface
        public void clickOnFace(){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    loadButton.setText("클릭 후 열기");
                    webView.loadUrl("javascript:changeFace()");
                }
            });
        }
    }

    final class WebBrowserClient extends WebChromeClient{
        public boolean onJsAlert(WebView view, String url, String message, JsResult result){
            Log.d("MainActivity",message);
            result.confirm();

            return true;
        }
    }
}