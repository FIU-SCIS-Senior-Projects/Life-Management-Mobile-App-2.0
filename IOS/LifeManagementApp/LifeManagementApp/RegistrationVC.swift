//
//  RegistrationVC.swift
//  LifeManagementApp
//
//  Created by Eric Rado on 9/17/17.
//  Copyright © 2017 SeniorProject. All rights reserved.
//

import UIKit
import Firebase
import Foundation


class RegistrationVC: UIViewController {

    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var firstNameTextField: UITextField!
    @IBOutlet weak var lastNameTextField: UITextField!
    @IBOutlet weak var dobTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var reTypePasswordTextField: UITextField!
    
    @IBOutlet weak var registerButton: UIButton!
    
    
    var usernameList = [String]()
    var emailList = [String]()
    var check:Bool = false
    var onlineUser:User = User()
    let delegate = UIApplication.shared.delegate as! AppDelegate
    
    let dbRef = Database.database().reference(fromURL: "https://life-management-f0cdf.firebaseio.com/")
    
    override func viewDidLoad() {
        super.viewDidLoad()
        getAllUsers()
        self.usernameTextField.attributedPlaceholder = NSAttributedString(string: "Username",
                                attributes:[NSForegroundColorAttributeName: UIColor.white])
        self.emailTextField.attributedPlaceholder = NSAttributedString(string: "Email",
                                attributes:[NSForegroundColorAttributeName: UIColor.white])
        self.firstNameTextField.attributedPlaceholder = NSAttributedString(string: "First Name",
                                attributes:[NSForegroundColorAttributeName: UIColor.white])
        self.lastNameTextField.attributedPlaceholder = NSAttributedString(string: "Last Name",
                                attributes:[NSForegroundColorAttributeName: UIColor.white])
        self.dobTextField.attributedPlaceholder = NSAttributedString(string: "Dob",
                                attributes:[NSForegroundColorAttributeName: UIColor.white])
        self.passwordTextField.attributedPlaceholder = NSAttributedString(string: "Password",
                                attributes:[NSForegroundColorAttributeName: UIColor.white])
        self.reTypePasswordTextField.attributedPlaceholder = NSAttributedString(string: "Confirm Password",
                                attributes:[NSForegroundColorAttributeName: UIColor.white])
        
        self.registerButton.layer.cornerRadius = 15
        self.registerButton.layer.masksToBounds = true
    }
    
    override func viewDidLayoutSubviews() {
        let lineColor = UIColor(red: 0.12, green: 0.23, blue: 0.35, alpha: 0.85)
        
        self.usernameTextField.setBottomLine(borderColor: lineColor)
        self.emailTextField.setBottomLine(borderColor: lineColor)
        self.firstNameTextField.setBottomLine(borderColor: lineColor)
        self.lastNameTextField.setBottomLine(borderColor: lineColor)
        self.dobTextField.setBottomLine(borderColor: lineColor)
        self.passwordTextField.setBottomLine(borderColor: lineColor)
        self.reTypePasswordTextField.setBottomLine(borderColor: lineColor)
        
    }
    
    func getAllUsers(){
        let userRef = dbRef.child("Users")
        userRef.observe(DataEventType.value, with: {(snapshot) in
            
            for user in snapshot.children.allObjects as! [DataSnapshot]{
                guard let userDict = user.value as? [String:Any] else {continue}
                self.usernameList.append((userDict["username"] as? String)!)
                self.emailList.append((userDict["email"] as? String)!)
               
            }
        })
    }

   
    func handleRegister() -> Bool{
        
        let childRef = dbRef.child("Users").childByAutoId()
        
        // check for empty fields
        guard let username = usernameTextField.text else{
            print("Please enter a username")
            return false
        }
        
        guard let email = emailTextField.text else{
            print("Please enter an email")
            return false
        }
        
        guard let password = passwordTextField.text else{
            print("Please enter a password.")
            return false
        }
        
        guard let reTypePassword = reTypePasswordTextField.text else{
            print("Please confirm your password.")
            return false
        }
        
        let firstName = firstNameTextField.text
        let lastName = lastNameTextField.text
        let dob = dobTextField.text
        
        // check if username input is valid
        if !checkUsername(username: username){
            return false
        }
        
        // check if email input is valid
        if !checkEmail(email: email){
            print("Email is invalid")
            return false
        }
        
        print("confirm password check ...")

        // check if password input is valid
        if !checkPassword(password:password, reTypePassword:reTypePassword){
            
            return false
        }
    
        // check if first name input is valid
        if !checkName(name: firstName!, option: "first"){
            
            return false
        }
        print("last name check...")
        // check if last name input is valid
        if !checkName(name:lastName!, option:"last"){
            
            return false
        }
        print("before the key...")
        let uid = childRef.key
        print("the key" + uid)
        onlineUser = User(id: uid ,email: email, username: username,firstName: firstName!, lastName: lastName!,
                        dob: dob!, password: password, adminFlag: false, coachFlag: false)
        
        childRef.setValue(onlineUser.toAnyObject(), withCompletionBlock: {(error, ref) in
            if error != nil{
                print(error!)
                return
            }
            

            print("Successfully saved a user into database.")
        })
        return true
    }
    
