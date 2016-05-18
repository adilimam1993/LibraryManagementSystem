package library.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.media.Cancellation;
import library.media.Reservation;

/**
 * The Cancellation JDBC class is works with the JDBC service package to use and edit
 * the cancellation data in the database.
 */

public class CancellationJDBC {
    
    private static Connection conn = null;
    private static Statement statement = null;
    private static final String DBURL = "jdbc:mysql://localhost:3306";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "";
    private ResultSet rs1 = null;
    private PreparedStatement ps1 = null;
    /**
     * This method lets us cancel one media for one specific patron
     *
     * @param r reservation object passed from control class which tells us
     * which patron has Cancelled what media
     * @return Boolean represents whether the desired operation was successful
     * or not
     */
    public boolean CancelMedia(Cancellation r) {
        getConnection();
        // Pulling the reservation data
        try {
            ps1 = conn.prepareStatement("INSERT INTO cancellation (patronId, mediaId, reservedDate) values(?,?,?)");
            ps1.setInt(1, r.getPatronId());
            ps1.setInt(2, r.getMediaId());
            ps1.setDate(3, r.getReserved_date());
        } catch (SQLException ex) {
            Logger.getLogger(ReservationJdbc.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
 
        return true;

    }

    /**
     * This method lets us view all cancelled media for one specific patron
     *
     * @param PatronId represents patron whose cancellation is required to be
     * displayed
     */
    public ArrayList<Cancellation> View_User_CancellationList(int PatronId) {
       // getConnection(); // First, it must be connected to the database 
        ArrayList<Cancellation> rc = new ArrayList<>();
        Reservation r = new Reservation();
        Cancellation c = new Cancellation();
        getConnection();
        try {
            statement = conn.createStatement();
            rs1 = statement.executeQuery("SELECT * FROM cancellation WHERE patronId = " + PatronId);
            while (rs1.next()) {
                r.setReservationId(rs1.getInt("reservationId"));
                r.setPatronId(rs1.getInt("PatronId"));
                r.setMediaId(rs1.getInt("MediaId"));
                c.setReserved_date(rs1.getDate("reservedDate"));
                c.setCancellationId(rs1.getInt("cancellationID"));
                rc.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CancellationJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rc;
    }
    
    public void View_User_CancellationList(String PatronId) {
        getConnection();
        String query1 = "";
    }

    /**
     * This method display all the reserved books in the library
     */
    public void view_All_Cancellation_List() {

    }

    public static Connection getConnection() {
        
        try
        {
            Class.forName(DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DBURL,USER,PASS);            
        }
       catch (Exception e)
       {e.printStackTrace();}
        return conn;
    }
    
}
