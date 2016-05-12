package library.account;


import java.util.Scanner;
import library.account.Account;


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
    PatronAccount(String first ,String last, String phone, String address, String email, double balance){
    	super(first, last, phone);
        this.address = address;
        this.accountBalance = balance;
        this.email = email;
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
        
        AccountJDBC a1 = new AccountJDBC();
        
        a1.connect();
        boolean result = AccountCollection.insertPatron(p1);
        
        if(!result)
            p1 = null;
        
        return p1;
    }

}
