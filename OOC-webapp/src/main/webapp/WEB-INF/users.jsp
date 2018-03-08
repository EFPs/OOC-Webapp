<%@ page import="io.muic.ooc.webapp.servlet.UsersServlet" %>
<%@ page import="io.muic.ooc.webapp.database.ConnectDB" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<style>

.btn {
    	width = 75%
        border: none; /* Remove borders */
        color: white; /* Add a text color */
        padding: 14px 60px; /* Add some padding */
        cursor: pointer; /* Add a pointer cursor on mouse-over */
    }

.lout {
             background-color: rgba(73,72,72,0.7);
            width = 100%
            }
.lout:hover {background-color: rgba(73,72,72,1.0);}

.regis {
            background-color: rgba(225,53,53,0.7);
            width = 100%
            }
.regis:hover {background-color: rgba(225,53,53,1.0);}

body {font-family: Arial, Helvetica, sans-serif;}
.UserTable{
    text-align:center;
}

th{
width: 27%;
}

tr:nth-child(even) {background-color: #f2f2f2;}

.container {
        border-radius: 5px;
        background-color: rgba(242,242,242,0.6);
        padding: 10px 30px 10px 30px ;
    }
.btnAction {
  text-align:center;
  padding-left:20%;
  padding-right: 20%;
}

.btnEditDel {
  text-align:left;

}

.logout {
float: left;
}

.addUser {
float: right;
}

.eb {
            background-color: rgba(69,185,242,0.7);
            width = 10%
            }
.eb:hover {background-color: rgba(69,185,242,1.0);}

.db {
            background-color: rgba(236,6,6,0.7);
            width = 10%
            }
.db:hover {background-color: rgba(236,6,6,1.0);}

</style>

<body style="text-align:center;" >

<scr

<br><br>
<h2>All Users</h2>
<h3>You Login as ${username}</h3>
<p>${error}</p>
<dir class= "container">
<table align="center">
<tr>
<th>"ID"</th>
<th>"Username"</th>
<th>"First name"</th>
<th>"Last name"</th>
</tr>

<%

Connection conn = ConnectDB.getConnection();

        ResultSet r;
        int counter=1;
        String sql = "SELECT id, user, FirstName, LastName FROM Members ;";
        PreparedStatement ps = conn.prepareStatement(sql);

        r = ps.executeQuery();
        ResultSetMetaData metaData = r.getMetaData();
        while(r.next())
        {
            %>

                <tr>

                 <%
                 for(int i = 1; i<=metaData.getColumnCount();i++)
                    { %>
                     <td>
                     <%= r.getString(i)%> <br>
                      <br> <br>
                     </td>
                <%
                    }
                %>
                <td>
                <div class="btnEditDel">

                                <div class="logout">
                                     <form action="/edit" method="get">
                                     <button class="btn eb" type="submit" name="editName" value = <%= r.getString(2)%>>Edit</button>
                                     </form>
                                </div>
                                <div class="adduser">
                                        <form action="/delete" method="get">

                                        <button class="btn db" type="submit" name="deleteName" value = <%= r.getString(2)%>>Delete</button>
                                     </form>
                                </div>
                                <div>
                </td>

                </tr>
            <%
        }
%>


</table>
</dir>
<div class="btnAction">
    <div class="logout">
        <form action="/logout" method="get">
                    <button class="btn lout">Logout</button>
        </form>
</div>
    <div class="addUser" >
        <form action="/register" method="get">
                    <button class="btn regis">Add User</button>
        </form>
</div>
</div>
</body>
</html>
