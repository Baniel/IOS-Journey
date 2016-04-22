package com.example.daniel.week_3_portfolio_exercise;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DataEntryActivity extends AppCompatActivity {

    // UI properties
    private EditText firstNameText;
    private EditText surnameText;
    private EditText motherMadienNameText;
    private EditText birthplaceText;
    private EditText favouriteBrandText;
    private Button sumbitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        // Setup UI controls
        firstNameText = (EditText) findViewById(R.id.firstNameText);
        surnameText = (EditText) findViewById(R.id.surnameText);
        motherMadienNameText = (EditText) findViewById(R.id.motherMadienNameText);
        birthplaceText = (EditText) findViewById(R.id.birthplaceText);
        favouriteBrandText = (EditText) findViewById(R.id.favouriteBrandText);
        sumbitButton = (Button) findViewById(R.id.submitButton);

        sumbitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateName(v);
            }
        });
    }



    // Button OnClickListener setup via Layout property
    public void generateName(View v) {
        // Grab input balues and create Monster object
        // You should ideally have some input validation here!
        String firstName = firstNameText.getText().toString();
        String surname = surnameText.getText().toString();
        String motherMadienName = motherMadienNameText.getText().toString();
        String birthdayPlace = birthplaceText.getText().toString();
        String favouriteBrand = favouriteBrandText.getText().toString();

        StarWars starWars = new StarWars(firstName,surname,motherMadienName,birthdayPlace,favouriteBrand);


        if (firstName.isEmpty() || surname.isEmpty() || motherMadienName.isEmpty() || birthdayPlace.isEmpty() || favouriteBrand.isEmpty()){

            // User the AlertDialog Builder utility to create the dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Set message and title for dialog (we can chain methods here!)
            builder.setTitle("Error").setMessage("Please input value");

            //Add a button to the dialog with an event handler for clicks
            builder.setPositiveButton("Contiune", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            // Set the dialog to not be distrubed by the application
            builder.setCancelable(false);

            // Create dialog and display it to the user
            builder.create().show();
        } else {

            // Setup our Intent and pass our Monster object to next Activity
            Intent name = new Intent(this,DisplayGeneratedNameActivity.class);
            name.putExtra("starWars",starWars);
            startActivity(name);
        }









    }





}
