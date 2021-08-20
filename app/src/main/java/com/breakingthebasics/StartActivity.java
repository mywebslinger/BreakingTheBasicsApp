package com.breakingthebasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.breakingthebasics.login.LoginActivity;
import com.breakingthebasics.login.RegistrationActivity;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvJoinTribe,tvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findid();
    }

    private void findid(){
        tvJoinTribe=findViewById(R.id.tvJoinTribe);
        tvLogin=findViewById(R.id.tvLogin);
        tvJoinTribe.setOnClickListener(StartActivity.this);
        tvLogin.setOnClickListener(StartActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvJoinTribe:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
            case R.id.tvLogin:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            default:
                break;
        }
    }
}