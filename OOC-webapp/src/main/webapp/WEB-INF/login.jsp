
<head><title>Login</title></head>

<style>
body {font-family: Arial, Helvetica, sans-serif;}

.btn {
    	width = 100%
        border: none; /* Remove borders */
        color: white; /* Add a text color */
        padding: 14px 60px; /* Add some padding */
        cursor: pointer; /* Add a pointer cursor on mouse-over */
    }

input[type=text]{
    	width: 20%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-top: 6px;
        margin-bottom: 16px;
        resize: vertical;

    }

    input[type=password]{
    	width: 20%;
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
            width: 100%;
            background-color: rgba(242,242,242,0.5);
        }

    .login {
        background-color: rgba(73,80,157,0.7);
        width = 100%
        }

    .login:hover {background-color: rgba(73,80,157,1.0);}

    .regis {
            background-color: rgba(225,53,53,0.7);
            width = 100%
            }
    .regis:hover {background-color: rgba(225,53,53,1.0);}

.bgImg {

}

</style>

<html>

<div  class = "container" style ="text-align: center;">
<div  style="padding: 10%">
    <body class = "bgImg">
        <h2>Login</h2>
        <p>${error}</p>
        <div>
        <form action="/login" method="post">
            Username:<br/>
            <input type="text" name="username"/>
            <br/>
            Password:<br/>
            <input type="password" name="password">
            <br><br>
            <button class="btn login">Login</button>
        </div>
        <div >
        </form>
        <p>Or sign up here</p>
        <form action="/register" method="get">
            <button class="btn regis">Sign up</button>
         </form>
        </div>
    </body>
</div>
</div>
</html>
