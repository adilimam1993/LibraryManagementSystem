package library.jdbc;

import java.util.ArrayList;
import java.sql.*;
import javax.sql.*;
import library.account.*;
import library.account.TypeSafe;
import java.util.Date;
import library.income.*;

public class AccountJDBC {
    private static String dbUrl = "jdbc:mysql://localhost:3306/library_system?autoReconnect=true&useSSL=false";
    private static String dbUsername = "root";
    private static String dbPassword = "rafa2012";
    private static Connection myConn = null;

    public AccountJDBC() {
    }
    
    public AccountJDBC(String url, String username, String password) {
        dbUrl = url;
        dbUsername = username;
        dbPassword = password;
    }
    
    //CONNECT
    public static void connect() {
       
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    //INSERT
    /*
    public int insert(String dbTable, String[] dbFields, String[] dbValues) {
        Stat
        int rowsAffected = 0;
        String mySQL = "";
        
        try {
            mySQL = "INSERT INTO ";
            mySQL += dbTable;
            mySQL += " (";

            for (int i = 0; i < dbFields.length; i++) {
                if (i == dbFields.length - 1)
                    mySQL += dbFields[i] + ")";
                else
                    mySQL += dbFields[i] + ",";
            }
            
            mySQL += " VALUES (";
            
            for (int i = 0; i < dbValues.length; i++) {
                if (i == dbValues.length - 1)
                    mySQL += dbValues[i] + ")";
                else
                    mySQL += dbValues[i] + ",";
            }
            
            rowsAffected = myStmt.executeUpdate(mySQL);
            
            
        }
        
        catch (Exception exc) {
                exc.printStackTrace();
        }

        return rowsAffected;
    }
    */
    
    /**
     * Insert new Patron Account into database.
     * 
     * @param fName First Name as String.
     * @param lName Last Name as String.
     * @param phone Phone Number as String.
     * @param email Email Address as String.
     * @param address Street Address as String.
     * @return 
     */
    public static boolean insertPatron(String fName, String lName, String phone, String email, String address) {
        //int rowsAffected = 0;
        boolean successful = false;
        String mySQL;
        PreparedStatement prepMySQL;
        
        mySQL = "INSERT INTO patron (pFname,pLname,pPhone,pEmail,pAddress)"
                + "VALUES (?,?,?,?,?)";
        
        try {
            myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            prepMySQL = myConn.prepareStatement(mySQL);
            prepMySQL.setString(1, fName);
            prepMySQL.setString(2, lName);
            prepMySQL.setString(3, phone);
            prepMySQL.setString(4, email);
            prepMySQL.setString(5, address);
            
            successful = prepMySQL.execute();
            
            prepMySQL.close();
            myConn.close();
        }
        
        catch (Exception exc) {
            exc.printStackTrace();
            return false;
        }
        
        return true;      
    }
    
    /*
	/**
	 * Value when the an operation is successful
	 */
	public static final boolean SUCCESS = true;
	/**
	 * Value when the operation fails
	 */
	public static final boolean FAILED = false;

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
            int rowsAffected = 0;
            boolean successful = false;
            String mySQL;
            PreparedStatement prepMySQL;

            mySQL = "UDPATE patron set "
                    + "pFname = ?, "
                    + "pLname = ?, "
                    + "pPhone = ?, "
                    + "pEmail = ? "
                    + "WHERE pID = ?";

            try {
                myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                prepMySQL = myConn.prepareStatement(mySQL);
                prepMySQL.setString(1, p1.getFirstName());
                prepMySQL.setString(1, p1.getLastName());
                prepMySQL.setString(1, p1.getPhoneNumber());
                prepMySQL.setString(1, p1.getEmail());
                prepMySQL.setInt(5, Integer.parseInt(p1.getId()));

                rowsAffected = prepMySQL.executeUpdate();

                prepMySQL.close();
                myConn.close();
            }

            catch (Exception exc) {
                exc.printStackTrace();
                return false;
            }

            return true; 
	}
        
	public static boolean updatePatronFname(String pID, String newFname) {
            int rowsAffected = 0;
            boolean successful = false;
            String mySQL;
            PreparedStatement prepMySQL;

            mySQL = "UPDATE patron SET pFname = ? WHERE pID = ?";

            try {
                myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                prepMySQL = myConn.prepareStatement(mySQL);
                prepMySQL.setString(1, newFname);
                prepMySQL.setInt(2, Integer.parseInt(pID));

                prepMySQL.executeUpdate();

                prepMySQL.close();
                myConn.close();
            }

            catch (Exception exc) {
                exc.printStackTrace();
                return false;
            }

            return true; 
	}
        
