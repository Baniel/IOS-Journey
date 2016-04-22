package com.example.yandongzhang.week4listviewexercise;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yandongzhang.week4listviewexercise.Reminder;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity {

//    the parameter below should be keep same in both request and response
    public static final int ADD_REMINDER_REQUEST = 1;
    public static final int UPDATE_REMINDER_REQUEST = 2;


    private ListView reminderListView;
    private ArrayList<Reminder> reminders;
    private int curentReminderIndex;
    private TextView noticeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        get the listView associated with layout
        reminderListView = (ListView)findViewById(R.id.reminderListView);

//        get the noticeBoard
        noticeTextView = (TextView)findViewById(R.id.noticeTextView);


//        initiate the data source in arrayList type.
        reminders = new ArrayList<Reminder>();

//        use adapter translate the arrayList to data can be used in list view.
        ReminderAdapter adapter = new ReminderAdapter(this,reminders);

        reminderListView.setAdapter(adapter);


        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pushToReminderDetail(position);

            }
        });

          if(reminders.size() <= 0){
                this.noticeTextView.setText("No Reminder!!!");
          }else
          {

              this.noticeTextView.setText("");
          }


        // Show dialog when holding list item to remove it start
        reminderListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,View view, final int position, long l) {
                // Build dialog to delete item
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Remove Reminder!!!");
                builder.setMessage("Do you want to remove this reminder???");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Remove monster from Database
                        Reminder r = reminders.get(position);
                       reminders.remove(r);
                        // Update ListView
                        refreshListView();
                        Toast.makeText(getBaseContext(), "Reminder has been removed.", Toast.LENGTH_SHORT).show();
                    }                 });
                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Close the dialog
                        dialogInterface.cancel();
                    }                 });
                // Create and show dialog
                builder.create().show();
                return true;
            }
        });

        updateReminderCount();


    }


    private void pushToReminderDetail(int position){
        Reminder reminder = (Reminder)reminderListView.getAdapter().getItem(position);

        Intent i = new Intent(this,UpdateReminderActivity.class);

        i.putExtra("reminder",reminder);

//        i.putExtra("position",position);
        this.curentReminderIndex = position;


        startActivityForResult(i,UPDATE_REMINDER_REQUEST);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {         super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_REMINDER_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Grab the Monster object out of the intent
                Reminder m = data.getParcelableExtra("result");
                reminders.add(m);
                // Apply new adapter and update count

                refreshListView();

            }
        }else if(requestCode == UPDATE_REMINDER_REQUEST){
                if(resultCode == RESULT_OK){
                    Reminder m = data.getParcelableExtra("result");
                    reminders.set(this.curentReminderIndex,m);
                    reminderListView.setAdapter(new ReminderAdapter(this,reminders));

                }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            Intent i = new Intent(this,AddOneReminderActivity.class);
            startActivityForResult(i,ADD_REMINDER_REQUEST);



        }

        return super.onOptionsItemSelected(item);
    }

    //    refresh the listview after change
    private void refreshListView() {
        // Get current monsters, update ListView then monster count

        Collections.sort(reminders, new SortByDueDate());
        reminderListView.setAdapter(new ReminderAdapter(this, reminders));
        this.updateReminderCount();
    }


    private void updateReminderCount() {
        // Get total number of monsters
        if(reminders.size() <= 0){
            this.noticeTextView.setText("No reminder,please add one reminder");
        }else
        {

            this.noticeTextView.setText("");
        }
    }

    //     sort solution
    class SortByDueDate implements Comparator {

        @Override
        public int compare(Object lhs, Object rhs) {
            Reminder r1 = (Reminder)lhs;
            Date r1DueDate =  r1.getDueDate();
            Reminder r2 = (Reminder)rhs;
            Date r2DueDate = ((Reminder) rhs).getDueDate();

            if(r1DueDate.before(r2DueDate))
                return -1;
            else if(r1DueDate.equals(r2DueDate))
                return 0;
            else
                return 1;

        }
    }


}
