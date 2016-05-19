package library.account;


import java.text.NumberFormat;
import java.util.Scanner;
import library.jdbc.AccountJDBC;


/**
 * PatronAccount is a subclass of Account that implements a patron account.
 * 
 * TEST FOR GIT SYNC WITH ****
 * 
 * @author Benny Pena
 * @author Jeffrey Godoy
 * @author Junaid Farooq 
 * @version 1.1
 */
public class PatronAccount extends Account {
 
    private String email, address;
    private double accountBalance;
    
    public final NumberFormat US_DOLLAR = NumberFormat.getCurrencyInstance();
 
    /**
     * Constructor: Basic Parameters
     * 
     * @param first	Patron's First name
     * @param last	Patron's Last name
     * @param address	Patron's Address
     * @param phone     Patron's Phone number
     */
    public PatronAccount(String first, String last, String address, String phone)
    {
        super(first, last, phone);
        this.address = address;
        this.accountBalance = 0.0;
        this.email = null;
    }
    
    /**
     * Overloaded Constructor: Basic Parameters + Email Address
     * 
     * @param first     Patron's First Name
     * @param last      Patron's Last Name
     * @param phone     Patron's Phone Number
     * @param email     Patron's Email Address
     * @param address   Patron's Street Address
     */
    public PatronAccount(String first, String last, String phone, String address, String email){
    	super(first, last, phone);
        this.address = address;
        this.email = email;
    }
    
    /**
     * Overloaded Constructor:  Basic Parameters + Email + Account Balance
     * 
     * @param first	Patron's First Name 
     * @param last	Patron's Last Name
     * @param phone	Patron's Phone Number
     * @param email	Patron's Email Address
     * @param address 	Patron's Street Address
     * @param balance 	Sets the starting balance for account
     */ 
    public PatronAccount(String first ,String last, String phone, String email, String address, double balance){
    	super(first, last, phone);
        this.email = email;
        this.address = address;
        this.accountBalance = balance;
    }
    
    public PatronAccount(String myID, String first ,String last, String phone, String email, String address, double balance){
    	super(myID, first, last, phone);
        this.email = email;
        this.address = address;
        this.accountBalance = balance;
    }
    
    public PatronAccount(PatronAccount p1){
    	super(p1.getId(), p1.getFirstName(), p1.getLastName(), p1.getPhoneNumber());
        this.email = p1.getEmail();
        this.address = p1.getAddress();
        this.accountBalance = p1.getAccountBalance();
    }
    
    /**
     * Returns the First Name associated with the account.
     * 
     * @return First Name as String.
     */
    public String getFirstName() {
        return super.getFirstName();
    }
    
    /**
     * Sets the First Name of the account.
     * 
     * @param first First Name as String.
     */
    public void setFirstName(String first) {
        super.setFirstName(first);
    }
    
    /**
     * Returns the Last Name associated with the account.
     * 
     * @return Last Name as String.
     */
    public String getLastName() {
        return super.getLastName();
    }
    
    /**
     * Sets the Last Name of the account.
     * 
     * @param last Last Name as String.
     */
    public void setLastName(String last) {
        super.setLastName(last);
    }
    
    public String getPhoneNumber(){
        return super.getPhoneNumber();
    }
    public void setPhoneNumber(String phone){
        super.setPhoneNumber(phone);
    }
    
    /** 
     * email address associated with the account.
     *
     * @return Email address as String.
     */
    public String getEmail() {
	return email;
    }
    
    /**
     * Sets the email address associated with the account.
     * 
     * @param email Email address as String.
     */
    public void setEmail(String email) {
	this.email = email;
    }
	
    /**
     * Returns the street address associated with the account.
     * 
     * @return Street Address as String.
     */
    public String getAddress() {
	return address;
    }
    
    /**
     * Sets the account Street Address.
     * 
     * @param address  Street Address as String
     */
    public void setAddress(String address) {
            this.address = address;
    }
    
    
    
    /** 
     * Returns accounts current balance.
     * 
     * @return Account Balance as double.
     */
    public double getAccountBalance() {
            return accountBalance;
    }

    /**
     * Sets the accounts balance.
     * 
     * @param accountBalance Input new balance for account.
     */
    public void setAccountBalance(double accountBalance) {
            this.accountBalance = accountBalance;
    }
    
    /**
     * Returns the account type as a character.
     * 'P' indicates a Patron Account.
     * 
     * @return Account Type as char.
     */
    @Override
    public char getAccountType(){
        return 'P';
    }
    
    //public PatronAccount(String myID, String first ,String last, String phone, String email, String address, double balance){
    @Override
    public String toString(){
        String v1,v2,v3,v4,v5,v6,v7,tPhone;
                
        v1 = getId();
        v2 = getFirstName();
        v3 = getLastName();
        tPhone = getPhoneNumber();
        v5 = getEmail();
        v6 = getAddress();
        v7 = US_DOLLAR.format(getAccountBalance());
        
        
        v4 = "(" + tPhone.substring(0, 3) + ") "
                + tPhone.substring(3, 6)
                + "-" + tPhone.substring(6);
        
        return String.format("ID: %s %n"
                + "First Name: %s %n"
                + "Last Name: %s %n"
                + "Tel. Number: %s %n"
                + "E-mail Address: %s %n"
                + "Street Address: %s %n"
                + "Account Balance: %s %n"
                ,v1,v2,v3,v4,v5,v6,v7);
    }
    
    
    
