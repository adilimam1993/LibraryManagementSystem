package library.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.media.Reservation;

/**
 * This is the class that uses JDBC service package to manipulate the data in
 * Reservation.
 *
 * @author Jéssica Carneiro
 */
public class ReservationJdbc {

    private Connection con = null; // To establish a connection
    private Statement st = null; // To select data
    private PreparedStatement ps = null; // To update, add, or delete data
    private ResultSet rs = null; // Result set from queries
    private static final java.util.Date UTIL_DATE = new java.util.Date();
    private final java.sql.Date SQL_DATE = new java.sql.Date(UTIL_DATE.getTime());

    /**
     * This methods will stablish a connection with the database.
     */
    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_system?autoReconnect=true&useSSL=false", "root", "rafa2012");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method allows us to reserve one media for one specific patron.
     *
     * @param r reservation object passed from control class which tells us
     * which patron will reserve what media.
     * @return true if media was successfully reserved, or false otherwise
     */
    public boolean reserveMedia(Reservation r) {

        // Checking if media is available
        if (!this.checkQuantity(r.getMediaId())) {
            System.out.println("This media is currently not available.");
            return false;
        }

        // Check if reservation was not made before
        if (this.searchReservation(r.getMediaId(), r.getPatronId()) != null) {
            System.out.println("Patron already reserved this media.");
            return false;
        }

        connect(); // First, it must be connected to the database

        // Reserving media
        try {
            ps = con.prepareStatement("INSERT INTO reservation (patronId, mediaId, reservedDate) values(?,?,?)");
            ps.setInt(1, r.getPatronId());
            ps.setInt(2, r.getMediaId());
            ps.setDate(3, SQL_DATE);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Decrease quantity of the media available
        if (!this.decreaseQuantity(r.getMediaId())) {
            // Roll-back last transaction
            System.out.println("An error occured.");
            return false;
        }

        r.setReservationId(this.searchReservation(r.getMediaId(), r.getPatronId()).getReservationId());

        return true;
    }

    /**
     * Check if quantity is greater than zero.
     *
     * @param mediaId media unique identifier
     * @return true if quantity is greater than zero, false otherwise
     */
    public boolean checkQuantity(int mediaId) {
        connect(); // First, it must be connected to the database 

        // First, check if there are any copies available
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT mediaQuantity FROM media WHERE mediaId = " + mediaId);

            while (rs.next()) {
                // No copies avaiable
                if (rs.getInt("mediaQuantity") == 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * Decrease quantity for a media.
     *
     * @param mediaId media unique identifier
     * @return true if update was made, false otherwise
     */
    public boolean decreaseQuantity(int mediaId) {
        connect(); // First, it must be connected to the database

        // Decreasing quantity
        try {
            ps = con.prepareStatement("UPDATE media SET mediaQuantity = mediaQuantity - 1 WHERE mediaId = "
                    + mediaId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationJdbc.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Increase quantity for a media.
     *
     * @param mediaId media unique identifier
     * @return true if update was made, false otherwise
     */
    public boolean increaseQuantity(int mediaId) {
        connect(); // First, it must be connected to the database

        // Decreasing quantity
        try {
            ps = con.prepareStatement("UPDATE media SET mediaQuantity = mediaQuantity + 1 WHERE mediaId = "
                    + mediaId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationJdbc.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Delete a reservation given its reservation id.
     *
     * @param reservationId reservation ID
     * @return the deleted reservation object if operation was successful, false
     * otherwise
     */
    public Reservation deleteReservation(int reservationId) {
        connect(); // First, it must be connected to the database
        Reservation r;

        r = this.searchReservation(reservationId); // Retrieve reservation object from search

        try {
            ps = con.prepareStatement("DELETE FROM reservation WHERE reservationId = ?");
            ps.setInt(1, r.getReservationId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            return null;
        }

        if (!this.increaseQuantity(r.getMediaId())) {
            // Roll back last transaction
            return null;
        }
        return r;
    }

    /**
     * This method lets us view all reserved media for one specific patron
     *
     * @param patronId represents patron whose reservation is required to be
     * displayed
     * @return an ArrayList with all reservations for that specific patron
     */
    public ArrayList<Reservation> viewPatronReserveList(int patronId) {
        connect(); // First, it must be connected to the database 

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM reservation WHERE patronId = "
                    + patronId);
            return unpack(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationJdbc.class.getName()).log(Level.SEVERE,
                    null, ex);
            return null;
        }
    }

    /**
     * This method display all the reserved media in the library.
     *
     * @return an ArrayList with Reservation objects or null if no reservation
     * found
     */
    public ArrayList<Reservation> viewLibReserveList() {
        connect(); // First, it must be connected to the database
        Reservation reservation = new Reservation();
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM reservation");
            return unpack(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationJdbc.class.getName()).log(Level.SEVERE, 
                    null, ex);
            return null;
        }
    }

    /**
     * Search a reservation by reservationId.
     *
     * @param reservationId the reservation ID
     * @return the reservation object if found or null if an error occurred
     */
    public Reservation searchReservation(int reservationId) {
        Reservation r = new Reservation();

        connect(); // First, it must be connected to the database 

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM reservation WHERE reservationId = " 
                    + reservationId);
            while (rs.next()) {
                r.setReservationId(rs.getInt("reservationId"));
                r.setPatronId(rs.getInt("patronId"));
                r.setMediaId(rs.getInt("mediaId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationJdbc.class.getName()).log(Level.SEVERE, 
                    null, ex);
            return null;
        }
        return r;
    }

    /**
     * Search a reservation by patronId and mediaId.
     *
     * @param mediaId media ID
     * @param patronId patron ID
     * @return the reservation object or null if an error occurred
     */
    public Reservation searchReservation(int mediaId, int patronId) {
        connect(); // First, it must be connected to the database 
        Reservation r = new Reservation();

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM reservation WHERE mediaId = "
                    + mediaId + " AND patronId = " + patronId);
            while (rs.next()) {
                r.setReservationId(rs.getInt("reservationId"));
                r.setPatronId(rs.getInt("patronId"));
                r.setMediaId(rs.getInt("mediaId"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationJdbc.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
        return null;
    }

    /**
     * Unpack result set.
     *
     * @param rs result set
     * @return ArrayList of reservations
     */
    private ArrayList<Reservation> unpack(ResultSet rs) {
        ArrayList<Reservation> collection = new ArrayList<>();

        try {
            while (rs.next()) {
                Reservation r = new Reservation(rs.getInt("reservationId"),
                        rs.getInt("mediaId"), rs.getInt("patronId"), 
                        rs.getDate("reservedDate"));
                collection.add(r);
            }
        } catch (SQLException se) {
        }
        return collection;
    }
}
