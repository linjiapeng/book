package com.example.book.util;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.example.book.R;


public class DiyAdapter extends BaseAdapter {

    private Context mContext;
    private Cursor mCursor;  /** 每一行的集合*/
    private LinearLayout mLayout;

    public DiyAdapter(Context context,Cursor cursor){
        this.mContext=context;
        this.mCursor=cursor;
    }


    @Override
    public int getCount() {
        return mCursor.getCount();
    }

    /** 因为Cursor 需要通过下标来取数据 */
    @Override
    public Object getItem(int position) {
        return mCursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**mCursor.getColumnIndex（） 返回指定列的名称，如果不存在返回-1*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(mContext);
        mLayout= (LinearLayout) inflater.inflate(R.layout.listview,null);

        TextView content=(TextView)mLayout.findViewById(R.id.tv_content);
        TextView time=(TextView)mLayout.findViewById(R.id.tv_time);

        mCursor.moveToPosition(position);  //光标移动到一个绝对的位置上
        String dbContext=mCursor.getString(mCursor.getColumnIndex("content"));
        String dbtime=mCursor.getString(mCursor.getColumnIndex("time"));

        content.setText(dbContext);
        time.setText(dbtime);
        return mLayout;
    }

}
