package com.example.famousquotequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.famousquotequizapp.DbHelper.LoginDbHelper;

public class SignUpActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private EditText password;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        buttonSave = findViewById(R.id.btnSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave_onClick(view);
            }
        });
    }

    public void buttonSave_onClick(View view) {
        try {
            LoginDbHelper userDB = new LoginDbHelper(getApplicationContext());
            User user = new User();
            user.setFirstName(firstName.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            if(userDB.create(user)) {
                Intent intent = new Intent(SignUpActivity.this, StartQuizActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(SignUpActivity.this, "Cannot create new account!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(SignUpActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}