        public static boolean updatePatronLname(String pID, String newLname) {
            int rowsAffected = 0;
            boolean successful = false;
            String mySQL;
            PreparedStatement prepMySQL;

            mySQL = "UPDATE patron set pLname = ? "
                    + "WHERE pID = ?";

            try {
                myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                prepMySQL = myConn.prepareStatement(mySQL);
                prepMySQL.setString(1, newLname);
                prepMySQL.setString(2, pID);

                rowsAffected = prepMySQL.executeUpdate();

                prepMySQL.close();
                myConn.close();
            }

            catch (Exception exc) {
                exc.printStackTrace();
                return false;
            }

            return true; 
	}
        
        public static boolean updatePatronPhone(String pID, String newPhone) {
            int rowsAffected = 0;
            boolean successful = false;
            String mySQL;
            PreparedStatement prepMySQL;

            mySQL = "UPDATE patron set pPhone = ? "
                    + "WHERE pID = ?";

            try {
                myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                prepMySQL = myConn.prepareStatement(mySQL);
                prepMySQL.setString(1, newPhone);
                prepMySQL.setString(2, pID);

                rowsAffected = prepMySQL.executeUpdate();

                prepMySQL.close();
                myConn.close();
            }

            catch (Exception exc) {
                exc.printStackTrace();
                return false;
            }

            return true; 
	}
        
        public static boolean updatePatronEmail(String pID, String newEmail) {
            int rowsAffected = 0;
            boolean successful = false;
            String mySQL;
            PreparedStatement prepMySQL;

            mySQL = "UPDATE patron set pEmail = ? "
                    + "WHERE pID = ?";

            try {
                myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                prepMySQL = myConn.prepareStatement(mySQL);
                prepMySQL.setString(1, newEmail);
                prepMySQL.setString(2, pID);

                rowsAffected = prepMySQL.executeUpdate();

                prepMySQL.close();
                myConn.close();
            }

            catch (Exception exc) {
                exc.printStackTrace();
                return false;
            }

            return true; 
	}
        
        public static boolean updatePatronAddress(String pID, String newAddress) {
            int rowsAffected = 0;
            boolean successful = false;
            String mySQL;
            PreparedStatement prepMySQL;

            mySQL = "UPDATE patron set pAddress = ? "
                    + "WHERE pID = ?";

            try {
                myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                prepMySQL = myConn.prepareStatement(mySQL);
                prepMySQL.setString(1, newAddress);
                prepMySQL.setString(2, pID);

                rowsAffected = prepMySQL.executeUpdate();

                prepMySQL.close();
                myConn.close();
            }

            catch (Exception exc) {
                exc.printStackTrace();
                return false;
            }

            return true; 
	}
        
