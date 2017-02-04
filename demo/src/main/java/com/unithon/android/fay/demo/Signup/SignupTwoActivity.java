package com.unithon.android.fay.demo.Signup;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.cameraview.demo.R;
import com.unithon.android.fay.demo.CameraActivity;

public class SignupTwoActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int CAMERA_TYPE_SINGUP = 1004;
    public static final int BANK_TYPE = 1000;
    TextView tvToolbar;
    Toolbar toolbar;
    Intent intent;

    ProgressDialog progressDialog;
    Button btSignupTwoNext;
    EditText etSignupTwoBankNum;
    TextView tvSignupTwoNetwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_two);
        bindingXml();
        setupData();
    }

    private void bindingXml(){

        toolbar = (Toolbar)findViewById(R.id.introtoolbar);
        tvToolbar = (TextView)toolbar.findViewById(R.id.tv_toolbar);
        tvSignupTwoNetwork = (TextView) findViewById(R.id.tv_signup_two_bank);
        btSignupTwoNext = (Button) findViewById(R.id.bt_signup_two_next);
        etSignupTwoBankNum = (EditText) findViewById(R.id.et_signup_two_bank_num);

    }

    private void setupData(){
        btSignupTwoNext.setOnClickListener(this);
        intent = new Intent();

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvSignupTwoNetwork.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                intent.setClass(SignupTwoActivity.this, BankListActivity.class);
                startActivityForResult(intent,BANK_TYPE);
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == BANK_TYPE) {
                tvSignupTwoNetwork.setText(data.getStringExtra("bank"));
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.bt_signup_two_next :
                Toast.makeText(getApplicationContext(),"SignupTwo",Toast.LENGTH_SHORT).show();
                /*final Handler mHandler = new Handler();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = ProgressDialog.show(SignupTwoActivity.this, "", "돈을 이체하는 중 입니다.", true);
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (progressDialog != null && progressDialog.isShowing()) {
                                        progressDialog.dismiss();

                                        final Dialog dialog = new Dialog(SignupTwoActivity.this);
                                        dialog.setContentView(R.layout.dialog_transfer_success);
                                        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                                        dialog.show();


                                        Runnable mRunnable = new Runnable() {
                                            @Override
                                            public void run() {
                                                dialog.dismiss();
                                            }
                                        };
                                        mHandler.postDelayed(mRunnable, 2000);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ;
                                }
                            }
                        }, 3000);

                    }
                });*/
                intent.putExtra("userBankNum",etSignupTwoBankNum.getText().toString());
                intent.putExtra("userBank",tvSignupTwoNetwork.getText().toString());
                intent.putExtra("userName",getIntent().getStringExtra("userName"));
                intent.putExtra("userRrnNum",getIntent().getStringExtra("userRrnNum"));
                intent.putExtra("userPhone",getIntent().getStringExtra("userPhone"));
                intent.putExtra("userNetwork",getIntent().getStringExtra("userNetwork"));
                intent.setClass(SignupTwoActivity.this, CameraActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),getIntent().getStringExtra("userName") + "/"
                        +getIntent().getStringExtra("userRrnNum") +" /"
                        +getIntent().getStringExtra("userNetwork") +" /"
                        +getIntent().getStringExtra("userPhone") +" /"
                        +tvSignupTwoNetwork.getText().toString() +" /"
                        +etSignupTwoBankNum.getText().toString() +" /"
                        ,Toast.LENGTH_SHORT).show();
                break;

        }
    }

}
