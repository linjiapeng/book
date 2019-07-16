package com.example.book.activity.home;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.util.DiyDialog;
import com.example.book.util.NoteDb;

import java.util.Date;

public class ShowActivity extends AppCompatActivity {

    private TextView mTvSuccess2;
    private TextView mTvTime2;
    private ImageView mIvBack2;
    private ImageView mIvDelete;
    private ImageView mIvShare;
    private EditText mEtContext2;
    private NoteDb mNoteDb;
    private SQLiteDatabase mSqlDb;
    private DiyDialog mDiyDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();

    }


    public void initView(){
        mTvSuccess2=findViewById(R.id.tv_success2);
        mTvTime2=findViewById(R.id.tv_time2);
        mIvBack2=findViewById(R.id.iv_back2);
        mIvDelete=findViewById(R.id.iv_delete);
        mEtContext2=findViewById(R.id.et_context2);

        mNoteDb=new NoteDb(this);
        mSqlDb=mNoteDb.getWritableDatabase();

        mEtContext2.setText(getIntent().getStringExtra(NoteDb.CONTENT));
        mTvTime2.setText(getIntent().getStringExtra(NoteDb.TIME));

        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });

        mIvBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        mTvSuccess2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                UpData();

            }
        });
    }

    public void DeleteContext(){
        int id=getIntent().getIntExtra(NoteDb.ID,0);
        mSqlDb.delete(NoteDb.TABLE_NAME,"_id="+id,null);
        finish();
    }

    //getResources().getString(R.string.app_name)

    public void ShowDialog(){
        mDiyDialog=DiyDialog.Builder(this)
                .setTitle("删除记录")
                .setMessage("删除后，将清除该记录的所有信息")
                .setOnConfirmClickListener("确认", new DiyDialog.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                        DeleteContext();
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


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void UpData(){
        int id=getIntent().getIntExtra(NoteDb.ID,0);
        ContentValues values=new ContentValues();
        values.put("content",mEtContext2.getText().toString().trim());
        values.put("time",getTime());
        mSqlDb.update(NoteDb.TABLE_NAME,values,"_id="+id,null);
        Toast.makeText(ShowActivity.this,"文本修改成功!",Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getTime() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date();

        String time= sdf.format(date);

        return time;
    }




}
