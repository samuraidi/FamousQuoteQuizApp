package com.example.famousquotequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.famousquotequizapp.DbHelper.LoginDbHelper;

public class LoginActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button SignIn;

    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.etName);
        Password = findViewById(R.id.etPassword);
        Info = findViewById(R.id.tvInfo);
        Login = findViewById(R.id.btnLogin);
        SignIn = findViewById(R.id.btnSignIn);

        Info.setText("No of attemps remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginDbHelper userDB = new LoginDbHelper(getApplicationContext());
                String username = Name.getText().toString();
                String password = Password.getText().toString();
                User user = userDB.login(username, password);
                if(user == null){
                    counter--;

                    Info.setText("No of Attemps remaining: " + String.valueOf(counter));
                    if(counter == 0) {
                        Login.setEnabled(false);
                    }
                } else {
                    Intent intent = new Intent(LoginActivity.this, StartQuizActivity.class);
 //                   intent.putExtra("user", user);
                    startActivity(intent);
                }

            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}