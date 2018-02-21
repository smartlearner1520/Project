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


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import com.example.yanglei.myapplication.MyApp;
import static com.example.yanglei.myapplication.SlidingTabStrip.mDefaultTabColorizer;

public class First extends Fragment {
    private EditText username,password,IC,Phone;
    protected static String name="";
    protected static String pw="";
    private String NRIC,PN;
    private Button button;
    ViewPager mViewPager;




    public First(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_first, container, false);

        username = view.findViewById(R.id.username);
        password =view.findViewById(R.id.password);
        IC=view.findViewById(R.id.NRIC);
        Phone=view.findViewById(R.id.Phone);
        button =view.findViewById(R.id.Next);
        MyApp myapp = MyApp.get();
        final RequestQueue queue = myapp.getRequestQueue();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = username.getText().toString();
                pw = password.getText().toString();
                NRIC = IC.getText().toString();
                PN = Phone.getText().toString();
                if(CheckEmpty(name,pw)){
                    Toast.makeText(view.getContext().getApplicationContext(), "You need to fill in the blank",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Log.i("Output","Have completed the personal detail!");
                    String url = "http://132lilinwei.pythonanywhere.com/realapp/registration/";
                    MyRequest postRequest = new MyRequest(Request.Method.POST, url,
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Log.d("Error.Response", error.toString());
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("username", name);
                            params.put("password", pw);
                            params.put("nric", NRIC);
                            params.put("phone_number", PN);
                            params.put("email", "yl@gg.com");
                            return params;
                        }
                    };
                    queue.add(postRequest);
                    mDefaultTabColorizer.setIndicatorColors(0xCC00FF00,0xCC33B5E5);
                    Register.jumpTopage(view);
                }
            }
        });




        return view;
    }

    public static Boolean CheckEmpty(String name, String pw){
        if(name.equals("") || pw.equals("")){
            return true;
        }else{
            return false;
        }
    }
}
