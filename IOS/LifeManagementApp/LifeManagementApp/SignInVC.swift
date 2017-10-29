//
//  SignInVC.swift
//  LifeManagementApp
//
//  Created by Eric Rado on 9/18/17.
//  Copyright © 2017 SeniorProject. All rights reserved.
//

import UIKit
import Firebase

extension UITextField{
    func setBottomLine(borderColor: UIColor) {
        
        self.borderStyle = UITextBorderStyle.none
        self.backgroundColor = UIColor.clear
        
        let borderLine = UIView()
        let height = 1.0
        borderLine.frame = CGRect(x: 0, y: Double(self.frame.height) - height,
                                  width: Double(self.frame.width),height: height)
        
        borderLine.backgroundColor = borderColor
        self.addSubview(borderLine)
    }
}


class SignInVC: UIViewController {

    
    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var signInButton: UIButton!
    
    let delegate = UIApplication.shared.delegate as! AppDelegate
    let dbRef = Database.database().reference(fromURL: "https://life-management-f0cdf.firebaseio.com/")
    var users = [User]()
    var signInUser = User()
    
    
    var username = ""
    var password = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        getAllUsers()
        self.signInButton.layer.cornerRadius = 15
        self.signInButton.layer.masksToBounds = true
        self.usernameTextField.attributedPlaceholder = NSAttributedString(string: "Username",
                                attributes: [NSForegroundColorAttributeName:UIColor.black])
        self.passwordTextField.attributedPlaceholder = NSAttributedString(string: "Password",
                                attributes: [NSForegroundColorAttributeName:UIColor.black])

    }
    
    override func viewDidLayoutSubviews() {
        let lineColor = UIColor(red:0.12, green:0.23, blue:0.35, alpha:0.8)
        self.usernameTextField.setBottomLine(borderColor: lineColor)
        self.passwordTextField.setBottomLine(borderColor: lineColor)
    }
    
    func getAllUsers(){
        let userRef = dbRef.child("Users")
    
        userRef.observe(DataEventType.value, with: {(snapshot) in
             
            for user in snapshot.children.allObjects as! [DataSnapshot]{
                print("Testing...")
                // store the data of the user into a dictionary
                guard let userDict = user.value as? [String: Any] else {continue}
                
                // store all of the user properties into variables
                let username = userDict["username"] as? String
                let email = userDict["email"] as? String
                let firstName = userDict["firstName"] as? String
                let lastName = userDict["lastName"] as? String
                let dob = userDict["dob"] as? String
                let password = userDict["password"] as? String
                let isAdmin = userDict["adminFlag"] as? Bool
                let isCoach = userDict["coachFlag"] as? Bool
                let id = userDict["id"] as? String
                
                // create user with variables previously created
                let addUser = User(id: id!,email: email!,username: username!,firstName: firstName!,
                                   lastName: lastName!, dob: dob!, password: password!, adminFlag: isAdmin!,coachFlag: isCoach!)
                print("this is FIRSTNAME " + addUser.firstName + " | " )
                print("this is id " + id! + " | ")
                self.users.append(addUser)
                
            }
            
            
            }, withCancel: {
                (error) in print(error.localizedDescription)
        })
    }
    
    func checkCredentials(username: String, password: String) -> Bool{
        for user in users{
            if user.username == username{
                if user.password != password{
                    print("Password entered is incorrect")
                    return false
                }
                
                /* Assign all instance variables of user with correct credentials to signInUser
                   to pass this object to the dashBoard controller
                */
                self.signInUser.id = user.id
                self.signInUser.username = user.username
                self.signInUser.email = user.email
                self.signInUser.firstName = user.firstName
                self.signInUser.lastName = user.lastName
                self.signInUser.password = user.password
                self.signInUser.dob = user.dob
                
                return true
            }
        }
        print("User entered does not exist.")
        return false
    }
    
    
    @IBAction func signInUser(_ sender: AnyObject) {
        if !((usernameTextField.text?.isEmpty)!){
            self.username = usernameTextField.text! as String
        }else{
            print("Username is blank")
            return
        }
        
        if !((passwordTextField.text?.isEmpty)!){
            self.password = passwordTextField.text! as String
        }else{
            print("Password is blank")
            return
        }
        
        
        let credentialCheck:Bool = checkCredentials(username: self.username, password: self.password)
        
        if (credentialCheck){
            print("Welcome to Life Management " + self.signInUser.username )
            
            // pass signInUser object to dashBoardVC
            performSegue(withIdentifier: "DashBoardSegue", sender: self)
            
        }else{
            print("User is invalid")
        }
    
        
    }
    
    @IBAction func createAccount(_ sender: AnyObject) {
        performSegue(withIdentifier: "RegistrationSegue", sender: self)
    }
    
    
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        if identifier == "RegistrationSegue"{
            return true
        }
        if self.signInUser.username != ""{
            return true
        }
        return false
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        print("SEGUE DESTINATION...")
        print(segue.destination)
        
        // passing user who successfully logged in to dashboard tab view controller
        if segue.identifier == "DashBoardSegue", let tabVC = segue.destination as? CategoryTabBarController{
            print("GOING TO THE Tab BAR CONTROLLER")
            tabVC.onlineUser = self.signInUser
            self.delegate.user = self.signInUser
            
        }
        
    }


}
