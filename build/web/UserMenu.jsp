<%-- 
    Document   : UserMenu
    Created on : 28 Jun, 2017, 9:47:08 AM
    Author     : Utkarsh Prabhakar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: #245269;">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="Profile.jsp" data-toggle="tooltip" data-placement="bottom" title="Home">RTI Tracking Portal</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar" >
                    <ul class="nav navbar-nav">
                        
                        <li class="active" ><a href="AdminPage.jsp">Administrator</a></li>
                        
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="SignOut.jsp" style="background-color: #2aabd2;"><font color="white" size="3"><span class="glyphicon glyphicon-log-in"></span> SignOut</font></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        
<div class="footer">Developed by <strong>Utkarsh Prabhakar</strong>. <strong>&#169;</strong> Maintained by <strong>C&IT, Bokaro Steel Plant, SAIL</strong></div>
