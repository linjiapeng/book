package com.example.book.activity.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.book.R;
import com.example.book.activity.home.ShowActivity;
import com.example.book.util.DiyAdapter;
import com.example.book.util.NoteDb;

public class FragmentTab1 extends Fragment {

    private ListView mLv_content;
    private DiyAdapter mAdapter;
    private NoteDb mNoteDb;
    private Cursor cursor;
    private SQLiteDatabase mSqlDb;
    public View mView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mView=inflater.inflate(R.layout.activity_text,container,false);
        mLv_content=mView.findViewById(R.id.lv_content);

        mNoteDb=new NoteDb(this.getContext());
        mSqlDb=mNoteDb.getReadableDatabase();
        mLv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                sendMessage();
            }
        });

        return mView;
    }



    public void sendMessage(){
        Intent intent=new Intent(getContext(), ShowActivity.class);
        intent.putExtra(NoteDb.ID,cursor.getInt(cursor.getColumnIndex(NoteDb.ID)));
        intent.putExtra(NoteDb.CONTENT,cursor.getString(cursor.getColumnIndex(NoteDb.CONTENT)));
        intent.putExtra(NoteDb.TIME,cursor.getString(cursor.getColumnIndex(NoteDb.TIME)));
        startActivity(intent);
    }


    public void SelectDb(){
        cursor = mSqlDb.query
                (NoteDb.TABLE_NAME,null,null,null,null,null,null);
        mAdapter = new DiyAdapter(getContext(),cursor);
        mLv_content.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        SelectDb();
        super.onResume();
    }
}

