package com.example.daniel.calculator;

/**
 * Created by daniel on 9/03/2016.
 */
public class Calculator {

    double number1 = 0;
    double number2 = 0;


    //Set the Add Method
    public String add(double number1,double number2)
    {

        this.number1 = number1;
        this.number2 = number2;

        if (number1 != 0  && number2 != 0) {
            return "The result is " + (this.number1 + this.number2);
        }else{
            return "You need input a number!!!";
        }

    }

   //Set the minus Method
    public String minus(double number1,double number2)
    {
        this.number1 = number1;
        this.number2 = number2;

        if (number1 != 0 && number2 != 0){
        return "The result is" + (this.number1 - this.number2);
    }else{
            return "You need input a number!!!";
        }

    }

    //Set the Multiply Method
    public String multiply(double number1,double number2)
    {
        this.number1 = number1;
        this.number2 = number2;

        if (number1 !=0 && number2 != 0)
        {
            return "The result is " + (this.number1 * this.number2);
        }else{
            return "You need input a number!!!";
        }
    }


    //Set divide Method
    public String divide(double number1,double number2)
    {
        this.number1 = number1;
        this.number2 = number2;

        if (number1 != 0 && number2 != 0)
        {
            return "The result is " + String.format("%.2f",(this.number1 / this.number2));
        }else{
            return "You need input a number!!!";
        }
    }




}
