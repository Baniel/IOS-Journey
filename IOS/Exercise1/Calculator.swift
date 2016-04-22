//
//  Calculator.swift
//  Calculator
//
//  Created by YanDongZhang on 8/03/2016.
//  Copyright © 2016 Monash. All rights reserved.
//

import UIKit

class Calculator: NSObject {
    
    var numberA:Float?
    var numberB:Float?
    
    override init()
    {
       
    }
    
    
    //Set the Calculator Add Method
    func add(numberA:Float,numberB:Float)->String{
        
        self.numberA = numberA
        self.numberB = numberB
    
        return String(format: "%.2f",self.numberA! + self.numberB!)
    }
    
    
    //Set the Calculator Substract Method
    func substract(numberA:Float,numberB:Float)->String{
        self.numberA = numberA
        self.numberB = numberB
        
        return String(format: "%.2f", self.numberA! - self.numberB!)
    }
    
    
    //Set the Calculator Multiply Method
    func multiply(numberA:Float,numberB:Float)->String{
        self.numberA = numberA
        self.numberB = numberB
        
        return String(format: "%.2f", self.numberA! * self.numberB!)
    }
    
    //Set the Calculator Divide Method
    func divide(numberA:Float,numberB:Float)->String{
        self.numberA = numberA
        self.numberB = numberB
        
        if self.numberB != 0
        {
            return String(format: "%.2f",self.numberA!/self.numberB!)
        }else{
            return "Attention!!! 0 not allowed to be dividend"
        }
        
       
    }
    
    
    
 
}
