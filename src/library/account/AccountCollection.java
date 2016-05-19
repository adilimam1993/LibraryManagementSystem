package library.account;
import library.jdbc.AccountJDBC;
/**
 * Bridge class between database and software for the Accounts class
 * @author Benny Pena
 * @author Jeffrey Godoy
 * @author Junaid Farooq 
 * @version 1.0
 */
import java.util.ArrayList;
import library.jdbc.AccountJDBC;

public class AccountCollection {
	
	/**
	 * Value when the an operation is successful
	 */
	public static final boolean SUCCESS = true;
	/**
	 * Value when the operation fails
	 */
	public static final boolean FAILED = false;
        
        public AccountCollection()
        {
            
        }

	/**
	 * Insert Patron Account
	 * 
	 * @param  patron  PatronAccount object
	 * @return  returns either success or failed value.
	 */
	public static boolean insertPatron(PatronAccount pAccount){
            //AccountJDBC a1 = new AccountJDBC();
            //AccountJDBC.connect();
            boolean result = AccountJDBC.insertPatron(pAccount.getFirstName(),
                    pAccount.getLastName(),
                    pAccount.getPhoneNumber(),
                    pAccount.getEmail(),
                    pAccount.getAddress());
	    return result;
	}
        public static PatronAccount searchPatron(String first, String last, String email) {
            return AccountJDBC.searchPatron(first, last, email);
        }
	/**
	 * Insert Staff Account
	 * 
	 * @param  staff  StaffAccount object
	 * @return  returns either success or failed value.
	 */
	public static boolean insertStaff(Account staff) {
	    return SUCCESS;
	}
        

	/**
	 * Update Patron Account
	 * @param  patron  PatronAccount object
	 * @return  returns either success or failed value.
	 */
        
        public static boolean updatePatron(PatronAccount p1) {
            return updatePatron(p1);
        }
        
	public static boolean updatePatronFname(String pID, String newFname) {
            return AccountJDBC.updatePatronFname(pID, newFname);
        }
        
        public static boolean updatePatronLname(String pID, String newLname) {
            return AccountJDBC.updatePatronLname(pID, newLname);
        }
        
        public static boolean updatePatronPhone(String pID, String newPhone) {
            return AccountJDBC.updatePatronPhone(pID, newPhone);
        }

        public static boolean updatePatronEmail(String pID, String newEmail) {
            return AccountJDBC.updatePatronPhone(pID, newEmail);
        }

        public static boolean updatePatronAddress(String pID, String newAddress) {
            return AccountJDBC.updatePatronPhone(pID, newAddress);
        }
         
         

	/**
	 * Delete Patron Account
	 * @param  key  PatronAccount id
	 * @return  returns either success or failed value.
	 */
	public static boolean deletePatron(String key) {
	    return SUCCESS;
	}

	/**
	 * Delete Staff Account
	 * @param  key  String object
	 * @return  returns either success or failed value.
	 */
	public static boolean deleteStaff(String key) {
	    return SUCCESS;
	}

	/**
	 *search Patron Account by ID number
	 * @param  key  The account ID (library card number)
	 * @return  PatronAccount object.
	 */
	 public static PatronAccount searchPatronByID(String key) {
 //            System.out.println("enter collection");
            return AccountJDBC.searchPatronByID(key);
	 }
	 
	/**
	 * search Staff Account by ID number
	 * @param  telNum  Phone number associated with the account
	 * @return  resultAccount  ArrayList of StaffAccount object.
	 */
	 public static StaffAccount searchStaffByID(String telNum) {
		StaffAccount resultAccount = null;
	 	return resultAccount;
	 }
	 
	/**
	 * search Patron Account by phone number
	 * @param  telNum  Phone number associated with the account
	 * @return  resultList  ArrayList of PatronAccount object.
	 */
	 public static ArrayList<PatronAccount> searchPatronByTel(String telNum) {
		 ArrayList<PatronAccount> resultList = new ArrayList<PatronAccount>();
		 return resultList;
	 }
	 
	/**
	 * search Staff Account by phone number
	 * @param  telNum  Phone number associated with the account
	 * @return  resultList  ArrayList of StaffAccount object.
	 */
	 public static ArrayList<StaffAccount> searchSraffByTel(String telNum) {
	 	ArrayList<StaffAccount> resultList = new ArrayList<StaffAccount>();
		 return resultList;
	 }

	/**
	 * search Patron Account by Last Name
	 * @param  last  The last name associated with the account
	 * @return  resultList  ArrayList of PatronAccount object.
	 */
	 public static ArrayList<PatronAccount> searchPatronByLName(String last) {
		ArrayList<PatronAccount> resultList = new ArrayList<PatronAccount>();
	 	return resultList;
	 }
	 
	/**
	 * search Staff Account by Last Name
	 * @param  last  The first name associated with the account
	 * @return  resultList  ArrayList of StaffAccount object.
	 */
	 public static ArrayList<StaffAccount> searchSraffByLName(String first) {
		ArrayList<StaffAccount> resultList = new ArrayList<StaffAccount>();
	 	return resultList;
	 }
         
          /**
          * @author Elbin Martinez
          * apply late fee method-- <BR>
          * This method applys late fees to all accounts that have late 
          * media out and have not returned it
          * @return boolean, to show it was completed or not 
          */
         public boolean applyLateFees()
         {
            //Account jdbc to bridge methods
            AccountJDBC jdb = new AccountJDBC();
            return jdb.applyLateFees();  
         }
         
          /**
          * @author Elbin Martinez
          * This method will update the chosen patrons 
          * balance by adding the amount passed to their
          * current balance
          * @param id, the patron id of patron you want to bill
          * @param amountToAdd, the amount to be added to balance
          * @return boolean true if successful
          */
         public boolean updateBalance(String id, double amount)
         {
             //Account jdbc to bridge methods
            
            return AccountJDBC.updateBalance(id, amount);
         }
         
         /**
         * @Author Elbin Martinez
         * This method will lower balance of patron 
         * by the amount passed also make a record in the income
         * table of that payment.
         * @param id - patron id who is making payment
         * @param amountPaid  amount he is paying 
         * @param typePayment - what type of payment is he making
         * @return 
         */
         public boolean makePayment(String id, double amountPaid, char typePayment)
         {
             AccountJDBC jdb= new AccountJDBC();
            return jdb.makePayment(id, amountPaid, typePayment);
         }
	 
	 
}
