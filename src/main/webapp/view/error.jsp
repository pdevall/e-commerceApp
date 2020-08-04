<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/taglibs/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Groceries Kart: Online Grocery Shopping: Rice, Lentils, Cooking oil etc.</title>
</head>
<body>
	<div id="loginContainer">
		<div class="errorMsg">
			<c:choose>
				<c:when test="${servletError != null && servletError != ''}">
					<p><font color="red"><c:out value="${servletError}"/></font></p>
				</c:when>
	
				<c:when test="${statusCode != null && statusCode == 404}">
					<p><font color="red"><c:out value="Page not available. Please try again later."/></font></p>
				</c:when>
		
				<c:when test="${statusCode != null && statusCode == 405}">
					<p><font color="red"><c:out value="The request is invalid. Please try again."/></font></p>
				</c:when>
				<c:otherwise>
					<p><font color="red"><c:out value="An error occured while processing the request. Please try again. If problem persists please contact customer service."/></font></p>
				</c:otherwise>	
			</c:choose>
		</div>
	</div>
</body>
</html>