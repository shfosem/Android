package com.example.sampleactionbar01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ActionBar abar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        abar=this.getSupportActionBar();
        abar.setSubtitle("옵션바 살펴보기");
        textView = (TextView) findViewById(R.id.textView);
    }

    public void onButton1Clicked(View v){
        abar.setLogo(R.drawable.home);
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME| ActionBar.DISPLAY_USE_LOGO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_search:
                Toast.makeText(this,"검색 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_settings:
                Toast.makeText(this,"설정 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}