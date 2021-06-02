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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText EmailBox, PasswordBox, PersonName;
    Button LOGIN;
    Button CREATEBTN;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        EmailBox = findViewById(R.id.EmailBox);
        PasswordBox = findViewById(R.id.PasswordBox);
        LOGIN = findViewById(R.id.LOGIN);
        CREATEBTN = findViewById(R.id.CREATEBTN);
        PersonName = findViewById(R.id.PersonName);


        CREATEBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,pass,email;
                email=EmailBox.getText().toString();
                pass=PasswordBox.getText().toString();
                name=PersonName.getText().toString();

                User user =new User();
                user.setEmail(email);
                user.setPass(pass);
                user.setName(name);

                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            database.collection("Users")
                                    .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                                }
                            });
                            Toast.makeText(SignUpActivity.this, "Account is Created", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }
}


