package com.example.administrator.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NoteDbHelper {
    private String LOGTAG = "NoteDbHelper";
    private static final String DB_NAME = "datal";
    private static final int DB_VETSION = 1;
    private static final String TABLE_NAME = "notes";
    static final String KEY_TITLE = "title";
    static final String KEY_BODY = "body";
    static final String KEY_ROWID = "_id";
    private Context mcontext = null;
    private NoteSQLiteHelper dbHelper = null;
    private SQLiteDatabase db = null;

    public NoteDbHelper(Context mcontext) {
        this.mcontext = mcontext;
    }

    public NoteDbHelper open() {
        dbHelper = new NoteSQLiteHelper(mcontext, DB_NAME, null, DB_VETSION);
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public int createNote(String title,String body){
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,title);
        values.put(KEY_BODY,body);
        int rowId=(int)db.insert(TABLE_NAME,null,values);
        return rowId;
    }
    //更新数据
    public boolean updateNote(long rowId,String title,String body){
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,title);
        values.put(KEY_BODY,body);
        int updateRows=db.update(TABLE_NAME,values,KEY_ROWID+"="+rowId,null);
        return updateRows>0;
    }
    public boolean deleteNote(long rowId){
        int deleteRows=db.delete(TABLE_NAME,KEY_ROWID+"="+rowId,null);
        return deleteRows>0;
    }
    //查询数据获取游标
    //retrieve获取
    public Cursor retrieveAllNotes(){
        Cursor cursor=db.query(TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }
    public List<Notebean> getAllNotes(){
        //获取游标
        Cursor c=retrieveAllNotes();
        //初始化集合
        ArrayList<Notebean> itemList=new ArrayList<>();
        //c.moveToNext()游标指向下一行
        while (c.moveToNext()){
            Notebean item=new Notebean();
           item.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
           item.setBody(c.getString(c.getColumnIndex(KEY_BODY)));
           int idindex=c.getColumnIndex(KEY_ROWID);
           int id=c.getInt(idindex);
           item.setId(id);
            Log.v(LOGTAG,"Notebean"+item.toString());
            itemList.add(item);
        }
        return itemList;
    }
}
