/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.account.Login;
import library.account.LoginCollection;
import library.jdbc.CheckedOutJdbcClass;
import library.jdbc.MediaJdbcClass;
import library.media.CheckedOutMedia;
import library.media.Media;
import library.media.MediaAcademic;
import library.media.MediaBook;
import library.media.MediaCollection;
import library.media.MediaMovie;
import library.media.Reservation;
import library.media.ReservationCollection;
import static userinterface.SharedFunctions.searchingModule;

/**
 * Interface that will be displayed for librarian users.
 *
 * @author Jéssica Carneiro
 */
public class LibrarianInterface {

    public static MediaJdbcClass media_jdbc = new MediaJdbcClass();
    public static Media media = new Media();
    public static MediaCollection media_collection = new MediaCollection();
    public static Reservation reservation = new Reservation();
    public static ReservationCollection reservation_collection = new ReservationCollection();
    public static Login login;
    public static MediaAcademic academ = new MediaAcademic();
    public static MediaBook b = new MediaBook();
    public static MediaMovie movie = new MediaMovie();
 public static Scanner scan = new Scanner(System.in);
       
    public static void librarianInterface() {
        int option = 0;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("\n\n\n=========MENU OPTIONS:=========");
            System.out.println("1. Search media");
            System.out.println("2. Add media");
            System.out.println("3. Delete media");
            System.out.println("4. Edit media");
            System.out.println("5. Manage reservations");
            System.out.println("6. Renew media");
            System.out.println("7. Check out media");
            System.out.println("8. Check in media");
            System.out.println("0. Return to previous menu");
            System.out.print("Type your option: ");

            // Check if it is a valid input
            try {
                option = scan.nextInt();
                switch (option) {
                    case 1:
                        if (!searchingModule(media_jdbc)) {
                            System.out.println("An error occured!");
                        }
                        break;
                    case 2:
                        if (!addingModule()) {
                            System.out.println("An error occured!");
                        }
                        break;
                    case 3:
                        if (!deletingModule()) {
                            System.out.println("An error occured!");
                        }
                        break;
                    case 4:
                        if (!editingModule()) {
                            System.out.println("An error occured!");
                        }
                        break;
                    case 5:
                        if (!reservationModule()) {
                            System.out.println("An error occured!");
                        }
                        break;
                    case 6:
                        if (!renewingModule()) {
                            System.out.println("An error occured!");
                        }
                        break;
                    case 7:
                        if (!checkoutMedia()) {
                            System.out.println("An error occured!");
                        }
                        break;
                    case 8:
                        if (!checkinMedia()) {
                            System.out.println("An error occured!");
                        }
                        
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Please, type a valid option: ");
                        break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                System.out.println("An error occured. Try again.");
            }

        } while (option != 0);
    }

