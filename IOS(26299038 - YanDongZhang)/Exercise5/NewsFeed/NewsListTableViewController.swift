//
//  NewsListTableViewController.swift
//  NewsFeed
//
//  Created by Daniel on 17/04/2016.
//  Copyright © 2016 YanDongZhang. All rights reserved.
//

import UIKit
import CoreData
import Foundation
import SafariServices

class NewsListTableViewController: UITableViewController {
    
    
    var syncCompleted:Bool = false
    var managedObjectContext: NSManagedObjectContext
    var allNews: NSArray?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.downloadNewsData()
       
        //Set the height of row of table view
        tableView.rowHeight = 250.0
    }
    
    //Initial Method
    required init?(coder aDecoder:NSCoder){
        let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
        self.managedObjectContext = appDelegate.managedObjectContext
        super.init(coder: aDecoder)
    }
    
    //Get the latest data from the Internet
    func downloadNewsData() {
        let lastUpdate = loadLastUpdate()
        var url: NSURL
        if lastUpdate == -1 {
            url = NSURL(string: "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20feed%20where%20url=%27www.abc.net.au%2Fnews%2Ffeed%2F51120%2Frss.xml%27&format=json")!
        } else {
            url = NSURL(string: "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20feed%20where%20url=%27www.abc.net.au%2Fnews%2Ffeed%2F51120%2Frss.xml%27&format=json?lastChecked=\(lastUpdate)")!
        }
        print(url)
        let task = NSURLSession.sharedSession().dataTaskWithURL(url){
            (data, response, error) in
            if (error != nil){
                print("Error \(error)")
            } else{
                self.parseNewsJSON(data!)
                self.fetchNewsData()
                self.saveLastUpdate()
            }
            self.syncCompleted = true
            self.tableView.reloadData()
        }
        task.resume()
    }
    
    
    //Get the Data Form the News Entity
    func fetchNewsData(){
        let fetchRequest = NSFetchRequest(entityName: "News")
        
        do{
            self.allNews = try managedObjectContext.executeFetchRequest(fetchRequest)
            print(allNews!.count)
        } catch let error as NSError {
            print("Fetch Failed \(error)")
        }
        tableView.reloadData()
    }
    
    //Set the timestamp
    func saveLastUpdate(){
        let currentDate: NSNumber = NSDate().timeIntervalSince1970
        let paths = NSSearchPathForDirectoriesInDomains(.DocumentDirectory,
                                                        .UserDomainMask, true) as NSArray
        let documentsDirectory = paths.objectAtIndex(0) as! NSString
        let path = documentsDirectory.stringByAppendingPathComponent("lastUpdate.plist")
        let dict: NSMutableDictionary = ["XInitializerItem": "DoNotEverChangeMe"]
        //saving values
        dict.setObject(currentDate, forKey: "lastUpdate")
        dict.writeToFile(path, atomically: false)
        
        let resultDictionary = NSMutableDictionary(contentsOfFile: path)
        print("Saved lastUpdate.plist file is --> \(resultDictionary?.description)")
    }
    
    
    
    //Identify whether the Internet data has been changed
    func loadLastUpdate() -> Double {
        let paths = NSSearchPathForDirectoriesInDomains(.DocumentDirectory,
                                                        .UserDomainMask, true) as NSArray
        let documentsDirectory = paths.objectAtIndex(0) as! NSString
        let path = documentsDirectory.stringByAppendingPathComponent("lastUpdate.plist")
        let resultDictionary = NSMutableDictionary(contentsOfFile: path)
        print("Loaded lastUpdate.plist file is --> \(resultDictionary?.description)")
        
        if let dict: NSDictionary = resultDictionary {
            let lastUpdate = dict.valueForKey("lastUpdate") as! NSNumber
            return lastUpdate.doubleValue
        } else {
            print("Failed to load")
            return -1
        }
    }
    
    //Parse the Json File
    func parseNewsJSON(newsJSON:NSData){
        do{
            let json = try NSJSONSerialization.JSONObjectWithData(newsJSON,
                                                                  options: NSJSONReadingOptions.MutableContainers) as? NSDictionary
            
            if let query :NSDictionary = json!["query"] as? NSDictionary{
                
                if let results : NSDictionary = query["results"] as? NSDictionary{
                    
                    if let items : NSArray = results["item"] as? NSArray{
                        
                        for item in items {
                            
                            var titleData = " "
                            var pictureData = " "
                            var linkData = " "
                            
                            //Get the News Title
                            if let title: String = item["title"] as? String {
//                                print(title)
                                
                                titleData = title
                            }
                            
                            //Get the News Content Link
                            if let guid: NSDictionary = item["guid"] as? NSDictionary{
                                
                                if let content: String = guid["content"] as? String{
                                    // print(content)
                                    linkData = content
                                }
                                
                            }
                            //Get the News Picture
                            
                            if let group: NSDictionary = item["group"] as? NSDictionary{
                                
                                if let content: NSArray = group["content"] as? NSArray{
                                    if let picture: NSDictionary = content[1] as? NSDictionary{
                                        if let url: String = picture["url"] as? String{
                                            pictureData = url
                                        }
                                    }
                                }
                                
                                

                                
                            }
                            
                            //Add the news into the database
                            
                            let newsData = NSEntityDescription.insertNewObjectForEntityForName("News", inManagedObjectContext: self.managedObjectContext) as? News
                            
                            newsData!.title = titleData
                            
                            newsData!.picture = pictureData
                            
                            newsData!.link = linkData
                        }
                        
                    }
                    
                }
                
            }
            saveRecords()
        }catch {
            print("JSON Serialization error")
        }
    }
    
    
    //Save the Internet Data into the News Entity(Database)
    
    func saveRecords(){
        do
        {
            try self.managedObjectContext.save()
            let fetchRequest = NSFetchRequest()
            let entityDescription = NSEntityDescription.entityForName("News", inManagedObjectContext:
                self.managedObjectContext)
            fetchRequest.entity = entityDescription
            do
            {
                self.allNews = try self.managedObjectContext.executeFetchRequest(fetchRequest) as! [News]
            }
            catch
            {
                let fetchError = error as NSError
                print(fetchError)
            }
        }
        catch let error
        {
            print("Could not save Deletion \(error)")
        }
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Table view data source
    
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        
        let reachability = Reachability.reachabilityForInternetConnection()
        
        
        //Identify Internet status(Online or OffLine)
        if reachability!.isReachable(){
            if syncCompleted{
                return self.allNews!.count
            }
            else{
                return 0
            }    //"OnLine"
        }else{
          offline()
            return 0//"OffLine"
        }
        
        
 
    
        
    }
    
    
    
   
    
    //Set the layout of cell
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCellWithIdentifier("NewsCell", forIndexPath: indexPath) as! NewsTableViewCell
        // Configure the cell...
        let n: News = self.allNews![indexPath.row] as! News
        
        cell.newsTitle.text = n.title
        
        let url = NSURL(string:n.picture!)
       
        dispatch_async(dispatch_get_main_queue(), {
        var data = NSData(contentsOfURL:url!)
        cell.newsPicture.image = UIImage(data:data!)
        })
        
        return cell
        
       
    }
    
    //Set the event listener about did select row
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath:
        NSIndexPath)
    {
        let selectedNews = self.allNews![indexPath.row] as! News
        
        let url = NSURL(string:selectedNews.link!)
        // import SafariServices
        
        let safariVC = SFSafariViewController(URL: url!)
        safariVC.view.tintColor = UIColor(red: 248/255.0, green: 47/255.0, blue: 38/255.0, alpha: 1.0)
        safariVC.delegate = self
        self.presentViewController(safariVC, animated: true, completion: nil)
    }
    
  
    // When the Network is offline 
    func offline(){
        
        let alert = UIAlertController(title: "NetWork is offline",
                                      message: "Please contect the Internet and try again",
                                      preferredStyle: .Alert)
        
       
        let cancelAction = UIAlertAction(title: "I got it.",
                                         style: .Default) { (action: UIAlertAction) -> Void in
        }
        
        alert.addAction(cancelAction)
        
        presentViewController(alert,
                              animated: true,
                              completion: nil)
    }
 
    
    
}


//When the user click the news , safari broswer will display the news detail
extension NewsListTableViewController : SFSafariViewControllerDelegate
{
    func safariViewControllerDidFinish(controller: SFSafariViewController) {
        controller.dismissViewControllerAnimated(true, completion: nil)
    }
}






