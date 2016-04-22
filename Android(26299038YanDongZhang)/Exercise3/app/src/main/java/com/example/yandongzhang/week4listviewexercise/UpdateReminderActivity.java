package com.example.yandongzhang.week4listviewexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;


public class UpdateReminderActivity extends Activity {

    private Reminder reminder;
    private TextView titleTextView;
    private TextView descTextView;
    private TextView dueDateTextView;
    private Switch isCompletedSwitch;
    private TextView statusTextView;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reminder);

        titleTextView = (TextView)findViewById(R.id.titleTextView);
        descTextView = (TextView)findViewById(R.id.descTextView);
        dueDateTextView = (TextView)findViewById(R.id.dueDateTextView);
        isCompletedSwitch = (Switch)findViewById(R.id.isComplete);
        statusTextView = (TextView)findViewById(R.id.statusTextView);

        Intent intent = getIntent();
        reminder = intent.getParcelableExtra("reminder");
//        postion = intent.getIntExtra("position");

        titleTextView.setText(reminder.getTitle());

        descTextView.setText(reminder.getDesc());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dueDateTextView.setText(sdf.format(reminder.getDueDate()));

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

            Intent intent = new Intent();
            intent.putExtra("result",reminder);
            setResult(RESULT_OK,intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