        public static boolean updatePatronBalance(String pID, Double newBalance) {
            int rowsAffected = 0;
            boolean successful = false;
            String mySQL;
            PreparedStatement prepMySQL;

            mySQL = "UPDATE patron set pBalance = ? "
                    + "WHERE pID = ?";

            try {
                myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                prepMySQL = myConn.prepareStatement(mySQL);
                prepMySQL.setDouble(1, newBalance);
                prepMySQL.setString(2, pID);

                rowsAffected = prepMySQL.executeUpdate();

                prepMySQL.close();
                myConn.close();
            }

            catch (Exception exc) {
                exc.printStackTrace();
                return false;
            }

            return true; 
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
	 * Search Patron Account by ID number.
         * 
	 * @param  key  The account ID (library card number)
	 * @return  myResultSet  SQL ResultSet object.
	 */
	 public static PatronAccount searchPatronByID(String key) {
            int rowsAffected = 0;
            boolean successful = false;
            String mySQL = null;
            PreparedStatement prepMySQL = null;
            ResultSet myRs = null;
            PatronAccount p1 = null;

            mySQL = "SELECT * "
                    + "FROM library_system.patron "
                    + "WHERE pID = ?";

            try {
                myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                prepMySQL = myConn.prepareStatement(mySQL);
                prepMySQL.setInt(1, Integer.parseInt(key));

                myRs = prepMySQL.executeQuery();
                
                if(myRs.next()){
                
                //myRs.next();
                
//                System.out.println(myRs.getString(1));
//                System.out.println(myRs.getString(2));
//                System.out.println(myRs.getString(3));
//                System.out.println(myRs.getString(4));
//                System.out.println(myRs.getString(5));
//                System.out.println(myRs.getString(6));
//                System.out.println(myRs.getString(7));
                               
              
                    p1 = new PatronAccount(
                        myRs.getInt(1)+"",
                        myRs.getString(2),
                        myRs.getString(3),
                        myRs.getString(4),
                        myRs.getString(5),
                        myRs.getString(6),
                        myRs.getDouble(7));
                    
                    prepMySQL.close();
                    myConn.close();
                }
                return p1;  
            }
            catch (Exception exc) {
                exc.printStackTrace();
                return p1;
            }
        
            
	 }
         
         /**
	 * Search Patron Account by ID number.
         * 
	 * @param  key  The account ID (library card number)
	 * @return  myResultSet  SQL ResultSet object.
	 */
	 public static PatronAccount searchPatron(String first, String last, String email) {
            int rowsAffected = 0;
            boolean successful = false;
            String mySQL = null;
            PreparedStatement prepMySQL = null;
            ResultSet myRs = null;
            PatronAccount p1 = null;

            mySQL =   "SELECT * "
                    + "FROM library_system.patron "
                    + "WHERE pFname = ? "
                    + " AND pLname = ? "
                    + " AND pEmail = ? ";

            try {
                prepMySQL = myConn.prepareStatement(mySQL);
                prepMySQL.setString(1, first);
                prepMySQL.setString(2, last);
                prepMySQL.setString(3, email);
                myRs = prepMySQL.executeQuery();
                
                if(myRs.next()){
                
                /*System.out.println(myRs.getString(1));
                System.out.println(myRs.getString(2));
                System.out.println(myRs.getString(3));
                System.out.println(myRs.getString(4));
                System.out.println(myRs.getString(5));
                System.out.println(myRs.getString(6));
                System.out.println(myRs.getString(7));
                */               
                //System.out.println("enter");
                    p1 = new PatronAccount(
                        myRs.getInt(1)+"",
                        myRs.getString(2),
                        myRs.getString(3),
                        myRs.getString(4),
                        myRs.getString(5),
                        myRs.getString(6),
                        myRs.getDouble(7));
                                
                
                }
                return p1;
            }
            catch (Exception exc) {
                exc.printStackTrace();
                return p1;
            }
        
            
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
		connect();
		try	{
			String query = "select * from checkedoutmedia";
			Statement stmt = myConn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next())
			{
				if (rs.getDate("dueDate").before(new Date()))
				{
					String id = rs.getString("patronId");
					System.out.println(id);
					updateBalance(id, .10);

				}
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}

		return true;
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
	public static boolean updateBalance(String id, double amountToAdd)
	{
		try{
			connect();
			String query = "select * from patron where pID = ?";
			PreparedStatement stmt = myConn.prepareStatement(query);
			stmt.setString(1,id);
			ResultSet rs = stmt.executeQuery();
			double current = 0;
			if (rs.next())
			{
			current = rs.getDouble("pBalance");
			}
			current = current + amountToAdd;
			String query1 = "update patron set pBalance = ? where pID = ?";
			PreparedStatement prestmt = myConn.prepareStatement(query1);
			prestmt.setDouble(1, current);
			prestmt.setString(2, id);
			
			prestmt.executeUpdate();
			
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
		return true;
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
        public static boolean makePayment(String id, double amountPaid, char typePayment)
        {
            try{
			connect();
			String query = "select * from patron where pID = ?";
			PreparedStatement stmt = myConn.prepareStatement(query);
			stmt.setString(1,id);
			ResultSet rs = stmt.executeQuery();
			double current = 0;
			if (rs.next())
			{
			current = rs.getDouble("pBalance");
			}
			current = current - amountPaid;
			String query1 = "update patron set pBalance = ? where pID = ?";
			PreparedStatement prestmt = myConn.prepareStatement(query1);
			prestmt.setDouble(1, current);
			prestmt.setString(2, id);
			
			prestmt.executeUpdate();
                        
                        IncomeCol iDB = new IncomeCol();
                        iDB.insert(new Income(id,typePayment,amountPaid,new Date()));
                        
			
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
            return true;
        }
}


