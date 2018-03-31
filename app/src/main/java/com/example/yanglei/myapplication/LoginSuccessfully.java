package com.example.yanglei.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

public class LoginSuccessfully extends AppCompatActivity {
    MyApp myapp = MyApp.get();
    final RequestQueue queue = myapp.getRequestQueue();
    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_successfully);
        logout = (TextView) findViewById(R.id.lg);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });
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
        Intent intent = new Intent(LoginSuccessfully.this, MainActivity.class);
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
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginSuccessfully.this, MainActivity.class);
                        startActivity(intent);
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
