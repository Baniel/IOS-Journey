package com.example.yandongzhang.week4listviewexercise;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by yandongzhang on 16/03/30.
 */
public class ReminderAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Reminder> reminders;

    public ReminderAdapter(Context context, ArrayList<Reminder> monsters) {
        this.context = context;
        this.reminders = monsters;
    }


    @Override
    public int getCount() {

        return reminders.size();

    }


    @Override
    public Reminder getItem(int i) {

        return reminders.get(i);
    }


    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Check if the view has been created for the row. If not, lets inflate it
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_reminder_item, null);
            // List layout here

        }
        // Grab the TextViews in our custom layout
        TextView titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        TextView dueDateTextView = (TextView) view.findViewById(R.id.dueDateTextView);
        // Assign values to the TextViews using the Monster object
        titleTextView.setText(reminders.get(i).getTitle());
        Date dueDate = reminders.get(i).getDueDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dueDateStr = sdf.format(dueDate);


        dueDateTextView.setText("due Date: " + dueDateStr);
        // Change the colour depending on the monster type

        // Return the completed View of the row being processed
        return view;
    }



}
