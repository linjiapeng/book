package com.example.book.activity.home;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

import com.example.book.R;
import com.example.book.util.NoteDb;

import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private EditText mEtContext;
    private ImageView mIvback;
    private TextView mTvSuccess;
    private NoteDb mDb;
    private SQLiteDatabase mSqldb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mEtContext=findViewById(R.id.et_context);
        mIvback=findViewById(R.id.iv_back);
        mTvSuccess=findViewById(R.id.tv_success);

        mIvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        mTvSuccess.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Save();
            }
        });

        mDb=new NoteDb(this);
        mSqldb=mDb.getWritableDatabase();


    }

    @TargetApi(Build.VERSION_CODES.N)  /** 版本兼容性问题*/
    public void Save(){
        DbAdd();
        finish();
    }


    @TargetApi(Build.VERSION_CODES.N)
    public void DbAdd(){
        ContentValues contentValues=new ContentValues();
        contentValues.put(NoteDb.CONTENT,mEtContext.getText().toString());
        contentValues.put(NoteDb.TIME,getTime());
        mSqldb.insert(NoteDb.TABLE_NAME,null,contentValues);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getTime() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");

        Date date=new Date();

        String time= sdf.format(date);

        return time;
    }

}
