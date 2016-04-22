//
//  ReminderListController.swift
//  To-Do-List
//
//  Created by YanDongZhang on 8/04/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit
import CoreData

class ReminderListController: UITableViewController,addReminderDelegate,updateReminderDelegate {
    
    var managedObjectContext: NSManagedObjectContext
    var reminderList:NSMutableArray
    var orderReminder:NSMutableArray
    var currentReminders : ReminderList?
    
    //Initial Method
    required init?(coder aDecoder:NSCoder){
        self.reminderList = NSMutableArray()
        self.orderReminder = NSMutableArray()
        let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
        self.managedObjectContext = appDelegate.managedObjectContext
        super.init(coder: aDecoder)
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        //Get the Reminders form the ReminderList Entity
        let fetchRequest = NSFetchRequest()
        let entityDescription = NSEntityDescription.entityForName("ReminderList", inManagedObjectContext: self.managedObjectContext)
        fetchRequest.entity = entityDescription
        
        var result = NSArray?()
        do{
            result = try self.managedObjectContext.executeFetchRequest(fetchRequest)
            if result!.count == 0
            {
                self.currentReminders = ReminderList.init(entity:NSEntityDescription.entityForName("ReminderList", inManagedObjectContext: self.managedObjectContext)!,insertIntoManagedObjectContext: self.managedObjectContext)
            }else
            {
                self.currentReminders = result![0] as? ReminderList
                self.reminderList = NSMutableArray(array:(currentReminders!.members?.allObjects as! [Reminder]))
            }
        }
        catch
        {
            let fetchError = error as NSError
            print(fetchError)
        }
        
        //Set the Table Row Height
        tableView.rowHeight = 100.0
    }
    
    
    
    //Set 2 Sections in Table View
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 2
    }
    
    //Return the row of Table View
    override func tableView(tableView:UITableView,numberOfRowsInSection section: Int)->Int{
        switch(section)
        {
        case 0: return self.reminderList.count
        case 1: return 1
        default: return 0
        }
    }
    
    override func tableView(tableView:UITableView,cellForRowAtIndexPath indexPath:NSIndexPath)->UITableViewCell{
        
        
        //Set the Reminder Cell Layout
        if indexPath.section == 0
        {
            let cell = tableView.dequeueReusableCellWithIdentifier("ReminderCell",forIndexPath: indexPath) as! ReminderCell
            
            //Configure the cell...
            let r: Reminder = self.reminderList[indexPath.row] as! Reminder
            cell.titleLabel.text = "Title:\(r.title!)"
            
            let dateFormatter = NSDateFormatter()
            dateFormatter.dateFormat = "dd/MM/yyyy"
            let strDate = dateFormatter.stringFromDate(r.dueDate!)
            cell.dueDateLabel.text = "Due Date:\(strDate)"
            return cell
        }
        else{
            let cell = tableView.dequeueReusableCellWithIdentifier("TotalCell",forIndexPath: indexPath)
            cell.textLabel!.text = "Total Reminders: \(reminderList.count)"
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
            managedObjectContext.deleteObject(reminderList.objectAtIndex(indexPath.row) as! NSManagedObject)
            self.reminderList.removeObjectAtIndex(indexPath.row)
            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
            self.tableView.reloadSections(NSIndexSet(index: 1), withRowAnimation: .Fade)
        }
        
        //Save the ManagedObjectContext
        do
        {
            try self.managedObjectContext.save()
        }
        catch let error
        {
            print("Could not save Deletion \(error)")
        }
        
    }
    
    
    
    
    
    override func prepareForSegue(segue:UIStoryboardSegue,sender:AnyObject?){
        
        
        //Transmit the delegate into Reminder Detail
        if segue.identifier == "ReminderDetailSegue"
        {
            
            let indexPath = self.tableView.indexPathForSelectedRow!
            
            let controller: ReminderDetailController = segue.destinationViewController as!
            ReminderDetailController
        
//            controller.managedObjectContext = self.managedObjectContext
    
            controller.currentReminder = self.reminderList[indexPath.row] as? Reminder
            controller.delegate = self
            
            self.navigationController!.popViewControllerAnimated(true)
        }
        
        //Transmit the delegate into the AddReminder
        if segue.identifier == "AddReminderSegue"
        {
            let controller: AddReminderController = segue.destinationViewController as! AddReminderController
            //Pass the MOC
            controller.managedObjectContext = self.managedObjectContext
            controller.delegate = self
        }
    }
    
    
//Add the Reminder into the Reminder List and sort reminder by due date
    func addReminder(reminder: Reminder) {
        self.currentReminders!.addReminder(reminder)
        self.reminderList = NSMutableArray(array:(currentReminders)!.members?.allObjects as! [Reminder])
        let sortByDate: NSSortDescriptor = NSSortDescriptor(key: "dueDate",ascending: true)
        self.reminderList.sortUsingDescriptors([sortByDate])
        //Save the ManagedObjectContext
        
        do
        {
            try self.managedObjectContext.save()
        }
        catch let error
        {
            print("Could not save Deletion \(error)")
        }
        
        self.tableView.reloadData()
    }
    
//Update the Reminder into the Reminder List and sort reminder by due date
    func updateReminder(reminder: Reminder) {
        self.currentReminders!.addReminder(reminder)
        self.reminderList = NSMutableArray(array:(currentReminders)!.members?.allObjects as! [Reminder])
        let sortByDate: NSSortDescriptor = NSSortDescriptor(key: "dueDate",ascending: true)
        self.reminderList.sortUsingDescriptors([sortByDate])
        //Save the ManagedObjectContext
        
        do
        {
            try self.managedObjectContext.save()
        }
        catch let error
        {
            print("Could not save Deletion \(error)")
        }
        
        self.tableView.reloadData()
        
    }
    
    
    
    
    
    
    
}
