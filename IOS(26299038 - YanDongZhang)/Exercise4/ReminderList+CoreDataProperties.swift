//
//  ReminderList+CoreDataProperties.swift
//  To-Do-List
//
//  Created by YanDongZhang on 16/04/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

import Foundation
import CoreData

extension ReminderList {

    @NSManaged var members: NSSet?
    
    // Add Reminder Method
    func addReminder(value:Reminder)
    {
        let rem = self.mutableSetValueForKey("members")
        rem.addObject(value)
    }
    

}
