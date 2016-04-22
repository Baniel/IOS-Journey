//
//  AddReminderController.swift
//  To-Do-List
//
//  Created by YanDongZhang on 8/04/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit

//Set the protocol to implement the delegate
protocol addReminderDelegate{
    func addReminder(reminder:Reminder)
}

class AddReminderController: UIViewController {

    //Save the list of Reminders
    var allReminders = [Reminder]()
    var delegate:addReminderDelegate?
    
    var titleText:String?
    var descriptionsText: String?
    var date : NSDate?
    var dateString : String?
    var reminder : Reminder?
    
    @IBOutlet weak var TitleInputText: UITextField?
 
    @IBOutlet weak var DescriptionInputText: UITextField?
    
    
    @IBOutlet weak var myDatePicker: UIDatePicker!
    
    //Use the DatePicker to select the date
    @IBAction func datePickerAction(sender: AnyObject) {
        let dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "dd/MM/yyyy"
        let strDate = dateFormatter.stringFromDate(myDatePicker.date)
        self.selectedDate.text = strDate
        date = myDatePicker.date
    }
    @IBOutlet weak var selectedDate: UILabel!
    
    
    //Save the information and transmit it to ReminderListController
    @IBAction func saveButton(sender: AnyObject) {
        
        if TitleInputText!.text == ""
        {
         
            let alert = UIAlertController(title: "Please input your Title",
                                          message: "error",
                                          preferredStyle: .Alert)
            
           
    
            
            let cancelAction = UIAlertAction(title: "Cancel",
                                             style: .Default) { (action: UIAlertAction) -> Void in
            }
            
        
            alert.addAction(cancelAction)
            
            presentViewController(alert,
                                  animated: true,
                                  completion: nil)
            
            
        }
        
        if self.DescriptionInputText!.text == ""
        {
            let alert = UIAlertController(title: "Please input your Decription",
                                          message: "error",
                                          preferredStyle: .Alert)
            
            
            
            
            let cancelAction = UIAlertAction(title: "Cancel",
                                             style: .Default) { (action: UIAlertAction) -> Void in
            }
            
            
            alert.addAction(cancelAction)
            
            presentViewController(alert,
                                  animated: true,
                                  completion: nil)
            

            
        }

        if date == nil
        {
            let alert = UIAlertController(title: "Please select the date",
                                          message: "error",
                                          preferredStyle: .Alert)
            
            
            
            
            let cancelAction = UIAlertAction(title: "Cancel",
                                             style: .Default) { (action: UIAlertAction) -> Void in
            }
            
            
            alert.addAction(cancelAction)
            
            presentViewController(alert,
                                  animated: true,
                                  completion: nil)
            
                  }
       
        
        if self.TitleInputText?.text != "" && self.DescriptionInputText?.text != "" && self.date != nil
        {
            
            self.titleText = TitleInputText!.text
            self.descriptionsText = DescriptionInputText!.text
            
            print(myDatePicker.date)
            
            reminder = Reminder(newtitle: self.titleText!, newDescriptions: self.descriptionsText!, newDueDate:date!)
            self.delegate!.addReminder(reminder!)
            self.navigationController!.popViewControllerAnimated(true)
            
        }
        
        
        
    }
    

    

    
}
