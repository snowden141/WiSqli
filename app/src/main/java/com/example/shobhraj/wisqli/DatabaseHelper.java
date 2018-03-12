package com.example.shobhraj.wisqli;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shobhraj on 12/3/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL1="student_id";
    public static final String COL2="student_name";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " +TABLE_NAME+ " (student_id integer primary key autoincrement, student_name text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" drop table "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(COL2,name);
        long result=db.insert(TABLE_NAME,null,content);

        if(result==1)
            return false;
        else
            return true;
    }

    public Cursor getData(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id,String name){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(COL1,id);
        content.put(COL2,name);
        db.update(TABLE_NAME,content,"student_id=?",new String[] {id});
        return true;
    }
}
