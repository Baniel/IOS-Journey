//
//  DataEntryScreen.swift
//  Week 3 Protofile Exercise
//
//  Created by YanDongZhang on 20/03/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit

class DataEntryScreen: UIViewController {

    @IBOutlet weak var firstNameField: UITextField!
    
    @IBOutlet weak var surnameField: UITextField!
    
    @IBOutlet weak var MotherMaidenNameField: UITextField!
    
    @IBOutlet weak var BirthplaceField: UITextField!
    
    @IBOutlet weak var favouriteBrandField: UITextField!
    
    
    // The Segue to trasmit the data into the DestinationViewController
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if(segue.identifier == "GenerateName") {
            
            let firstName = self.firstNameField.text
            let surname = self.surnameField.text
            let motherMaidenNamed = self.MotherMaidenNameField.text
            let birthPlace = self.BirthplaceField.text
            let favouriteBrand = self.favouriteBrandField.text
            
            let starWars: StarWars = StarWars(firstName: firstName!, surname: surname!, motherMaidenName: motherMaidenNamed!,birthplace: birthPlace!, favouriteBrand: favouriteBrand!)
            
            if let destinationVC = segue.destinationViewController as? GeneratedNameScreen{
                destinationVC.currentStarWars = starWars
            
        }
    }
    
    }
    
}
