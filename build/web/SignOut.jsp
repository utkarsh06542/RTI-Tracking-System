<%-- 
    Document   : SignOut
    Created on : 23 Jun, 2017, 9:50:11 PM
    Author     : Utkarsh Prabhakar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% try {
        if(session.getAttribute("user")==null){
           RequestDispatcher rd=request.getRequestDispatcher("ErrorPage.jsp"); 
           rd.forward(request, response); 
        }
    } catch (Exception e) {
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BSL : RTI Track System</title>
        <LINK REL="SHORTCUT ICON" HREF="favicon.ico" />
    </head>
    <body>
    
    <center><div style="position:absolute;bottom:0px;">
        <% if(session.getAttribute("user")!=null){out.print("<h2>Successfully signed-out ,"+session.getAttribute("user")+"!");}else{}session.setAttribute("session","FALSE");session.invalidate(); %></h2></div></center>
    <jsp:include page="index.jsp"></jsp:include>  
    </body>
</html>
