package com.example.fbauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText Username,Password;
    Button Login;
    TextView Signup,Forgot;
    FirebaseAuth fauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username = findViewById(R.id.UserLogin);
        Password = findViewById(R.id.password_login);
        Login = findViewById(R.id.login);
        Signup = findViewById(R.id.signup);
        Forgot = findViewById(R.id.reset);
        fauth = FirebaseAuth.getInstance();


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = Username.getText().toString().trim();
                String password1 =Password.getText().toString().trim();

                if(TextUtils.isEmpty(username1)) {
                    Toast.makeText(MainActivity.this, "username empty", Toast.LENGTH_SHORT).show();
                }if(TextUtils.isEmpty(password1)){
                    Toast.makeText(MainActivity.this, "password empty", Toast.LENGTH_SHORT).show();
                }
                fauth.signInWithEmailAndPassword(username1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Login Complete", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ResetActivity.class));
            }
        });


    }
}