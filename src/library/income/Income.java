/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.income;
import java.util.Date; 
/**
 *Income Class <BR>
 * <p>This class is used to store the 3 different types of incomes
 * that are made by the library. This includes donations, late fees, and damage
 * payments</p> 
 * @author Elbin Martinez, Khizar Alvi, Adnan Alam
 */
public class Income {
    
    private Date datePayed;
    private char type;
    private String patronId;
    private double amountPaid;
    
    /**
     *Income Constructor <BR>
     * Creates a Income object that stores 
     * when a payment was made, what kind of payment
     * was it, and who payed it
     * @param theDay - Date, object of the date it was paid
     * @param typeOfPay - char, representing the type of payment (late fee, Damages, or Donations)
     * @param id - int, the id of the patron that made payment. 
     */
    public Income(String iD, char typeOfPay, double amount,Date theDay )// will also include patrons id
    {
        datePayed = theDay;
        type = typeOfPay;
        patronId = iD;
        amountPaid = amount;
    }

    /**
     * @Author Elbin Martinez
     *getDate Method-- <BR> 
     * A function used to get the date payed for 
     * any type of payment 
     * @return Date, The date that the payment was made
     */ 
    public Date getDate()
    {
        return datePayed;
    }
    
    /**
     * @Author Elbin Martinez
     * getType Method-- <BR>
     * This method is used to return the type 
     * of payment was made
     * @return char - the represents the type(g is donation, d is damages, and l for late fee) 
     */
    public char getType()
    {
        return type;
    }
    
    /**
     * @Author Elbin Martinez
     * getId Method-- <BR>
     * This method returns the id of the patron
     * that made the payment
     * @return int, id of patron who made payment
     */
    public String getID()
    {
        return patronId;
    }
    
    /**
     * @Author Elbin Martinez
     * getAmountPaid Method--
     * This method will return a double 
     * representing the amount that was paid
     * @return double, with the amount that was paid
     */
    public double getAmountPaid()
    {
    	return amountPaid; 
    }
    
    /**
     * @Author Elbin Martinez
     * toString Method-- <BR>
     * This method will return the status of the
     * object by returning a string with all information
     * @return String, Returns a String with the status of the object 
     */
	public String toString()
    {
    	String str = "";
    	String typeIn = "";
    	
    	if (type == 'g')
    		typeIn = "donation";
    	else if (type == 'd')
    		typeIn = "damages";
    	else if (type == 'l')
    		typeIn = "late fee";
    	
    	str = "\nPatron Id: " + patronId + "\nDate: " + datePayed
    			+ "\nType: " + typeIn + "\nAmount Paid: " + amountPaid + "\n";
    	return str;
    }
    
}