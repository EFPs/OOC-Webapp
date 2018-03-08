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
import java.sql.*;

/**
 * Created by Boat on 3/7/2018 AD.
 */
public class EditServlet extends HttpServlet implements Routable {

//    String user;

    private SecurityService securityService;

    public EditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse respond)throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if(!authorized){
            System.out.println("Sent to login");
            request.getSession().setAttribute("error","You need to login first.");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.include(request, respond);
            respond.sendRedirect("/login");
        }else{
            System.out.println("Go to edit");
            request.getSession().setAttribute("error","");
            String editUsername = request.getParameter("editName");
            System.out.println("Username are "+editUsername);
            request.getSession().setAttribute("edit",editUsername);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
            rd.include(request, respond);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse respond)throws ServletException, IOException{

        try {
            String username = (String)request.getSession().getAttribute("edit");
            System.out.println("We edit ... "+username);
            String password = Encryption.encoder(request.getParameter("password"));
            String firstName = request.getParameter("FirstName");
            String lastName = request.getParameter("LastName");

            String getIDsql = "SELECT * FROM Members WHERE user = "+"\'"+request.getSession().getAttribute("edit")+"\'"+" ;";
            String sql = "UPDATE Members SET password=?,FirstName=?,LastName=? WHERE user=?;";


            System.out.println(getIDsql);
            Connection con = ConnectDB.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(getIDsql);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int colNum =metaData.getColumnCount();
            String userInfo[] = new String[colNum];
            if(rs.next()){
                for(int i =1 ; i<= colNum;i++){
                    userInfo[i-1] = rs.getString(i);
                }
            }



            preparedStatement = con.prepareStatement(sql);
            if(StringUtils.isBlank(password)){
                System.out.println("Password is blank");
                password = userInfo[2];
            }
            if(StringUtils.isBlank(firstName)){
                System.out.println("Firstname is blank");
                firstName = userInfo[3];
            }
            if(StringUtils.isBlank(lastName)){
                System.out.println("Lastname is blank");
                lastName = userInfo[4];
            }
            System.out.println("We edit FN: "+firstName+" LN: "+lastName+" PW: "+password);
                preparedStatement.setString(1,password);
                preparedStatement.setString(2,firstName);
                preparedStatement.setString(3,lastName);
                preparedStatement.setString(4,username);
                preparedStatement.executeUpdate();

                request.getSession().setAttribute("error","Add user successful.");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/users.jsp");
                rd.include(request, respond);
                respond.sendRedirect("/users");
//            PrintWriter out = respond.getWriter();
//            out.println("Registration Successful.");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getMapping() {
        return "/edit";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
