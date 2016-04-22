package com.example.yandongzhang.week5studioexercise.com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by yandongzhang on 16/4/1.
 */
public class Reminder implements Parcelable {



//     1   database constants start
//       database name
    public static final String TABLE_NAME = "reminders";

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_DUE_DATE = "dueDate";
    public static final String COLUMN_IS_COMPLETE = "isComplete";


    public static final String CREATE_STATEMENT =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_DESC + " TEXT NOT NULL, " +
                    COLUMN_DUE_DATE + " INTEGER NOT NULL," +
                    COLUMN_IS_COMPLETE + " INTEGER NOT NULL" +   ")";




    private long id;
    //    1 end


    private String title;

    private String desc;

    private Date dueDate;

    private boolean isComplete;

    // Static method to create Parcelable object (required)
    public static final Creator CREATOR = new Creator() {


        public Reminder createFromParcel(Parcel in) {

            return new Reminder(in);
        }


        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };


//    2 add the new method
    public Reminder(long id, String title, String desc, Date dueDate, boolean isComplete) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.dueDate = dueDate;
        this.isComplete = isComplete;
    }

    public Reminder(String title, String desc, Date dueDate, boolean isComplete) {
        this.title = title;
        this.desc = desc;
        this.dueDate = dueDate;
        this.isComplete = isComplete;
    }

    public Reminder(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.desc = in.readString();
        this.dueDate =  new Date(in.readLong());
        this.isComplete = in.readByte() != 0;

    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeLong(dueDate.getTime());
        parcel.writeByte((byte)(isComplete?1:0));

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
