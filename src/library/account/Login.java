package library.account;

/**
 * The LoginManager class handles everything involved in Login for the system.
 * 
 * @author Benny Pena
 * @version 1.2
 */
public class Login {
	
	private String username, password, userID;
	
	/**
	 * Constructor for Account Login entry
	 * 
	 * @param userN Username associated with the account.
	 * @param userP Password associated with the account.
	 * @param userI Account ID.
	 */
	public Login(String userN, String userP, String userI){
		this.username = userN;
		this.password = userP;
		this.userID = userI;
	}
	
	/** Return the username */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username.
	 * 
	 * @param username New username for the account.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/** Return the password */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 * 
	 * @param password New password for the account.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * MAYBE NOT USE
	 * 
	 * Gain access to a Patron account with the username and password.
	 * If both are found, return back the associated account.
	 *  
	 * @param userName Username to lookup.
	 * @param password Password to lookup.
	 * @return Patron Account
	 */
	public PatronAccount loginPatron(String userName, String password) {
		//Returns Key
		//JDBC findID(Username,password)
		return null;
	}
	
	/**
	 * MAYBE NOT USE.  APPLICATION LAYER.
	 * 
	 * Gain access to a Staff account with the username and password.
	 * If both are found, return back the associated account.
	 *  
	 * @param userName Username to lookup.
	 * @param password Password to lookup.
	 * @return Staff Account
	 */
	public static StaffAccount loginStaff(String userName, String password) {
		
		
		
		return null;
	}
	
	/**
	 * Logout of loaded account.
	 * 
	 * @param a Account object.
	 * @return 
	 */
	public boolean logout(Account a){
		//show Login
		return true;
	}
	
	/**
	 * Resets password to a randomly generated password for Patron Account.
	 * It will be emailed to the account on file.
	 * 
	 * @param username The username associated with the account.
	 * @return true if change was successful
	 */
	public boolean recoverPassword(String username){
		//update table for user with random password
		//use id for confirmation
		//or email random password
		return true;
	}
	
	/**
	 * Compares account be accessed to account logged in to verify access.
	 * 
	 * @param a Account logged in.
	 * @param b Account being accessed.
	 * @return true if access level is granted. 
	 */
	public boolean verifyAccess(Account a, Account b){
		// can it change info on account b?
		return true;
	}
        public String getId(){
            return userID;
        }
	
        @Override
	public String toString(){
		return "\n=======================\nUsername: "+username +"\nPassword: "+password+"\n" +"ID: " + userID+"\n=======================\n";
	}
		
}
