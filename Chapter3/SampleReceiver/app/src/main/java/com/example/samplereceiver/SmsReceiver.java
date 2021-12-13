package com.example.samplereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG ="SmsReceiver";

// 시간 포맷을 위한 형식
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive() 메소드 호출됨.");

        //인텐트 안에 들어있는 SMS 메시지 파싱
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages!=null && messages.length>0){
            // SMS 발신 번호 확인
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG, "SMS sender : "+ sender);

            //SMS 메시지 확인
            String contents = messages[0].getMessageBody().toString();

            Log.i(TAG, "SMS contents : "+ contents);

            //SMS 수신 기간 확인
            Date receivedDate = new Date(messages[0].getTimestampMillis());

            Log.i(TAG, "SMS received date : " + receivedDate.toString());

            sendToActivity(context,sender, contents,receivedDate);
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for(int i=0; i<smsCount ; i++){
            //PDU 포맷으로 되어 있는 메시지를 복원
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i]= SmsMessage.createFromPdu((byte[])objs[i],format);
            }else{
                messages[i]= SmsMessage.createFromPdu((byte[])objs[i]);
            }
        }
        return messages;
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate){
        Intent myIntent = new Intent(context, SmsActivity.class);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        myIntent.putExtra("sender",sender);
        myIntent.putExtra("contents", contents);
        myIntent.putExtra("receivedDate",format.format(receivedDate));

        context.startActivity(myIntent);
    }
}