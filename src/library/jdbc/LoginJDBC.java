package library.jdbc;


import library.account.Login;
import library.account.PatronAccount;
import library.account.StaffAccount;
import library.account.Account;
import java.sql.*;

public class LoginJDBC {
	
	private static String url = "jdbc:mysql://localhost:3306/library_system?autoReconnect=true&useSSL=false";
	private static String username = "root";
	private static String password = "    ";
        
	
	/**
	 * Value when the an operation is successful
	 */
	public static final boolean SUCCESS = true;
	
	/**
	 * Value when the operation fails
	 */
	public static final boolean FAILED = false;
	
	/**
	 * Inserts an Staff account's login credentials. This method should only be used when it is a new account being created.
	 * The default username will be in the following format:
	 * username = lastName+ID
	 * password = last 4 digits of registered phone number 
	 * @param associatedAccount
	 * @return Confirmation of action
	 */
	public static boolean insertStaffLogin(StaffAccount associatedAccount){
		Connection connection = null;
		PreparedStatement statement;
		String sql = "INSERT into staff_login (login_id, username, password) values (?, ?, ?)";
		try {
			connection = DriverManager.getConnection(url,username,password);
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1,Integer.parseInt(associatedAccount.getId()));
			statement.setString(2, makeUsername(associatedAccount));
			statement.setString(3, makePassword(associatedAccount));
			
			statement.execute();
	
			statement.close();
			connection.close();
		
		} catch (SQLException e) {
			
			return false;
		}
		
		return true;
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
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "INSERT into patron_login (patronId, username, password) values (?, ?, ?)";
		try {
			connection = DriverManager.getConnection(url,username,password);
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1,Integer.parseInt(associatedAccount.getId()));
			statement.setString(2, makeUsername(associatedAccount));
			statement.setString(3, makePassword(associatedAccount));
			
			statement.execute();
	
			statement.close();
			connection.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Deletes a Staff account's login credentials
	 * @param associatedAccount
	 * @return Confirmation of action
	 */
	public static boolean deleteStaffLogin(StaffAccount associatedAccount){
		Connection connection = null;
		PreparedStatement statement;
		String sql = "DELETE FROM staff_login WHERE login_id = ?";
		try {
			connection = DriverManager.getConnection(url,username,password);
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1,Integer.parseInt(associatedAccount.getId()));
			
			statement.execute();
			
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Deletes a Patron account's login credentials
	 * @param associatedAccount
	 * @return Confirmation of action
	 */
	public static boolean deletePatronLogin(PatronAccount associatedAccount){
		Connection connection = null;
		PreparedStatement statement;
		String sql = "DELETE FROM patron_login WHERE patronId = ?";
		try {
			connection = DriverManager.getConnection(url,username,password);
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1,Integer.parseInt(associatedAccount.getId()));
			
			statement.execute();
			
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		
		} catch (Exception e) {
                        
			return false;
		}
		
		return true;
	}
	
	/**
	 * Updates an account's login credentials
	 * @param associatedAccount
	 * @param newUser
	 * @param newPass
	 * @return Confirmation of action
	 */
	public static boolean updatePatronLogin(String id, String newUser, String newPass){
		
		Connection connection = null;
		PreparedStatement statement;
		String sql = "update patron_login set username = ?, password = ? where patronId = ?";
		
		try {
			connection = DriverManager.getConnection(url,username,password);
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, newUser);
			statement.setString(2, newPass);
			statement.setString(3, id);
			
			statement.execute();
			
		
			statement.close();
			
			connection.close();
		
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Updates an account's login credentials
	 * @param associatedAccount
	 * @param newUser
	 * @param newPass
	 * @return Confirmation of action
	 */
	public static boolean updateStaffLogin(String id, String newUser, String newPass){
		
		Connection connection = null;
		PreparedStatement statement;
		//SQL that actually makes the query
		String sql = "update staff_login set username = ?, password = ? where login_id = ?";
		
		try {
			// Get Connection to the DB
			connection = DriverManager.getConnection(url,username,password);
			// Make The SQL string into an actual SQL statement
			statement = connection.prepareStatement(sql);
			
			//replace the first ? with the username
			statement.setString(1, newUser);
			//replace the second ? with the password
			statement.setString(2, newPass);
			statement.setString(3, id);
			//Execute the SQL statement
			statement.execute();
			
			//Close statement and connection
			statement.close();
			connection.close();
		
		} catch (Exception e) {
			//if any issues return false to signal failure
			return false;
		}
		//return true if everything works out fine
		return true;
	}
	
	public static Login searchPatronLogin(String id){
		Login a = null;
		try{
	 		
	 		Connection connection = DriverManager.getConnection(url,username,password);
			
	 		String sql =("select * from patron_login where patronId = ?");
			
	 		PreparedStatement statement = connection.prepareStatement(sql);
	 		
	 		statement.setString(1, id);
	 		
			ResultSet set = statement.executeQuery();
			
			if(set.next())
                            a = new Login(set.getString(1),set.getString(2),set.getString(3));
			else
                            System.out.println("Invalid username password combonation");
                        
                        statement.close();
                        set.close();
                        connection.close();
	 	}catch(Exception e){
	 		e.printStackTrace();
	 	}
		return a;
	}
	
	public static Login searchStaffLogin(String id){
		Login a = null;
		try{
	 		
	 		Connection connection = DriverManager.getConnection(url,username,password);
			
	 		String sql =("select staff_login where login_id = ?");
			
	 		PreparedStatement statement = connection.prepareStatement(sql);
	 		
	 		statement.setString(1, id);
	 		
			ResultSet set = statement.executeQuery();
			
			
                        if (set.next())
                            a = new Login(set.getString(2),set.getString(3),set.getString(1));
                        else{
                            System.out.println("There is no such staff login account");
                        }
	 	}catch(Exception e){
	 		e.printStackTrace();
                        
	 	}
		return a;
	}
	public static Login searchStaffUsername(String user){
		Login a = null;
		try{
	 		
	 		Connection connection = DriverManager.getConnection(url,username,password);
			
	 		String sql =("select * from staff_login where username = ?");
			
	 		PreparedStatement statement = connection.prepareStatement(sql);
	 		
	 		statement.setString(1, user);
	 		
			ResultSet set = statement.executeQuery();
			
			if (set.next())
                            a = new Login(set.getString(2),set.getString(3),set.getString(1));
                        else{
                            System.out.println("There is no such staff login account");
                        }
	 	}catch(Exception e){
	 		e.printStackTrace();
                        
	 	}
		return a;
	}
	public static Login searchPatronUsername(String user){
		Login a = null;
		try{
	 		
	 		Connection connection = DriverManager.getConnection(url,username,password);
			
	 		String sql =("select * from patron_login where username = ?");
			
	 		PreparedStatement statement = connection.prepareStatement(sql);
	 		
	 		statement.setString(1, user);
	 		
			ResultSet set = statement.executeQuery();
		
			if(set.next())
                            a = new Login(set.getString(1),set.getString(2),set.getString(3));
			else
                            System.out.println("Invalid username password combonation");
			
	 	}catch(Exception e){
	 		e.printStackTrace();
	 	}
		return a;
	}
	
	//TODO revise API stuff to make sure its updated then finally move to LoginCollection
	
	private static String makeUsername(Account a){
		String username = a.getLastName()+a.getId();
		return username;
	}
	private static String makePassword(Account a){
		String password = a.getPhoneNumber().substring(a.getPhoneNumber().length()-4, a.getPhoneNumber().length());
		return password;
	}
}
