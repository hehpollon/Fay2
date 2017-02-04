package com.unithon.android.fay.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.unithon.android.fay.demo.Signup.*;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.cameraview.demo.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView ivLoginLogo;
    Button btLoginLogin,btLoginSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindingXml();
        setupData();
    }


    private void bindingXml(){
        ivLoginLogo = (ImageView)findViewById(R.id.iv_login_logo);
        btLoginSignup = (Button) findViewById(R.id.bt_login_signup);
        btLoginLogin = (Button)findViewById(R.id.bt_login_login);
        if(Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(Color.parseColor("#ffffff"));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }

    private void setupData(){
        Glide.with(LoginActivity.this).load(R.drawable.group_5)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop().into(ivLoginLogo);
        btLoginSignup.setOnClickListener(this);
        btLoginLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login_login :
                Toast.makeText(getApplicationContext(),"login",Toast.LENGTH_SHORT).show();
                break;


            case R.id.bt_login_signup :
                Toast.makeText(getApplicationContext(),"Signup",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                break;

        }
    }
}
