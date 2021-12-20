package com.example.sampleactionbar02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        View v = menu.findItem(R.id.menu_search).getActionView();
        if (v != null) {
            editText = (EditText) v.findViewById(R.id.editText);

            if (editText != null) {
                editText.setOnEditorActionListener(onSearchListener);
            } else {
                Toast.makeText(getApplicationContext(), "ActionView is null.", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_refresh:
                textView.setText("새로고침 메뉴를 선택했습니다.");
               return true;
            case R.id.menu_search:
                textView.setText("검색 메뉴를 선택했습니다.");
                return true;
            case R.id.menu_settings:
                textView.setText("설정 메뉴를 선택했습니다");
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private TextView.OnEditorActionListener onSearchListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (keyEvent == null || keyEvent.getAction() == KeyEvent.ACTION_UP) {
                search();

                InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(textView.getWindowToken(), 0);
            }
            return (true);
        }
    };

    private void search() {
        String searchString = editText.getEditableText().toString();
        Toast.makeText(this, "검색어 : "+searchString, Toast.LENGTH_SHORT).show();
    }
}