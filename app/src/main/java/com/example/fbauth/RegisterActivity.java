package com.example.fbauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText username,email,password;
    String email1,password1;
    FirebaseAuth Fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.user_register);
        email=findViewById(R.id.email_signup);
        password=findViewById(R.id.password_signup);
        Button signup = (Button) findViewById(R.id.signupbutton);
        Fauth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email1 = email.getText().toString().trim();
                password1 = password.getText().toString().trim();

                Fauth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser Fuser = Fauth.getCurrentUser();
                            Fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(RegisterActivity.this, "Email registered", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "Email not registered", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(i);
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                });






            }
        });




    }
}