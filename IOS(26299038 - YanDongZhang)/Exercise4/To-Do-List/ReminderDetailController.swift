//
//  ReminderDetailController.swift
//  To-Do-List
//
//  Created by YanDongZhang on 8/04/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit
import CoreData


//Set the Portocl for update Reminder Detail (Like title,content,dueDate)
protocol updateReminderDelegate{
    func updateReminder(reminder:Reminder)
}

class ReminderDetailController: UIViewController {

   
    var currentReminder : Reminder?
    var delegate:updateReminderDelegate?
    var managedObjectContext:NSManagedObjectContext
    
    required init?(coder aDecoder: NSCoder){
        let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
        self.managedObjectContext = appDelegate.managedObjectContext
        super.init(coder: aDecoder)
    }
    
    @IBOutlet weak var DatePicker: UIDatePicker!
    
    @IBAction func DatePickerAction(sender: AnyObject) {
        let dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "dd/MM/yyyy"
        let strDate = dateFormatter.stringFromDate(DatePicker.date)
        self.dueDateContentLabel.text = strDate
       
    }
    
    @IBOutlet weak var titleContentText: UITextField!
    
    @IBOutlet weak var descriptionContentText: UITextView!
    
    @IBOutlet weak var dueDateContentLabel: UILabel!
    
    @IBOutlet weak var completeLabel: UILabel!
   
    @IBOutlet weak var statueSwitch: UISwitch!
    
    @IBAction func statusValueChanged(sender: AnyObject) {
        
        if self.statueSwitch.on {
            self.currentReminder?.status = true
            self.completeLabel.text = "Completed"
        }else
        {
            self.currentReminder?.status = false
            self.completeLabel.text = "Incompleted"
        }
    }
    
    
    
    @IBAction func changeReminderButton(sender: AnyObject) {
        currentReminder?.title = self.titleContentText.text
        currentReminder?.content = self.titleContentText.text
        currentReminder?.dueDate = self.DatePicker.date
        
        if self.completeLabel.text == "Completed"
        {
            currentReminder?.status = true
        }else
        {
            currentReminder?.status = false
        }

        self.delegate!.updateReminder(currentReminder!)
       
//        self.navigationController!.popViewControllerAnimated(true)
    }
    
   
    
    override func viewDidLoad() {
        //Title Text
        self.titleContentText.text = currentReminder?.title
        //Description Text
        self.descriptionContentText.text = currentReminder?.content
        
        let dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "dd/MM/yyyy"
        let strDate = dateFormatter.stringFromDate((self.currentReminder?.dueDate)!)
        
        
        //Due Data Label
        self.dueDateContentLabel.text = strDate
        
        if self.currentReminder?.status == true
        {
            self.statueSwitch.setOn(true, animated: true)
            self.completeLabel.text = "Completed"
        }
        
        if self.currentReminder?.status == false
        {
            self.statueSwitch.setOn(false, animated: true)
            self.completeLabel.text = "Incompleted"
        }
        

    }
    
    
    @IBOutlet weak var changeReminderButton: UIButton!
    
//    override func viewWillAppear(animated: Bool) {
//        super.viewWillAppear(animated)
//        
//       }
    
    
}
