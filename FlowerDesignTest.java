/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Lenovo
 */
public class FlowerDesignTest {



    public FlowerDesignTest() {
    }

    @Before
    public void setUp() {
        
    }

    /**
     * Test of getData method, of class FlowerDesign.
     */

    /**
     * Test of getID method, of class FlowerDesign.
     */

    @Test
    public void btnConfirmActionPerformedbtnConfirmActionPerformed() {
//        System.out.println("btnConfirmActionPerformed");

        try (Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/FlowerShop", "nbuser", "nbuser")) {

            try (Statement stCheck = connection.createStatement()) {
                connection.setAutoCommit(false);

                stCheck.executeUpdate("DELETE FROM ARRANGEMENT");

                String ID = "A0001";
                String style = "Oval Flower Arrangement";
                String size = "Large";
                String accessories = "Sea Shells, Sea Shells, Decorative Butterflies";
                String deliOpt = "normal";
                String price = "99.0";
                String flowers = "Football Moms, Alstroemeries, Alstroemeries";
                String status = "PENDING";
                String custID = "C0001";
                PreparedStatement pst = connection.prepareStatement("INSERT INTO ARRANGEMENT VALUES(?,?,?,?,?,?,CAST(? as DOUBLE),?,?)");
                pst.setString(1, ID);
                pst.setString(2, style);
                pst.setString(3, size);
                pst.setString(4, accessories);
                pst.setString(5, flowers);
                pst.setString(6, deliOpt);
                pst.setString(7, price);
                pst.setString(8, status);
                pst.setString(9, custID);

                pst.executeUpdate();
                try (ResultSet rs = stCheck.executeQuery("SELECT * FROM ARRANGEMENT")) {
                    assertTrue(rs.next());
                    assertEquals(status,rs.getString("ARR_STATUS"));
                    assertFalse(rs.next());
                    System.out.println("STATUS IN DATABASE MATCH WITH DEFAULT STATUS.");
                }
                
            } finally {
                connection.rollback();
            }

        } catch (SQLException e) {
            fail(e.toString());
        }

    }

    /**
     * Test of main method, of class FlowerDesign.
     */
    

}
