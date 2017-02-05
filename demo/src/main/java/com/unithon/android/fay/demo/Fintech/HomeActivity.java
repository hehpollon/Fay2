package com.unithon.android.fay.demo.Fintech;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.cameraview.demo.R;
import com.unithon.android.fay.demo.CameraActivity3;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by seonilkim on 2017. 2. 4..
 */

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rl_home_recently;
    private RecentlyAdapter recentlyAdapter;
    private LinearLayoutManager llm;

    private ImageView iv_home_setting, iv_home_camera;
    private CircleImageView civ_home_profile;
    private DBHelper dbHelper;

    private TextView tv_home_name, tv_home_bank, tv_home_account, tv_home_recentlyclear;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String SHARED_MAIN_NAME = "shared_unithon";

    int i=0;

    ArrayList<String> names, names2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(Color.parseColor("#ffffff"));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        sharedPreferences =  getSharedPreferences(SHARED_MAIN_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putString("userName","김선일");// 이름
        editor.putString("userRrnNum","9108121");// 주민등록번호
        editor.putString("userPhoneNum","01099241553"); //핸드펀 번호
        editor.putString("userNetwork","KT");// 통신사
        editor.putString("userBank","우리은행");// 은행사
        editor.putString("userBankNum","1002-632-231475");// 계좌번호
        editor.commit();

        dbHelper = new DBHelper(getApplicationContext(), "recent.db", null, 1);

        iv_home_camera = (ImageView) findViewById(R.id.iv_home_camera);
        iv_home_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //사진 촬영 페이지로 이동.
                Intent intent = new Intent(HomeActivity.this, CameraActivity3.class);
//                startActivity(intent);
                startActivityForResult(intent, 2);
            }
        });
        tv_home_account = (TextView) findViewById(R.id.tv_home_account);
        tv_home_bank = (TextView) findViewById(R.id.tv_home_bank);
        tv_home_name = (TextView) findViewById(R.id.tv_home_name);
        tv_home_account.setText(sharedPreferences.getString("userBankNum", ""));
        tv_home_bank.setText(sharedPreferences.getString("userBank", ""));
        tv_home_name.setText(sharedPreferences.getString("userName", ""));

        recentlyAdapter = new RecentlyAdapter(dbHelper.getRecent());
        rl_home_recently = (RecyclerView) findViewById(R.id.rl_home_recently);
        llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rl_home_recently.setLayoutManager(llm);
        rl_home_recently.setAdapter(recentlyAdapter);

        tv_home_recentlyclear = (TextView) findViewById(R.id.tv_home_recentlyclear);
        tv_home_recentlyclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(HomeActivity.this);
                dialog.setContentView(R.layout.dialog_transfer_finalconfirm);
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                TextView tv_finalconfirm_yes = (TextView) dialog.findViewById(R.id.tv_finalconfirm_yes);
                TextView tv_finalconfirm_no = (TextView) dialog.findViewById(R.id.tv_finalconfirm_no);
                TextView tv_finalconfirm_title = (TextView) dialog.findViewById(R.id.tv_finalconfirm_title);
                tv_finalconfirm_title.setText("최근 기록을 모두 지우시겠습니까?");
                tv_finalconfirm_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbHelper.delete();
                        recentlyAdapter = new RecentlyAdapter(dbHelper.getRecent());
                        rl_home_recently = (RecyclerView) findViewById(R.id.rl_home_recently);
                        llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        rl_home_recently.setLayoutManager(llm);
                        rl_home_recently.setAdapter(recentlyAdapter);
                        dialog.dismiss();
                    }
                });
                tv_finalconfirm_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        civ_home_profile = (CircleImageView) findViewById(R.id.civ_home_profile);

        civ_home_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (i){
                    case 0:
                        dbHelper.insert("김선일", "우리은행", "1234-123-123456", System.currentTimeMillis());
                        break;
                    case 1:
                        dbHelper.insert("손흥민", "우리은행", "1234-123-123456", System.currentTimeMillis());
                        break;
                    case 2:
                        dbHelper.insert("박지성", "우리은행", "1234-123-123456", System.currentTimeMillis());
                        break;
                    case 3:
                        dbHelper.insert("차두리", "우리은행", "1234-123-123456", System.currentTimeMillis());
                        break;
                    case 4:
                        dbHelper.insert("호날두", "우리은행", "1234-123-123456", System.currentTimeMillis());
                        break;
                    case 5:
                        dbHelper.insert("김선일", "우리은행", "1234-123-123456", System.currentTimeMillis());
                        break;
                    case 6:
                        dbHelper.insert("손흥민", "우리은행", "1234-123-123456", System.currentTimeMillis());
                        break;
                    case 7:
                        dbHelper.insert("박지성", "우리은행", "1234-123-123456", System.currentTimeMillis());
                        break;
                    case 8:
                        dbHelper.insert("차두리", "우리은행", "1234-123-123456", System.currentTimeMillis());
                        break;
                    case 9:
                        dbHelper.insert("호날두", "우리은행", "1234-123-123456", System.currentTimeMillis());
                        break;

                    case 10:
                        recentlyAdapter = new RecentlyAdapter(dbHelper.getRecent());
                        rl_home_recently = (RecyclerView) findViewById(R.id.rl_home_recently);
                        llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        rl_home_recently.setLayoutManager(llm);
                        rl_home_recently.setAdapter(recentlyAdapter);
                        break;

                }
                i++;
            }
        });

