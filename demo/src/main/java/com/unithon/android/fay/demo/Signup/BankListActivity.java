package com.unithon.android.fay.demo.Signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.cameraview.demo.R;
import com.unithon.android.fay.demo.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class BankListActivity extends AppCompatActivity {
    GridView gvBankListBank,gvBankListFund;
    List<BankListItem> items,itemsTwo;
    ImageView ivBankListExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);
        bindingXml();
        setupData();
    }

    private void bindingXml(){
        ivBankListExit = (ImageView) findViewById(R.id.iv_bank_list_exit);
        gvBankListBank = (GridView) findViewById(R.id.gv_bank_list_bank);
        gvBankListFund = (GridView) findViewById(R.id.gv_bank_list_fund);

    }

    private void setupData(){
        items = new ArrayList<>();
        items.add(new BankListItem("NH농협"));
        items.add(new BankListItem("KB국민"));
        items.add(new BankListItem("신한"));
        items.add(new BankListItem("IBK기업"));
        items.add(new BankListItem("새마을"));
        items.add(new BankListItem("부산"));
        items.add(new BankListItem("경남"));
        items.add(new BankListItem("광주"));
        items.add(new BankListItem("전북"));
        items.add(new BankListItem("신협"));
        items.add(new BankListItem("SC"));
        items.add(new BankListItem("KDB산업"));
        items.add(new BankListItem("대구"));
        items.add(new BankListItem("제주"));
        items.add(new BankListItem("하나"));
        items.add(new BankListItem("외한"));
        items.add(new BankListItem("우체국"));
        items.add(new BankListItem("수협"));
        BankListAdapter bankListAdapter = new BankListAdapter(getApplicationContext(),items,R.layout.bank_list_item);
        bankListAdapter.notifyDataSetChanged();
        gvBankListBank.setAdapter(bankListAdapter);
        gvBankListBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle extra = new Bundle();
                Intent intent = new Intent();
                extra.putString("bank", ((TextView)view.findViewById(R.id.tv_bank_list_item)).getText().toString());
                intent.putExtras(extra);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        itemsTwo = new ArrayList<>();
        itemsTwo.add(new BankListItem("NH투자"));
        itemsTwo.add(new BankListItem("대신"));
        BankListAdapter bankListAdapterTwo = new BankListAdapter(getApplicationContext(),itemsTwo,R.layout.bank_list_item);
        bankListAdapterTwo.notifyDataSetChanged();
        gvBankListFund.setAdapter(bankListAdapterTwo);
        gvBankListFund.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle extra = new Bundle();
                Intent intent = new Intent();
                extra.putString("bank", ((TextView)view.findViewById(R.id.tv_bank_list_item)).getText().toString());
                intent.putExtras(extra);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        ivBankListExit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Bundle extra = new Bundle();
                Intent intent = new Intent();
                extra.putString("bank", "선택");
                intent.putExtras(extra);
                setResult(RESULT_OK, intent);
                finish();
                return false;
            }
        });


    }

}
