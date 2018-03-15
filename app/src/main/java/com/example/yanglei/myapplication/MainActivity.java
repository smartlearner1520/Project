package com.example.yanglei.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private EditText ID,Password;
    private TextView signup,Something;
    private String id,password;
    private boolean result =false;
    private Button login;
    MyApp myapp = MyApp.get();
    final RequestQueue queue = myapp.getRequestQueue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = MyApp.Domain + "logout/";
        MyRequest postRequest = new MyRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
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


//        MediaPlayer music= MediaPlayer.create(MainActivity.this,R.raw.Something_Just_Like_This);
//        music.start();
        final RequestQueue queue = Volley.newRequestQueue(this);
        ID = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        login =(Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = MyApp.Domain+ "login/basic/";
                id=ID.getText().toString();
                password=Password.getText().toString();
                if(NotEmpty(id,password)) {
                    MyRequest postRequest = new MyRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    //SUCCESS, FAIL
                                    result = Check(response);
                                    Log.i("Response","   -->" + response);
                                    if (result) {
                                        result = false; // to protect the MainFirstPage activity
                                        password="";
                                        Intent intent = new Intent(MainActivity.this, MainSecondPage.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Invalid ID or Password",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Log.d("Error.Response", error.toString());
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", id);
                            params.put("password", password);

                            return params;
                        }
                    };
                    queue.add(postRequest);
                }
            }
        });


        signup =(TextView) findViewById(R.id.signuptext);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, RegisterFirstPage.class);
                startActivity(intent1);
            }
        });

//        Something = (TextView) findViewById(R.id.Something);
//        Something.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url = "http://132lilinwei.pythonanywhere.com/realapp/something/";
//                MyRequest postRequest = new MyRequest(Request.Method.POST, url,
//                        new Response.Listener<String>()
//                        {
//                            @Override
//                            public void onResponse(String response) {
//                                // response
//                                Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
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
//                );
//                queue.add(postRequest);
//
//            }
//        });


    }


    public static Boolean Check(String s){
        // need check the password from server; if it is the same
        if(s.equals("SUCCESS")){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean NotEmpty(String id, String password){
        if(id.equals("") && !password.equals("")){
            Toast.makeText(getApplicationContext(), "Please Enter Your ID",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(password.equals("") && !id.equals("")){
            Toast.makeText(getApplicationContext(), "Please Enter Your Password",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (id.equals("") && password.equals("")){
            Toast.makeText(getApplicationContext(), "Please Enter Your ID and Password",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }


}