//        iv_home_setting = (ImageView) findViewById(R.id.iv_home_setting);
//        iv_home_setting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog = new Dialog(HomeActivity.this);
//                dialog.setContentView(R.layout.dialog_confirm_others);
//                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
//                ImageView iv_confirmothersdialog_exit = (ImageView) dialog.findViewById(R.id.iv_confirmothersdialog_exit);
//                iv_confirmothersdialog_exit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                names2 = new ArrayList<String>();
//                names2.add("김선일");
//                names2.add("손흥민");
//                names2.add("박지성");
//                RecyclerView rv_confirmothersdialog = (RecyclerView) dialog.findViewById(R.id.rv_confirmothersdialog);
//                OthersAdapter othersAdapter = new OthersAdapter(names2);
//                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//                rv_confirmothersdialog.setLayoutManager(llm);
//                rv_confirmothersdialog.setAdapter(othersAdapter);
//
//
////                dialog.setContentView(R.layout.dialog_confirm);
////                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
////                ImageView iv_confirmdialog_exit= (ImageView) dialog.findViewById(R.id.iv_confirmdialog_exit);
////                iv_confirmdialog_exit.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        dialog.dismiss();
////                    }
////                });
//
//                dialog.show();
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode== Activity.RESULT_OK){
                recentlyAdapter = new RecentlyAdapter(dbHelper.getRecent());
                rl_home_recently = (RecyclerView) findViewById(R.id.rl_home_recently);
                llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                rl_home_recently.setLayoutManager(llm);
                rl_home_recently.setAdapter(recentlyAdapter);
            }
        } else if(requestCode==2){
            if(resultCode==Activity.RESULT_OK){
                final Dialog dialog = new Dialog(HomeActivity.this);
                dialog.setContentView(R.layout.dialog_confirm);
                final TextView tv_confirmdialog_accept = (TextView) dialog.findViewById(R.id.tv_confirmdialog_accept);
                final TextView tv_confirmdialog_other = (TextView) dialog.findViewById(R.id.tv_confirmdialog_other);
                final TextView tv_confirmdialog_name = (TextView) dialog.findViewById(R.id.tv_confirmdialog_name);
                final TextView tv_confirmdialog_bank = (TextView) dialog.findViewById(R.id.tv_confirmdialog_bank);
                final TextView tv_confirmdialog_account = (TextView) dialog.findViewById(R.id.tv_confirmdialog_account);
                final ImageView iv_confirmdialog_exit = (ImageView) dialog.findViewById(R.id.iv_confirmdialog_exit);
                iv_confirmdialog_exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                tv_confirmdialog_accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomeActivity.this, TransferConfirmActivity.class);
                        intent.putExtra("name", tv_confirmdialog_name.getText().toString());
                        intent.putExtra("bank", tv_confirmdialog_bank.getText().toString());
                        intent.putExtra("account", tv_confirmdialog_account.getText().toString());
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                tv_confirmdialog_other.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        final Dialog dialog2 = new Dialog(HomeActivity.this);
                        dialog2.setContentView(R.layout.dialog_confirm_others);

                        names2 = new ArrayList<String>();
                        names2.add("김선일");
                        names2.add("손흥민");
                        names2.add("박지성");
                        RecyclerView rv_confirmothersdialog = (RecyclerView) dialog2.findViewById(R.id.rv_confirmothersdialog);
                        OthersAdapter othersAdapter = new OthersAdapter(names2);
                        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        rv_confirmothersdialog.setLayoutManager(llm);
                        rv_confirmothersdialog.setAdapter(othersAdapter);
                        TextView tv_confirmothersdialog_recapture = (TextView) dialog2.findViewById(R.id.tv_confirmothersdialog_recapture);
                        tv_confirmothersdialog_recapture.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog2.dismiss();
                                Intent intent = new Intent(HomeActivity.this, CameraActivity3.class);
                                startActivityForResult(intent, 2);
                            }
                        });

                        dialog2.show();
                    }
                });
                dialog.show();
            }
        }
    }

    public class RecentlyAdapter extends RecyclerView.Adapter<RecentlyAdapter.MyViewHolder>{
//        private List<String> recentlyList;
        private List<RecentXML> recentlyList;

        public class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView tv_radapter_name;
            public ImageView civ_radapter_profile;
            public RelativeLayout rl_radapter;

            public MyViewHolder(View view){
                super(view);
                tv_radapter_name = (TextView) view.findViewById(R.id.tv_radapter_name);
                civ_radapter_profile = (ImageView) view.findViewById(R.id.civ_radapter_profile);
                rl_radapter = (RelativeLayout) view.findViewById(R.id.rl_radapter);
            }
        }

        public RecentlyAdapter(List<RecentXML> recentlyList){
            this.recentlyList = recentlyList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recently, parent, false);
            return new MyViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.tv_radapter_name.setText(recentlyList.get(position).name);
            holder.rl_radapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(HomeActivity.this, Long.toString(recentlyList.get(position).time), Toast.LENGTH_SHORT).show();
                    //TransferConfirmActivity로 이동.

                    Intent intent = new Intent(getApplicationContext(), TransferConfirmActivity.class);
                    intent.putExtra("name", recentlyList.get(position).name);
                    intent.putExtra("bank", recentlyList.get(position).bank);
                    intent.putExtra("account", recentlyList.get(position).account);
                    startActivityForResult(intent, 1);

                }
            });

        }

        @Override
        public int getItemCount() {
            return recentlyList.size();
        }
    }

    public class OthersAdapter extends RecyclerView.Adapter<OthersAdapter.MyViewHolder>{
        private List<String> othersList;

        public class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView tv_confirmothersdialogadapter_name;
            public View view_confirmothersdialogadapter_bottomline;
            public RelativeLayout rl_confirmothersdialogadapter;

            public MyViewHolder(View view){
                super(view);
                rl_confirmothersdialogadapter = (RelativeLayout) view.findViewById(R.id.rl_confirmothersdialogadapter);
                tv_confirmothersdialogadapter_name = (TextView) view.findViewById(R.id.tv_confirmothersdialogadapter_name);
                view_confirmothersdialogadapter_bottomline = (View) view.findViewById(R.id.view_confirmothersdialogadapter_bottomline);
            }
        }

        public OthersAdapter(List<String> othersList){
            this.othersList = othersList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dialog_confirm_others, parent, false);
            return new MyViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.tv_confirmothersdialogadapter_name.setText(othersList.get(position));
            if(position==getItemCount()-1){
                holder.view_confirmothersdialogadapter_bottomline.setVisibility(View.GONE);
            }
//            holder.rl_confirmothersdialogadapter.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

        }

        @Override
        public int getItemCount() {
            return othersList.size();
        }
    }
}
