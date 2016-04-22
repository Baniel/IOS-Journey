package com.example.daniel.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Set plus Button
        Button plusButton = (Button) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPlus(v);
            }
        });


        //Set substract Button
        Button substractButton = (Button) findViewById(R.id.substractButton);
        substractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSubstract(v);
            }
        });

        //Set Multiply Button
        Button multiplyButton = (Button) findViewById(R.id.multiplyButton);
        multiplyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonMultiply(v);
            }
        });

       //Set Divide Button
        Button dividedButton = (Button) findViewById(R.id.divideButton);
        dividedButton   .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDivide(v);
            }
        });
    }


    //Set the Plus Event listener
    public void buttonPlus(View v){

        EditText inputNumber1 = (EditText) findViewById(R.id.inputNumber1);
        EditText inputNumber2 = (EditText) findViewById(R.id.inputNumber2);
        final TextView result = (TextView) findViewById(R.id.result);


        if (inputNumber1.getText().toString().isEmpty() || inputNumber2.getText().toString().isEmpty()  )
        {
            result.setText("Please input number!!!");
        }else {
            final Double number1 = Double.valueOf(inputNumber1.getText().toString());
            final Double number2 = Double.valueOf(inputNumber2.getText().toString());
            final Calculator calculator = new Calculator();
            result.setText(calculator.add(number1, number2));
        }
    }

    //Set the Substract Event Listener
    public void buttonSubstract(View v){

        EditText inputNumber1 = (EditText) findViewById(R.id.inputNumber1);
        EditText inputNumber2 = (EditText) findViewById(R.id.inputNumber2);
        final TextView result = (TextView) findViewById(R.id.result);

        if (inputNumber1.getText().toString().isEmpty() || inputNumber2.getText().toString().isEmpty())
        {
            result.setText("Please input number!!!");
        }else{
            final Double number1 = Double.valueOf(inputNumber1.getText().toString());
            final Double number2 = Double.valueOf(inputNumber2.getText().toString());
            final Calculator calculator = new Calculator();

            result.setText(calculator.minus(number1, number2));
        }
    }


    //Set the Multiply Event Listener
    public void buttonMultiply(View v){
        EditText inputNumber1 = (EditText) findViewById(R.id.inputNumber1);
        EditText inputNumber2 = (EditText) findViewById(R.id.inputNumber2);
        final TextView result = (TextView) findViewById(R.id.result);


        if (inputNumber1.getText().toString().isEmpty() || inputNumber2.getText().toString().isEmpty())
        {
            result.setText("Please input number!!!");
        }else{
            final Double number1 = Double.valueOf(inputNumber1.getText().toString());
            final Double number2 = Double.valueOf(inputNumber2.getText().toString());
            final Calculator calculator = new Calculator();

            result.setText(calculator.multiply(number1, number2));
        }
    }


    //Set the Divide Evenet Listener
    public void buttonDivide(View v){
        EditText inputNumber1 = (EditText) findViewById(R.id.inputNumber1);
        EditText inputNumber2 = (EditText) findViewById(R.id.inputNumber2);
        final TextView result = (TextView) findViewById(R.id.result);




        if (inputNumber1.getText().toString().isEmpty() || inputNumber2.getText().toString().isEmpty())
        {
            result.setText("Please input number!!!");
        }else{
            final Double number1 = Double.valueOf(inputNumber1.getText().toString());
            final Double number2 = Double.valueOf(inputNumber2.getText().toString());
            final Calculator calculator = new Calculator();

            if (number2 == 0)
            {
               result.setText("Dividend is not allowed to be Zero!!!");
            }else{
                result.setText(calculator.divide(number1, number2));
            }

        }
    }


}
