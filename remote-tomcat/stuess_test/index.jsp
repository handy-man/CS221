<%
session.setAttribute("name","stuess_test.jsp");
Object username = session.getAttribute("name");
%>

<html>
<body>
	<h1><%= username %></h1>
</body>
</html>
