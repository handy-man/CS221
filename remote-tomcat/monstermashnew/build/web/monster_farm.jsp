<%-- 
    Document   : monster_farm
    Created on : Jan 29, 2013, 3:41:53 PM
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
        <div id="page-banner">
        </div>
        <!-- Top menu bar.
        -->
        <div id="container-menu">
            <ul>
                <!-- if session is active can go somewhere else? -->
                <li><a href="MyProfile.jsp">MyProfile</a></li>
                <li><a href="notification.html">Notofication</a></li>
                <li><a href="monsters.html">Monsters</a></li>
                <li><a href="highscores.html">Highscores</a></li>
                <li class="logout"><a href="index.html">Log out</a></li>
            </ul>
        </div>
        <!-- Spacing between top menu bar and main container. 
        -->
        <div style="clear:both"><p>&nbsp;</p></div>
        <!-- Main  container. 
        -->
        <div id="container">
            <p class="money">MONEY: <%= session.getAttribute("money") %></p>
            <hr />
            <!--Freinds pan
            -->
            <p><table style="float:right; border: 1px solid #000;height:300px;width:215px;">
                    <tr>
                        <td colspan="2" style="text-align:top; height:40px; border-bottom: 1px solid #000;">FRIENDS: <span style="float:right;width:50px;">$</span></td>
                    </tr>
                    <tr><td style="width:135px;" colspan="2">Player 1 <span style="float:right; width:50px;">$ 900</span></td></tr>
                    <tr><td style="width:135px;" colspan="2">Player 2 <span style="float:right; width:50px;">$ 800</span></td></tr>
                    <tr><td style="width:135px;" colspan="2">Player 3 <span style="float:right; width:50px;">$ 2503</span></td></tr>
                    <tr><td style="width:135px;" colspan="2">Player 4 <span style="float:right; width:50px;">$ 321</span></td></tr>
                    <tr><td style="width:135px;" colspan="2">Player 5 <span style="float:right; width:50px;">$ 621</span></td></td></tr>
                    <tr><td style="width:135px;" colspan="2">Player 6 <span style="float:right; width:50px;">$ 123</span></td></tr>
                    <tr>
                        <td  style="height: 40px; border-top: 1px solid #000;" colspan="2">
                            <input class="textfield" type="text" name="username" maxlength="20"/>
                            <input type="submit" value="Add" />
                        </td>
                    </tr>
                </table>
                <!--Notofication pan 
                -->
                <table style="float:left; border: 1px solid #000; height:300px;width:570px; margin-top:-10px;border-collapse: collapse;">
                    <tr style="border: 1px solid #000; border-collapse:collapse;">
                        <td style="border: 1px solid #000; border-collapse:collapse; text-align:center; width:190px;">
                            <p><img src="person2.jpg"/></p>
                            <p><input type="submit" value="Stats" /></p>
                        </td>
                        <td style="border: 1px solid #000; border-collapse:collapse; text-align:center; width:190px;""> 
                            <p><img src="person1.jpg"/></p>
                            <p><input type="submit" value="Stats" /></p>
                        </td>
                        <td style="border: 1px solid #000; border-collapse:collapse; text-align:center; width:190px;"">
                            <p><img src="person2.jpg"/></p>
                            <p><input type="submit" value="Stats" /></p>
                        </td>
                    </tr>
                    <tr style="border: 1px solid #000;border-collapse:collapse; "">
                        <td style="border: 1px solid #000;border-collapse:collapse; text-align:center; width:190px;"">
                            <p><img src="person1.jpg"/></p>
                            <p><input type="submit" value="Stats" /></p>
                        </td>
                        <td style="border: 1px solid #000;border-collapse:collapse; text-align:center; width:190px;"">
                            <p><img src="person2.jpg"/></p>
                            <p><input type="submit" value="Stats" /></p>
                        </td>
                        <td style="border: 1px solid #000; border-collapse:collapse; text-align:center; width:190px;"">
                            <p><img src="person1.jpg"/></p>
                            <p><input type="submit" value="Stats" /></p>
                        </td>
                    </tr>
                </table>
            </p>
        </div>
        <div style="clear:both"></div>
</body>
</html>