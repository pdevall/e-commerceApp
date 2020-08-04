<%@ taglib uri="/taglibs/c.tld" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Groceries Kart: Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="<spring:message code="content.url"/>/css/grocerieskart-main.css" type="text/css" />
</head>
<body>
	<div id="loginContainer">
	<form:form method="post" action="" id="userAccountForm" commandName="userAccountForm">
		<form:hidden path="actionString" id="actionString"/>
		<form:hidden path="fromCheckout" id="fromCheckout"/>		
		<table class="loginTable">
			<tr>
				<td style="width:600px;" align="center" >
					<div style="width:600px;">
						<form:errors path = "*" cssClass="errorMsg" element="ul"/>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<div class="loginContent">
						<table class="innerTable">
							<tr>
								<td class="firstchild">
									Login to your Account or Register
								</td>
							</tr>
							<tr>
								<td>
									Enter your Email and Password to log into your Account or Click the "Register" link to Register.
								</td>
							</tr>
							<tr>
								<td>									
								</td>
							</tr>
							<tr>
								<td class="content">
									Enter your Email Address
								</td>
							</tr>
							<tr>
								<td>
									<form:input path="userEmailAddress" id="userEmailAddress" size="30"/>
								</td>
							</tr>
							<tr>
								<td>									
								</td>
							</tr>							
							<tr>
								<td class="content">
									Did you Register with us?
								</td>
							</tr>
							<tr>
								<td>
									No I am New Customer. <a href="<spring:url value="/useraccount/register.html?fromCheckout="/><c:out value="${userAccountForm.fromCheckout}"/>">Register</a>
								</td>
							</tr>
							<tr>
								<td>									
								</td>
							</tr>							
							<tr>
								<td>
									Yes, Password is
								</td>
							</tr>
							<tr>
								<td>
									<form:password path="password" id="password" size="30"/>
								</td>
							</tr>
							<tr>
								<td>
									<br>
								</td>
							</tr>
							<tr>
								<td>
									<input class="button blue" type="button" name="login" id="login" value="login" onclick="submitForm('userAccountForm', '<spring:url value="/useraccount/loginSubmit.html"/>', 'login')"/> &nbsp;&nbsp;&nbsp;<span class="content"><a href="javascript:;" onclick="passwordReset()">Forgot your Password?</a></span>
								</td>
							</tr>
							
						</table>
					</div>
				</td>
			</tr>
		</table>
	</form:form>	
	</div>
	
	<div id="resetPopUp" style="display:none; cursor: default"> 
   		<div id="ajaxPopUp" style="width: 500px;">
			<h1 class="title">Reset Password</h1>
			<div class="content">
				<div id="ajaxImage">
		   		</div>
		   		<div id="messageText">
		   		</div>
		        <h3>Please enter the Email Address associated with your Account</h3>
		        <input type="text" id="emailAddress" value="" size="30"/> <br><br>
       			<input id="resetPassword" type="button" class="button blue" value="Reset Password" onclick="passwordResetAjax('<spring:url value="/useraccount/forgotPassword.html"/>')">
     				&nbsp;
     			<input id="close" type="button" class="button blue" value="Close">
		     </div>
		</div>  
	</div> 
</body>
</html>