    /**
     * Take a customer's payment and deduct's it from the account balance.
     * 
     * @param amount Amount of the payment
     * @return Returns a string displaying the new account balance
     */
    public String makePayment(double amount)
    {
    	accountBalance -= amount;
        return "New Balance: " + accountBalance;
    }
    
    /**
     * Adds an amount of money to the account balance.
     * 
     * @param amount	Fee amount
     * @return			returns a string displaying the new account balance
     */
    public String addFee(double amount)
    {
    	accountBalance += amount;
        return "New Balance: " + accountBalance;
    }
    
    public static PatronAccount createPatronAccount(){
        String firstName, lastName, phoneNumber, address, email;
        Scanner scan = new Scanner(System.in);
        
        System.out.print("\nEnter info for new Patron Account: ");
        firstName = Account.enterFirstName();
        lastName = Account.enterLastName();
        phoneNumber = Account.enterPhoneNum();
        address = Account.enterAddress();
        email = Account.enterEmail();
        
        PatronAccount p1 = new PatronAccount(firstName, lastName, phoneNumber, address, email);
        
        //AccountJDBC a1 = new AccountJDBC();
        
        AccountJDBC.connect();
        boolean result = AccountCollection.insertPatron(p1);
        
        if(!result)
            p1 = null;
        
        return p1;
    }
    
    public String updateFirstName(){
        Scanner scan = new Scanner(System.in);
        String newName, prompt1 = "", prompt2 = "";
        int choice;
        boolean correct = false, success = false;
        
        while (!correct){
            
            prompt1 += "Please Enter a New First Name";
            prompt1 += "\nCurrent First Name: " + getFirstName();
            prompt1 += "\nNew First Name: ";
            
            newName = TypeSafe.name(prompt1);
            
            prompt2 += "You entered \"" + newName + "\". Is this correct?"
                    + "\n1. Yes\n2. No\n3. Cancel\n";

            choice = TypeSafe.posInt(prompt2);
            
            switch(choice){
                case 1:
                    success = AccountCollection.updatePatronFname(getId(), newName);
                    correct = true;
                    break;
                case 2:
                    break;
                case 3:
                    correct = true;
                default:
                    System.out.println("Sorry, \"" + choice + "\" is an invalid option.");
                    break;
            }
            
        }
        if(success)
            return "The account was updated successfully!";
        else
            return "The account was not updated";
    }
    
    public String updateLastName(){
        Scanner scan = new Scanner(System.in);
        String newName, prompt1 = "", prompt2 = "";
        int choice;
        boolean correct = false, success = false;
        
        while (!correct){
            
            prompt1 += "Please Enter a New Last Name";
            prompt1 += "\nCurrent Last Name: " + getFirstName();
            prompt1 += "\nNew Last Name: ";
            
            newName = TypeSafe.name(prompt1);
            
            prompt2 += "You entered \"" + newName + "\". Is this correct?"
                    + "\n1. Yes\n2. No\n3. Cancel";

            choice = TypeSafe.posInt(prompt2);
            
            switch(choice){
                case 1:
                    success = AccountCollection.updatePatronLname(getId(), newName);
                    correct = true;
                    break;
                case 2:
                    break;
                case 3:
                    correct = true;
                default:
                    System.out.println("Sorry, \"" + choice + "\" is an invalid option.");
                    break;
            }
            
        }
        if(success)
            return "The account was updated successfully!";
        else
            return "The account was not updated";
    }
    
    public String updateEmail(){
        Scanner scan = new Scanner(System.in);
        String newEmail, prompt1 = "", prompt2 = "";
        int choice;
        boolean correct = false, success = false;
        
        while (!correct){
            
            prompt1 += "Please Enter a New Email Address";
            prompt1 += "\nCurrent Email Address: " + getFirstName();
            prompt1 += "\nNew Email Address: ";
            
            newEmail = TypeSafe.email(prompt1);
            
            prompt2 += "You entered \"" + newEmail + "\". Is this correct?"
                    + "\n1. Yes\n2. No\n3. Cancel";

            choice = TypeSafe.posInt(prompt2);
            
            switch(choice){
                case 1:
                    success = AccountCollection.updatePatronEmail(getId(), newEmail);
                    correct = true;
                    break;
                case 2:
                    break;
                case 3:
                    correct = true;
                default:
                    System.out.println("Sorry, \"" + choice + "\" is an invalid option.");
                    break;
            }
            
        }
        if(success)
            return "The account was updated successfully!";
        else
            return "The account was not updated";
    }
    
    public String updatePhone(){
        Scanner scan = new Scanner(System.in);
        String newPhone, formatPhone, prompt1 = "", prompt2 = "";
        int choice;
        boolean correct = false, success = false;
        
        while (!correct){
            
            prompt1 += "Please Enter a New Phone Number";
            prompt1 += "\nCurrent Phone Number: " + getFirstName();
            prompt1 += "\nNew Phone Number: ";
            
            newPhone = TypeSafe.validatePhone(prompt1);
            
            prompt2 += "You entered \"" + newPhone + "\". Is this correct?"
                    + "\n1. Yes\n2. No\n3. Cancel";

            choice = TypeSafe.posInt(prompt2);
            
            switch(choice){
                case 1:
                    success = AccountCollection.updatePatronPhone(getId(), newPhone.replaceAll("[^0-9]+", ""));
                    correct = true;
                    break;
                case 2:
                    break;
                case 3:
                    correct = true;
                default:
                    System.out.println("Sorry, \"" + choice + "\" is an invalid option.");
                    break;
            }
            
        }
        if(success)
            return "The account was updated successfully!";
        else
            return "The account was not updated";
    }
    
            

}
