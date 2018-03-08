package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.database.ConnectDB;
import io.muic.ooc.webapp.service.Encryption;
import io.muic.ooc.webapp.service.SecurityService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Boat on 3/5/2018 AD.
 */
public class RegisterServlet extends HttpServlet implements Routable {

    private static final long serialVersionUID = 1L;
    private SecurityService securityService;

    public RegisterServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse respond)throws ServletException, IOException{
        boolean authorized = securityService.isAuthorized(request);
        if(!authorized){
            System.out.println("Sent to login");
            request.getSession().setAttribute("error","You need to login first.");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.include(request, respond);
            respond.sendRedirect("/login");
        }else{
            System.out.println("Go to regis");
            request.getSession().setAttribute("error","");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
            rd.include(request, respond);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse respond)throws ServletException, IOException{

        try {
            String username = request.getParameter("user");
            String password = Encryption.encoder(request.getParameter("password"));
            String firstName = request.getParameter("FirstName");
            String lastName = request.getParameter("LastName");
            if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
                System.out.println("User pass is empty");
                request.getSession().setAttribute("error","Username and Password CAN'T be empty.");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
                rd.include(request, respond);
//                respond.sendRedirect("/register");

            }else {

                if(!securityService.userUnique(username,request)){
                    System.out.println("Username is already used..");
                    request.getSession().setAttribute("error","Username Taken.");
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
                    rd.include(request, respond);
                }else {
                    System.out.println("User pass is good");
                    String sql = "INSERT INTO Members(user,password,FirstName,LastName) value(?,?,?,?)";
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OOCWebapp","boat","boatpassword");

//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8082/OOCWebapp","boat","boatpassword");
                    Connection con = ConnectDB.getConnection();
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,password);
                    preparedStatement.setString(3,firstName);
                    preparedStatement.setString(4,lastName);
                    preparedStatement.executeUpdate();

                    request.getSession().setAttribute("error","Add user successful.");
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/users.jsp");
                    rd.include(request, respond);
                    respond.sendRedirect("/users");
//            PrintWriter out = respond.getWriter();
//            out.println("Registration Successful.");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getMapping() {
        return "/register";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
       this.securityService = securityService;
    }
}
