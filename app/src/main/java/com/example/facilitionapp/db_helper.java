package com.example.facilitionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class db_helper extends SQLiteOpenHelper {


    public db_helper(Context context) {
        super(context, "db_user", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table user_details(id integer primary key autoincrement, fname text, lname text, phone text, email text unique, newpass text, retypepass text)");
      db.execSQL("create table user_complain(id integer primary key autoincrement, user_email text, subject text, comment text)");
        db.execSQL("create table user_suggestion(id integer primary key autoincrement, user_email text, suggestion text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user_details");
        db.execSQL("drop table if exists user_complain");
        db.execSQL("drop table if exists user_suggestion");
    }

    public boolean insert_data(String first_name,String last_name,String contact,String e_mail,String new_pass,String retype_pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fname",first_name);
        values.put("lname",last_name);
        values.put("phone",contact);
        values.put("email",e_mail);
        values.put("newpass",new_pass);
        values.put("retypepass",retype_pass);

        long check =  db.insert("user_details", null,values);
        if (check==-1)
            return false;
        else
            return true;
    }

    public boolean insert_query(String useremail,String Subject, String Comment)
    {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_email",useremail);
        values.put("subject",Subject);
        values.put("comment",Comment);
        long check1 = db1.insert("user_complain",null,values);
        if (check1==-1)
            return false;
        else
            return true;
    }

    public boolean insert_suggestion(String Useremail, String Suggestion)
    {
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_email",Useremail);
        values.put("suggestion",Suggestion);
        long check2 = db2.insert("user_suggestion",null,values);

        if (check2==-1)
            return false;
        else
            return true;
    }

//    public boolean update_pass(String newpass, String retypepass){
//        SQLiteDatabase db3 = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("newpass",newpass);
//        values.put("retypepass",retypepass);
//        long check3 = db3.update("user_details",null,values);
//        if (check3==-1)
//            return false;
//        else
//            return true;
//    }

//    //update function
//    public boolean updatepass(String email, String new_pass, String retype_pass){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("newpass",new_pass);
//        contentValues.put("retypepass",retype_pass);
//        db.update(contentValues,"user_details","email")
//    }



    public Cursor getuser()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user_details",null );
        return cursor;
    }
}
