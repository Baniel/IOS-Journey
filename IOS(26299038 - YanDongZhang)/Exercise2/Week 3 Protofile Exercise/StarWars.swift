//
//  StarWars.swift
//  Week 3 Protofile Exercise
//
//  Created by YanDongZhang on 20/03/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit

class StarWars:NSObject {
    
    var firstName: String?
    var surname: String?
    var motherMaidenName: String?
    var birthplace: String?
    var favouriteBrand: String?
    
    override init()
    {
        self.firstName = "mystery"
        self.surname = "mystery"
        self.motherMaidenName = "mystery"
        self.birthplace = "mystery"
        self.favouriteBrand = "mystery"
    }
    
    init(firstName: String, surname: String, motherMaidenName: String,birthplace: String, favouriteBrand: String)
    {
        self.firstName = firstName
        self.surname = surname
        self.motherMaidenName = motherMaidenName
        self.birthplace = birthplace
        self.favouriteBrand = favouriteBrand
        
    }
}