    public static boolean addingModule() {
        int op = 0;
        int quantity = 1;
        String field;
        double cost;
        Scanner scan = new Scanner(System.in);
        ArrayList<String> authors = new ArrayList<>();
        authors.clear();

        do {
            System.out.println("\n\n=========MENU OPTIONS:=========");
            System.out.println("1. Add an academic material");
            System.out.println("2. Add a book");
            System.out.println("3. Add a movie");
            System.out.println("0. Return to previous menu");
            System.out.print("Type your option: ");
            // Check if it is a valid input
            try {
                op = scan.nextInt();
                String skip = scan.nextLine(); // Skipping \n character
                int numOfAuthors = 0;
                switch (op) {
                    case 1:
                        academ.setMediaType("A");
                        System.out.print("Type the title and press <ENTER>: ");
                        field = scan.nextLine();
                        academ.setMediaTitle(field);
                        System.out.print("Type the year of the publication and press <ENTER>: ");
                        field = scan.nextLine();
                        academ.setMediaYear(field);
                        System.out.print("Type the publisher name and press <ENTER>: ");
                        field = scan.nextLine();
                        academ.setAcademicPublisher(field);
                        System.out.print("Type the type of academic material and press <ENTER>: ");
                        field = scan.next();
                        academ.setCategory(field);
                        System.out.print("Type the cost of the material and press <ENTER>: ");
                        cost = scan.nextDouble();
                        skip = scan.nextLine();
                        academ.setMediaCost(cost);
                        System.out.print("Type the ISBN/DOI code: ");
                        field = scan.nextLine();
                        academ.setAcademicISBN(field);
                        System.out.print("Type the number of authors for the media: ");
                        numOfAuthors = scan.nextInt();
                        skip = scan.nextLine();

                        // Receive authors
                        for (int i = 0; i < numOfAuthors; i++) {
                            System.out.print("Type the author name: ");
                            field = scan.nextLine();
                            authors.add(field);
                        }
                        academ.setAuthors(authors);

                        // Add media
                        if (!media_jdbc.addMedia(academ)) {
                            return false;
                        } else {
                            System.out.println("Media was added successfully!");
                        }
                        break;
                    case 2:
                        b.setMediaType("B");
                        System.out.print("Type the title and press <ENTER>: ");
                        field = scan.nextLine();
                        b.setMediaTitle(field);
                        System.out.print("Type the year of the book and press <ENTER>: ");
                        field = scan.nextLine();
                        b.setMediaYear(field);
                        System.out.print("Type the publisher name and press <ENTER>: ");
                        field = scan.nextLine();
                        b.setPublisher(field);
                        System.out.print("Type the type of book and press <ENTER>: ");
                        field = scan.nextLine();
                        b.setCategory(field);
                        System.out.print("Type the edition and press <ENTER>: ");
                        field = scan.nextLine();
                        b.setBookEdition(field);
                        System.out.print("Type the volume and press <ENTER>: ");
                        field = scan.nextLine();
                        b.setBookVolume(field);
                        System.out.print("Type the ISBN and press <ENTER>: ");
                        field = scan.nextLine();
                        b.setIsbn(field);
                        System.out.print("Type the cost of the material and press <ENTER>: ");
                        cost = scan.nextDouble();
                        b.setMediaCost(cost);
                        System.out.print("Type the number of authors for the media: ");
                        numOfAuthors = scan.nextInt();
                        skip = scan.nextLine();

                        // Receive authors
                        for (int i = 0; i < numOfAuthors; i++) {
                            System.out.print("Type the author name: ");
                            field = scan.nextLine();
                            authors.add(field);
                        }
                        b.setAuthors(authors);

                        // Add media
                        if (!media_jdbc.addMedia(b)) {
                            return false;
                        } else {
                            System.out.println("Media was added successfully!");
                        }
                        break;
                    case 3:
                        movie.setMediaType("M");
                        System.out.println(movie.getMediaType());
                        System.out.print("Type the title and press <ENTER>: ");
                        field = scan.nextLine();
                        movie.setMediaTitle(field);
                        System.out.print("Type the year of the movie and press <ENTER>: ");
                        field = scan.nextLine();
                        movie.setMediaYear(field);
                        System.out.print("Type the movie genre: ");
                        field = scan.nextLine();
                        movie.setCategory(field);
                        System.out.print("Type the director name and press <ENTER>: ");
                        field = scan.nextLine();
                        movie.setDirector(field);
                        System.out.print("Type the running time and press <ENTER>: ");
                        field = scan.nextLine();
                        movie.setRunning_time(field);
                        System.out.print("Type the quantity available of this media and press <ENTER>: ");
                        quantity = scan.nextInt();
                        movie.setQuantity(quantity);
                        System.out.print("Type the cost of the material and press <ENTER>: ");
                        cost = scan.nextDouble();
                        movie.setMediaCost(cost);
                        if (!media_jdbc.addMedia(movie)) {
                            return false;
                        } else {
                            System.out.println("Media was added successfully!");
                        }
                        break;
                    case 0:
                        return true;
                    default:
                        System.out.println("Type a valid option!");
                        break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                System.out.println("An error occured. Try again.");
            }

        } while (true);
    }
    
    public static boolean checkinMedia()
    {
        int patronId=0;
        int mediaId=0;
        
        System.out.println("Enter Patron id");
        patronId=scan.nextInt();
        
        
        System.out.println("Enter Media id");
        mediaId=scan.nextInt();
        
        CheckedOutJdbcClass check=new CheckedOutJdbcClass();
        if(!check.deleteCheckedOutMedia(patronId, mediaId)){return false;}
        else {return true;}
    
    }

    public static boolean checkoutMedia() {
        
        int patronId=0;
        int mediaId=0;
        
        System.out.println("Enter Patron id");
        patronId=scan.nextInt();
        
        
        System.out.println("Enter Media id");
        mediaId=scan.nextInt();
        
        java.util.Date util_date=new java.util.Date();
        java.util.Date basit=new java.util.Date();
        
        Calendar c=Calendar.getInstance();
        c.setTime(basit);
        c.add(Calendar.DATE,7);
        basit=c.getTime();
        
     
     java.sql.Date borrow_date=new java.sql.Date(util_date.getTime());
     java.sql.Date due_date = new java.sql.Date(basit.getTime());
     
     
     
        CheckedOutMedia ch=new CheckedOutMedia(mediaId,patronId,borrow_date,due_date,"xxx@hotmail.com");
        
        CheckedOutJdbcClass check=new CheckedOutJdbcClass();
        if(!check.insertCheckoutMedia(ch))
        { return false;
        }
        else
        {
        return true;
        }
    }

    public static boolean deletingModule() {
        int mediaID;
        Scanner scan = new Scanner(System.in);

        System.out.print("Type the media ID for the media you want to delete: ");
        mediaID = scan.nextInt();
        if (media_jdbc.deleteMedia(mediaID)) {
            System.out.println("Media deleted successfully!");
        } else {
            return false;
        }

        return true;
    }

    public static boolean editingModule() {
        int mediaID=0;
        int mediaType=0;
        String input=null;
        double cost;
        int qty;
        String skip;
        Scanner scan = new Scanner(System.in);
        
          if (searchingModule(media_jdbc)) {
            System.out.println("Type the media ID for the media you want to edit: ");
            mediaID = scan.nextInt();
    System.out.println(mediaID);
           
            
            System.out.println("Select media type you want to edit: \n1.Book\n2.Movie\n3.Academic\n");
            mediaType = scan.nextInt();
     skip = scan.nextLine();
                       
           // System.out.println(mediaId);
            // retrieve data
        
          
         switch(mediaType)
         {
             case 1:
             {     MediaBook b=new MediaBook();    
                        b.setMediaId(mediaID);
                        b.setMediaType("b");
                        System.out.print("Type the title and press <ENTER>: ");
                        input = scan.nextLine();
                        System.out.println(input);
                                    
                        b.setMediaTitle(input);
                        System.out.print("Type the year of the book and press <ENTER>: ");
                        input = scan.nextLine();
                        b.setMediaYear(input);
                        System.out.println(input);
                      
                        System.out.print("Type the publisher name and press <ENTER>: ");
                        input = scan.nextLine();
                        LibrarianInterface.b.setPublisher(input);
                        System.out.print("Type the type of book and press <ENTER>: ");
                        input= scan.nextLine();
                        System.out.println(input);
                      
                        b.setCategory(input);
                        
                        System.out.print("Type the edition and press <ENTER>: ");
                        input= scan.nextLine();
                        b.setBookEdition(input);
                        System.out.println(input);
                      
                        System.out.print("Type the volume and press <ENTER>: ");
                        input = scan.nextLine();
                        b.setBookVolume(input);
                        System.out.print("Type the cost of the material and press <ENTER>: ");
                        cost = scan.nextDouble();
                        b.setMediaCost(cost);
  
                     //   media_jdbc.editMedia(b);
                       if (!media_jdbc.editMedia(b)) {
                            return false;
                        
             }
                        else {
                            System.out.println("Media was edited successfully!");
                        }

                break;             
             }
             case 2:
             {
                 MediaMovie mov=new MediaMovie();
                mov.setMediaId(mediaID);
                        
                 movie.setMediaType("m");
                        System.out.println(movie.getMediaType());
                        System.out.print("Type the title and press <ENTER>: ");
                        input = scan.nextLine();
                        movie.setMediaTitle(input);
                        System.out.print("Type the year of the movie and press <ENTER>: ");
                        input = scan.nextLine();
                        movie.setMediaYear(input);
                        System.out.print("Type the movie genre: ");
                        input = scan.nextLine();
                        movie.setCategory(input);
                        System.out.print("Type the director name and press <ENTER>: ");
                        input = scan.nextLine();
                        movie.setDirector(input);
                        System.out.print("Type the running time and press <ENTER>: ");
                        input = scan.nextLine();
                        movie.setRunning_time(input);
                        System.out.print("Type the quantity available of this media and press <ENTER>: ");
                        qty = scan.nextInt();
                        movie.setQuantity(qty);
                        System.out.print("Type the cost of the material and press <ENTER>: ");
                        cost = scan.nextDouble();
                        movie.setMediaCost(cost);
                        if (!media_jdbc.editMedia(mov)) {
                            return false;
                        } else {
                            System.out.println("Media was edited successfully!");
                        }
            break;
                  
         
         }
             case 3:
             {
                 MediaAcademic academ=new MediaAcademic();       
                 academ.setMediaId(mediaID);
               
                 LibrarianInterface.academ.setMediaType("a");
                        System.out.print("Type the title and press <ENTER>: ");
                        input = scan.nextLine();
                        academ.setMediaTitle(input);
                        System.out.print("Type the year of the publication and press <ENTER>: ");
                        input = scan.nextLine();
                        LibrarianInterface.academ.setMediaYear(input);
                        System.out.print("Type the publisher name and press <ENTER>: ");
                        input= scan.nextLine();
                        LibrarianInterface.academ.setAcademicPublisher(input);
                        System.out.print("Type the type of academic material and press <ENTER>: ");
                        input = scan.next();
                        LibrarianInterface.academ.setCategory(input);
                        System.out.print("Type the cost of the material and press <ENTER>: ");
                        cost = scan.nextDouble();
                        skip = scan.nextLine();
                        LibrarianInterface.academ.setMediaCost(cost);
                        System.out.print("Type the ISBN/DOI code: ");
                        input = scan.nextLine();
                        LibrarianInterface.academ.setAcademicISBN(input);
             
                        if (!media_jdbc.editMedia(academ)) {
                            return false;
                        } else {
                            System.out.println("Media was edited successfully!");
                        }
           break;  
             
             
             }
         }
          }
        return true;
    }

    public static boolean renewingModule() {
        int mediaID;
        int patronID;
        CheckedOutJdbcClass check=new CheckedOutJdbcClass();
        Scanner scan = new Scanner(System.in);

        System.out.println("Type the patron ID: ");
        patronID = scan.nextInt();
        // view all patron's checked out media
        System.out.println("Type the media ID to renew: ");
        mediaID = scan.nextInt();
        // renew media
check.renewMedia(patronID,mediaID);
        return true;
    }

    public static boolean reservationModule() {
        int op;
        int patronID;
        int mediaID;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("\n\n=========MENU OPTIONS:=========");
            System.out.println("1. Reserve media");
            System.out.println("2. Cancel reservation");
            System.out.println("3. List all reservations");
            System.out.println("4. List all cancellations");
            System.out.println("0. Return to previous menu");
            System.out.print("Type your option: ");
            op = scan.nextInt(); // Implement a parser to check if is int

            // Options
            switch (op) {
                case 1:
                    // Ask mediaID and patronID
                    System.out.print("Type the media ID: ");
                    mediaID = scan.nextInt();
                    media.setMediaId(mediaID);
                    System.out.print("Type the patron ID: ");
                    patronID = scan.nextInt();

                    // Try to reserve
                    if (reservation_collection.reserveMedia(media, patronID)) {
                        System.out.println("Reservation was successful!");
                    } else {
                        return false;
                    }
                    break;
                case 2:
                    // Ask for mediaID and patronID
                    System.out.print("Type the mediaID: ");
                    mediaID = scan.nextInt();
                    reservation.setMediaId(mediaID);
                    System.out.print("Type the patronID: ");
                    patronID = scan.nextInt();
                    reservation.setPatronId(patronID);
                    reservation = reservation_collection.deleteReservation(reservation);
                    if (reservation != null) {
                        System.out.println("The following reservation was cancelled:\n"
                                + "Media ID: " + reservation.getMediaId()
                                + "Patron ID: " + reservation.getPatronId());
                    } else {
                        return false;
                    }
                    break;
                case 3:
                    if (reservation_collection.viewLibReserveList().toString().equals("")) {
                        System.out.println("No reservations were made");
                    } else {
                        System.out.println(reservation_collection.toString());
                    }
                    break;
                case 4:
                    // Not implemented yet
                    break;
                case 0:
                    return true;
                default:
                    System.out.println("Type a valid option");
                    break;
            }

        } while (true);
    }

    //Edit by Ben 5/11
    public static void changePassword(Login login) {
        Scanner scan = new Scanner(System.in);
        String curPass;
        for (int i = 0; i < 3; i++) {
            System.out.println("Please enter your current password");
            curPass = scan.next();
            if (login.getPassword().equals(curPass)) {
                System.out.println("Please enter your new password:");
                String newPass = scan.next();
                login.setPassword(newPass);
                if (LoginCollection.updateStaffLogin(login.getId(), login.getUsername(), login.getPassword())) {
                    System.out.println("Password Change Successful!!");
                }
                break;
            }
            System.out.println("Incorrect Password");
        }

    }

}
