package com.example.yandongzhang.week5studioexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;


import com.example.yandongzhang.week5studioexercise.com.model.Reminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class UpdateReminderActivity extends Activity {

    private Reminder reminder;
    private EditText titleEditText;
    private EditText descEditText;
    private DatePicker dueDatePicker;
    private Switch isCompletedSwitch;
    private TextView statusTextView;
    private int position;

    private int mYear;
    private int mMonth;
    private int mDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reminder);

        titleEditText = (EditText)findViewById(R.id.updateTitleEditText);
        descEditText = (EditText)findViewById(R.id.updateDescEditText);
        dueDatePicker = (DatePicker)findViewById(R.id.updateDatePicker);
        isCompletedSwitch = (Switch)findViewById(R.id.isComplete);
        statusTextView = (TextView)findViewById(R.id.statusTextView);

        Intent intent = getIntent();
        reminder = intent.getParcelableExtra("reminder");
//        postion = intent.getIntExtra("position");

        titleEditText.setText(reminder.getTitle());

        descEditText.setText(reminder.getDesc());

        Calendar dueDateCal = Calendar.getInstance();
        dueDateCal.setTime(reminder.getDueDate());

        mYear = dueDateCal.get(Calendar.YEAR);
        mMonth = dueDateCal.get(Calendar.MONTH);
        mDay = dueDateCal.get(Calendar.DAY_OF_MONTH);

        dueDatePicker.init(mYear,mMonth,mDay,new DatePicker.OnDateChangedListener() {
            //           when the user change datepicker,method below is called
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;

            }
        });

        isCompletedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                   statusTextView.setText("task completed");
                    reminder.setComplete(isChecked);

                }else{
                   statusTextView.setText("task not completed");
                   reminder.setComplete(isChecked);

                }
            }
        });

        if(reminder.isComplete()){
           isCompletedSwitch.setChecked(true);
           statusTextView.setText("task completed");

        }else{
            isCompletedSwitch.setChecked(false);
            statusTextView.setText("task not completed");

        }






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {


            String title = titleEditText.getText().toString();
            String desc = descEditText.getText().toString();
            Calendar cal = Calendar.getInstance();
            cal.set(mYear,mMonth,mDay);
            Date mDate = cal.getTime();


            reminder.setTitle(title);
            reminder.setDesc(desc);
            reminder.setDueDate(mDate);



            Intent intent = new Intent();
            intent.putExtra("result",reminder);



            setResult(RESULT_OK,intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
