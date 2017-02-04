package com.unithon.android.fay.demo.Fintech;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.cameraview.demo.R;

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by seonilkim on 2017. 2. 5..
 */

public class TransferConfirmActivity extends AppCompatActivity {

    TextView tv_transferconfirm_comfirm, tv_transferconfirm_later, tv_transferconfirm_name, tv_transferconfirm_bank, tv_transferconfirm_account, tv_transferconfirm_moneykor;
    EditText et_transferconfirm_money;
    CircleImageView civ_transferconfirm_profile;
    ImageView iv_transferconfirm_back;
    FrameLayout fl_transferconfirm;

    ProgressDialog progressDialog;

    private DBHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferconfirm);

        dbHelper = new DBHelper(getApplicationContext(), "recent.db", null, 1);

        if(Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(Color.parseColor("#ffffff"));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        fl_transferconfirm = (FrameLayout) findViewById(R.id.fl_transferconfirm);
        fl_transferconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_transferconfirm_money.isFocused()) et_transferconfirm_money.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et_transferconfirm_money.getWindowToken(), 0);
            }
        });

        tv_transferconfirm_name = (TextView) findViewById(R.id.tv_transferconfirm_name);
        tv_transferconfirm_bank = (TextView) findViewById(R.id.tv_transferconfirm_bank);
        tv_transferconfirm_account = (TextView) findViewById(R.id.tv_transferconfirm_account);
        tv_transferconfirm_moneykor = (TextView) findViewById(R.id.tv_transferconfirm_moneykor);
        et_transferconfirm_money = (EditText) findViewById(R.id.et_transferconfirm_money);
        et_transferconfirm_money.addTextChangedListener(new CustomTextWathcer(et_transferconfirm_money));
//        et_transferconfirm_money.addTextChangedListener(new TextWatcher() {
//            String strAmount = "";
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.toString().equals(strAmount)) { // StackOverflow를 막기위해,
//                    strAmount = convertHangul(et_transferconfirm_money.getText().toString());
//                    et_transferconfirm_money.setText(strAmount);
//                    Editable e = et_transferconfirm_money.getText();
//                    Selection.setSelection(e, strAmount.length());
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        et_transferconfirm_money.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(et_transferconfirm_money.isFocused()) et_transferconfirm_money.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_transferconfirm_money.getWindowToken(), 0);
                    return true;
                }
                return false;

            }
        });

        civ_transferconfirm_profile = (CircleImageView) findViewById(R.id.civ_transferconfirm_profile);

        tv_transferconfirm_comfirm = (TextView) findViewById(R.id.tv_transferconfirm_confirm);
        tv_transferconfirm_later = (TextView) findViewById(R.id.tv_transferconfirm_later);

        Intent intent = getIntent();
        tv_transferconfirm_name.setText(intent.getStringExtra("name"));
        tv_transferconfirm_bank.setText(intent.getStringExtra("bank"));
        tv_transferconfirm_account.setText(intent.getStringExtra("account"));

        iv_transferconfirm_back = (ImageView) findViewById(R.id.iv_transferconfirm_back);
        iv_transferconfirm_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_transferconfirm_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_transferconfirm_money.getText().length()==0 || Integer.parseInt(et_transferconfirm_money.getText().toString().replaceAll(",", ""))<=0){
                    Toast.makeText(TransferConfirmActivity.this, "금액을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    final Handler mHandler = new Handler();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog = new ProgressDialog(TransferConfirmActivity.this);
                            progressDialog.setMessage("돈을 이체하는 중 입니다.");
                            progressDialog.setCancelable(false);
                            progressDialog.setCanceledOnTouchOutside(false);

                            progressDialog.show();
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        if (progressDialog != null && progressDialog.isShowing()) {
                                            progressDialog.dismiss();
                                            dbHelper.insert(tv_transferconfirm_name.getText().toString(), tv_transferconfirm_bank.getText().toString(), tv_transferconfirm_account.getText().toString(), System.currentTimeMillis());
                                            final Dialog dialog = new Dialog(TransferConfirmActivity.this);
                                            dialog.setContentView(R.layout.dialog_transfer_success);
                                            dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                                            dialog.show();


                                            Runnable mRunnable = new Runnable() {
                                                @Override
                                                public void run() {
                                                    dialog.dismiss();
                                                    Intent intent = new Intent();
                                                    intent.putExtra("result", 1);
                                                    setResult(Activity.RESULT_OK, intent);
                                                    finish();
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
                    });
                }

            }
        });

        tv_transferconfirm_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(TransferConfirmActivity.this);
                dialog.setContentView(R.layout.dialog_transfer_finalconfirm);
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                TextView tv_finalconfirm_yes = (TextView) dialog.findViewById(R.id.tv_finalconfirm_yes);
                TextView tv_finalconfirm_no = (TextView) dialog.findViewById(R.id.tv_finalconfirm_no);
                tv_finalconfirm_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        setResult(Activity.RESULT_CANCELED, intent);
                        dialog.dismiss();
                        finish();
                    }
                });
                tv_finalconfirm_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
//
//                Handler mHandler  = new Handler();
//                Runnable mRunnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        dialog.dismiss();
//                    }
//                };
//                mHandler.postDelayed(mRunnable, 2000);
            }
        });
    }

    public class CustomTextWathcer implements TextWatcher {
        @SuppressWarnings("unused")
        private EditText mEditText;
        String strAmount = ""; // 임시저장값 (콤마)

        public CustomTextWathcer(EditText e) {
            mEditText = e;
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                if (!s.toString().equals(strAmount)) { // StackOverflow를 막기위해,
                    strAmount = makeStringComma(s.toString().replace(",", ""));
                    mEditText.setText(strAmount);
                    Editable e = mEditText.getText();
                    Selection.setSelection(e, strAmount.length());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        protected String makeStringComma(String str) {
            if (str.length() == 0)
                return "";
            long value = Long.parseLong(str);
            DecimalFormat format = new DecimalFormat("###,###");
            return format.format(value);
        }
    }

    public String convertHangul(String money){
        if(money.contains(",")){
            money = money.replaceAll(",", "");
        }
        String[] han1 = {"","일","이","삼","사","오","육","칠","팔","구"};
        String[] han2 = {"","십","백","천"};
        String[] han3 = {"","만","억","조","경"};

        StringBuffer result = new StringBuffer();
        int len = money.length();
        for(int i=len-1; i>=0; i--){
            result.append(han1[Integer.parseInt(money.substring(len-i-1, len-i))]);
            if(Integer.parseInt(money.substring(len-i-1, len-i)) > 0)
                result.append(han2[i%4]);
            if(i%4 == 0)
                result.append(han3[i/4]);
        }

        return result.toString()+"원";
    }
}
