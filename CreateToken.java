package com.example.dell.myapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.widget.TextView;

/**
 * Created by Dell on 2/21/2018.
 */

public class CreateToken extends AppCompatActivity{
    public static final int REQUEST_CODE = 1001;
    public static final int FLAG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create_token);
        TextView textView = (TextView) findViewById(R.id.tv_token);
        SmsManager smsManager = SmsManager.getDefault();
        String appSmsToken = smsManager.createAppSpecificSmsToken(createSmsTokenPendingIntent());
        textView.setText(appSmsToken);
    }
    private PendingIntent createSmsTokenPendingIntent(){
        return PendingIntent.getActivity(this, REQUEST_CODE, new Intent(this, SmsTokenResultVerificationActivity.class), FLAG);
    }
}
