package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity{
    Button btn_lregister, btn_llogin;
    EditText et_lusername, et_lpassword, attempt;
    com.example.lab3.DatabaseHelper databaseHelper;
    int attempt_counter= 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper= new  com.example.lab3.DatabaseHelper(this);

        et_lusername= (EditText)findViewById(R.id.et_lusername);
        et_lpassword= (EditText)findViewById(R.id.et_lpassword);

        btn_llogin= (Button)findViewById(R.id.btn_llogin);
        btn_lregister= (Button)findViewById(R.id.btn_lregister);

        attempt = (EditText) findViewById(R.id.textView_attempt_count);

        btn_lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent( com.example.lab3.Login.this, com.example.lab3.MainActivity.class);
                startActivity(intent);
            }
        });
        attempt.setText(Integer.toString(attempt_counter));
        btn_llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_lusername.getText().toString();
                String password = et_lpassword.getText().toString();

                Boolean checklogin= databaseHelper.CheckLogin(username, password);
                if(checklogin== true){
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent( com.example.lab3.Login.this,  com.example.lab3.Homescreen.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    attempt_counter--;
                    attempt.setText(Integer.toString(attempt_counter));
                    if (attempt_counter== 0)
                        btn_llogin.setEnabled(false);
                }
            }
        });
    }

}
