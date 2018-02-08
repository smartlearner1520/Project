package com.example.yanglei.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText ID,Password;
    private TextView signup;
    private String id,password,sign;
    private boolean result =false;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        login =(Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=ID.getText().toString();
                password=Password.getText().toString();
                //TODO : check the ID and password
                // searching for id in server--get the id
                // check the password under this id -- output result= true if both are correct
                if (id.equals("ID") && password.equals("123")){
                    result=true;
                }
                if(!id.equals("") && !password.equals("") && !result) {
                    Toast.makeText(getApplicationContext(), "Invalid ID or Password",
                            Toast.LENGTH_SHORT).show();
                }
                if(id.equals("") && !password.equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Your ID",
                            Toast.LENGTH_SHORT).show();
                }
                if(password.equals("") && !id.equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Your Password",
                            Toast.LENGTH_SHORT).show();
                }
                if (id.equals("") && password.equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Your ID and Password",
                            Toast.LENGTH_SHORT).show();
                }
                if(result) {
                    result=false; // to protect the login activity
                    Intent intent = new Intent(MainActivity.this, login.class);
                    startActivity(intent);
                }

            }
        });
        signup =(TextView) findViewById(R.id.signuptext);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, Register.class);
                startActivity(intent1);
            }
        });



    }


}