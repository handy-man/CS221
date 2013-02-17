<%-- 
    Document   : registration
    Created on : Jan 28, 2013, 1:32:29 AM
    Author     : adriangawryszewski
--%>

<%@page import="servlets.Registration_servlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%--
 ><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 --%>
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
        <div id="registration-page">
            <form action="Registration_servlet" method="post" >
                <h1 class="login-page-header">Register: </h1>
                <hr />
                <input class="textfield" value="e-mail address" type="text" name="email" maxlength="20" onclick="this.value=''"/> 
                <input class="textfield" value="repeat e-mail address" type="text" name="cemail" maxlength="20" onclick="this.value=''"/> 
                <input class="textfield" value="insert password" type="text" name="password" maxlength="20" onclick="this.value=''"/>
                <input class="textfield" value="repeat password" type="tex" name="cpassword" maxlength="20" onclick="this.value=''"/> 
                <button class="button">Register</button>
                <p class="error">${message}</p>
            </form>
        </div>	
    </body>
</html>	
