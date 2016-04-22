package com.example.daniel.week_3_portfolio_exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayGeneratedNameActivity extends AppCompatActivity {

    // Properties
    private StarWars currentStarWars;
    private TextView nameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_generated_name);

        // Setup UI elements
        nameLabel = (TextView) findViewById(R.id.generateName);

        //Get intent and set up StarWar object passed as Parcel
        Intent name = getIntent();
        currentStarWars = (StarWars) name.getParcelableExtra("starWars");

        // Update text labels
        nameLabel.setText(currentStarWars.getFirstName().substring(0,1).toUpperCase() +
                currentStarWars.getFirstName().substring(1,3).toLowerCase() +
                currentStarWars.getSurname().substring(0,2).toLowerCase() + " " +
                currentStarWars.getMotherMaidenName().substring(0,1).toUpperCase() +
                currentStarWars.getMotherMaidenName().substring(1,2).toLowerCase() +
                currentStarWars.getBirthplace().substring(0,3).toLowerCase() + " " + "of" + " " +
                currentStarWars.getSurname().substring(currentStarWars.getSurname().length()-2,currentStarWars.getSurname().length()-1).toUpperCase() +
                currentStarWars.getSurname().substring(currentStarWars.getSurname().length()-1,currentStarWars.getSurname().length()).toLowerCase() +
                currentStarWars.getFavouriteBrand().toLowerCase());
    }
}
