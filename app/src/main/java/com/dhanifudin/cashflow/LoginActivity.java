package com.dhanifudin.cashflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dhanifudin.cashflow.models.Session;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.textUsername);
        passwordInput = findViewById(R.id.textPassword);
        session = Application.getSession();

        if (session.isKeepUsername()) {
            usernameInput.setText(session.getUsername());
        }

    }

    public void HandleReset(View view) {
        usernameInput.setText("");
        passwordInput.setText("");
    }

    public void HadleLogin(View view) {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        boolean success = session.validate(username,password);
        if(success){
            if (session.isKeepUsername()) {
                session.setUsername(username);
            }
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Snackbar.make(view,"Authentication Failed",Snackbar.LENGTH_SHORT).show();
        }
    }
}
