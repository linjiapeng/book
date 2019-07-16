package com.example.book.activity.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.book.R;

public class LoginActivity extends AppCompatActivity {

    private EditText mEtAccont;
    private EditText mEtPassWord;
    private CheckBox mCbRemenber;
    private Button mBtLogin;

    private SharedPreferences mSharedPreFerences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView(){

        mEtAccont=findViewById(R.id.et_account);
        mEtPassWord=findViewById(R.id.et_password);
        mCbRemenber=findViewById(R.id.cb_remember);
        mBtLogin=findViewById(R.id.bt_login);

        mSharedPreFerences= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember=mSharedPreFerences.getBoolean("remember_password",false);

        if(isRemember){
            String account=mSharedPreFerences.getString("account","");
            String password=mSharedPreFerences.getString("password","");
            mEtAccont.setText(account);
            mEtPassWord.setText(password);
            mCbRemenber.setChecked(true);
        }

        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=mEtAccont.getText().toString();
                String password=mEtPassWord.getText().toString();

                boolean bl=account.equals("admin") && password.equals("123456");

                Log.d("TAG","账号："+account);
                Log.d("TAG","密码："+password);
                Log.d("TAG", String.valueOf(bl));



                if(account.equals("admin") && password.equals("123456")){

                    mEditor=mSharedPreFerences.edit();
                    if(mCbRemenber.isChecked()){
                        mEditor.putBoolean("remember_password",true);
                        mEditor.putString("account",account);
                        mEditor.putString("password",password);
                    }else {
                        mEditor.clear();
                    }
                    mEditor.apply();
                    OpenActivity();
                }else {
                    Toast.makeText(LoginActivity.this,"账号或密码错误...",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void OpenActivity(){
        Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
