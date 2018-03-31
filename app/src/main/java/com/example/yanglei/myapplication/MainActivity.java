package com.example.yanglei.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    ProgressDialog progressDialog;
    private int Presstime =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logout();


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

                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Loading..."); // Setting Message
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                    progressDialog.show(); // Display Progress Dialog
                    progressDialog.setCancelable(true);

                    MyRequest postRequest = new MyRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    //SUCCESS, FAIL
                                    progressDialog.dismiss();
                                    Log.i("Response","   dismiss" );
                                    result = Check(response);
                                    Log.i("Response","   -->" + response);
                                    if (result) {
                                        result = false; // to protect the login_third_page activity
                                        password="";
                                        Intent intent = new Intent(MainActivity.this, LoginFirstPage.class);
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
        new AlertDialog.Builder(this,R.style.MyDialogTheme)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Logout();
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void Logout(){
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
    }

}
