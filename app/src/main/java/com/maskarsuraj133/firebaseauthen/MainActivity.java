package com.maskarsuraj133.firebaseauthen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
EditText mFullName,mEmail,mPassword,mPhone;
Button mRegisterBtn;
FirebaseAuth firebaseAuth;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFullName=findViewById(R.id.fullName);
        mEmail=findViewById(R.id.Email);
        mPassword=findViewById(R.id.password);
        mRegisterBtn=findViewById(R.id.registerBtn);
        mPhone=findViewById(R.id.phone);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mFullName.getText().toString();
                String mobile = mPhone.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }
                if (TextUtils.isEmpty(userName)){
                    mFullName.setError("Username is Required");
                    return;
                }
                if (TextUtils.isEmpty(mobile)){
                    mFullName.setError("Mobile Number is Required");
                    return;
                }
                if (password.length()<5){
                    mPassword.setError("Length Should be more the 6");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()){
                       Toast.makeText(MainActivity.this, "User Register Successfully", Toast.LENGTH_SHORT).show();
                       progressBar.setVisibility(View.INVISIBLE);
                   }else {
                       Toast.makeText(MainActivity.this, "Error!" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   }
                    }
                });
            }
        });
    }
}