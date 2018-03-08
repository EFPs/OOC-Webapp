
<head><title>Delete</title></head>

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

    .del {
        background-color: rgba(236,6,6,0.7);
        width = 100%
        }

    .del:hover {background-color: rgba(236,6,6,1.0);}

    .back {
            background-color: rgba(35,141,206,0.7);
            width = 100%
            }
    .back:hover {background-color: rgba(35,141,206,1.0);}


</style>

<html>
<div  class = "container" style ="text-align: center;">
<div  style="padding: 10%">
    <body >
        <h2>Do you want to delete user - ${delete} - ?</h2>
        <p>${error}</p>
        <div>
        <form action="/delete" method="post">
            <button class="btn del">YES, DELETE IT</button>
        </div>
        <div >
        </form>
        <br><br>
        <form action="/users" method="get">
            <button class="btn back">NO!! GO BACK</button>
         </form>
        </div>
    </body>
</div>
</div>
</html>
