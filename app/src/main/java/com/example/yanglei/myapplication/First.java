package com.example.yanglei.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class First extends Fragment {
    private EditText username,password,NRIC,Address,phone,passport;
    private String name,pw,nric,addr,ph,pp;
    private Button button;
    ViewPager mViewPager;


    public First(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_first, container, false);

        username = view.findViewById(R.id.username);
        password =view.findViewById(R.id.password);
        NRIC = view.findViewById(R.id.NRIC);
        Address =view.findViewById(R.id.address);
        phone = view.findViewById(R.id.phone);
        passport = view.findViewById(R.id.Passport);
        button =view.findViewById(R.id.Next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = username.getText().toString();
                pw = password.getText().toString();
                nric = NRIC.getText().toString();
                addr = Address.getText().toString();
                ph = phone.getText().toString();
                pp = passport.getText().toString();

                if(name.equals("") || pw.equals("") || nric.equals("")|| addr.equals("")||ph.equals("")){
                    Toast.makeText(view.getContext().getApplicationContext(), "You need to fill in the blank",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Log.i("Output","Have completed the personal detail!");
                    Register.jumpTopage(view);
                }
                if(pp.equals("")){
                    Log.i("Output","Don't have passport!");
                }
            }
        });




        return view;
    }
}
