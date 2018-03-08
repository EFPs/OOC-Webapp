package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.database.ConnectDB;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Boat on 3/7/2018 AD.
 */
public class DeleteServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    protected void doGet(HttpServletRequest request, HttpServletResponse respond)throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if(!authorized){
            System.out.println("Sent to login");
            request.getSession().setAttribute("error","You need to login first.");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.include(request, respond);
            respond.sendRedirect("/login");
        }else {

            String deleteUsername = request.getParameter("deleteName");
            if(deleteUsername.equals(request.getSession().getAttribute("username"))){
                request.getSession().setAttribute("error","You CAN'T delete yourself.");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/users.jsp");
                rd.include(request, respond);
                respond.sendRedirect("/users");
            }else{
                System.out.println("Delete Username are "+deleteUsername);
                request.getSession().setAttribute("error","");
                request.getSession().setAttribute("delete",deleteUsername);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/delete.jsp");
                rd.include(request, respond);
            }


        }



    }

    protected void doPost(HttpServletRequest request, HttpServletResponse respond)throws ServletException, IOException{

        try{
            String sql = "DELETE FROM Members WHERE user=? ;";
            Connection con = ConnectDB.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,(String)request.getSession().getAttribute("delete"));
            preparedStatement.executeUpdate();
            request.getSession().setAttribute("error","Successfully deleted user \""+ request.getSession().getAttribute("delete")+"\".");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/users.jsp");
            rd.include(request, respond);
            respond.sendRedirect("/users");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getMapping() {
        return "/delete";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
