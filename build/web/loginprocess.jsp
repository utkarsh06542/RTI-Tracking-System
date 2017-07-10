<%-- 
    Document   : loginprocess
    Created on : 23 Jun, 2017, 4:16:49 PM
    Author     : Utkarsh Prabhakar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BSL : RTI Track System</title>
        <LINK REL="SHORTCUT ICON" HREF="favicon.ico" />
    </head>
    <body>
        <jsp:include page="index.jsp"></jsp:include>  

        <%@page import="bean.LoginDao"%>  
        <jsp:useBean id="obj" class="bean.LoginBean"/>  

        <jsp:setProperty property="*" name="obj"/>  

        <% try {
                if (obj.getUname() == null) {
                    RequestDispatcher rd = request.getRequestDispatcher("ErrorPage.jsp");
                    rd.forward(request, response);
                } else {
                    boolean status = LoginDao.validate(obj);
                    if (status) {
                        out.println("<h2>You are successfully logged in</h2><br>Your profile couldn't be loaded because of database connection problem<br>Please try again !");
                        session.setAttribute("session", "TRUE");
                        session.setAttribute("user", obj.getUname());
                        session.setAttribute("password", obj.getPass());
                        request.setAttribute("access", "rti");
                        RequestDispatcher rd = request.getRequestDispatcher("Profile.jsp");
                        rd.forward(request, response);

                    } else {
                        out.print("<h2>Sorry, wrong email or password.</h2><br>Please try again!");
                    }
                }
            } catch (Exception e) {
                System.out.print("Error:" + e);
            }
        %>  


    </body>
</html>
