<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
body {font-family: Arial, Helvetica, sans-serif;}
</style>

<head><title>Edit Users</title></head>

<html>
    <head>
    <style>
    bgSolid {
        border: 1px solid black;
    }
    .btn {
    	width = 100%
        border: none; /* Remove borders */
        color: white; /* Add a text color */
        padding: 14px 60px; /* Add some padding */
        cursor: pointer; /* Add a pointer cursor on mouse-over */
    }

    input[type=text]{
    	width: 50%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-top: 6px;
        margin-bottom: 16px;
        resize: vertical;


    }

    input[type=password]{
    	width: 50%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-top: 6px;
        margin-bottom: 16px;
        resize: vertical;
    }

    .container {
        border-radius: 20px;
        width: 55%;
        background-color: rgba(242,242,242,0.5);
        padding: 20px;
    }

    .back {
         background-color: rgba(35,141,206,0.7);
         width = 100%
         }
        .back:hover {background-color: rgba(35,141,206,1.0);}

    .update {
    background-color: rgba(76,175,80,0.7);
    width = 100%
    }
    .success:hover {background-color: rgba(76,175,80,1.0);}


    </style>

    </head>
    <body style="text-align:center;">
    <div style="padding: 2% 10% 2% 30% ;text-align: center;">
    <div class = "container">
    <h2>Edit User - ${edit} - </h2>
            <p>${error}</p>
            <form action="/edit" method="post">
                <label>First name</label>
                <br><br>
                <input type="text"  name="FirstName"  placeholder="Insert Your Name.."/>
                <br><br>
                <label f>Last name</label>
                <br><br>
                 <input type="text" name="LastName" placeholder="Insert Your Last Name.."/>
                <br><br>
                <label >Password</label>
                <br><br>
                <input type="password" name="password" placeholder="Insert Your Password..">

                <br><br>

                <button class="btn update">Update</button>


            </form>
            <form action="/users" method="get">

                        <button class="btn back">Back</button>
                        </form>

    </div>
    </div>
    </body>

</html>
