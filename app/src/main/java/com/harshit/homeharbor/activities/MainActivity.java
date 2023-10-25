package com.harshit.homeharbor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.harshit.homeharbor.R;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {


    ProgressBar progressBar;
    FirebaseAuth auth;
    SharedPreferences sp;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = new Timer();
        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        if(auth.getCurrentUser()!=null){
            if(sp.getBoolean("logged",false)){
                Toast.makeText(getApplicationContext(),"Please wait you are already logged in",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
            progressBar.setVisibility(View.VISIBLE);
            sp.edit().putBoolean("logged",true).apply();
        }
    }
    public void login(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void registration(View view) {
        startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
    }
}