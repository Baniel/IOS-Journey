package com.example.yandongzhang.week4listviewexercise;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by yandongzhang on 16/3/30.
 */
public class Reminder implements Parcelable {

    private String title;

    private String desc;

    private Date dueDate;

    private boolean isComplete;


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {


        public Reminder createFromParcel(Parcel in) {

            return new Reminder(in);
        }


        public Reminder[] newArray(int size) { return new Reminder[size]; }
    };

    public Reminder(String title, String desc, Date dueDate, boolean isComplete) {
        this.title = title;
        this.desc = desc;
        this.dueDate = dueDate;
        this.isComplete = isComplete;
    }

    public Reminder(Parcel in) {
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
}
