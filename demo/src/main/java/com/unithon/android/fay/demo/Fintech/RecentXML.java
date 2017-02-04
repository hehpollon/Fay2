package com.unithon.android.fay.demo.Fintech;

/**
 * Created by seonilkim on 2017. 2. 5..
 */

public class RecentXML {
    String name, bank, account;
    long time;

    public void setRecent(String name, String bank, String account, long time){
        this.name = name;
        this.bank = bank;
        this.account = account;
        this.time = time;
    }

}
