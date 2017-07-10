<%-- 
    Document   : insertProcess
    Created on : 30 Jun, 2017, 10:01:47 AM
    Author     : Utkarsh Prabhakar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BSL : RTI Track System</title>
    </head>
    <body>
        <%@page import="bean.LoginDao"%>  
        <jsp:useBean id="obj" class="bean.InsertRecord"/>  
        <jsp:setProperty property="*" name="obj"/>
        <% if(obj.getName()==null || obj.getUser()==null){
                    RequestDispatcher rd=request.getRequestDispatcher("ErrorPage.jsp");  
                rd.forward(request, response);
                }
            else{
            boolean status=LoginDao.insert(obj);
            if(status){
                RequestDispatcher rd=request.getRequestDispatcher("AdminPage.jsp"); 
                request.setAttribute("data","Record added successfully");
                rd.forward(request, response);
            }
            else{
                RequestDispatcher rd=request.getRequestDispatcher("AdminPage.jsp"); 
                request.setAttribute("data","Error in connection with Database");
                rd.forward(request, response);
            }
        }
        %>
    </body>
</html>
