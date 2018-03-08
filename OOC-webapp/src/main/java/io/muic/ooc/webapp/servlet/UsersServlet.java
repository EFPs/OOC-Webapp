package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Boat on 3/6/2018 AD.
 */
public class UsersServlet extends HttpServlet implements Routable {


    private static final long serialVersionUID = 1L;
    private SecurityService securityService;

    public UsersServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse respond)throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if(!authorized){
            System.out.println("Sent to login");
            request.getSession().setAttribute("error","You need to login first");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.include(request, respond);
            respond.sendRedirect("/login");
        }else{
//            request.getSession().setAttribute("error","");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/users.jsp");
            rd.include(request, respond);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse respond)throws ServletException, IOException{
        request.getSession().setAttribute("error","");
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/users.jsp");
        rd.include(request, respond);

    }


    @Override
    public String getMapping() {
        return "/users";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }


}
