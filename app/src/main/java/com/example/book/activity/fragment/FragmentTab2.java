package com.example.book.activity.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.book.R;
import com.example.book.activity.home.LoginActivity;
import com.example.book.util.DiyAdapter;
import com.example.book.util.DiyDialog;


public class FragmentTab2 extends Fragment {

    private View mView;
    private LinearLayout mLanguage;
    private LinearLayout mReact;
    private Button mBtOut;
    private DiyDialog mDiyDialog,mDiyFbDialog;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         mView=inflater.inflate(R.layout.activity_setting,container,false);
         mLanguage=mView.findViewById(R.id.l_language);
         mReact=mView.findViewById(R.id.l_react);
         mBtOut=mView.findViewById(R.id.bt_out);
         ToLanguageSetting();
         ToFaceBack();
         LoginOut();
        return mView;
    }

    public void ToLanguageSetting(){

        mLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
            }
        });
    }

    public void ToFaceBack(){

        mReact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFaceBackDialog();
            }
        });

    }

    public void LoginOut(){
        mBtOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ShowLoginDialog();
            }
        });
    }

    public void ShowLoginDialog(){
        mDiyDialog= DiyDialog.Builder(getContext())
                .setMessage("是否退出登录")
                .setOnConfirmClickListener("确认", new DiyDialog.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                }).setOnCancelClickListener("取消",new DiyDialog.onCancelClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mDiyDialog!=null){
                            mDiyDialog.dismiss();
                        }
                    }
                }).build().Shown();
    }

    public void ShowFaceBackDialog(){

        mDiyFbDialog= DiyDialog.Builder(getContext())
                .setMessage("请选择反馈方式")
                .setOnConfirmClickListener("短信", new DiyDialog.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                       Intent intent=new Intent(Intent.ACTION_SENDTO);
                       intent.setData(Uri.parse("smsto:10086"));
                        mDiyFbDialog.dismiss();
                       startActivity(intent);

                    }
                }).setOnCancelClickListener("电话",new DiyDialog.onCancelClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:10086"));
                        mDiyFbDialog.dismiss();
                        startActivity(intent);

                    }
                }).build().Shown();
        mDiyFbDialog.setCanceledOnTouchOutside(true);
    }


}
