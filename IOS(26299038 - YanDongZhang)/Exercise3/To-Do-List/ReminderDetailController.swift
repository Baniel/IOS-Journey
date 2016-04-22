//
//  ReminderDetailController.swift
//  To-Do-List
//
//  Created by YanDongZhang on 8/04/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit



class ReminderDetailController: UIViewController {
    
    
    var currentReminder : Reminder?
    
    
    @IBOutlet weak var titleContentLabel: UILabel!
    
    @IBOutlet weak var descriptionContentLabel: UILabel!
    
    @IBOutlet weak var dueDateContentLabel: UILabel!
    
    @IBOutlet weak var completeLabel: UILabel!
    
    @IBOutlet weak var statueSwitch: UISwitch!
    
    @IBAction func statusValueChanged(sender: AnyObject) {
        
        //Identify the status of the complete label
        if self.statueSwitch.on {
            self.currentReminder?.complete = true
            self.completeLabel.text = "Completed"
        }else
        {
            self.currentReminder?.complete = false
            self.completeLabel.text = "Incompleted"
        }
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        
        self.titleContentLabel.text = currentReminder?.title
        self.descriptionContentLabel.text = currentReminder?.descriptions
        
        
        //Fomate the Data inot the String
        let dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "dd/MM/yyyy"
        let strDate = dateFormatter.stringFromDate((self.currentReminder?.dueDate)!)
        self.dueDateContentLabel.text = strDate
        
        //Event listening the Complete Status
        if self.currentReminder?.complete == true
        {
            self.statueSwitch.setOn(true, animated: true)
            self.completeLabel.text = "Completed"
        }
        
        if self.currentReminder?.complete == false
        {
            self.statueSwitch.setOn(false, animated: true)
            self.completeLabel.text = "Incompleted"
        }
        
    }
    
    
}
