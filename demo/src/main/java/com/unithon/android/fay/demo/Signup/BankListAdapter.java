package com.unithon.android.fay.demo.Signup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.cameraview.demo.R;

import java.util.List;

/**
 * Created by SK392 on 2017-02-05.
 */

public class BankListAdapter extends BaseAdapter {
    Context mContext;
    int item_layout;
    List<BankListItem> items;
    BankListItem item;
    public final static class BankListHolder{
        TextView tvBank;
        public BankListHolder(View itemView){
            tvBank = (TextView)itemView.findViewById(R.id.tv_bank_list_item);
        }
    }

    public BankListAdapter(Context mContext, List<BankListItem> items, int item_layout) {
        this.mContext = mContext;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BankListHolder bankListHolder;
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(item_layout,null);
        }
        bankListHolder = new BankListHolder(view);
        item = items.get(i);
        bankListHolder.tvBank.setText(item.getStrBank());
        return view;
    }

}