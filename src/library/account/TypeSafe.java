package library.account;

import java.util.*;
import java.util.regex.*;

public class TypeSafe {

private static String garbage;

//TypeSafe NAME
public static String name(String prompt) {
	Scanner scan = new Scanner(System.in);
	boolean makeUpper, tryAgain, noSpace, hasPunc = false;
	String newName="";

	do {
		tryAgain = false;
		makeUpper = false;
		noSpace = false;
		hasPunc = false;
		newName = "";

		System.out.print(prompt);
		String userName = scan.nextLine();

		if (userName.equals("")) tryAgain = true;
		int nameLength = userName.length();

		for (int letterPos=0; letterPos < nameLength; letterPos++) {
			//assign the first character to tempLetter
			char tempLetter = userName.charAt(letterPos);

			//convert the character to an integer
			int tempIntChar = (tempLetter);

			//determine what kind of character it is
			String caseType = new String();

			if(tempIntChar >= 65 && tempIntChar <= 90) caseType = "uppercase";
			else if(tempIntChar >= 97 && tempIntChar <= 122) caseType = "lowercase";
			else if(tempIntChar == 32) caseType = "space";
			else caseType = "punctuation";

			if (caseType == "punctuation") {
				hasPunc = true;
				tryAgain = true;
				break;
			}
			else {
				if(letterPos == 0) {
					switch(caseType) {
						case "lowercase":
							makeUpper = true;
							break;

						case "space":
							noSpace = true;
							tryAgain = true;
							break;
					}
				}

				if (makeUpper == true) {
					tempIntChar -= 32;
					makeUpper = false;
				}

				if (caseType == "space") makeUpper = true;

				//convert integer back to character
				char tempChar = (char)tempIntChar;

				//parse new phrase (now scrambled)
				newName += tempChar;
			}
		}

		if (noSpace == true) System.out.println("You cannot have a space as the first letter. Try again.\n\n");
		if (hasPunc == true) System.out.print("\nSorry, you can't have punctation or digits in a name.\n\n");

	}while (tryAgain == true);
	return newName;
}

//TypeSafe Positive Integer above zero
public static int posInt(String prompt) {
	Scanner scan = new Scanner(System.in);
	int userInt;
        
	do {
		System.out.print(prompt);
		while (!scan.hasNextInt()) {
			garbage = scan.nextLine();
			System.out.print("\nThis is not valid.  It must be an integer.\n\n"
							+ prompt);
		}

		userInt = scan.nextInt();

		if(userInt < 1) {
			garbage = scan.nextLine();
			System.out.println("\nThe number cannot be 0 or a negative number.\n\n");
		}
	}while (userInt < 1);

return userInt;
}

public static String phone(String prompt) {
	Scanner scan = new Scanner(System.in);
	String userPhone;
        boolean tryAgain;
        
        //temp code
        do {
            
            tryAgain = false;
                        
            System.out.print(prompt);
            userPhone = scan.nextLine();
            
            userPhone = userPhone.replaceAll("[^0-9]+", "");
            
            if (userPhone.length()>10 || userPhone.length()<10){
                System.out.println("Your number you entered is invalid.\n\n");
                tryAgain = true;
            }
                       
        }while (tryAgain);
    
    return userPhone;
}

public static String address(String prompt) {
	Scanner scan = new Scanner(System.in);
	String userAddress;
        boolean tryAgain;
        
        //temp code
        do {
            tryAgain = false;
                        
            System.out.print(prompt);
            userAddress = scan.nextLine();
            
            if (userAddress.length()<10) {
                tryAgain = true;
                System.out.println("The data entered is too short to be a valid Street Address.\n\n");
            }
                
        }while(tryAgain);    
            
        
        //create Validation code
/*
        //possible validation code:
        
        
	do {
		System.out.print(prompt);
		while (!scan.hasNextInt()) {
			garbage = scan.nextLine();
			System.out.print("\nThis is not valid.  It must be an integer.\n\n"
							+ prompt);
		}

		userInt = scan.nextInt();

		if(userInt < 1) {
			garbage = scan.nextLine();
			System.out.println("\nThe number cannot be 0 or a negative number.\n\n");
		}
	}while (userInt < 1);
*/
return userAddress;
}

public static String email(String prompt) {
	Scanner scan = new Scanner(System.in);
	String userEmail;
        boolean tryAgain;
        
        do {
            tryAgain = false;

            System.out.print(prompt);
            userEmail = scan.nextLine();

            Pattern validEmail = 
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

            Matcher matcher = validEmail.matcher(userEmail);

            if(!matcher.find()) {
                tryAgain = true;
                System.out.println("The E-mail Address entered is invalid.\n\n");
            }
        }while(tryAgain);
        
return userEmail;
}

//TypeSafe Positive Integer including zero
public static int posIntZero(String prompt) {
	Scanner scan = new Scanner(System.in);
	int userInt;

	do {
		System.out.print(prompt);
		while (!scan.hasNextInt()) {
			garbage = scan.nextLine();
			System.out.print("\nThis is not valid.  It must be an integer.\n\n"
							+ prompt);
		}

		userInt = scan.nextInt();

		if(userInt < 0) {
			garbage = scan.nextLine();
			System.out.println("\nThe number cannot be a negative number.\n\n");
		}
	}while (userInt < 0);

return userInt;
}

//TypeSafe Positive Double above zero
public static double posDouble(String prompt) {
	Scanner scan = new Scanner(System.in);
	double userDouble;

	do {
		System.out.print(prompt);
		while (!scan.hasNextDouble()) {
			garbage = scan.nextLine();
			System.out.print("\nThis is not valid.  It must be a double.\n\n"
							+ prompt);
		}

		userDouble = scan.nextDouble();

		if(userDouble < 1) {
			garbage = scan.nextLine();
			System.out.println("\nThe number cannot be 0 or a negative number.\n\n");
		}
	}while (userDouble < 1);

return userDouble;
}

//TypeSafe Positive Double including zero
public static double posDoubleZero(String prompt) {
	Scanner scan = new Scanner(System.in);
	double userDouble;

	do {
		System.out.print(prompt);
		while (!scan.hasNextDouble()) {
			garbage = scan.nextLine();
			System.out.print("\nThis is not valid.  It must be a double.\n\n"
							+ prompt);
		}

		userDouble = scan.nextDouble();

		if(userDouble < 0) {
			garbage = scan.nextLine();
			System.out.println("\nThe number cannot be a negative number.\n\n");
		}
	}while (userDouble < 0);

return userDouble;
}



//TypeSafe Employee Title
public static String empClass(String prompt){
	Scanner scan = new Scanner(System.in);
	int checkLength = 0;
	boolean empClassBad;
	String garbage="", checkEmpClass="", empClassName = "";


	do {

		empClassBad = false;
		System.out.print(prompt);
		checkEmpClass = scan.next();

		checkEmpClass = checkEmpClass.toUpperCase();
		checkLength = checkEmpClass.length();
		char firstLet = checkEmpClass.charAt(0);

		if (!(   (firstLet == 'M' && checkLength == 1)
		      || (firstLet == 'S' && checkLength == 1)
		      || (firstLet == 'C' && checkLength == 1)
		      || checkEmpClass.equals("MANAGER")
		      || checkEmpClass.equals("STAFF")
		      || checkEmpClass.equals("CONTRACTOR")))
		{
			garbage = scan.nextLine();
			System.out.print("\nThis is not a valid Employment Class.\n\n");
			empClassBad = true;
		}
		else
		{
			empClassName = checkEmpClass;
			empClassBad = false;
		}
	}while (empClassBad);
	return empClassName;
}



}