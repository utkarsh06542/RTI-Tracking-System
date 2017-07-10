<%-- 
    Document   : AdminPage
    Created on : 28 Jun, 2017, 10:57:42 AM
    Author     : Utkarsh Prabhakar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% try {
        if (session.getAttribute("user") == null) {
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
        <title>BSL : RTI Tracking System</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/dropdown.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <LINK REL="SHORTCUT ICON" HREF="favicon.ico" />
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <style>
            .edit{
                width:150px;
            }
            #insert_table td{
                padding:7px;
            }
            .footer {
                position: absolute;
                right: 0;
                bottom: 0;
                left: 0;
                padding: 1rem;
                background-color: #efefef;
                text-align: center;
            }
        </style>
        <script>
            $(document).ready(function () {
                var today = new Date();
                $('.datepicker').datepicker({
                    format: 'mm-dd-yyyy',
                    autoclose: true,
                    endDate: "today",
                    maxDate: today
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });


                $('.datepicker').keyup(function () {
                    if (this.value.match(/[^0-9]/g)) {
                        this.value = this.value.replace(/[^0-9^-]/g, '');
                    }
                });

               
            });
        </script>               
    </head>
    <body>
        <jsp:include page="UserMenu.jsp"></jsp:include><br><br><br>
        <% if (request.getAttribute("data") != null) {
                out.print("<h4>" + request.getAttribute("data") + "</h4>");
            }%>
        <div id="main_body" style="margin-left:2%;">
            <h3>Insert New Record: </h3>
            <a href="Profile.jsp" role="button" class="btn btn-danger btn-lg" style="float:right;">Go Home</a>

            <form action="insertProcess.jsp" method="POST" id="insert_record_form">
                <table id="insert_table">

                    <tr><td>Name :</td><td><input type="text" name="name" required></td></tr>
                    <tr><td>Receiving Channel :</td><td><input type="text" name="receivingChannel" required></td></tr>
                    <tr><td>Address :</td><td><input type="text" name="address" required></td></tr>
                    <tr><td>Ref. Number :</td><td><input type="text" name="rtiReference" required></td></tr>
                    <tr><td>Fee Detail(in Rs.) :</td><td><input type="number" id="fee" name="fee" value="10" readonly="readonly" required></td></tr>
                    <tr><td>Subject Matter :</td><td><textarea name="subject" form="insert_record_form" required>Write subject matter here...</textarea></td></tr>
                    <tr><td>RTI Type :</td><td><div class="form-group" required>
                                <select class="form-control" id="RtiType" name="rtiType">
                                    <option>Normal</option>
                                    <option>Life and Liberty</option>
                                </select>
                            </div></td></tr>
                    <tr><td>Receiving Date :</td><td><input type="text" class="datepicker" name="datepicker" required></td></tr>
                    <tr><td>Forward To :</td><td><input type="text" name="forwardList" required></td></tr>
                    <!--<tr><td>Forward To :</td><td><select multiple><option value="abc">abc</option><option value="sda">sda</option><option value="opel">Opel</option><option value="audi">Audi</option></select></td></tr>-->
                    <tr  style="display: none"><td>Active User :</td><td><input type="text" name="user" value="<% out.print(session.getAttribute("user"));%>"></td></tr>
                    <tr><td><button type="submit" class="btn btn-success btn-lg">Submit</button></td><td></td></tr>

                </table>
            </form>
        </div>
    </body>
</html>
