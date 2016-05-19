package library.jdbc;

import library.media.Media;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.media.MediaAcademic;
import library.media.MediaBook;
import library.media.MediaCollection;
import library.media.MediaMovie;

/**
 * The MediaJdbcClass is the bridge between the MediaCollection and the JDBC
 * (database). This class implements all communications with the database
 * related to the functions of MediaCollection.
 *
 * @author Adil Imam
 */
public class MediaJdbcClass {

    private Media m = null;
    private Connection con = null;
    private ResultSet rs = null;
    private Statement statement = null;
    private PreparedStatement prepared = null;
    private CallableStatement precall = null;
    private MediaBook book = null;
    private MediaMovie movie = null;
    private MediaAcademic academic = null;
    private PreparedStatement prepared2 = null;
    private PreparedStatement prepared3 = null;
    private ResultSet rs1 = null;

    private ArrayList<String> author_list = new ArrayList();

    private MediaCollection collection = new MediaCollection();

    /**
     * Constructor for MediaJdbcClass.
     */
    public MediaJdbcClass() {
    }

    /**
     * This methods will stablish a connection with the database.
     */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_system?autoReconnect=true&useSSL=false", "root", "rafa2012");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This methods adds a new media to the catalog.
     *
     * @param m Media to be added
     * @return true if the insertion was successful or false otherwise
     * @throws java.sql.SQLException it throws SQL Exception
     */
    public boolean addMedia(Media m) throws SQLException {

        connect();
        // First, it must be connected to the database 

        // prepared2=con.prepareStatement("insert into mydb.authorbooks values(?,?)");
        prepared2 = con.prepareStatement("insert into author (authorName) value(?)");
        prepared3 = con.prepareStatement("insert into authorbooks values(?,?)");

        try {

            String str = m.getMediaType();

            prepared = con.prepareStatement("insert into media(mediaTitle,mediaYear,mediaCategory,mediaCost,"
                    + "mediaType," + "mediaQuantity,mediaPublisher,"
                    + "mediaCode,bookEdition,bookVolume,runningTime,movieDirector)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)");
            statement = con.createStatement();

            //populate the appropriate fields for each media Subtype   
            switch (str) {
                case "B": {
                    prepared.setString(1, m.getMediaTitle());
                    prepared.setString(2, m.getMediaYear());
                    prepared.setString(3, m.getCategory());
                    prepared.setDouble(4, m.getMediaCost());
                    prepared.setString(5, m.getMediaType());
                    prepared.setInt(6, m.getQuantity());
                    prepared.setString(7, m.getPublisher());
                    prepared.setString(8, m.getIsbn());
                    prepared.setString(9, m.getEdition());
                    prepared.setString(10, m.getVolume());
                    prepared.setString(11, null);
                    prepared.setString(12, null);

                    rs = statement.executeQuery("select * from media where mediaCode='" + m.getIsbn() + "'");
                    if (rs.next()) {
                        System.out.println("Book with the given DOI code is already in the system");
                    } else {
                        prepared.executeUpdate();

                        author_list = m.getAuthors();

                        for (int i = 0; i < author_list.size(); i++) {
                            //prepared2.setString(1,m.getMediaId());
                            prepared2.setString(1, author_list.get(i));

                            // checking if the same author name doesnt exist in the table
                            rs = statement.executeQuery("select * from author where authorName='" + author_list.get(i) + "'");
                            if (rs.next()) {
                            } else {
                                prepared2.executeUpdate();
                            }
                        }
                        for (int i = 0; i < author_list.size(); i++) {
                            rs = statement.executeQuery("select * from author where authorName='" + author_list.get(i) + "'");
                            rs.next();
                            prepared3.setString(2, rs.getString("authorID"));
                            rs = statement.executeQuery("select * from media where mediaCode='" + m.getIsbn() + "'");
                            rs.next();
                            prepared3.setString(1, rs.getString("mediaId"));
                            prepared3.executeUpdate();
                        }
                    }
                    break;
                }
                case "M": {
                    prepared.setString(1, m.getMediaTitle());
                    prepared.setString(2, m.getMediaYear());
                    prepared.setString(3, m.getCategory());
                    prepared.setDouble(4, m.getMediaCost());
                    prepared.setString(5, m.getMediaType());
                    prepared.setInt(6, m.getQuantity());
                    prepared.setString(7, null); // mediaPublisher
                    prepared.setString(8, null); // bookISBN
                    prepared.setString(9, null); // bookEdition
                    prepared.setString(10, null); // bookVolume
                    prepared.setString(11, m.getRunning_time());
                    prepared.setString(12, m.getDirector());
                    prepared.executeUpdate();
                    break;
                }
                case "A": {
                    // prepared.setString(1,m.getMediaId());
                    prepared.setString(1, m.getMediaTitle());
                    prepared.setString(2, m.getMediaYear());
                    prepared.setString(3, m.getCategory());
                    prepared.setDouble(4, m.getMediaCost());
                    prepared.setString(5, m.getMediaType());
                    prepared.setInt(6, m.getQuantity());
                    prepared.setString(7, m.getAcademicPublisher());
                    prepared.setString(8, m.getAcademicISBN());
                    prepared.setString(9, null);
                    prepared.setString(10, null);
                    prepared.setString(11, null);
                    prepared.setString(12, null);

                    prepared.executeUpdate();

                    author_list = m.getAuthors_of_Ebook();


                    for (int i = 0; i < author_list.size(); i++) {
                        //prepared2.setString(1,m.getMediaId());
                        prepared2.setString(1, author_list.get(i));

                        // checking if the same author name doesnt exist in the table
                        rs = statement.executeQuery("select * from author where authorName='" + author_list.get(i) + "'");
                        if (rs.next()) {

                        } else {
                            prepared2.executeUpdate();

                        }
                    }
                    for (int i = 0; i < author_list.size(); i++) {
                        rs = statement.executeQuery("select * from author where authorName='" + author_list.get(i) + "'");
                        rs.next();

                        prepared3.setString(2, rs.getString("authorID"));

                        rs = statement.executeQuery("select * from media where mediaCode='" + m.getAcademicISBN() + "'");

                        rs.next();

                        prepared3.setString(1, rs.getString("mediaId"));

                        prepared3.executeUpdate();
                    }

                    break;
                }
                default:
                    break;
            }

            //    prepared.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            // prepared.close();
            // statement.close();
            // rs.close();
            // con.close();
        }

        return true;
    }

