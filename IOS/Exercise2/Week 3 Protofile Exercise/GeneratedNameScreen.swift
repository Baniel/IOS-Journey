//
//  GeneratedNameScreen.swift
//  Week 3 Protofile Exercise
//
//  Created by YanDongZhang on 20/03/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit

class GeneratedNameScreen: UIViewController {

    var currentStarWars: StarWars?

    @IBOutlet weak var nameField: UILabel!
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        
        let firstName = self.currentStarWars?.firstName
        let surname = self.currentStarWars?.surname
        let motherMaidenName = self.currentStarWars?.motherMaidenName
        let birthPlace = self.currentStarWars?.birthplace
        let favouriteBrand = self.currentStarWars?.favouriteBrand
        
    
        let firstNameIndex = firstName?.startIndex
        let surnameStartIndex = surname?.startIndex
        let surnameEndIndex = surname?.endIndex
        let motherMaidenNameIndex = motherMaidenName?.startIndex
        let birthPlaceIndex = birthPlace?.startIndex
        
        
        
        
      
    let sentence  = String(firstName![firstNameIndex!]).uppercaseString +
            String(firstName![(firstNameIndex?.advancedBy(1))!...(firstNameIndex?.advancedBy(2))!]).lowercaseString + String(surname![(surnameStartIndex?.advancedBy(0))!...(surnameStartIndex?.advancedBy(1))!]).lowercaseString + "    " +
        String(motherMaidenName![motherMaidenNameIndex!]).uppercaseString +
        String(motherMaidenName![(motherMaidenNameIndex?.advancedBy(1))!]).lowercaseString +
        String(birthPlace![(birthPlaceIndex?.advancedBy(0))!...(birthPlaceIndex?.advancedBy(2))!]).lowercaseString + "     " + "of" + "     "
          String(surname![(surnameEndIndex?.predecessor().predecessor())!]).uppercaseString +
        String(surname![(surnameEndIndex?.predecessor())!]).lowercaseString
      
            
           
     nameField.text = sentence + (favouriteBrand?.lowercaseString)!
    
       
        
    }
    
    
    
    
    
}
