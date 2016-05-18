/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
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
    public static MediaAcademic academic = new MediaAcademic();
    public static MediaBook book = new MediaBook();
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
                        academic.setMediaType("A");
                        System.out.print("Type the title and press <ENTER>: ");
                        field = scan.nextLine();
                        academic.setMediaTitle(field);
                        System.out.print("Type the year of the publication and press <ENTER>: ");
                        field = scan.nextLine();
                        academic.setMediaYear(field);
                        System.out.print("Type the publisher name and press <ENTER>: ");
                        field = scan.nextLine();
                        academic.setAcademicPublisher(field);
                        System.out.print("Type the type of academic material and press <ENTER>: ");
                        field = scan.next();
                        academic.setCategory(field);
                        System.out.print("Type the cost of the material and press <ENTER>: ");
                        cost = scan.nextDouble();
                        skip = scan.nextLine();
                        academic.setMediaCost(cost);
                        System.out.print("Type the ISBN/DOI code: ");
                        field = scan.nextLine();
                        academic.setAcademicISBN(field);
                        System.out.print("Type the number of authors for the media: ");
                        numOfAuthors = scan.nextInt();
                        skip = scan.nextLine();

                        // Receive authors
                        for (int i = 0; i < numOfAuthors; i++) {
                            System.out.print("Type the author name: ");
                            field = scan.nextLine();
                            authors.add(field);
                        }
                        academic.setAuthors(authors);

                        // Add media
                        if (!media_jdbc.addMedia(academic)) {
                            return false;
                        } else {
                            System.out.println("Media was added successfully!");
                        }
                        break;
                    case 2:
                        book.setMediaType("B");
                        System.out.print("Type the title and press <ENTER>: ");
                        field = scan.nextLine();
                        book.setMediaTitle(field);
                        System.out.print("Type the year of the book and press <ENTER>: ");
                        field = scan.nextLine();
                        book.setMediaYear(field);
                        System.out.print("Type the publisher name and press <ENTER>: ");
                        field = scan.nextLine();
                        book.setPublisher(field);
                        System.out.print("Type the type of book and press <ENTER>: ");
                        field = scan.nextLine();
                        book.setCategory(field);
                        System.out.print("Type the edition and press <ENTER>: ");
                        field = scan.nextLine();
                        book.setBookEdition(field);
                        System.out.print("Type the volume and press <ENTER>: ");
                        field = scan.nextLine();
                        book.setBookVolume(field);
                        System.out.print("Type the ISBN and press <ENTER>: ");
                        field = scan.nextLine();
                        book.setIsbn(field);
                        System.out.print("Type the cost of the material and press <ENTER>: ");
                        cost = scan.nextDouble();
                        book.setMediaCost(cost);
                        System.out.print("Type the number of authors for the media: ");
                        numOfAuthors = scan.nextInt();
                        skip = scan.nextLine();

                        // Receive authors
                        for (int i = 0; i < numOfAuthors; i++) {
                            System.out.print("Type the author name: ");
                            field = scan.nextLine();
                            authors.add(field);
                        }
                        book.setAuthors(authors);

                        // Add media
                        if (!media_jdbc.addMedia(book)) {
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
        int mediaID;
        Scanner scan = new Scanner(System.in);

        if (searchingModule(media_jdbc)) {
            System.out.println("Type the media ID for the media you want to edit: ");
            mediaID = scan.nextInt();
            // retrieve data
        }
        return true;
    }

    public static boolean renewingModule() {
        int mediaID;
        int patronID;
        Scanner scan = new Scanner(System.in);

        System.out.println("Type the patron ID: ");
        patronID = scan.nextInt();
        // view all patron's checked out media
        System.out.println("Type the media ID to renew: ");
        mediaID = scan.nextInt();
        // renew media

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