    /**
     * This method edits an existing media in the catalog. Each subtype class
     * will have their own implementation of this method
     *
     * @param editedMedia Media object with all modifications
     * @return true if the edition was successful or false otherwise
     */
    public boolean editMedia(Media editedMedia) {

        String type = editedMedia.getMediaType();
        System.out.println("Type:" + type);

        connect(); // First, it must be connected to the database 

        try {
            prepared = con.prepareStatement("update library_system.media set mediaTitle=?, "
                    + "mediaYear=?, mediaCategory=?, mediaCost=?, mediaType=?, "
                    + "mediaQuantity=?, mediaPublisher=?, bookEdition=?, "
                    + "bookVolume=?, runningTime=?, movieDirector=? where mediaId=?");

            switch (type) {
                case "b": {

                    prepared.setString(1, editedMedia.getMediaTitle());
                    prepared.setString(2, editedMedia.getMediaYear());
                    prepared.setString(3, editedMedia.getCategory());
                    prepared.setDouble(4, editedMedia.getMediaCost());
                    prepared.setString(5, editedMedia.getMediaType());
                    prepared.setInt(6, editedMedia.getQuantity());
                    prepared.setString(7, editedMedia.getPublisher());

                    prepared.setString(8, editedMedia.getEdition());
                    prepared.setString(9, editedMedia.getVolume());
                    prepared.setString(10, null);
                    prepared.setString(11, null);
 
                    prepared.setInt(12, editedMedia.getMediaId());

                    prepared.executeUpdate();

                                       // System.out.println("MediaId= "+editedMedia.getMediaId());

                   // System.out.println("in here");
                    break;
                }
                case "m": {
                    prepared.setString(1, editedMedia.getMediaTitle());
                    prepared.setString(2, editedMedia.getMediaYear());
                    prepared.setString(3, editedMedia.getCategory());
                    prepared.setDouble(4, editedMedia.getMediaCost());
                    prepared.setString(5, editedMedia.getMediaType());
                    prepared.setInt(6, editedMedia.getQuantity());
                    prepared.setString(7, null); // mediaPublisher
                    prepared.setString(8, null); // bookISBN
                    prepared.setString(9, null); // bookEdition
                    prepared.setString(10, editedMedia.getRunning_time()); // bookVolume
                    prepared.setString(11, editedMedia.getDirector());
                    prepared.setInt(12, editedMedia.getMediaId());
                    prepared.executeUpdate();
                    break;
                }
                case "a": {

                    prepared.setString(1, editedMedia.getMediaTitle());
                    prepared.setString(2, editedMedia.getMediaYear());
                    prepared.setString(3, editedMedia.getCategory());
                    prepared.setDouble(4, editedMedia.getMediaCost());
                    prepared.setString(5, editedMedia.getMediaType());
                    prepared.setInt(6, editedMedia.getQuantity());
                    prepared.setString(7, editedMedia.getAcademicPublisher());

                    prepared.setString(8, null);
                    prepared.setString(9, null);
                    prepared.setString(10, null);
                    prepared.setString(11, null);
                    prepared.setInt(12, editedMedia.getMediaId());

                    prepared.executeUpdate();
                       
                    break;

                }
                
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MediaJdbcClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * This methods deletes an existing media from the catalog.
     *
     * @param mediaId Media id to delete
     * @return Media object thats deleted
     */
    public boolean deleteMedia(int mediaId) {

        connect(); // First, it must be connected to the database 
        try {

            prepared = con.prepareStatement("delete from media where mediaId=?");
            prepared2 = con.prepareStatement("delete from authorbooks where mediaId=?");
            prepared.setInt(1, mediaId);
            prepared2.setInt(1, mediaId);
            
            // First, delete row(s) in authorbook table
            int j = prepared2.executeUpdate();
            
            // Then, delete row in media table
            int i = prepared.executeUpdate();
            if (i > 0) {
                return true;
            }

        } catch (SQLException ex) { 
            Logger.getLogger(MediaJdbcClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * This method will perform a search in the catalog.
     *
     * @param attribute attribute by which patrons want to filter the media
     * @param value value typed by user to perform search
     * @return MediaCollection object which contains an ArrayList of media
     */
    public MediaCollection searchMedia(int attribute, String value) {
        ArrayList<Media> resultSet = new ArrayList<>();

        connect(); // First, it must be connected to the database

        try {

            statement = con.createStatement();

            switch (attribute) {
                // Author
                case 1: {

                    rs = statement.executeQuery("select * from (media join authorbooks "
                            + "using(mediaId))"
                            + "where authorId=(select authorID from "
                            + "author where authorname='" + value + "')");
                    break;
                }
                // Category
                case 2: {
                    rs = statement.executeQuery("select * from media where mediaCategory='" + value + "'");
                    break;
                }
                // ID
                case 3: {
                    rs = statement.executeQuery("select * from media where mediaId='" + value + "'");
                    break;
                }
                // ISBN
                case 4: {
                    rs = statement.executeQuery("select * from media where mediaCode='" + value + "'");
                    break;
                }
                // Title
                case 5: {
                    rs = statement.executeQuery("select *from media where mediaTitle='" + value + "'");
                    break;
                }
                // Year
                case 6: {
                    rs = statement.executeQuery("select * from media where mediaYear='" + value + "'");
                    break;
                }
                default:
                    break;
            }

            // precall.executeQuery();
            unpack(rs);
            //testPrint(rs); 

        } catch (SQLException se) {
            se.printStackTrace();
        }

        // unpack rs into object//
        return collection;
    }

    private void unpack(ResultSet rs) {
        collection.cleanCollection();

        try {
            while (rs.next()) {
                switch (rs.getString("mediaType")) {
                    case "B": {
                        book = new MediaBook(rs.getInt("mediaId"), rs.getString("mediaTitle"), rs.getString("mediaYear"),
                                rs.getString("bookEdition"), rs.getString("bookVolume"),
                                rs.getString("mediaPublisher"), rs.getString("mediaCode"), rs.getDouble("mediaCost"), rs.getInt("mediaQuantity"), null, rs.getString("mediaCategory"));

                        collection.addMedia(book);

                        break;

                    }
                    case "M": {

                        movie = new MediaMovie(rs.getInt("mediaId"), rs.getString("mediaTitle"), rs.getString("mediaYear"),
                                rs.getDouble("mediaCost"), rs.getString("movieDirector"), rs.getString("runningTime"), rs.getInt("mediaQuantity"), rs.getString("mediaCategory"));

                        collection.addMedia(movie);

                        break;
                    }
                    case "A": {
                        academic = new MediaAcademic(rs.getInt("mediaId"), rs.getString("mediaTitle"), rs.getString("mediaYear"),
                                rs.getDouble("mediaCost"), rs.getString("mediaPublisher"), null, null, rs.getString("mediaCategory"), rs.getInt("mediaQuantity"), rs.getString("mediaCode"), rs.getString("mediaCategory"));

                        collection.addMedia(academic);

                        break;
                    }
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * Browse Media.
     *
     * @return an ArrayList of Media objects
     */
    public MediaCollection Browse_Media() {
        connect(); // First, it must be connected to the database 

        try {
            rs = statement.executeQuery("select * from media");
        } catch (SQLException ex) {
            Logger.getLogger(MediaJdbcClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        unpack(rs);
        return collection;

    }
}
