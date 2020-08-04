<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/taglibs/c.tld" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Groceries Kart: Online Grocery Shopping: Rice, Lentils, Cooking oil etc.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="<spring:message code="content.url"/>/css/grocerieskart-main.css" type="text/css" />
</head>
<body>
<div id="bread">
	<table width="200px">
		<tr>
			<td>
				<font style="color:#FF3300;font-size: 14px; font-weight: bold">
					<% String sessionExpired = request.getParameter("sessionExpired"); %>
					<% if (sessionExpired != null && !sessionExpired.equals("")) {%>
						<%= sessionExpired%>
						<br>
					<%} %>
					<c:if test="${bread_crumb != null }">
						<c:out value="${bread_crumb}"/>
					</c:if>
					
				</font>
			</td>
		</tr>
	</table>
</div>
</body>
</html>