package com.vadimsleonovs.horoscope;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by vadims.leonovs on 11/11/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Horoscope.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "entry";
    private static final String COLUMN_NAME_ENTRY_ID = "entryid";
    private static final String COLUMN_NAME_TEXT = "text";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + "( " + COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME_TEXT + " TEXT" + " )";


    private static final String DATABASE_FLAG = "myDataBase";
    Context mContext;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_ENTRY_ID, 1);
            values.put(COLUMN_NAME_TEXT, "TEKST");
        db.insert(TABLE_NAME, null, values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }





}
