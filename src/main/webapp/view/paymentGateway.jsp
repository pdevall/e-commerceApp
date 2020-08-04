<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Groceries Kart</title>
<script language='javascript'>
	window.onload =  function redirectUrl(){
	   formObj = document.getElementById("redirectForm");
	   formObj.target = "_parent";
	   formObj.submit();
	}
</script>
</head>
<body>
<form id="redirectForm" method="post" action="http://localhost:8080/givevoucher/redirect.jsp">
	<input type="hidden" name="ssid" value='<%= session.getId()%>'>
</form>
</body>
</html>