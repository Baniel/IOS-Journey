//
//  AddReminderController.swift
//  To-Do-List
//
//  Created by YanDongZhang on 8/04/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit
import Foundation
import CoreData

protocol addReminderDelegate{
    func addReminder(reminder:Reminder)
}

class AddReminderController: UIViewController {
    
    var allReminders = NSArray?()
    var delegate:addReminderDelegate?
    var managedObjectContext:NSManagedObjectContext
    
    var titleText:String?
    var descriptionsText: String?
    var date : NSDate?
    var dateString : String?
    var reminder : Reminder?
    
    
    //Initial Method
    required init?(coder aDecoder: NSCoder) {
        let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
        self.managedObjectContext = appDelegate.managedObjectContext
        super.init(coder: aDecoder)
    }
    
    
    @IBOutlet weak var TitleInputText: UITextField?
    
    @IBOutlet weak var DescriptionInputText: UITextField?
    
    @IBOutlet weak var myDatePicker: UIDatePicker!
    
    //Event Listener datePicker
    @IBAction func datePickerAction(sender: AnyObject) {
        let dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "dd/MM/yyyy"
        let strDate = dateFormatter.stringFromDate(myDatePicker.date)
        self.selectedDate.text = strDate
        date = myDatePicker.date
    }
    
    
    @IBOutlet weak var selectedDate: UILabel!
    
    
    
    
    
    //Save the changed information of Reminder
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
            
            saveRecords()
            
            let newReminder: Reminder = (NSEntityDescription.insertNewObjectForEntityForName("Reminder", inManagedObjectContext: self.managedObjectContext) as? Reminder)!
            
            newReminder.title = TitleInputText?.text
            newReminder.content = DescriptionInputText?.text
            newReminder.dueDate = myDatePicker.date
            newReminder.status = false
            
            self.delegate!.addReminder(newReminder)
            self.navigationController!.popViewControllerAnimated(true)
            
        }
        
        
        
      
    }
    
    
    //Save the changed data into the Reminder Entity
    func saveRecords(){
        do
        {
            try self.managedObjectContext.save()
            let fetchRequest = NSFetchRequest()
            let entityDescription = NSEntityDescription.entityForName("Reminder", inManagedObjectContext: self.managedObjectContext)
            fetchRequest.entity = entityDescription
            do
            {
                self.allReminders = try self.managedObjectContext.executeFetchRequest(fetchRequest) as! [Reminder]
            }
            catch
            {
                let fetchError = error as NSError
                print(fetchError)
            }
        }
        catch let error
        {
            print("Could not save Deletion \(error)")
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
