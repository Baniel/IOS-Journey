//
//  NewsTableViewCell.swift
//  NewsFeed
//
//  Created by Daniel on 17/04/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit

class NewsTableViewCell: UITableViewCell {

    
  
    @IBOutlet weak var newsPicture: UIImageView!
    
    @IBOutlet weak var newsTitle: UILabel!
    
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
