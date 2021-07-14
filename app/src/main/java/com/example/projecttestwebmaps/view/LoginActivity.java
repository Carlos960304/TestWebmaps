package com.example.projecttestwebmaps.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecttestwebmaps.MainActivity;
import com.example.projecttestwebmaps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btnLogIn, btnCreateAccount;
    EditText etUserLogIn, etPasswordLogIn;
    TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogIn = (Button)findViewById(R.id.btnLogIn);
        btnCreateAccount = (Button)findViewById(R.id.btnCreateAccount);
        etUserLogIn = (EditText)findViewById(R.id.etUser);
        etPasswordLogIn = (EditText)findViewById(R.id.etPassword);
        tvForgotPassword = (TextView)findViewById(R.id.tvForgotPassword);

        mAuth = FirebaseAuth.getInstance();

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailLogIn = etUserLogIn.getText().toString().trim();
                final String passwordLogIn = etPasswordLogIn.getText().toString().trim();
                if(isValidEmailAndPassword(emailLogIn, passwordLogIn)) {
                    if(isValidEmail(emailLogIn) && isValidPassword(passwordLogIn)){
                        logInByEmail(emailLogIn, passwordLogIn);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor complete los campos y verifique que sean correctos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void logInByEmail(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "¡Ha ocurrido un error! Vuelva a intentar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Log.d("Mensaje", "Este es el mensaje");
    }

    private Boolean isValidEmailAndPassword(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }

    private Boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private Boolean isValidPassword(String password) {
        String passwordPattern = "^[a-zA-Z0-9_]{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        return pattern.matcher(password).matches();
    }
}