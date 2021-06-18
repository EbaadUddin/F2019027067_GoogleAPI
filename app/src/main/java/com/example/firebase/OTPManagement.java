package com.example.firebase;

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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPManagement extends AppCompatActivity
{
EditText et1;
Button lgn;
String number;
String otpid;
FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        number=getIntent().getStringExtra("CONTACT").toString();
        et1=findViewById(R.id.otp);
        lgn=findViewById(R.id.login);
        mAuth=FirebaseAuth.getInstance();
        et1.setText(number);

        initiateOtp();

        lgn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PhoneAuthCredential credential=PhoneAuthProvider.getCredential(otpid,et1.getText().toString());
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void initiateOtp()
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                otpid=s;
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
            {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e)
            {
                Toast.makeText(getApplicationContext(),"Sigin error",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                        startActivity(new Intent(OTPManagement.this, Home.class));
                        finish();
                        }
                        else

                            {
                                Toast.makeText(getApplicationContext(),"Sigin error",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



}