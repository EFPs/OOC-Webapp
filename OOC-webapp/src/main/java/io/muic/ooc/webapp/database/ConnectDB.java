package io.muic.ooc.webapp.database;

import java.sql.*;

/**
 * Created by Boat on 3/5/2018 AD.
 */
public class ConnectDB {

    static Connection con;

    public ConnectDB() {

    }

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OOCWebapp","boat","boatpassword");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


}


