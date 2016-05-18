package library.media;
import library.jdbc.CheckedOutJdbcClass;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 * CheckedOutCollection is the collection of all Media that has been checked out
 * by a specific Patron. The CheckedOutCollection has an array for Media and an
 * array for Patrons
 *
 * @author <a href="abasit1@oldwestbury.edu">Abdul Basit</a>
 */
import java.sql.SQLException;
public class CheckedOutCollection {

    private ArrayList<CheckedOutMedia> checkmedia = new ArrayList();

    /**
     * Default constructor
     */
    public CheckedOutCollection() {

    }

    /**
     * Constructor for CheckedOutCollection
     *
     * @param media an ArrayList of Media
     */
    public CheckedOutCollection(ArrayList<Media> media) {
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
     *
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
     * toString() method to CheckedOutCollection
     *
     * @return detail for every Media
     */
    @Override
    public String toString() {

        String str = "";

        for (int i = 0; i < checkmedia.size(); i++) {
            str += String.valueOf(checkmedia.get(i).getMediaId()) + " " + String.valueOf(checkmedia.get(i).getPatronId()) + ""
                    + " " + checkmedia.get(i).getBorrowDate().toString() + " " + checkmedia.get(i).getDueDate().toString()
                    + " " + checkmedia.get(i).getPatronEmail() + "\n";

        }

        return str;

    }

    public ArrayList<CheckedOutMedia> getMedia() {
        return checkmedia;
    }
    
    
       /**
     * @author Khizar Alvi
     * This method uses JDBC to retrieve Patron Emails and
     * his/her due dates from the checkedOutMedia table in Database, stores them
     * into
     * String and Date arrayLists and compares the difference of due dates
     * with the current date using the Java.util.date and joda.time.DateTime
     * class.
     * @return an ArrayList of DueDates
     * @throws java.sql.SQLException Throughs exception
     */
    public boolean checkDueDates () {
       
       CheckedOutJdbcClass c1 = new CheckedOutJdbcClass();
       try {
           c1.checkDueDates();
       } 
       catch (SQLException ex) {
          ex.printStackTrace();
       }
       return true;
    }
    
    
    
       /**
     * @author Khizar Alvi
     * This method uses Java.mail.Api and JDBC class to
     * retrieve Patron emails from checkedOutMedia table in Database and send
     * email notifications to the Patrons who have missed Due Date of their
     * return of media
     * @return returns true on successful delivery of email
     */
    public boolean sendLateNotificaton () {
        CheckedOutJdbcClass c1 = new CheckedOutJdbcClass();
        try {
            c1.SendLateNotification();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
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
    public boolean sendDueDateNotification () {
        CheckedOutJdbcClass c1 = new CheckedOutJdbcClass();
        try {
            c1.SendDueDateNotification();
        }
        catch (SQLException ex) {
         ex.printStackTrace();
        }
        return true; 
    }
    
}