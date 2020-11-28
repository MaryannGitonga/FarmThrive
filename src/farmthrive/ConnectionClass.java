/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmthrive;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class ConnectionClass {
    public  Connection connection;
    
    public Connection getConnection(){
        String dbName = "farmthrive";
        String username = "root";
        String password = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ dbName, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
