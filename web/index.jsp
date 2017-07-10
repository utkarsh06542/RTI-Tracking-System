<%-- 
    Document   : index
    Created on : 23 Jun, 2017, 3:45:09 PM
    Author     : Utkarsh Prabhakar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BSL : RTI Track System</title>
        <link rel="stylesheet" type="text/css" href="css/stylesheet1.css">
        <LINK REL="SHORTCUT ICON" HREF="favicon.ico" />
    </head>
    <body>
    <center><div class="intro" style="top:0px;"><font size=6><u>RTI files Track System</u></font><button onclick="document.getElementById('login_modal').style.display = 'block'" style="width:auto;margin-left:10px;">Login</button>
        </div></center>
    <!-- Login modal -->
    <div id="login_modal" class="modal">
        <br/>  
        <form class="modal-content animate" action="loginprocess.jsp" method="post">
            <div class="imgcontainer">
                <span onclick="document.getElementById('login_modal').style.display = 'none'" class="close" title="Close Modal">&times;</span>

            </div>

            <div class="container">

                <label><b>Username</b></label>
                <input type="text" placeholder="Enter 6-digit Staff Number" name="uname" maxlength="6" autofocus required>

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="pass" required>

                <button type="submit">Login</button>
                <input type="hidden" value="rti" name="access">
            </div>

            <div class="container" style="background-color:#f1f1f1">
                <button type="button" onclick="document.getElementById('login_modal').style.display = 'none'" class="cancelbtn">Cancel</button>
                <span class="psw">Forgot <a href="#">password?</a></span>
            </div>
        </form>
    </div>

    <script>
    // Get the login modal
        var modal = document.getElementById('login_modal');

    // When the user clicks anywhere outside of the login modal, close it
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>

</body>
</html>

