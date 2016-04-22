//
//  ReminderListController.swift
//  To-Do-List
//
//  Created by YanDongZhang on 8/04/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit

class ReminderListController: UITableViewController,addReminderDelegate {
    
    var currentReminder:NSMutableArray
    var orderReminder:NSMutableArray
    
    var indexReminder : Int?
    var selectedReminder : Reminder?
    var titleDetail : String?
    var descriptionDetail: String?
    var dueDateDetail: String?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //Set the every row height
        tableView.rowHeight = 100.0
        
    }
    
    //Initial Constructor
    required init?(coder aDecoder:NSCoder){
        self.currentReminder = NSMutableArray()
        self.orderReminder = NSMutableArray()
        super.init(coder: aDecoder)
    }
    
    //Set the number of Section
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 2
    }
    
    
    //Return the row of the section
    override func tableView(tableView:UITableView,numberOfRowsInSection section: Int)->Int{
        switch(section)
        {
        case 0: return self.currentReminder.count
        case 1: return 1
        default: return 0
        }
    }
    
    //Return the Duedate and the title of the Reminder
    override func tableView(tableView:UITableView,cellForRowAtIndexPath indexPath:NSIndexPath)->UITableViewCell{
        
        if indexPath.section == 0
        {
            let cell = tableView.dequeueReusableCellWithIdentifier("ReminderCell",forIndexPath: indexPath) as! ReminderCell
            
            //Configure the cell...
            let r: Reminder = self.currentReminder[indexPath.row] as! Reminder
            cell.titleLabel.text = "Title:\(r.title!)"
            
            let dateFormatter = NSDateFormatter()
            dateFormatter.dateFormat = "dd/MM/yyyy"
            let strDate = dateFormatter.stringFromDate(r.dueDate!)
            cell.dueDateLabel.text = "Due Date:\(strDate)"
            
            
            return cell
        }
        else{
            let cell = tableView.dequeueReusableCellWithIdentifier("TotalCell",forIndexPath: indexPath)
            cell.textLabel!.text = "Total Reminders: \(currentReminder.count)"
            return cell
        }
    }
    //Override to supoort conditional editing of the table view
    override func tableView(tableView:UITableView,canEditRowAtIndexPath indexPath:NSIndexPath)->Bool{
        if indexPath.section == 0{
            return true
        }
        else{
            return false
        }
    }
    
    //Override to support editing the table view
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        
        if editingStyle == .Delete{
            //Delete the row from the data source
            self.currentReminder.removeObjectAtIndex(indexPath.row)
            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
            let totalPath = NSIndexPath(forRow:0,inSection: 1)
            tableView.reloadRowsAtIndexPaths([totalPath], withRowAnimation: .None)
        }
        
    }
    
    
    
    
    
    override func prepareForSegue(segue:UIStoryboardSegue,sender:AnyObject?){
        
        
        //See the detail of the Reminder
        if segue.identifier == "ReminderDetailSegue"
        {
            let indexPath = self.tableView.indexPathForSelectedRow!
            
            let controller: ReminderDetailController = segue.destinationViewController as!
            ReminderDetailController
            
            controller.currentReminder = self.currentReminder[indexPath.row] as? Reminder
        }
        
        
        //Get the informatioin from the AddReminderController with the delegate
        if segue.identifier == "AddReminderSegue"
        {
            let controller: AddReminderController = segue.destinationViewController as! AddReminderController
            controller.delegate = self
        }
    }
    
    
    //How to implement the addReminder delegate
    func addReminder(reminder: Reminder) {
        self.orderReminder.addObject(reminder)
        let sortByDate: NSSortDescriptor = NSSortDescriptor(key: "dueDate",ascending: true)
        self.orderReminder.sortUsingDescriptors([sortByDate])
        
        self.currentReminder = self.orderReminder
        self.tableView.reloadData()
    }
    
    
    
    
    
    
    
}

