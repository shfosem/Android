package org.androidtown.samplecalendar;

import android.content.Context;
import android.graphics.Color;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.time.Month;
import java.util.Calendar;

public class MonthAdapter extends BaseAdapter {

    public static final String TAG = "MonthAdapter";
    Context mContext;

    public static int oddColor = Color.rgb(225,225,225);
    public static int headColor = Color.rgb(12,32,158);

    private int selectedPosition = -1;

    private MonthItem[] items;
    private int countColumn = 7;

    int mStartDay;
    int startDay;
    int curYear;
    int curMonth;

    int firstDay;
    int lastDay;

    Calendar mCalender;
    boolean recreateItems =false;

    public MonthAdapter(Context context){
        super();
        mContext = context;
        init();
    }

    public MonthAdapter(Context context, AttributeSet attrs){
        super();
        mContext=context;
        init();
    }
    private void init(){
        items = new MonthItem[7*6];
        mCalender = Calendar.getInstance();
        recalculate();
        resetDayNumbers();
    }


    private void recalculate() {
        mCalender.set(Calendar.DAY_OF_MONTH,1);

        int dayOfWeek = mCalender.get(Calendar.DAY_OF_WEEK);
        firstDay = getFirstDay(dayOfWeek);
        Log.d(TAG, "firstDay : "+ firstDay);

        mStartDay=mCalender.getFirstDayOfWeek();
        curYear=mCalender.get(Calendar.YEAR);
        curMonth=mCalender.get(Calendar.MONTH);
        lastDay = getMonthLastDay(curYear, curMonth);

        int diff = mStartDay - Calendar.SUNDAY-1;
        startDay = getFirstDayOfWeek();
    }


    public void setPreviousMonth(){
        mCalender.add(Calendar.MONTH,-1);
        recalculate();

        resetDayNumbers();
        selectedPosition=-1;
    }

    public void setNextMonth(){
        mCalender.add(Calendar.MONTH,1);
        recalculate();

        resetDayNumbers();
        selectedPosition =-1;
    }

    private void resetDayNumbers() {
        for(int i=0;i<42;i++){
            int dayNumber = (i+1)-firstDay;
            if(dayNumber<1 || dayNumber > lastDay){
                dayNumber=0;
            }
            items[i] = new MonthItem(dayNumber);
        }
    }


    private int getFirstDay(int dayOfWeek) {
        int result=0;
        if(dayOfWeek==Calendar.SATURDAY){
            result=0;
        }else if(dayOfWeek==Calendar.MONDAY){
            result=1;
        }else if(dayOfWeek==Calendar.TUESDAY){
            result=2;
        }else if(dayOfWeek==Calendar.WEDNESDAY){
            result=3;
        }else if(dayOfWeek==Calendar.THURSDAY){
            result=4;
        }else if(dayOfWeek==Calendar.FRIDAY){
            result=5;
        }else if(dayOfWeek==Calendar.SATURDAY){
            result=6;
        }
        return result;
    }
    public int getCurYear(){
        return curYear;
    }
    public int getCurMonth(){
        return curMonth;
    }
    public int getNumColumns(){
        return 7;
    }


    @Override
    public int getCount() {
        return 7*6;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        MonthItemView itemView;
        if(convertView==null){
            itemView=new MonthItemView(mContext);
        }else{
            itemView=(MonthItemView) convertView;
        }

      /*  AbsListView.LayoutParams params = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,70
        );*/
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,70
        );

        int rowIndex = i/countColumn;
        int columnIndex = i%countColumn;

        itemView.setItem(items[i]);
        itemView.setLayoutParams(params);
        itemView.setPadding(2,2,2,2);

        itemView.setGravity(Gravity.LEFT);

        if(columnIndex==0){
            itemView.setTextColor(Color.RED);
        }else {
            itemView.setTextColor(Color.BLACK);
        }

        if(i==getSelectedPosition()){
            itemView.setBackgroundColor(Color.YELLOW);
        }else {
            itemView.setBackgroundColor(Color.WHITE);
        }
        return itemView;
    }
    //생략
    public static int getFirstDayOfWeek(){
        int startDay = Calendar.getInstance().getFirstDayOfWeek();
        if(startDay==Calendar.SATURDAY){
            return Time.SATURDAY;
        }else if(startDay == Calendar.MONDAY){
            return Time.MONDAY;
        }else{
            return Time.SUNDAY;
        }
    }
    private int getMonthLastDay(int year, int month){
        switch (month){
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return (31);
            case 3:
            case 5:
            case 8:
            case 10:
                return(30);
            default:
                if(((year%4==0)&&(year%100!=0))||(year%400==0)){
                    return (29);
                }else {
                    return (28);
                }
        }
    }

    public void setSelectedPosition(int i) {
        this.selectedPosition = i;
    }

    public int getSelectedPosition(){
        return selectedPosition;
    }

}
