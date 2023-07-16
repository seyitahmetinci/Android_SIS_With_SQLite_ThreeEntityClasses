package com.example.lab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {

    private Context context;

    private  static final String DBName="myDatabase";
    //    private  static final String DBTablename="myStudents"; old database name
    private  static final String cmpeTablename="cmpeTable";
    private  static final String cmseTablename="cmseTable";
    private  static final String blgmTablename="blgmTable";
    private  static final int DBversion=1;

    private  static final String STU_Number="studentNo";

    private  static final String STU_NAME="studentName";
    private  static final String STU_SURNAME="studentSurname";
    private  static final String STU_PROGRAM="studentProgram";

    private  static final String CREATE_CMPE_TABLE="CREATE TABLE " + cmpeTablename +
            " (" + STU_Number + " INTEGER PRIMARY KEY, " +
            STU_NAME + " INTEGER, " + STU_SURNAME +
            " INTEGER, " + STU_PROGRAM + " INTEGER) ;";

    private  static final String CREATE_CMSE_TABLE="CREATE TABLE " + cmseTablename +
            " (" + STU_Number + " INTEGER PRIMARY KEY, " +
            STU_NAME + " INTEGER, " + STU_SURNAME +
            " INTEGER, " + STU_PROGRAM + " INTEGER) ;";

    private  static final String CREATE_BLGM_TABLE="CREATE TABLE " + blgmTablename +
            " (" + STU_Number + " INTEGER PRIMARY KEY, " +
            STU_NAME + " INTEGER, " + STU_SURNAME +
            " INTEGER, " + STU_PROGRAM + " INTEGER) ;";


    private  static final String DROP_CMPE_TABLE="DROP TABLE IF EXISTS " + cmpeTablename;

    private  static final String DROP_CMSE_TABLE="DROP TABLE IF EXISTS " + cmseTablename;

    private  static final String DROP_BLGM_TABLE="DROP TABLE IF EXISTS " + blgmTablename;



    public MyDB(@Nullable Context context) {
        super(context, DBName, null, DBversion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_CMPE_TABLE);
            db.execSQL(CREATE_CMSE_TABLE);
            db.execSQL(CREATE_BLGM_TABLE);
        } catch (SQLException e) {
            Toast.makeText(context, "Error Occurred in Database", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL(DROP_CMPE_TABLE);
            db.execSQL(DROP_CMSE_TABLE);
            db.execSQL(DROP_BLGM_TABLE);
            onCreate(db);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void AddCMPEStu(int stno, String name, String surname, String program){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(STU_Number,stno);
        cv.put(STU_NAME,name);
        cv.put(STU_SURNAME,surname);
        cv.put(STU_PROGRAM,program);

        try {
            db.insert(cmpeTablename,null,cv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    void AddCMSEStu(int stno, String name, String surname, String program){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(STU_Number,stno);
        cv.put(STU_NAME,name);
        cv.put(STU_SURNAME,surname);
        cv.put(STU_PROGRAM,program);

        try {
            db.insert(cmseTablename,null,cv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    void AddBLGMStu(int stno, String name, String surname, String program){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();

        cv.put(STU_Number,stno);
        cv.put(STU_NAME,name);
        cv.put(STU_SURNAME,surname);
        cv.put(STU_PROGRAM,program);

        try {
            db.insert(blgmTablename,null,cv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    Cursor GetCMPEData(){
        String readquery="SELECT * FROM " + cmpeTablename;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor c=null;
        if (db!=null) {
            c = db.rawQuery(readquery, null);

        }

        return c;
    }

    Cursor GetCMSEData(){
        String readquery="SELECT * FROM " + cmseTablename;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor c=null;
        if (db!=null) {
            c = db.rawQuery(readquery, null);

        }

        return c;
    }

    Cursor GetBLGMData(){
        String readquery="SELECT * FROM " + blgmTablename;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor c=null;
        if (db!=null) {
            c = db.rawQuery(readquery, null);

        }

        return c;
    }

    public void deleteCMPE(String student_Number){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(cmpeTablename, STU_Number + " = ?", new String[]{String.valueOf(student_Number)});
    }
    public void deleteCMSE(String student_Number){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(cmseTablename, STU_Number + " = ?", new String[]{String.valueOf(student_Number)});
    }

    public void deleteBLGM(String student_Number){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(blgmTablename, STU_Number + " = ?", new String[]{String.valueOf(student_Number)});
    }


    void UpdateStu(int stno, String name, String surname, String program){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if (program.equals("CMPE")){

            deleteCMSE(String.valueOf(stno));

            deleteBLGM(String.valueOf(stno));

        } else if (program.equals("CMSE")) {

            deleteCMPE(String.valueOf(stno));

            deleteBLGM(String.valueOf(stno));

        } else if (program.equals("BLGM")) {

            deleteCMPE(String.valueOf(stno));
            
            deleteCMSE(String.valueOf(stno));
        }

//        cv.put(STU_Number,stno);
//        cv.put(STU_NAME,name);
//        cv.put(STU_SURNAME,surname);
//        cv.put(STU_PROGRAM,program);
//        String[] whereArgs = { Integer.toString(stno) };

//        int count = db.update(cmpeTablename, cv, STU_Number + " = ?", whereArgs);
//        if (count < 1) {
//            throw new RuntimeException("No student found with student number " + stno);
//        }

        if (program.equals("CMPE")){
            AddCMPEStu(stno,name,surname,program);
        } else if (program.equals("CMSE")) {
            AddCMSEStu(stno,name,surname,program);
        } else if (program.equals("BLGM")) {
            AddBLGMStu(stno,name,surname,program);
        }
    }



}


