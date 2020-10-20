package com.jry.rudyjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.emailedt);
        password = (EditText) findViewById(R.id.passedt);
    }

    public void login(View view) {
        mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"เข้าสู่ระบบสำเร็จ",Toast.LENGTH_LONG).show();
                            String uid = FirebaseAuth.getInstance().getUid();
                            Intent login = new Intent(LoginActivity.this,HomeActivity.class);
                            login.putExtra("UserUid",uid);
                            startActivity(login);
                        }else {
                            Toast.makeText(LoginActivity.this,"เข้าสู่ระบบไม่สำเร็จ",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void Register(View view) {
        Intent Register = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(Register);
    }
}