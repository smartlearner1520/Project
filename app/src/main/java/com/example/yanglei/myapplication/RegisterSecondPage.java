package com.example.yanglei.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class RegisterSecondPage extends AppCompatActivity {
    private EditText n1,n2,n3,n4;
    private Button resend,verf;
    private TextView logout;
    String num,i1,i2,i3,i4;
    ProgressDialog progressDialog;
    MyApp myapp = MyApp.get();
    final RequestQueue queue = myapp.getRequestQueue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_second_page);

        resend = (Button) findViewById(R.id.resend);
        verf = (Button) findViewById(R.id.verification);


        n1 = (EditText) findViewById(R.id.email1);
        n1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("Length","------> " + n1.getText().toString().length());
                if(n1.getText().toString().length()==1){
                    n2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        n2 = (EditText) findViewById(R.id.email2);
        n2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == keyEvent.KEYCODE_DEL && n2.getText().toString().equals("")) {
                    n1.requestFocus();
                }
                return false;
            }
        });
        n2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(n2.getText().toString().length()==1){
                    n3.requestFocus();
                }

                if(i1==1 && i2==0) {
                    n2.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View view, int i, KeyEvent keyEvent) {
                            if (i == keyEvent.KEYCODE_DEL) {
                                n1.requestFocus();
                            }
                            return false;
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        n3 = (EditText) findViewById(R.id.email3);
        n3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == keyEvent.KEYCODE_DEL && n3.getText().toString().equals("")) {
                    n2.requestFocus();
                }
                return false;
            }
        });
        n3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(n3.getText().toString().length()==1){
                    n4.requestFocus();
                }

                if(i1==1 && i2==0) {
                    n3.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View view, int i, KeyEvent keyEvent) {
                            if (i == keyEvent.KEYCODE_DEL) {
                                n2.requestFocus();
                            }
                            return false;
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        n4 = (EditText) findViewById(R.id.email4);
        n4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == keyEvent.KEYCODE_DEL && n4.getText().toString().equals("")) {
                    n3.requestFocus();
                }
                return false;
            }
        });
        n4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(n3.getText().toString().length()==1){
                    n4.requestFocus();
                }

                if(i1==1 && i2==0) {
                    n4.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View view, int i, KeyEvent keyEvent) {
                            if (i == keyEvent.KEYCODE_DEL) {
                                n3.requestFocus();
                            }
                            return false;
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(RegisterSecondPage.this);
                progressDialog.setMessage("Loading..."); // Setting Message
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(true);
                String url = MyApp.Domain + "registration/email/";
                MyRequest postRequest = new MyRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                progressDialog.dismiss();
                                Log.i("R 2nd page resend", response);
                                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
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
        });

        verf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i1 = n1.getText().toString();
                i2 = n2.getText().toString();
                i3 = n3.getText().toString();
                i4 = n4.getText().toString();
                num = i1+ i2+ i3+ i4;

                progressDialog = new ProgressDialog(RegisterSecondPage.this);
                progressDialog.setMessage("Loading..."); // Setting Message
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(true);

                Log.i("verf"," the number is " + num);
                String url = MyApp.Domain + "registration/emailveri/";
                MyRequest postRequest = new MyRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                progressDialog.dismiss();
                                Log.i("R 2nd page verf", response);
                                if(response.equals("SUCCESS")){
                                    Intent intent = new Intent(RegisterSecondPage.this,RegisterThirdPage.class);
                                    startActivity(intent);
                                } else if(response.equals("TIMEOUT")){
                                    Logout();
                                    Intent intent1 = new Intent(RegisterSecondPage.this,MainActivity.class);
                                    startActivity(intent1);
                                }
                                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

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
                        params.put("randomcode", num);

                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });

//        logout = (TextView) findViewById(R.id.Logout3);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url = MyApp.Domain + "logout/";
//                MyRequest postRequest = new MyRequest(Request.Method.POST, url,
//                        new Response.Listener<String>()
//                        {
//                            @Override
//                            public void onResponse(String response) {
//                                // response
//                                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
//                                Intent intent = new Intent(register_second_page.this, MainActivity.class);
//                                startActivity(intent);
//                            }
//                        },
//                        new Response.ErrorListener()
//                        {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // error
//                                Log.d("Error.Response", error.toString());
//                            }
//                        }
//                ) {
//                    @Override
//                    protected Map<String, String> getParams()
//                    {
//
//
//                        return null;
//                    }
//                };
//                queue.add(postRequest);
//            }
//        });
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
        Intent intent = new Intent(RegisterSecondPage.this, MainActivity.class);
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
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
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
