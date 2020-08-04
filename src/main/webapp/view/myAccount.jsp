<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/taglibs/c.tld" prefix="c" %>
<%@ taglib uri="/taglibs/fn.tld" prefix="fn" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="<spring:message code="content.url"/>/css/grocerieskart-main.css" type="text/css" />
<title>Groceries Kart: My Account</title>
</head>
<body>
	<div id="loginContainer">
		<h1 class="title">My Account</h1>
  			<table class="account">
			<tr>
				<td class="accountFirstColumn" valign="top">
					<font>
						<b>Orders</b>
					</font>	
					<p style="font-weight: normal;">View Orders</p>								     
				</td>
				<td class="accountSecondColumn" valign="top">
					<font>
						<b>Order History</b>
					</font>	
					<ul>
						<li>View Orders</li>
						<li>Cancel Orders</li>
				  	</ul>
				  	<p><a href="" class="button rosy">View/Cancel Orders</a>							     
				</td>
			</tr>
		</table>
		<br>
   		<table class="account">
			<tr>
				<td class="accountFirstColumn" valign="top">
					<font>
						<b>Settings</b>
					</font>	
					<br>
					<p style="font-weight: normal;">Password, Email Address</p>								     
				</td>
				<td class="accountSecondColumn" valign="top">
					<font>
						<b>Account Settings:</b>
					</font>	
					<br>
					<p><a href="javascript:;" onclick="changePassword()" style="color: #26C3EA; text-decoration: none" onMouseOver="this.style.textDecoration='underline'" onMouseOut="this.style.textDecoration='none'">Change Password</a></p>
     				<p><a href="javascript:;" onclick="changeEmail()" style="color: #26C3EA; text-decoration: none" onMouseOver="this.style.textDecoration='underline'" onMouseOut="this.style.textDecoration='none'" onclick="changeEmail()">Change Email Address</a></p>
     				<p><a href="#" style="color: #26C3EA; text-decoration: none" onMouseOver="this.style.textDecoration='underline'" onMouseOut="this.style.textDecoration='none'" onclick="changeEmail()">Manage Shipping Address</a></p>
     				<p>
     					<a class="tooltip" href="<spring:url value="/glist/findGList" htmlEscape="true"/>" style="color: #26C3EA; text-decoration: none" onMouseOver="this.style.textDecoration='underline'" onMouseOut="this.style.textDecoration='none'">Manage gList
     						<span class="classic" style="color:black;line-height:20px;width:300px;text-align: left;padding:2px;">Manage List of Groceries for easy shopping.</span>
     					</a>
     				</p>
				</td>
			</tr>
	    </table>
	</div>				
   	
   	<div id="changePasswordPopUp" style="display:none; cursor: default"> 
   		<div id="ajaxPopUp" style="width: 500px;">
			<h1 class="title">Change Password</h1>
			<div class="content">
				<div id="changePasswordAjaxImage">
		   		</div>
		   		<div id="changePasswordMessageText">
		   		</div>
		        <h3>Please fill and submit the form below to Change the Password.</h3>
		        <table>
		        	<tr>
		        		<td valign="top">
		        			<font class="ajaxFont">Email Address</font>
		        		</td>
		        		<td>
		        			<input type="text" id="changePasswordEmailAddress" value="" size="30"/>
		        		</td>
		        	</tr>
		        	<tr>
		        		<td valign="top">
		        			<font class="ajaxFont">Current Password</font>
		        		</td>
		        		<td>
		        			<input type="password" id="changePasswordCurrentPassword" value="" size="30"/>
		        		</td>
		        	</tr>
		        	<tr>
		        		<td valign="top">
		        			<font class="ajaxFont">New Password</font>
		        		</td>
		        		<td>
		        			<input type="password" id="changePasswordNewPassword" value="" size="30"/>
		        		</td>
		        	</tr>
		        	<tr>
		        		<td valign="top">
		        			<font class="ajaxFont">Confirm New Password</font>
		        		</td>
		        		<td>
		        			<input type="password" id="changePasswordConfirmNewPassword" value="" size="30"/>
		        		</td>
		        	</tr>
		        	<tr>
		        		<td  colspan="2">
			      			<input id="changePasswordButton" type="button" class="button blue" value="Change Password" 
			      					onclick="changePasswordAjax('<spring:url value="/useraccount/changePassword.html"/>')"/>&nbsp;
			     			<input id="changePasswordClose" type="button" class="button blue" value="Close"/>
		        		</td>
		        	</tr>		        	
		        </table>		        
 		     </div>
		</div>  
	</div>
	
   	<div id="changeEmailPopUp" style="display:none; cursor: default"> 
   		<div id="ajaxPopUp" style="width: 500px;">
			<h1 class="title">Change Email Address</h1>
			<div class="content">
				<div id="changeEmailAjaxImage">
		   		</div>
		   		<div id="changeEmailMessageText">
		   		</div>
		        <h3>Please fill and submit the form below to Change the Email Address.</h3>
		        <table>
		        	<tr>
		        		<td valign="top">
		        			<font class="ajaxFont">Email Address</font>
		        		</td>
		        		<td>
		        			<input type="text" id="changeEmailCurrentEmailAddress" value="" size="30"/>
		        		</td>
		        	</tr>
		        	<tr>
		        		<td valign="top">
		        			<font class="ajaxFont">New Email Address</font>
		        		</td>
		        		<td>
		        			<input type="text" id="changeEmailNewEmailAddress" value="" size="30"/>
		        		</td>
		        	</tr>
		        	<tr>
		        		<td valign="top">
		        			<font class="ajaxFont">Confirm New Email Address</font>
		        		</td>
		        		<td>
		        			<input type="text" id="changeEmailConfirmNewEmailAddress" value="" size="30"/>
		        		</td>
		        	</tr>
		        	<tr>
		        		<td valign="top">
		        			<font class="ajaxFont">Password</font>
		        		</td>
		        		<td>
		        			<input type="password" id="changeEmailPassword" value="" size="30"/>
		        		</td>
		        	</tr>
		        	<tr>
		        		<td  colspan="2">
			      			<input id="changeEmailButton" type="button" class="button blue" value="Change Email" 
			      					onclick="changeEmailAjax('<spring:url value="/useraccount/changeEmailAddress.html"/>')"/>&nbsp;
			     			<input id="changeEmailClose" type="button" class="button blue" value="Close"/>
		        		</td>
		        	</tr>		        	
		        </table>		        
 		     </div>
		</div>  
	</div>
</body>
</html>