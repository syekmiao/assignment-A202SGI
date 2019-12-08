package com.example.contacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.contacts.ContactContract.*;

public class ContactDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "contactlist.db";
    public static final int DATABASE_VERSION = 1;


    public ContactDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CONTACTLIST_TABLE = "CREATE TABLE " +
                ContactEntry.TABLE_NAME + " (" +
                ContactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContactEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ContactEntry.COLUMN_PHONE + " TEXT NOT NULL, " +
                ContactEntry.COLUMN_EMAIL + " TEXT, " +
                ContactEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_CONTACTLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContactEntry.TABLE_NAME);
        onCreate(db);
    }
}
