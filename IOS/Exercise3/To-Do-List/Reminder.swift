//
//  Reminder.swift
//  To-Do-List
//
//  Created by YanDongZhang on 6/04/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit

class Reminder: NSObject {

    var title:String?
    var descriptions: String?
    var dueDate: NSDate?
    var complete: Bool?
    
    //Initial Method
    init(newtitle:String,newDescriptions:String,newDueDate:NSDate)
    {
        title = newtitle
        descriptions = newDescriptions
        dueDate = newDueDate
        complete = false
    }
    
    
    
    
}
