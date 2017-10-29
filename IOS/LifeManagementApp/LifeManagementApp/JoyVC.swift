//
//  JoyVC.swift
//  LifeManagementApp
//
//  Created by Eric Rado on 9/26/17.
//  Copyright © 2017 SeniorProject. All rights reserved.
//

import UIKit
import Firebase

extension String {
    
    //Enables replacement of the character at a specified position within a string
    func replace(_ index: Int, _ newChar: Character) -> String {
        var chars = Array(characters)
        chars[index] = newChar
        let modifiedString = String(chars)
        return modifiedString
    }
}

class JoyVC: UIViewController {

    var onlineUser: User = User()
    var activity1OnDisplay: Activity = Activity()
    var activity2OnDisplay: Activity = Activity()
    var sideMenuVC: SideMenuVC!
    
    @IBOutlet weak var goalScore1: UILabel!
    @IBOutlet weak var goalScore2: UILabel!
    @IBOutlet weak var currentScore1: UILabel!
    @IBOutlet weak var currentScore2: UILabel!
    @IBOutlet weak var joyActivity1Img: UIImageView!
    @IBOutlet weak var joyActivity2Img: UIImageView!
    
    @IBOutlet weak var goalPercentage1: UILabel!
    @IBOutlet weak var goalPercentage2: UILabel!
    
    @IBOutlet weak var startingDate: UILabel!
    @IBOutlet weak var endingDate: UILabel!
    
    
    @IBOutlet var dayBtns: [UIButton]!
    
    @IBOutlet var dayBottomBtns: [UIButton]!
    
    @IBOutlet weak var menuBtn: UIBarButtonItem!
    
    @IBOutlet weak var joyScore: KDCircularProgress!
    @IBOutlet weak var joyScoreLabel: UILabel!
    
    
    var dbref = Database.database().reference(fromURL: "https://life-management-f0cdf.firebaseio.com/")
    
    var delegate = UIApplication.shared.delegate as! AppDelegate
    
    var userCategory: Category = Category()
    var userCategoryKey: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.layoutIfNeeded()
        
        self.joyScore.progressThickness = 0.5
        
        // turn label and images to circles
        
        turnLabelToCircle(label: goalScore1)
        turnLabelToCircle(label: goalScore2)
        
        turnLabelToCircle(label: goalPercentage1)
        turnLabelToCircle(label: goalPercentage2)
        
        turnLabelToCircle(label: currentScore1)
        turnLabelToCircle(label: currentScore2)
        
        sideMenuVC = self.storyboard?.instantiateViewController(withIdentifier: "SideMenuVC") as! SideMenuVC
        
