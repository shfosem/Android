package com.example.doitmission07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button bdayButton;
    Button buttonSave;
    DatePickerDialog.OnDateSetListener birthday;
    Date selectedDate;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bdayButton = (Button) findViewById(R.id.bdayButton);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        EditText name = (EditText)findViewById(R.id.editName);
        EditText age = (EditText)findViewById(R.id.editAge);


        bdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = name.getText().toString();
                String ageStr = age.getText().toString();
                String birthStr = bdayButton.getText().toString();

                Toast.makeText(getApplicationContext(),"이름 : " + nameStr + ", 나이 : " + ageStr + ", 생년월일 : "+birthStr,Toast.LENGTH_SHORT).show();
            }
        });

        Date curDate = new Date();
        setSelectedDate(curDate);

    }

    private void setSelectedDate(Date curDate) {
        selectedDate=curDate;

        String selectedDateStr = dateFormat.format(curDate);
        bdayButton.setText(selectedDateStr);
    }

    private void showDateDialog() {
        String birthDateStr = bdayButton.getText().toString();

        Calendar calendar = Calendar.getInstance();
        Date curBirthDate = new Date();
        try{
            curBirthDate = dateFormat.parse(birthDateStr);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        calendar.setTime(curBirthDate);

        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, birthDateListener, curYear, curMonth, curDay);
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener birthDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Calendar selectedCalendar = Calendar.getInstance();
            selectedCalendar.set(Calendar.YEAR,i);
            selectedCalendar.set(Calendar.MONTH,i1);
            selectedCalendar.set(Calendar.DAY_OF_MONTH,i2);

            Date curDate = selectedCalendar.getTime();
            setSelectedDate(curDate);
        }
    };


}