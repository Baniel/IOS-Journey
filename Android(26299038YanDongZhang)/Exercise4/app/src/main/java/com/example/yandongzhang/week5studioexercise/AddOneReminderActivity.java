package com.example.yandongzhang.week5studioexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;


import com.example.yandongzhang.week5studioexercise.R;

import com.example.yandongzhang.week5studioexercise.com.model.Reminder;

import java.util.Calendar;
import java.util.Date;


public class AddOneReminderActivity extends Activity {

//    declare the variation of date
    private int mYear;
    private int mMonth;
    private int mDay;




     DatePicker dueDatePicker;
     EditText titleEditText;
     EditText descEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_one_reminder);


        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        dueDatePicker = (DatePicker)findViewById(R.id.datePicker);
        titleEditText = (EditText)findViewById(R.id.titleEditText);
        descEditText = (EditText)findViewById(R.id.descEditText);

        dueDatePicker.init(mYear,mMonth,mDay,new DatePicker.OnDateChangedListener() {
//           when the user change datepicker,method below is called
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;

            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_one_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_createReminder) {

            String title = titleEditText.getText().toString();
            String desc = descEditText.getText().toString();
            Calendar cal = Calendar.getInstance();
            cal.set(mYear,mMonth,mDay);


            Date mDate = cal.getTime();



            Reminder result = new Reminder(title,desc,mDate,false);

//          return to the previous interface
            Intent intent = new Intent();
            intent.putExtra("result",result);
            setResult(RESULT_OK,intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
