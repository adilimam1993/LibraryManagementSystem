/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.jdbc;

import java.sql.*;
import java.util.ArrayList;
import library.income.Income;

public class IncomeJDBC {

    private Connection conn;
    private Statement st; // Select data
    private PreparedStatement ps; // Update, add, or delete data
    private ResultSet rs;

    public IncomeJDBC() {

    }

    /**
     * Connect Method--<BR>
     * makes a connection to the income table
     */
    public void connect() {

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_system?autoReconnect=true&useSSL=false", "root", "rafa2012");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    /**
     * @Author Adnan Alam 
     * Insert Method<BR>
     * Inserts a income object to data base to
     * keep record.
     * @param theIncome - object to be inserted
     * @return boolean to see if successful or not
     */
    public boolean insert(Income theIncome) {

        try {
            connect();
            java.sql.Date sqlDate = new java.sql.Date(theIncome.getDate().getTime());

            // the mysql insert statement
            String query = " insert into income (patronId, type, amountPaid, date)"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, theIncome.getID());
            preparedStmt.setString(2, String.valueOf(theIncome.getType()));
            preparedStmt.setDouble(3, theIncome.getAmountPaid());
            preparedStmt.setDate(4, sqlDate);

            // execute the preparedstatement
            preparedStmt.execute();

            //conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return true;
    }

    /**
     * @Author Adnan Alam
     * View income by the type wanting to be viewed.
     * @param type char representing the type of income wished to view
     * @return 
     */
    public ArrayList<Income> view(char type) {
        ArrayList<Income> array = new ArrayList<Income>();

        try {
            connect();
            if (type == '*') {
                String s = "SELECT * FROM income WHERE date = CURDATE() - INTERVAL 1 DAY";
                st = conn.createStatement();
                rs = st.executeQuery(s);

            } else {
                String s = "SELECT * FROM income WHERE date = CURDATE() - INTERVAL 1 DAY and type = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(s);
                preparedStmt.setString(1, String.valueOf(type));
                rs = preparedStmt.executeQuery();
            }

            while (rs.next()) {
                System.out.println(rs.getString("type").charAt(0));
                array.add(new Income(rs.getString("patronId"), rs.getString("type").charAt(0), rs.getDouble("amountPaid"), rs.getDate("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

}
