/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author souls
 */
public class DBConnection {
    public static Connection getconnection(){
        Connection con = null;
        
        try {
            Class.forName("org.apache.derby.iapi.jdbc.AutoloadedDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/fee_management","soham","2986");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
