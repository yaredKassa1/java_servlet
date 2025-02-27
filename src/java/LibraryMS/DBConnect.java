/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryMS;

import java.sql.*;

public class DBConnect {
     public static Connection getConnection() {
        Connection con =null;
        String url="jdbc:mysql://localhost:3306/LibraryMS";
        String username="root";
        String password="";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,username,password);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return con;
    }
}
     
    