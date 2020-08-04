<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Groceries Kart: Online Grocery Shopping: Rice, Lentils, Cooking oil etc.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="<spring:message code="content.url"/>/css/grocerieskart-main.css" type="text/css" />

</head>
<body>
<div id="outerDiv">
</div>
<div id="mainContainer">
	<div id="bodyContainer">
		<tiles:insertAttribute name="header" />
		<div id="contentContainer">
			<tiles:insertAttribute name="breadcrumb" />
			<tiles:insertAttribute name="body" />
			<tiles:insertAttribute name="sidemenu" /> 			
 			<div style="clear: both;">&nbsp;</div>
 		</div>
 	</div>  	    		
</div>
<tiles:insertAttribute name="footer" />
</body>
</html>
