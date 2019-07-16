package com.example.book.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.book.R;

public class DiyDialog extends Dialog {


    private final String mTitle;
    private final String mMessage;
    private final String mConfirmText;
    private final String mCancelText;
    private final onConfirmClickListener onConfirmClickListener;
    private final onCancelClickListener onCancelClickListener;

    private Button mBtnConfirm;
    private Button mBtnCancel;
    private TextView mTvTitle;
    private TextView mTvMessage;


    public DiyDialog(Context context, String mTitle, String mMessage, String mConfirmText, String mCancelText, DiyDialog.onConfirmClickListener onConfirmClickListener, DiyDialog.onCancelClickListener onCancelClickListener) {
        super(context, R.style.DeleteDialog);
        this.mTitle = mTitle;
        this.mMessage = mMessage;
        this.mConfirmText = mConfirmText;
        this.mCancelText = mCancelText;
        this.onConfirmClickListener = onConfirmClickListener;
        this.onCancelClickListener = onCancelClickListener;
    }

    public interface onConfirmClickListener{
        void onClick(View view);
    }

    public interface onCancelClickListener{
        void onClick(View view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        setCanceledOnTouchOutside(false);    //dialog弹出后会点击屏幕，dialog不消失；点击物理返回键dialog消失
        intView();

    }

    //创建弹框
    public static Builder Builder(Context context){
        return new DiyDialog.Builder(context);
    }

    public DiyDialog Shown(){
        show();
        return this;
    }


    private void intView(){
        mTvTitle=findViewById(R.id.tv_title);
        mTvMessage=findViewById(R.id.tv_message);
        mBtnCancel=findViewById(R.id.btn_cancel);
        mBtnConfirm=findViewById(R.id.btn_confirm);

        if(!TextUtils.isEmpty(mTitle)){
            mTvTitle.setText(mTitle);
        }

        if(!TextUtils.isEmpty(mMessage)){
            mTvMessage.setText(mMessage);
        }

        if(!TextUtils.isEmpty(mConfirmText)){
            mBtnConfirm.setText(mConfirmText);
        }

        if(!TextUtils.isEmpty(mCancelText)){
            mBtnCancel.setText(mCancelText);
        }

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onConfirmClickListener==null){
                    throw new NullPointerException("clicklistener is not null");
                }else {
                    onConfirmClickListener.onClick(v);
                }
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onCancelClickListener==null){
                    throw new NullPointerException("clicklistener is not null");
                }else {
                    onCancelClickListener.onClick(v);
                }
            }
        });
    }

    public void CancelDialog(){
        setCanceledOnTouchOutside(true);
    }

    public static class Builder {
        private String mTitle;
        private String mMessage;
        private String mConfirmText;
        private String mCancelText;
        private onConfirmClickListener mOnConfirmClickListener;
        private onCancelClickListener mOnCcancelClickListener;
        private Context mContext;

        private Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public Builder setOnConfirmClickListener(String confirmText, DiyDialog.onConfirmClickListener confirmclickListener) {
            this.mConfirmText = confirmText;
            this.mOnConfirmClickListener =  confirmclickListener;
            return this;
        }

        public Builder setOnCancelClickListener(String cancelText, DiyDialog.onCancelClickListener onCancelclickListener) {
            this.mCancelText = cancelText;
            this.mOnCcancelClickListener = onCancelclickListener;
            return this;
        }

        public DiyDialog build() {
            return new DiyDialog(mContext, mTitle, mMessage, mConfirmText, mCancelText,
                    mOnConfirmClickListener, mOnCcancelClickListener);
        }
    }


}
