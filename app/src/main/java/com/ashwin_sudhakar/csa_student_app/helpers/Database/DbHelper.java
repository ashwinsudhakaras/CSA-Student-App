package com.ashwin_sudhakar.csa_student_app.helpers.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Login.db";

    public DbHelper(Context context){

        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create Table users (email Text primary key,Password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int oldVersion, int newVersion) {
        myDb.execSQL("drop Table if exists users");
    }
    public boolean insert(String email, String password)
    {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);

        long newRow = myDb.insert("users",null, contentValues);
        return newRow != -1;
    }

    public boolean checkExistingEmail(String email){

        SQLiteDatabase myDb = this.getReadableDatabase();
        Cursor cursor = myDb.rawQuery("select * from users where email = ?",new String[]{email});
        return cursor.getCount() > 0;
    }
    public boolean checkExistingEmailPassword(String email,String password){

        SQLiteDatabase myDb = this.getReadableDatabase();
        Cursor cursor = myDb.rawQuery("select * from users where email = ? and password = ?",new String[]{email,password});
        return cursor.getCount() > 0;
    }
}
