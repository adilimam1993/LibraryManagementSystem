package library.account;


import library.jdbc.LoginJDBC;
import library.account.PatronAccount;
import library.account.StaffAccount;


public class LoginCollection {
	
	/**
	 * Value when the an operation is successful
	 */
	public static final boolean SUCCESS = true;
	
	/**
	 * Value when the operation fails
	 */
	public static final boolean FAILED = false;
	
	//TODO make all the methods and any use-case methods
	/**
	 * Searches  the Database for any staff login credentials associated with the id.
	 * @param id Associated Id
	 * @return a login object of the associated id 
	 */
	public static Login searchStaffLogin(String id){
		return LoginJDBC.searchStaffLogin(id);
	}
	/**
	 * Searches  the Database for any patron login credentials associated with the id.
	 * @param id Associated Id
	 * @return a login object of the associated id 
	 */
	public static Login searchPatronLogin(String id){
		return LoginJDBC.searchPatronLogin(id);
	}
	/**
	 * Updates a staff account's login credentials
	 * @param associatedAccount
	 * @param newUser
	 * @param newPass
	 * @return Confirmation of action
	 */
	public static boolean updateStaffLogin(String id, String newUser, String newPass){
		return LoginJDBC.updateStaffLogin(id, newUser, newPass);
	}
	/**
	 * Updates a patron account's login credentials
	 * @param id
	 * @param newUser
	 * @param newPass
	 * @return Confirmation of action
	 */
	public static boolean updatePatronLogin(String id, String newUser, String newPass){
		return LoginJDBC.updatePatronLogin(id, newUser, newPass);
	}
	/**
	 * Deletes a Patron account's login credentials
	 * @param associatedAccount
	 * @return Confirmation of action
	 */
	public static boolean deletePatronLogin(PatronAccount associatedAccount){
		return LoginJDBC.deletePatronLogin(associatedAccount);
	}
	/**
	 * Deletes a Staff account's login credentials
	 * @param associatedAccount
	 * @return Confirmation of action
	 */
	public static boolean deleteStaffLogin(StaffAccount associatedAccount){
		return LoginJDBC.deleteStaffLogin(associatedAccount);
	}
	/**
	 * Inserts a Patron account's login credentials. This method should only be used when it is a new account being created.
	 * The default username will be in the following format:
	 * username = lastName+ID
	 * password = last 4 digits of registered phone number 
	 * @param associatedAccount
	 * @return Confirmation of action
	 */
	public static boolean insertPatronLogin(PatronAccount associatedAccount){
		return LoginJDBC.insertPatronLogin(associatedAccount);
	}
	/**
	 * Inserts an Staff account's login credentials. This method should only be used when it is a new account being created.
	 * The default username will be in the following format:
	 * username = lastName+ID
	 * password = last 4 digits of registered phone number 
	 * @param associatedAccount
	 * @return Confirmation of action
	 */
	public static boolean insertStaffLogin(StaffAccount associatedAccount){
		return LoginJDBC.insertStaffLogin(associatedAccount);
	}
	
	
	public static Login loginStaff(String username, String password){
		Login l = LoginJDBC.searchStaffUsername(username);
                if(l != null){
                    if(l.getPassword().equals(password))
			return l;
                    else{
			return null;
                    }
                }
                else{
                    return null;
                }
	}

    public static Login loginPatron(String user, String pass) {
        Login l = LoginJDBC.searchPatronUsername(user);
                if(l != null){
                    if(l.getPassword().equals(pass))
			return l;
                    else{
			return null;
                    }
                }
                else{
                    return null;
                }
    }
    public static Login searchPatronUsername(String user){
        return LoginJDBC.searchPatronUsername(user);
    }
}
