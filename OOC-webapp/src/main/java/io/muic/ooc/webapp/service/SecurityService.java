/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.service;

import io.muic.ooc.webapp.database.ConnectDB;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;


public class SecurityService {
    

    
    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
//       return (username != null && userCredentials.containsKey(username));
        System.out.println(username);
        return username!=null;
    }

    public boolean userUnique(String username, HttpServletRequest request){
        String sql = "SELECT * FROM Members WHERE user=?";
        Connection con = ConnectDB.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String dbUser = rs.getString("user");
                System.out.println("USER are: "+dbUser);
                System.out.println("NEW USER are: "+username);
                if(username.equals(dbUser)){
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean authenticate(String username, String password, HttpServletRequest request) {
        String sql = "SELECT * FROM Members WHERE user=? ";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String dbUser = rs.getString("user");
                String dbPass = rs.getString("password");
//                password.equals(dbPass)
                if(username.equals(dbUser) && Encryption.hashChecker(password,dbPass)){
                    request.getSession().setAttribute("username",username);
                    return true;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

//
//        String passwordInDB = userCredentials.get(username);
//        boolean isMatched = StringUtils.equals(password, passwordInDB);
//        if (isMatched) {
//            request.getSession().setAttribute("username", username);
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }
    
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
    
}
