<%-- 
    Document   : Profile
    Created on : 23 Jun, 2017, 6:11:23 PM
    Author     : Utkarsh Prabhakar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bean.LoginDao"%> 
<%@page import="bean.OtherProcesses"%> 
<% try {
        if (session.getAttribute("user") == null || ((String) request.getAttribute("access")).compareTo("rti") != 0) {
            RequestDispatcher rd = request.getRequestDispatcher("ErrorPage.jsp");
            rd.forward(request, response);
        }
    } catch (Exception e) {

    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BSL: RTI Tracking System</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/dropdown.css">
        <LINK REL="SHORTCUT ICON" HREF="favicon.ico" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/Profile_Stylesheet.css">
        <script src="js/ProfileJS.js"></script>
        <script>
            //function for searching names/address/subject
            function myFunction() {
                var input, filter, table, tr, td, i;
                input = document.getElementById("myInput");
                filter = input.value.toUpperCase();
                table = document.getElementById("table1");
                tr = table.getElementsByTagName("tr");
                for (i = 1; i < tr.length; i++) {
                    td1 = tr[i].getElementsByTagName("td")[1];
                    td3 = tr[i].getElementsByTagName("td")[3];
                    td4 = tr[i].getElementsByTagName("td")[4];
                    td6 = tr[i].getElementsByTagName("td")[6];
                    if (td1 && td3 && td6) {
                        if ((td1.innerHTML.toUpperCase().indexOf(filter) > -1) || (td3.innerHTML.toUpperCase().indexOf(filter) > -1) || (td4.innerHTML.toUpperCase().indexOf(filter) > -1) || (td6.innerHTML.toUpperCase().indexOf(filter) > -1) ) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }
        </script>
    </head>
    <body>
        <jsp:include page="UserMenu.jsp"></jsp:include><br><br><br>
            <div id="subjectContent" style="width:100%;"></div>
            
            <div id="mainTable">
            <%                    String user = (String) session.getAttribute("user");
                out.print(OtherProcesses.getProfileTable(user));
                LoginDao.closeAll();
            %>
        </div>
        <!-- Modal for viewing/adding "forward to" List-->
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog modal-sm">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <center><h4 class="modal-title">Forward To List</h4></center>
                    </div>
                    <div class="modal-body">
                    </div>
                    <div class="modal-footer">
                        <button id="addinput" class="btn-info btn btn-sm" style="float:left;">add</button>
                        <button id="submit" class="btn-success btn btn-sm">save</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>
