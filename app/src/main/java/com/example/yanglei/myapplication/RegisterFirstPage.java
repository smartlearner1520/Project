package com.example.yanglei.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

public class RegisterFirstPage extends AppCompatActivity {
    private EditText username,password,IC,Phone,CC;
    private TextView untext,pwtext,ictext,pntext,logout;
    protected static String name="";
    protected static String pw="";
    protected static String NRIC="";
    protected static String PN = "";
    String index0 = "";
    String index1 = "";
    int length;
    protected static Boolean canswip=false;
    private String cc;
    private Button button;
    private Boolean isvalid=true;
    MyApp myapp = MyApp.get();
    final RequestQueue queue = myapp.getRequestQueue();
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_first_page);


        username = (EditText) findViewById(R.id.username);
        password =(EditText) findViewById(R.id.password);
        untext = (TextView) findViewById(R.id.untext);
        pwtext = (TextView) findViewById(R.id.pwtext);
        ictext = (TextView) findViewById(R.id.ictext);
        pntext = (TextView) findViewById(R.id.pntext);

        IC= (EditText) findViewById(R.id.NRIC);
        CC= (EditText) findViewById(R.id.CC);
        CC.setText("+65");
        Phone= (EditText) findViewById(R.id.Phone);
        button = (Button) findViewById(R.id.Next);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                isvalid=true;
                cc = CC.getText().toString();
                name = username.getText().toString();
                if(!name.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
                    untext.setText("Invalid username");
                    Log.i("username","    -->invalid username");
                    isvalid=false;
                } else{
                    untext.setText("");
                }
                pw = password.getText().toString();
                if(!pw.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")){
                    pwtext.setText("At least one lower and upper case letter\nAt least eight characters");
                    int c = Color.parseColor("#c417ccd6");
                    pwtext.setTextColor(c);
                    Log.i("username","    -->invalid password");
                    isvalid=false;
                } else{
                    pwtext.setText("");
                }
                NRIC = IC.getText().toString();
                length = NRIC.length();
                Boolean state=true;
                if(length!=0){
                    index0 = "" + NRIC.charAt(0);
                    index1 = "" + NRIC.charAt(length-1);
                    int count=0;
                    for(String s : NRIC.split("")){
                        if(s.matches("[a-zA-Z]")){
                            count++;
                            Log.i("Count","    --->" + count);
                        }
                    }

                    if(!index0.matches("[a-zA-Z]") || !index1.matches("[a-zA-Z]") || count>2){
                        state=false;
                    }
                }

                if(NRIC.matches("(^(?=\\S+$))([@#$%^&+=])$") || length!=9 || !state){
                    ictext.setText("Invalid NRIC");
                    Log.i("username","    -->invalid NRIC");
                    isvalid=false;
                } else{
                    ictext.setText("");
                }

                PN = Phone.getText().toString();
                if(!PN.matches("^[0-9]+$") || PN.length()!=8){
                    pntext.setText("Invalid Phone number");
                    Log.i("username","    -->invalid phone number");
                    isvalid=false;
                } else {
                    pntext.setText("");
                }
                PN = cc+PN;
                Log.i("valid","  " + isvalid);
                Log.i("state","    " + canswip);
                if(CheckEmpty(name,pw)){
                    Toast.makeText(getApplicationContext(), "Please fill in the blanks.",
                            Toast.LENGTH_SHORT).show();
                } else if(!isvalid){
                    Toast.makeText(getApplicationContext(), "Invalid input",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Log.i("Output","Have completed the personal detail!");

                    progressDialog = new ProgressDialog(RegisterFirstPage.this);
                    progressDialog.setMessage("Loading..."); // Setting Message
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                    progressDialog.show(); // Display Progress Dialog
                    progressDialog.setCancelable(true);

                    String url = MyApp.Domain+ "registration/basic/";
                    MyRequest postRequest = new MyRequest(Request.Method.POST, url,
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    progressDialog.dismiss();
                                    if(!response.equals("USERNAME EXISTS") && isvalid){
                                        untext.setText("");
                                        pwtext.setText("");
                                        ictext.setText("");
                                        pntext.setText("");
                                        Intent intent = new Intent(RegisterFirstPage.this,RegisterSecondPage.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Username exists.", Toast.LENGTH_LONG).show();
                                    }

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
                            params.put("email", name);
                            return params;
                        }
                    };
                    queue.add(postRequest);


                }
            }
        });

    }

    public static Boolean CheckEmpty(String name, String pw){
        if(name.equals("") || pw.equals("")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("yl", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("yl", "onBackPressed Called");
        Logout();
        Intent intent = new Intent(RegisterFirstPage.this, MainActivity.class);
        startActivity(intent);
    }

    public void Logout(){
        String url = MyApp.Domain + "logout/";
        MyRequest postRequest = new MyRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(getApplicationContext(),"You have successfully logged out!",Toast.LENGTH_LONG).show();
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


                return null;
            }
        };
        queue.add(postRequest);
    }
}
