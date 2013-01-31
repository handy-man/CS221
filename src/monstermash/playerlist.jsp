<%@include file="includes/header.jsp" %>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://74.53.183.226/handyman_monster"
	user="handyman_group07"
	password="3213560921*+*" />
	
<sql:query dataSource="${snapshot}" var="result">SELECT * from player;</sql:query>

<h2>Players</h2>
<table>
    <tr>
        <th>Email</th>
    </tr>
    <c:forEach var="row" items="{result.rows}">
    <tr>
    	<td>c:out value="${row.email}</td>
    </tr>
    </c:forEach>
</table>
<%@include file="includes/footer.jsp" %>
