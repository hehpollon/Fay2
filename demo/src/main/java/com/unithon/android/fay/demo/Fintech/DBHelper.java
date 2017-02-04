package com.unithon.android.fay.demo.Fintech;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by seonilkim on 2017. 2. 5..
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RECENT(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, bank TEXT, account TEXT, time INT);");
        // 이름, 은행, 계좌번호, 시간
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE RECENT");
        onCreate(db);
    }

    public void insert(String name, String bank, String account, long time){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO RECENT VALUES(null, '"+name+"', '"+bank+"', '"+account+"', "+time+");");
        db.close();
    }

    public void drop(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE RECENT");
        db.close();
    }

    public void delete(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM RECENT");
        db.close();
    }

    public int getDBCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM RECENT", null);
        int cnt = cursor.getCount();
        db.close();
        if(cursor==null) return 0;
        else return cnt;
    }

    public ArrayList<RecentXML> getRecent(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM RECENT ORDER BY time DESC limit 10", null);
        ArrayList<RecentXML> recents = new ArrayList<>();
        RecentXML recent = null;
        while(cursor.moveToNext()){
            recent = new RecentXML();
            recent.setRecent(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            recents.add(recent);
        }
        return recents;

    }
}
