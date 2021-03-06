package library.media;

import java.util.ArrayList;
import library.jdbc.ReservationJdbc;

/**
 * This is a ReservationCollection Class which will have collection of
 * reservation objects.
 *
 */
public class ReservationCollection {

    private ArrayList<Reservation> reserveList;
    private final ReservationJdbc rj;

    /**
     * Default constructor for Reservation Collection.
     */
    public ReservationCollection() {
        this.reserveList = new ArrayList();
        this.rj = new ReservationJdbc();
    }

    /**
     * This method allows us reserve one media for one specific patron.
     *
     * @param m media object to be reserved
     * @param patronId unique identifier for patron account
     * @return true if the desired operation was successful, false otherwise
     */
    public boolean reserveMedia(Media m, int patronId) {
        Reservation r = new Reservation(m.getMediaId(), patronId);
        return rj.reserveMedia(r);
    }

    /**
     * This method lets us view all reserved media for one specific patron
     *
     * @param patronId represents patron whose reservation is required to be
     * displayed
     * @return an ArrayList with all reservations for that specific patron
     */
    public ArrayList<Reservation> viewPatronReserveList(int patronId) {
        this.reserveList = rj.viewPatronReserveList(patronId);
        return this.reserveList;
    }

    /**
     * This method display all the reserved media in the library.
     *
     * @return an ArrayList with all reserved items in the library
     */
    public ArrayList<Reservation> viewLibReserveList() {
        this.reserveList = rj.viewLibReserveList();
        return this.reserveList;
    }

    /**
     * Prints the reservation collection.
     *
     * @return string formatted with all reservation objects
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < reserveList.size(); i++) {
            s += "Reservation ID: " + reserveList.get(i).getReservationId() + "\tMedia ID: " + reserveList.get(i).getMediaId() 
                    + "\tPatron ID: " + reserveList.get(i).getPatronId()
                    + "\tDate: " + reserveList.get(i).getReservationDate()
                    + "\n";
        }
        return s;
    }

    /**
     * Returns the ArrayList of reservations.
     *
     * @return ArrayList lets you return the arrayList holding objects of
     * Reservation
     */
    public ArrayList<Reservation> getArr() {
        return reserveList;
    }

    /**
     * Sets the ArrayList of reservations.
     *
     * @param list ArrayList of Reservation objects
     */
    public void setArr(ArrayList<Reservation> list) {
        reserveList = list;
    }

    /**
     * Search for a reservation by reservationId
     *
     * @param reservationId the reservation ID
     * @return a reservation object
     */
    public Reservation searchReservation(int reservationId) {
        return rj.searchReservation(reservationId);
    }

    /**
     * Search for a reservation by mediaId and patronId.
     *
     * @param mediaId media ID
     * @param patronId patron ID
     * @return a reservation object
     */
    public Reservation searchReservation(int mediaId, int patronId) {
        return rj.searchReservation(mediaId, patronId);
    }

    /**
     * Delete a reservation.
     *
     * @param reservation a reservation object
     * @return a reservation object
     */
    public Reservation deleteReservation(Reservation reservation) {
        Reservation r;
        r = this.searchReservation(reservation.getMediaId(), reservation.getPatronId());
        return rj.deleteReservation(r.getReservationId());
    }
    
     /**
     * Delete a reservation given its reservation id.
     *
     * @param reservationId reservation ID
     * @return the deleted reservation object if operation was successful, null
     * otherwise
     */
    public Reservation deleteReservation(int reservationId) {
        return this.rj.deleteReservation(reservationId);
    }
 
}
