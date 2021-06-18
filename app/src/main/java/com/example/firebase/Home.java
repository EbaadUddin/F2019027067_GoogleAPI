package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity
{
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener()
        {
    @Override
    public void onClick(View v)
    {
        FirebaseAuth.getInstance().signOut();
        finish();
    }
});
    }
}