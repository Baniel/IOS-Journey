//
//  News+CoreDataProperties.swift
//  NewsFeed
//
//  Created by Daniel on 17/04/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

import Foundation
import CoreData

extension News {

    @NSManaged var picture: String?
    @NSManaged var title: String?
    @NSManaged var link: String?

}
