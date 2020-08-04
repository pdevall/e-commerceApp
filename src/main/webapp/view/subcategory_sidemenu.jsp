<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/taglibs/c.tld" prefix="c"%>
<%@ taglib uri="/taglibs/fn.tld" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Groceries Kart</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"
	href="<spring:message code="content.url"/>/css/grocerieskart-main.css"
	type="text/css" />
</head>
<body>
	<div id="sidebar">
		<c:if
			test="${left_menu_category != null && fn:length(left_menu_category) > 0}">

			<div id="vertmenu">
				<h1>Browse by Sub Category</h1>
				<ul>
					<c:forEach items="${left_menu_category }" var="subCategory">
						<li><a href="<spring:url value="/gk" htmlEscape="true" />/<c:out value="${subCategory.name}"/>/category/<c:out value="${subCategory.category.id}"/>/subCategory/<c:out value="${subCategory.id }"/>">
						<c:out value="${subCategory.name}" /> 
						<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
							<br/> <spring:message code="${subCategory.springLabel}" text=""/>
						</c:if>
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>