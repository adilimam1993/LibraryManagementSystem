/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.account;    

import library.jdbc.*;

/**
 *
 * @author ookik
 */
public class patronTest {
    
    public static void main(String[] args) {
        //AccountJDBC.connect();
        //PatronAccount.createPatronAccount();
        PatronAccount p1 = AccountCollection.searchPatronByID("1");
        System.out.println("test");
        System.out.println(p1);
        
    }
}
