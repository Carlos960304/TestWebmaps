package com.example.projecttestwebmaps.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projecttestwebmaps.MainActivity;
import com.example.projecttestwebmaps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btnGoToLogin, btnSignUp;
    EditText etEmail, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnGoToLogin = (Button)findViewById(R.id.btnGoLogIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        etEmail = (EditText)findViewById(R.id.etEmailSignUp);
        etPassword = (EditText)findViewById(R.id.etPasswordSignUp);
        etConfirmPassword = (EditText)findViewById(R.id.etPasswordConfirmSignUp);

        mAuth = FirebaseAuth.getInstance();

        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
                final String confirmPassword = etConfirmPassword.getText().toString().trim();
                if(isValidEmailAndPassword(email, password, confirmPassword)) {
                    if(isValidEmail(email) && isValidPassword(password) && isValidConfirmPassword(password, confirmPassword)) {
                        signUpByEmail(email, password);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor complete los campos y verifique que los datos sean correctos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signUpByEmail(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Creación del usuario exitosa", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Creación del usuario fallida", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean isValidEmailAndPassword(String email, String password, String confirmPassword) {
        return !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && password.equals(((EditText)findViewById(R.id.etPasswordConfirmSignUp)).getText().toString());
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

    private Boolean isValidConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}