package com.example.yandongzhang.week5studioexercise.com.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.yandongzhang.week5studioexercise.com.model.Reminder;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by yandongzhang on 16/4/1.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Set database properties
    public static final String DATABASE_NAME = "ReminderDB";
    public static final int DATABASE_VERSION = 1;

    // Constructor

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Reminder.CREATE_STATEMENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Reminder.TABLE_NAME);
        onCreate(db);

    }


//    Reminder database methods includes basic CRUD
//    Create Method
public void addReminder(Reminder r) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(Reminder.COLUMN_TITLE, r.getTitle());
    values.put(Reminder.COLUMN_DESC, r.getDesc());
    values.put(Reminder.COLUMN_DUE_DATE,r.getDueDate().getTime());
    values.put(Reminder.COLUMN_IS_COMPLETE,r.isComplete()?1:0);
    db.insert(Reminder.TABLE_NAME, null, values);
    db.close();

}


//  retrieve method
    public HashMap<Long, Reminder> getAllReminders() {
        HashMap<Long, Reminder> reminders = new LinkedHashMap<Long, Reminder>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Reminder.TABLE_NAME, null);

        // Add monster to hash map for each row result
        if(cursor.moveToFirst()) {
            do {
                Reminder r = new Reminder(cursor.getLong(0), cursor.getString(1), cursor.getString(2), new Date(cursor.getLong(3)),cursor.getLong(4) != 0);
                reminders.put(r.getId(), r);
            } while(cursor.moveToNext());
        }
        // Close cursor
        cursor.close();
        return reminders;

    }

//   Delete Method
    public Reminder deleteReminder(Reminder r){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Reminder.TABLE_NAME, Reminder.COLUMN_ID + " = ?",
                new String[]{String.valueOf(r.getId())}
        );
        db.close();

        return r;

    }

//    Update Method
    public Reminder updateReminder(Reminder r){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Reminder.COLUMN_TITLE, r.getTitle());
        values.put(Reminder.COLUMN_DESC, r.getDesc());
        values.put(Reminder.COLUMN_DUE_DATE,r.getDueDate().getTime());
        values.put(Reminder.COLUMN_IS_COMPLETE,r.isComplete()?1:0);

        db.update(Reminder.TABLE_NAME,values,Reminder.COLUMN_ID + " = ?",new String[]{String.valueOf(r.getId())});
        db.close();

        return r;




    }






}
