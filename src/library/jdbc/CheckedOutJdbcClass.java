package library.jdbc;

import library.media.CheckedOutMedia;
import library.media.CheckedOutCollection;
import java.sql.*;
import java.util.ArrayList;
import library.media.Media;
import library.media.Reservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
/**
 *
 * @author Abdul Basit, Khizar Alvi 
 */
public class CheckedOutJdbcClass {

    private Media m = null;
    private Connection con = null;
    private ResultSet rs = null;
    private Statement statement = null;
    private PreparedStatement prepared = null;
    
    static final String dbUrl = "jdbc:mysql://localhost:3306/library";
    static final String username = "root";
    static final String password = "";
    public ArrayList <Date> dueDates = new ArrayList<Date>();
	

    /**
     * Constructor for CheckedOutJdbc
     */
    public CheckedOutJdbcClass() {
    }
    
    public void connect() throws SQLException {
	// 1: Get a connection to MySQL database
	con = DriverManager.getConnection(dbUrl, username, password);
	System.out.println("Connection Established to MySQL Database");	
	}

    
    /**
     * This methods records info of the checked out media and the info of the
     * patron checking it out
     *
     * @param insertCheckoutMedia record of info pertaining to check out
     * @return true if recorded successfully or false otherwise
     */
    public boolean insertCheckoutMedia(CheckedOutMedia insertCheckoutMedia) {
        return true;
    }

    /**
     * This method allows people to search the checked out media allowing the
     * patron to view borrowing history
     *
     * @param searchCheckedOutMedia provides checked out media history
     * @return true if this was successful or false otherwise
     */
    public boolean searchCheckedOutMedia(CheckedOutMedia searchCheckedOutMedia) {
        return true;
    }

    /**
     * This method marks the media that is currently checked out of the catalog,
     * as returned. It is only called when checkIn is true
     *
     * @param deletedCheckedOutMedia Media to be marked as returned
     * @return true if this was successful or false otherwise
     */
    public boolean deleteCheckedOutMedia(CheckedOutMedia deletedCheckedOutMedia) {
        return true;
    }

    /**
     * This methods validates a renewal for media that is currently checked from
     * the catalog if it is not currently reserved already
     *
     * @param renewMedia Media is renewed
     * @return true if media was renewed successfully, false otherwise
     */
    public boolean renewMedia(CheckedOutMedia renewMedia) {
        return true;
    }

    /**
     * This method uses JDBC to retrieve Patron Emails and
     * his/her due dates from the checkedOutMedia table in Database, stores them
     * into
     * String and Date arrayLists and compares the difference of due dates
     * with the current date using the Java.util.date and joda.time.DateTime
     * class.
     * @return an ArrayList of DueDates
     * @throws java.sql.SQLException Throughs exception
     */
    public ArrayList<Date> checkDueDates() throws SQLException {
        
       ArrayList <String> PatronEmails = new ArrayList<String>();
       ArrayList<Date> DueDatesDifference = new ArrayList<Date>();
       Date currentDate = new Date();
       
	connect();
	statement = con.createStatement();
	//3: Execute SQL query
	 rs = statement.executeQuery("SELECT * from checkedoutmedia");
	//4: Process the result set..
	 while (rs.next()) {
		dueDates.add(rs.getDate("dueDate"));
		PatronEmails.add(rs.getString("patronEmail"));
	}
		for (int i = 0; i < dueDates.size(); i++){
		        System.out.println(dueDates.get(i));
		}
		 
		for (int i = 0; i < dueDates.size(); i++) {
			int differenceOfDays = Days.daysBetween(new DateTime(dueDates.get(i)), new DateTime(currentDate)).getDays();
			if (differenceOfDays > 2) {
				System.out.println (PatronEmails.get(i)+ " is " + differenceOfDays + " days behind of his/her Media DueDate");
				DueDatesDifference.add(dueDates.get(i));
			}
				
			else if (Math.abs(differenceOfDays) == 2){
				System.out.println (PatronEmails.get(i)+ " has " + Math.abs(differenceOfDays) + " days left to his/her approaching DueDate");
				DueDatesDifference.add(dueDates.get(i));
			}
}
			//Invoking SendLateNotification method
			//SendLateNotification();
			
			return DueDatesDifference;
    }

    /**
     * @author Khizar Alvi
     * This method uses Java.mail.Api and JDBC class to
     * retrieve Patron emails from checkedOutMedia table in Database and send
     * email notifications to the Patrons who have missed Due Date of their
     * return of media
     * @return returns true on successful delivery of email
     */
    public boolean SendLateNotification() throws SQLException {
        
        ArrayList <String> PatronEmails = new ArrayList <String>();
		ArrayList<Date> DueDates = new ArrayList<Date>();
		Date currentDate = new Date();
		//Sender's email ID 
		String from = "khizex20@gmail.com";
		//Getting system properties
		Properties properties = new Properties();
		//Setup mail server
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.auth", "true");  
                properties.put("mail.debug", "false");  
                properties.put("mail.smtp.ssl.enable", "true");  
                properties.put("mail.smtp.starttls.enable", "true");
        
                //Starting new session
	        Session session = Session.getInstance(properties, new SocialAuth());

		connect();//Connecting to retrieve Patron Emails and Due Dates from the Database
		
		statement = con.createStatement();
		ResultSet myRs = statement.executeQuery("Select * from checkedoutmedia");
		while (myRs.next()) {
			PatronEmails.add(myRs.getString("patronEmail"));
			DueDates.add(myRs.getDate("dueDate"));
		}
		/**Looping through each Patron Email in the Database, finding the difference of days between their DueDates and Sending 
		   Email Notifications to those who have missed their due deadlines by 2 days **/
		for (int i = 0; i < PatronEmails.size(); i++) {
			int differenceOfDays = Days.daysBetween(new DateTime(DueDates.get(i)), new DateTime(currentDate)).getDays();
			if (differenceOfDays > 2) {
			  try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(PatronEmails.get(i)));
				message.setSubject("Lateness Reminder!");
				message.setText("You have missed your deadline. Please return your media to the library as soon as possible. Thank You!");
				Transport.send(message);
				System.out.println("Email sent to " + PatronEmails.get(i) + " successfully!");
			  }
			
			catch (MessagingException mex) {
				mex.getMessage();
			}
		  }
			
		}
		return true;
    }

    /**
     * @author Khizar Alvi
     * This method uses Java.mail.Api and JDBC class to
     * retrieve Patron emails from checkedOutMedia table in Database and send
     * due date email notifications to the Patrons who have two days left to
     * return their media
     * @return returns true on successful delivery of email
     */
    public boolean SendDueDateNotification() {
        return true;
    }
    
    /**
     * @author Khizar Alvi
     * Class for email account verification
     */
	class SocialAuth extends Authenticator {  
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication("khizex20@gmail.com", "");  
        }  
      }

}

