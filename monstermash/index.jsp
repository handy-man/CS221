<%@include file="includes/header.jsp" %>

<h1><%= new String("Hello World") %></h1>
<img src="static/images/person2.jpg">

<div id="login">
    <form action="/login" method="POST">
        <table>
          <tr>
            <th>Email</th>
            <td>
                <input name="email" type="text" placeholder="email"/>
            </td>
          </tr>
          <tr>
            <th>Password</th>
            <td><input name="email" type="text" placeholder="password"/></td>
          </tr>
          <tr colspan=2>
            <td><input name="login" type="submit" value="Login"/></td>
          </tr>
        </table>
    </form>
</div>

<%@include file="includes/footer.jsp" %>
