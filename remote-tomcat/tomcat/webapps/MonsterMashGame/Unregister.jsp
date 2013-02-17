<%-- 
    Document   : Unregister.jsp
    Created on : Jan 30, 2013, 10:25:32 AM
    Author     : adriangawryszewski
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String url="index.jsp";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>MonsterMash - Game</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="description" content="Monster Mash online Game" />
        <meta name="keywords" content="Game, Monster, Mash, on-line, gaming, fights" />
        <meta name="author" content="Group_07" />
        <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
    </head>
    <body>
        <div id="login-page-banner">
        </div>
        <div id="unregistration-page">
            <p class="error">
                Your account was removed. 
            </p>
            <p><a href="<%=url%>">Main page</p>
        </div>	
    </body>
</html>	