        let swipeRight = UISwipeGestureRecognizer(target: self, action: #selector(self.respondToGesture))
        swipeRight.direction = UISwipeGestureRecognizerDirection.right
        
        let swipeLeft = UISwipeGestureRecognizer(target: self, action: #selector(self.respondToGesture))
        swipeLeft.direction = UISwipeGestureRecognizerDirection.left
        
        self.view.addGestureRecognizer(swipeRight)
        self.view.addGestureRecognizer(swipeLeft)
        
        queryByStartingDate()
        
        print("JOY \(self.delegate.user.id)")
        getUserCategory(userId: self.delegate.user.id)
    
    }
    
    func turnLabelToCircle(label: UILabel){
        label.layer.cornerRadius = label.frame.size.width / 2
        label.clipsToBounds = true
    }
    
    
    func turnImageToCircle(picture: UIImageView){
        picture.layer.cornerRadius = picture.frame.size.width / 2
        picture.clipsToBounds = true
        self.view.layoutIfNeeded()
    }
    
    func queryByStartingDate(){
        let categoryRef = dbref.child("Categories")
        let userCategoryQuery = categoryRef.queryOrdered(byChild: "startingDate").queryEqual(toValue: self.delegate.user.id)
        print("INSIDE QUERY BY STARTING DATE...")
        userCategoryQuery.observeSingleEvent(of: .value, with: {(snapshot) in
            for child in snapshot.children.allObjects as! [DataSnapshot]{
                if !child.exists(){
                    print("Snapshot is empty")
                    return
                }
                print("STARTING DATE QUERY...")
                print(child)
            }
        })
    }
    
    func getUserCategory(userId: String){
        let categoryRef = dbref.child("Categories")
        
        /* query the category collection and find the record which
           contains the current online user's id */
        let userCategoryQuery = categoryRef.queryOrdered(byChild: "userId")
            .queryEqual(toValue: userId)
        
        /* store the key of the user category collection in order to
           make a reference to joy, contribution and passion sprints */
        userCategoryQuery.observeSingleEvent(of: .value, with: {(snapshot) in
            for child in snapshot.children.allObjects as! [DataSnapshot]{
                if !child.exists(){
                    print("Snapshot is empty")
                    return
                }
                self.userCategoryKey = child.key
                let joySprintSnapShot = child.childSnapshot(forPath:"JoySprints/")
              
                print(joySprintSnapShot)
                
                self.storeSprints(snapshot: joySprintSnapShot, categoryName: "Joy")

                print(self.userCategory.joySprints)
                let activityId1 = self.userCategory.joySprints[0].sprintActivityId1
                let activityId2 = self.userCategory.joySprints[0].sprintActivityId2
                self.getActivities(id1: activityId1, id2: activityId2)

                //self.reloadInputViews()

                self.setDates()
                
            }
            
        }, withCancel: {
            (error) in print(error.localizedDescription)
        })
    }
    
    func storeSprints(snapshot: DataSnapshot, categoryName: String){
        for child in snapshot.children.allObjects as! [DataSnapshot]{
            // check if snapshot is empty before adding it to category arrays
            if !child.exists(){
               print("Snapshot is empty...")
                return
            }
            // store the data from the snapshot to a sprint object
            let sprintDict = child.value as? [String: Any]
            let newSprint = parseSprintDictionary(dict: sprintDict!)
            // store the sprint to its specific Sprint array 
            if(categoryName == "Joy"){
                self.userCategory.joySprints.append(newSprint)
            }else if(categoryName == "Passion"){
                self.userCategory.passionSprints.append(newSprint)
            }else{
                self.userCategory.contributionSprints.append(newSprint)
            }
        
        }
    }
    
    func getActivities(id1: String, id2: String){
      
        //set two referencece points to each activity that will be displayed on dashboard
        let activity1Ref = Database.database().reference(withPath: "Activities/\(id1)")
        let activity2Ref = Database.database().reference(withPath: "Activities/\(id2)")
        
        // get activity 1 values from the database
        activity1Ref.observe(.value, with: {(snapshot) in
            if !snapshot.exists(){
                print("Snapshot is empty...")
                return
            }
            
            let activityDict = snapshot.value as? [String:Any]
            self.activity1OnDisplay = self.parseActivityDictionary(dict: activityDict!)
            
            self.setScoresAndPercentages()
            self.setActivityImg(activityName: self.activity1OnDisplay.name, option: "1")
            self.setCalendar(btnArray: self.dayBtns, dailyPointsStr: self.activity1OnDisplay.sprintDailyPoints)
        })
        
        // get activity 2 values from the database
        activity2Ref.observe(.value, with: {(snapshot) in
            if !snapshot.exists(){
                print("Snapshot is empty...")
                return
            }
            let activityDict = snapshot.value as? [String:Any]
            self.activity2OnDisplay = self.parseActivityDictionary(dict: activityDict!)
            
            self.setScoresAndPercentages()
            self.setActivityImg(activityName: self.activity2OnDisplay.name, option: "2")
            self.setCalendar(btnArray: self.dayBottomBtns, dailyPointsStr: self.activity2OnDisplay.sprintDailyPoints)
        })
        
    }
    
    func parseSprintDictionary(dict: [String: Any]) -> Sprint {
        let endingDate = dict["endingDate"] as? String
        let startingDate = dict["startingDate"] as? String
        let numberOfWeeks = dict["numberOfWeeks"] as? String
        let sprintActivityId1 = dict["sprintActivityId1"] as? String
        let sprintActivityId2 = dict["sprintActivityId2"] as? String
        let sprintOverallScore = dict["sprintOverallScore"] as? String
        let goal1 = dict["goal1"] as? String
        let goal2 = dict["goal2"] as? String
        let goal3 = dict["goal3"] as? String
        let goal4 = dict["goal4"] as? String
        let categoryId = dict["categoryId"] as? String
        
        let sprint = Sprint(numberOfWeeks: numberOfWeeks!, sprintOverallScore: sprintOverallScore!, startingDate: startingDate!, endingDate: endingDate!, sprintActivityId1: sprintActivityId1!, sprintActivityId2: sprintActivityId2!,goal1: goal1!, goal2: goal2!, goal3: goal3!, goal4: goal4!, categoryId: categoryId!)
        
        return sprint
    }
    
    func parseActivityDictionary(dict: [String:Any]) -> Activity{
        let name = dict["name"] as? String
        let activityScore = dict["activityScore"] as? String
        let targetPoints = dict["targetPoints"] as? String
        let actualPoints = dict["actualPoints"] as? String
        let categoryId = dict["categoryId"] as? String
        let userId = dict["userId"] as? String
        let sprintDailyPoints = dict["sprintDailyPoints"] as? String
        
        let activity = Activity(name:name!, activityScore: activityScore!,  actualPoints:actualPoints!,targetPoints: targetPoints!,sprintDailyPoints: sprintDailyPoints!, categoryId: categoryId!,userId: userId!)
        
        return activity
    }
    
    func setScoresAndPercentages(){
        self.goalScore1?.text = self.activity1OnDisplay.targetPoints
        self.goalScore2?.text = self.activity2OnDisplay.targetPoints
        
        self.currentScore1?.text = self.activity1OnDisplay.actualPoints
        self.currentScore2?.text = self.activity2OnDisplay.actualPoints
        
        var goalP1:Double?
        var goalP2:Double?
        
        // set goal percentage actual/target for activity 1
        // use if let to safely unwrap values in case any are nils
        if let actual1 = Double(self.activity1OnDisplay.actualPoints), let target1 =
            Double(self.activity1OnDisplay.targetPoints){
            goalP1 = (actual1 / target1) * 100
            let goalP1Int = Int(round(goalP1!))
            self.goalPercentage1?.text = "\(String(goalP1Int))%"
        }else{return}
        
        // set goal percentage actual/target for activity 2
        if let actual2 = Double(self.activity2OnDisplay.actualPoints), let target2 =
            Double(self.activity2OnDisplay.targetPoints){
            goalP2 = (actual2 / target2) * 100
            let goalP2Int = Int(round(goalP2!))
            self.goalPercentage2?.text = "\(String(goalP2Int))%"
        }else{return}
        
        // find the average score of both joy activies by taking their 
        // percentage score for each activity and dividing by 2
        if let p1 = goalP1, let p2 = goalP2{
            let joyAvg = ((p1 + p2)/2.0)
            let joyAvgInt = Int(round(joyAvg * 3.6))
            self.joyScore.angle = Double(joyAvgInt)
            self.joyScoreLabel?.text = String(format:"%.01f%"+"%", joyAvg)
            
        }else{return}

    }
    
    func setDates(){
        
        let dateFmt = DateFormatter()
        
        // convert date strings to date objects
        dateFmt.dateFormat = "MMddyyyy"
        let startDate = dateFmt.date(from: self.userCategory.joySprints[0].startingDate)
        let endDate = dateFmt.date(from: self.userCategory.joySprints[0].endingDate)
        
        // format dates to MM/dd/yyyy
        dateFmt.dateFormat = "MM/dd/yyyy"
        let startDateStr = dateFmt.string(from: startDate!)
        let endDateStr = dateFmt.string(from: endDate!)
        
        // assign new date strings to their respective labels
        startingDate?.text = startDateStr
        endingDate?.text = endDateStr
    }
    
    func setCalendar(btnArray: [UIButton],dailyPointsStr: String){
        let dateFmt = DateFormatter()
        
        // convert date strings to date objects
        dateFmt.dateFormat = "MMddyyyy"
        let startDate = dateFmt.date(from: self.userCategory.joySprints[0].startingDate)
        
        // determine the day of the week such as Wenesday = 3 or Friday = 5
        // the day of the week is substracted by 1 because the button tags start at index 0
        let dayOfTheWeek = Int(Calendar.current.component(.weekday, from: startDate!)) - 1
        
        // extract the day the sprint starts from starting date
        var startDay = Calendar.current.component(.day, from: startDate!)
        
        // determine the last day of the sprint from the number of weeks input by the user
        let dayCountInWeekChoice = (Int(self.userCategory.joySprints[0].numberOfWeeks)! * 7) - 1
        var dayCounter = 0
        
        // store the indexes of all the day buttons that are displayed on the calendar
        var btnIndexes = [Int]()
        
        // setup the calendar display
        for button in btnArray{
            if button.tag < dayOfTheWeek{
                button.isHidden = true
            }
            if (dayOfTheWeek <= button.tag) &&  (dayCounter <= dayCountInWeekChoice){
                button.setTitle(String(startDay), for: .normal)
                btnIndexes.append(button.tag)
                startDay = startDay + 1
                dayCounter = dayCounter + 1
            }else{
                button.isHidden = true
            }
        }

        var counter = 0
        
        // setup the sprint daily points
        for index in dailyPointsStr.characters.indices{
            if dailyPointsStr[index] == "1"{
                // get the index of the button on display
                let btnIndex = btnIndexes[counter]
                btnArray[btnIndex].backgroundColor = UIColor.green
            }
            counter = counter + 1
        }
    }
    
    
    func setActivityImg(activityName: String, option: String){
        let imgUrlDBref = Database.database().reference(withPath: "ActivityImgs/JoyActivities/")
        let imgUrlQuery = imgUrlDBref.queryOrdered(byChild: "name")
            .queryEqual(toValue: activityName)
        
        // get url for the activity image from DataSnapshot
        imgUrlQuery.observeSingleEvent(of: .value, with: {(snapshot) in
            for child in snapshot.children.allObjects as! [DataSnapshot]{
                var dict = child.value as? [String: Any]
                if let url = dict?["url"] as? String{
                    let imgRef = storage.reference(forURL: url)
                    imgRef.getData(maxSize: 1 * 1024 * 1024, completion: {data,error in
                        if let error = error {
                            print(error.localizedDescription)
                            return
                        }else{
                            if option == "1"{
                                self.joyActivity1Img.image = UIImage(data:data!)
                                self.turnImageToCircle(picture: self.joyActivity1Img)
                            }else{
                                self.joyActivity2Img.image = UIImage(data:data!)
                                self.turnImageToCircle(picture: self.joyActivity2Img)
                            }
                            
                        }
                    })
                }
            }
        })
    }
    
    func updateActualScoreAndDailyPoints(newScore: String, id: String, dailyPointsIndex: Int, dailyPoints: String){
        // update daily points
        let index = dailyPoints.index(dailyPoints.startIndex, offsetBy: dailyPointsIndex)
        let value = dailyPoints[index]
        var newDailyPoints: String
        print("Daily points index : \(dailyPointsIndex)")
        print(value)
        if value == "1"{
            newDailyPoints = dailyPoints.replace(dailyPointsIndex, "0")
        }else{
            newDailyPoints = dailyPoints.replace(dailyPointsIndex, "1")
        }
        print(newDailyPoints)
        let activityRef = dbref.child("Activities/\(id)")
        activityRef.updateChildValues(["actualPoints": newScore,"sprintDailyPoints": newDailyPoints])
    }
    
    @IBAction func topDayBtnPressed(_ sender: UIButton) {
        var newScore: Int
        
        // convert date strings to date objects
        let dateFmt = DateFormatter()
        dateFmt.dateFormat = "MMddyyyy"
        let startDate = dateFmt.date(from: self.userCategory.joySprints[0].startingDate)
        let startDay = Calendar.current.component(.day, from: startDate!)
        
        // store the index that will be changed in sprint daily points
        let index = Int(sender.title(for: .normal)!)! - startDay
        
        
        if sender.backgroundColor == UIColor.green{
            // the score has decreased
            newScore = Int(self.activity1OnDisplay.actualPoints)! - 1
            sender.backgroundColor = UIColor.lightGray
        }else{
            // the score has increased
            newScore = Int(self.activity1OnDisplay.actualPoints)! + 1
            sender.backgroundColor = UIColor.green
        }
      
        updateActualScoreAndDailyPoints(newScore: String(newScore), id: self.userCategory.joySprints[0].sprintActivityId1, dailyPointsIndex: index, dailyPoints: self.activity1OnDisplay.sprintDailyPoints)
    }
    
    @IBAction func bottomDayBtnPressed(_ sender: UIButton) {
        var newScore: Int
        
        // convert date strings to date objects
        let dateFmt = DateFormatter()
        dateFmt.dateFormat = "MMddyyyy"
        let startDate = dateFmt.date(from: self.userCategory.joySprints[0].startingDate)
        let startDay = Calendar.current.component(.day, from: startDate!)
        
        // store the index that will be changed in sprint daily points
        let index = Int(sender.title(for: .normal)!)! - startDay
        
        if sender.backgroundColor == UIColor.green{
            // the score has decreased
            newScore = Int(self.activity2OnDisplay.actualPoints)! - 1
            sender.backgroundColor = UIColor.lightGray
        }else{
            // the score has increased
            newScore = Int(self.activity2OnDisplay.actualPoints)! + 1
            sender.backgroundColor = UIColor.green
        }
        updateActualScoreAndDailyPoints(newScore: String(newScore), id: self.userCategory.joySprints[0].sprintActivityId2, dailyPointsIndex: index, dailyPoints: self.activity2OnDisplay.sprintDailyPoints)
    }


    /***********************************************************
 
                        Side Menu Functions
 
    ***********************************************************/
    
    func respondToGesture(gesture: UISwipeGestureRecognizer){
        
        switch gesture.direction{
        case UISwipeGestureRecognizerDirection.right:
            print("right")
            
            // show menu
            showSideMenu()
            
        case UISwipeGestureRecognizerDirection.left:
            print("left")
            
            // close menu
            hideSideMenu()
            
        default:
            break
        }
    }
    
    
    @IBAction func sideMenuPressed(_ sender: UIBarButtonItem) {
        if AppDelegate.menu_bool{
            // show menu
            showSideMenu()
        }else{
            // close menu
            hideSideMenu()
        }
    
    }
    
    func showSideMenu(){
        UIView.animate(withDuration: 0.3) { ()-> Void in
            self.sideMenuVC.view.frame = CGRect(x: 0, y: 0, width: UIScreen.main.bounds.size.width, height: UIScreen.main.bounds.size.height)
            self.addChildViewController(self.sideMenuVC)
            self.view.addSubview(self.sideMenuVC.view)
            AppDelegate.menu_bool = false
        }
    }
    
    func hideSideMenu(){
        UIView.animate(withDuration: 0.3, animations: { () -> Void in
            self.sideMenuVC.view.frame = CGRect(x: -UIScreen.main.bounds.size.width, y: 0, width: UIScreen.main.bounds.size.width, height: UIScreen.main.bounds.size.height)
        }) { (finished) in
            self.sideMenuVC.view.removeFromSuperview()
        }
       
        AppDelegate.menu_bool = true
    }

}
