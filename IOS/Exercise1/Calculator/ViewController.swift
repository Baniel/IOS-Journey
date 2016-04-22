//
//  ViewController.swift
//  Calculator
//
//  Created by YanDongZhang on 8/03/2016.
//  Copyright © 2016 Monash. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var number1: UITextField!
    
    @IBOutlet weak var number2: UITextField!
    
    @IBOutlet weak var result: UILabel!
    
    
    //Plus Method
    @IBAction func plusButton(sender: UIButton) {
        var messageString:String = " Not all fields were fillled in!"
        
        
        if number1.text != "" && number2.text != ""{
            let number01 = Float(number1.text!)
            let number02 = Float(number2.text!)
            let Calculator1 = Calculator()
            messageString = Calculator1.add(number01! , numberB: number02!)
        }
        
        result.text = messageString
    
    }
    
    
    //Substract Method
    @IBAction func substractButton(sender: AnyObject) {
        
        var messageString:String = " Not all fields were fillled in!"
        
        if number1.text != "" && number2.text != ""{
            let number01 = Float(number1.text!)
            let number02 = Float(number2.text!)
            let Calculator1 = Calculator()
            messageString = Calculator1.substract(number01! , numberB: number02!)
        }
        
        result.text = messageString
        
        
    }
   
    
    //Multiply Method
    @IBAction func multiplyButton(sender: UIButton) {
        var messageString:String = " Not all fields were fillled in!"
        
        if number1.text != "" && number2.text != ""{
            let number01 = Float(number1.text!)
            let number02 = Float(number2.text!)
            let Calculator1 = Calculator()
            messageString = Calculator1.multiply(number01! , numberB: number02!)
        }
        
        result.text = messageString
    }
    
    //Divide Method
    @IBAction func divideButton(sender: AnyObject) {
        var messageString:String = " Not all fields were fillled in!"
        
        if number1.text != "" && number2.text != ""{
            let number01 = Float(number1.text!)
            let number02 = Float(number2.text!)
            let Calculator1 = Calculator()
            messageString = Calculator1.divide(number01! , numberB: number02!)
        }
        
        result.text = messageString
    }
    
    



}

