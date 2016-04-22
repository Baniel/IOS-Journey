//
//  Reminder+CoreDataProperties.swift
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

extension Reminder {

    @NSManaged var dueDate: NSDate?
    @NSManaged var content: String?
    @NSManaged var title: String?
    @NSManaged var status: NSNumber?
    @NSManaged var list: ReminderList?

}
