package com.example.fitnesapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.TextUtils;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    private EditText confirm_passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.passwd);
        confirm_passwd = findViewById(R.id.confirm_passwd);
        Btn = findViewById(R.id.btnregister);
        progressbar = findViewById(R.id.progressbar);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {
        progressbar.setVisibility(View.VISIBLE);

        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String confirmPassword = confirm_passwd.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(getApplicationContext(),
                    "Please fill in all fields",
                    Toast.LENGTH_LONG).show();
            progressbar.setVisibility(View.GONE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(),
                    "Passwords do not match",
                    Toast.LENGTH_LONG).show();
            progressbar.setVisibility(View.GONE);
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.GONE);
                            Intent intent = new Intent(RegisterActivity.this, SplashActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Registration failed!! Please try again later",
                                    Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}