package com.example.assignmentdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String Ctn = "Userdetails";

    public DBHelper(Context context)
    {
        super(context, "Userdata.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create Table " +Ctn+"(id TEXT Primary Key,name TEXT,program TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop Table if exists " +Ctn);
    }

    public Boolean insert(String id ,String name,String program)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("program",program);

        long result=db.insert(Ctn,null,contentValues);
        if(result==-1)

        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public Boolean update(String id ,String name,String program)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("program",program);
        Cursor cursor=db.rawQuery("Select * from "+Ctn+" where id =?",new String[]{id});
        if(cursor.getCount()>0)
        {
            long result=db.update(Ctn,contentValues,"id=?",new String[]{id});
            if(result==-1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }

    }

    public Boolean delete(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+Ctn+" where id =?",new String[]{id});
        if(cursor.getCount()>0)
        {
            long result=db.delete(Ctn,"id=?",new String[]{id});
            if(result==-1)

            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public Cursor viewData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from " +Ctn ,null);
        return cursor;
    }

}

