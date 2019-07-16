package com.example.book.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.book.R;
import com.example.book.activity.fragment.FragmentTab1;
import com.example.book.activity.fragment.FragmentTab2;

public class HomeActivity extends AppCompatActivity {

    private RadioGroup mRgTabs;
    private ImageView mIvAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    public void initView(){

        FragmentTransaction mfragmentTransaction=getSupportFragmentManager().beginTransaction();
        mfragmentTransaction.replace(R.id.fragment_container,new FragmentTab1());
        mfragmentTransaction.commit();

        mRgTabs=findViewById(R.id.rg_tabs);
        mRgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                FragmentTransaction mfragmentTransaction=getSupportFragmentManager().beginTransaction();

                switch (checkedId){
                    case R.id.rb_text:
                        mfragmentTransaction.replace(R.id.fragment_container,new FragmentTab1());
                        mfragmentTransaction.commit();
                        break;
                    case R.id.rb_setting:
                        mfragmentTransaction.replace(R.id.fragment_container,new FragmentTab2());
                        mfragmentTransaction.commit();
                        break;
                        default:break;
                }


            }
        });


        mIvAdd=findViewById(R.id.iv_add);
        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

    }


}
