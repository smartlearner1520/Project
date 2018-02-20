package com.example.yanglei.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import static com.example.yanglei.myapplication.SlidingTabStrip.mDefaultTabColorizer;

public class First extends Fragment {
    private static EditText username;
    private static EditText password;
    protected static String name;
    protected static String pw;
    private Button button;
    ViewPager mViewPager;




    public First(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_first, container, false);

        username = view.findViewById(R.id.username);
        password =view.findViewById(R.id.password);
        button =view.findViewById(R.id.Next);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CheckEmpty(name,pw)){
                    Toast.makeText(view.getContext().getApplicationContext(), "You need to fill in the blank",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Log.i("Output","Have completed the personal detail!");
                    mDefaultTabColorizer.setIndicatorColors(0xCC00FF00,0xCC33B5E5);
                    Register.jumpTopage(view);
                }
            }
        });




        return view;
    }

    public static Boolean CheckEmpty(String name, String pw){
        name = username.getText().toString();
        pw = password.getText().toString();
        if(name.equals("") || pw.equals("")){
            return true;
        }else{
            return false;
        }
    }
}