    // username validation
    func checkUsername(username:String) -> Bool{
        var check = true
        
        // check the length of username
        if username.characters.count < 5{
            print("Username is too short")
            check = false
            return false
        }
        
        if username.characters.count >= 15{
            print("Username is too long")
            check = false
            return false
        }
        
        // check if username contains space
        if containsSpace(word: username){
            print("Username CANNOT contain any spaces")
            check = false
            return false
        }
        
        // check if username contains any symbols
        if containsSymbol(word: username){
            print("Username CANNOT contain any symbols")
            check = false
            return false
        }
        
        // check list of username already in use for availability
        for uname in self.usernameList{
            if uname == username{
                print("This username has already been taken")
                check = false
                return false
            }
        }
        return check
    }
    
    // password validation
    func checkPassword(password:String, reTypePassword:String) -> Bool{
        var check = true
        
        // check the length of password
        if password.characters.count >= 16{
            print("Password is too long")
            check = false
            return check
        }
        
        if password.characters.count < 5{
            print("Password is too short")
            check = false
            return check
        }
        
        // check if password contains spaces
        if containsSpace(word: password){
            print("Password CANNOT contain spaces")
            check = false
            return check
        }
        
        // check if password contains any symbols
        if containsSymbol(word: password){
            print("Password CANNOT contain any symbols")
            check = false
            return check
        }
        
        // check if first character of the password is a number
        if isNumber(char:String(password[password.startIndex])){
            print("Password cannot start with numbers")
            check = false
            return check
        }
        
        // check if password contains a combination of letters and numbers
        // create a characterset with letters and add numbers to it

        if !(isNumber(char: password) && containsLetter(word: password)){
            print("Password MUST contain letters and numbers")
            check = false
            return check
        }
        
        // check if password and confirmed password are the same
        if password != reTypePassword{
            print("Password MUST match the confirm password")
            check = false
            return false
        }
        
        return check
    }
    
    // email validation
    func checkEmail(email: String) -> Bool{
        var check = true
        let emailRegEx = "[A-Z0-9a-z.-_]+@[A-Z0-9a-z.-]+\\.[A-Za-z]{2,3}"
        
        do{
            let regEx = try NSRegularExpression(pattern: emailRegEx)
            let nsString = email as NSString
            let results = regEx.matches(in: email, range: NSRange(location: 0, length: nsString.length))
            
            if results.count == 0{
                check = false
            }
        } catch let error as NSError{
            print("Invalid regex: \(error.localizedDescription)")
            check = false
        }
        
        return check
    }
    
    // first and last name validation
    func checkName(name: String, option: String) -> Bool{
        var check = true
        
        // check length of first name
        if name.characters.count >= 15{
            if option == "first"{
                print("First name is too long")
            }else{
                print("Last name is too long")
            }
            check = false
            return check
        }
        
        // check if first name contains spaces
        if containsSpace(word: name){
            if option == "first"{
                print("First name CANNOT contain spaces")
            }else{
                print("Last name CANNOT contain spaces")
            }
            check = false
            return check
        }
        
        // check if first name contains numbers or symbols
        if isNumber(char: name) || containsSymbol(word: name){
            if option == "first"{
                print("First name CANNOT contain numbers or symbols")
            }else{
                print("Last name CANNOT contain numbers or symbols")
            }
            check = false
            return check
        }
        
        return check
    }
    
    // checks if string contains a space
    func containsSpace(word: String) -> Bool {
        let whitespace = NSCharacterSet.whitespaces
        
        // range will be nil if no whitespace is found
        if word.rangeOfCharacter(from: whitespace) != nil{
            return true
        }
        return false
    }
    
    // checks if string contains a number
    func isNumber(char: String) -> Bool {
        let numbers = NSMutableCharacterSet()
        numbers.addCharacters(in: "0123456789")
        
        if char.rangeOfCharacter(from: numbers as CharacterSet) != nil{
            return true
        }
        return false
    }
    
    // checks if string contains a letter
    func containsLetter(word: String) -> Bool{
        do{
            let regex = try NSRegularExpression(pattern: "[a-zA-Z]")
            let nsString = word as NSString
            let results = regex.matches(in: word, range: NSRange(location: 0, length: nsString.length))
            if results.count == 0 {
                return false
            }
        
        }catch let error{
            print("Invalid regex: \(error.localizedDescription)")
            return false
        }
        
        return true
    }
    
    
    // checks if string contains a symbol
    func containsSymbol(word: String) -> Bool {
        let lettersNumbers = NSCharacterSet.alphanumerics
        
        // range will be nil if no symbols is found
        if word.rangeOfCharacter(from: lettersNumbers.inverted) != nil{
            return true
        }
        return false
    }
    
    @IBAction func registerUser(_ sender: AnyObject) {
        self.check = handleRegister()
        
        if check{
            performSegue(withIdentifier: "ActivitySelectionSegue", sender: self)
        }
    }
    
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        // check if handleRegister form was valid before performing segue to Activity Selection
        if check{
            return true
        }
        return false
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "ActivitySelectionSegue", let joySelection = segue.destination as? JoyActivitySelectionVC{
            print("Transitioning to joy selection activity")
            
            joySelection.onlineUser = self.onlineUser
            self.delegate.user = self.onlineUser
            
        }
    }
    
}
