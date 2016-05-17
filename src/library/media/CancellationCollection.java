package library.media;

import java.util.ArrayList;
import library.jdbc.CancellationJDBC;
import java.lang.*;

/**
 * The CancellationCollection is the class that holds the Cancellation objects 
 * related to cancelling a reservation.
 * 
 * @author <a>Brett Landau</a>
 */

public class CancellationCollection {

    private ArrayList<Cancellation> cancellation;
    private CancellationJDBC cancel;

    public CancellationCollection() {
        this.cancellation = new ArrayList();
        this.cancel = new CancellationJDBC();
    }

    /**
     * This method lets us cancel one media reservation for one specific patron
     *
     * @param r cancellation object passed from control class which tells us
     * which patron has cancelled what media reservation
     * @return Boolean represents whether the desired operation was successful
     * or not
     */
    public Boolean CancelMedia(Reservation r) {
        Cancellation c = new Cancellation(r.getMediaId(), r.getPatronId(), r.getReservationId(), cancellationId, cancelled_date);
        return cancel.CancelMedia(c);
    }
    
   //Cancellation search
    public Cancellation searchCancellation(int cancellationId) {
        return cancel.searchCancellation(cancellationId);
    }
    
     /**
     * Delete a cancellation
     * @param cancellationId unique cancellationId
     * @return the deleted object
     */
    public Cancellation deleteCancellation(int cancellationId) {
        Cancellation c;
        c = this.searchReservation(reservation.getMediaId(), reservation.getPatronId());
        return cancel.deleteReservation(r.getReservationId());
        Cancellation deletedC = new Cancellation();
        return deletedC;
    }

    //This method lets us view all cancelled media for one specific patron
    public ArrayList<Cancellation> View_User_CancellationList(int PatronId) { {
        this.cancellation = cancel.View_User_CancellationList(PatronId);
        return this.cancel;
    }

    /**
     * This method display all the cancelled books in the library
     */
    public ArrayList<Cancellation> view_All_Cancellation_List() {
        this.cancellation = cancel.view_All_Cancellation_List();
        return this.cancel;
        cancellation.toString();
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < reserveList.size(); i++) {
            s += "Reservation ID: " + reserveList.get(i).getReservationId() + "\tMedia ID: " + reserveList.get(i).getMediaId() 
                    + "\tPatron ID: " + reserveList.get(i).getPatronId()
                    + "\tDate: " + reserveList.get(i).getReservationDate()
                    + "\tCancellation ID: " + cancel.get(i).getCancellationId()
                    + "\tCancellation Date: " + cancel.get(i).getCancelled_date()
                    + "\n";
        }
        return s;
    }

    /**
     *
     * @return ArrayList lets you return the arrayList holding objects of
     * Cancellation
     */
    public ArrayList<Cancellation> getArr() {
        return cancellation;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o); //To change body of generated methods, choose Tools | Templates.
    }

}
