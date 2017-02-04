package com.unithon.android.fay.demo.Signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.cameraview.demo.R;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int CAMERA_TYPE_SINGUP = 1004;
    public static final String SHARED_MAIN_NAME = "shared_unithon";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button btSignupNext;
    TextView tvToolbar;
    Toolbar toolbar;
    EditText etSignupName,etSignupRrn,etSignupRrnTwo,etSignupPhone;
    Spinner snSignupNetwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindingXml();
        setupData();
    }

    private void bindingXml(){
        toolbar = (Toolbar)findViewById(R.id.introtoolbar);
        tvToolbar = (TextView)toolbar.findViewById(R.id.tv_toolbar);
        snSignupNetwork = (Spinner)findViewById(R.id.sn_signup_network);
        btSignupNext = (Button) findViewById(R.id.bt_signup_next);
        etSignupName = (EditText) findViewById(R.id.et_signup_name);
        etSignupPhone = (EditText) findViewById(R.id.et_signup_phone);
        etSignupRrn = (EditText) findViewById(R.id.et_signup_rrn);
        etSignupRrnTwo = (EditText) findViewById(R.id.et_signup_rrn_two);

    }

    private void setupData(){
        btSignupNext.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        this, R.array.NetworkList, R.layout.signup_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snSignupNetwork.setAdapter(adapter);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        sharedPreferences =  getSharedPreferences(SHARED_MAIN_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putString("userName","");// 이름
        editor.putString("userRrnNum","");// 주민등록번호
        editor.putString("userPhoneNum",""); //핸드펀 번호
        editor.putString("userNetwork","");// 통신사
        editor.putString("userBank","");// 은행사
        editor.putString("userBankNum","");// 계좌번호
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.bt_signup_next :
                Toast.makeText(getApplicationContext(),"Signup",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("userName",etSignupName.getText().toString());
                intent.putExtra("userRrnNum",etSignupRrn.getText().toString() + etSignupRrnTwo.getText().toString());
                intent.putExtra("userPhone",etSignupPhone.getText().toString());
                intent.putExtra("userNetwork",snSignupNetwork.getSelectedItem().toString());
                intent.setClass(SignupActivity.this, SignupTwoActivity.class);

                startActivity(intent);
                Toast.makeText(getApplicationContext(),snSignupNetwork.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                break;

        }
    }

}
