package com.example.videome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText EmailBox,PasswordBox;
    Button LOGINBTN;
    Button CREATEBTN;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth= FirebaseAuth.getInstance();

        EmailBox = findViewById(R.id.EmailBox);
        PasswordBox = findViewById(R.id.PasswordBox);
        CREATEBTN= findViewById(R.id.CREATEBTN);
        LOGINBTN = findViewById(R.id.LOGINBTN);

        LOGINBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,pass;
                email=EmailBox.getText().toString();
                pass=PasswordBox.getText().toString();
             auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         startActivity(new Intent(LoginActivity.this,DashBoardActivity.class));
                         Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();

                     }else{
                         Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                     }
                 }
             });
            }
        });

        CREATEBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
    }
}