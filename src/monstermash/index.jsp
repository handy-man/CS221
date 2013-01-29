<%@include file="includes/header.jsp" %>
<script>

function validateForm()

{
	
	var x=document.forms["form"] ["email"].value;
	var atpos=x.indexOf("@");
	var dotpos=x.lastIndexOf(".");
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>x.length)
		{
		
		alert("Not a valid e-mail address");
		return false;
		}

	var x=document.forms["form"]["password"].value;
	if (x==null || x=="") {
	
		alert("Please enter your password");
		return false;
	}
}

</script>
<h2 class="login">Login</h2>
<form name="form" class="login" action="/login" onsubmit="return validateForm();" method="POST" >
    <p><input name="email" type="text" placeholder="email"/></p>
    <p><input name="password" type="text" placeholder="password"/></p>
    <p><input name="login" type="submit" value="Login"/></p>
</form>

<%@include file="includes/footer.jsp" %>
