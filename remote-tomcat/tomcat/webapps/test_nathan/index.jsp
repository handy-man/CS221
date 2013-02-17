<%-- 
    Document   : index
    Created on : Jan 27, 2013, 11:39:02 PM
    Author     : adriangawryszewski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
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
        <div id="login-container">
            <form action="Login_servlet" method="post" >
                <h1 class="login-page-header">Log in:</h1>
                <hr />
                <input class="textfield" value="Email" type="text" name="email" maxlength="40" onclick="this.value=''"/>
                <input class="textfield" value="Password" type="text" name="password" maxlength="40" onclick="this.value=''"/>
                <button class="button">Login</button>
                <p class="error">${message}</p>
                <p><%= session.getAttribute("money")%></p>
            </form>
        </div>     
        <div id="register-container">
            <form action="registration.jsp" method="post" >
                <h1 class="login-page-header">Register:</h1>
                <hr /><br /><br />
                <button class="button">Register</button>
            </form>
        </div>
    </body>
</html>	
