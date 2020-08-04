<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/taglibs/c.tld" prefix="c" %>
<%@ taglib uri="/taglibs/fn.tld" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Groceries Kart: Online Grocery Shopping: Rice, Lentils, Cooking oil etc.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="<spring:message code="content.url"/>/css/grocerieskart-main.css" type="text/css" />
</head>
<body>
	<div id="content" style="padding: 0px;">
		<div id="contentBody" style="width: 800px;">
			<div id="results" style="width: 800px;">
				<div class="resultsAltHeadingContent" style="width: 780px;">
					<div class="divHeading" style="padding : 0px">
						<table>
							<tr>
								<td colspan="4" class="firstChild">
									<font>
										<c:out value="Select Payment Method"/>
									</font>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="checkoutContent">
					<form:form action="" id="checkoutForm" commandName="checkoutForm" method="post">
					<table class="checkoutTable">
						<c:if test="${sessionScope.shoppingCartForm.selectedAddressDto != null }">
							<tr>
								<td valign="top">
									<p class="checkoutTitle" style="font-size: 12px;">
										<u>Shipping Address</u>
									</p>
									<p class="checkoutPara">
										<c:out value="${sessionScope.shoppingCartForm.selectedAddressDto.shipTo}"></c:out></br>
										<c:out value="${sessionScope.shoppingCartForm.selectedAddressDto.address1}"></c:out><c:if test="${sessionScope.shoppingCartForm.selectedAddressDto.address2 != null && sessionScope.shoppingCartForm.selectedAddressDto.address2 != ''}">,<c:out value="${sessionScope.shoppingCartForm.selectedAddressDto.address2}"/></c:if></br>
										<c:out value="${sessionScope.shoppingCartForm.selectedAddressDto.city}"></c:out></br>
										<c:out value="${sessionScope.shoppingCartForm.selectedAddressDto.postalCode}"></c:out></br>
										<c:out value="${sessionScope.shoppingCartForm.selectedAddressDto.stateName}"></c:out></br>
										<c:out value="${sessionScope.shoppingCartForm.selectedAddressDto.phoneNumber}"></c:out></br>
									</p>									
								</td>
								<td valign="top">
									<p class="checkoutTitle" style="font-size: 12px;">
										<u>From</u>
									</p>
									<p class="checkoutPara">
										<c:out value="${sessionScope.userAccountObj.customerFirstname}"></c:out>&nbsp;<c:out value="${sessionScope.userAccountObj.customerLastname}"></c:out></br>
										<c:out value="${sessionScope.userAccountObj.maskEmail}"></c:out>
									</p>									
								</td>								
							</tr>
							<tr>
								<td colspan="2">	
									<br>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<p class="checkoutTitle">
										Select the Payment method
									</p>								
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div id="errors" style="display: none;" class="errorMsg">										
									</div>
								</td>
							</tr>							
							<tr>
								<td colspan="2">
									<c:forEach items="${payment_types}" var="payment">									
										<form:radiobutton path="paymentType" label="${payment.paymentType }" value="${payment.paymentTypeId}"/>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td colspan="2">	
									<br>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div id="showImage" style="display: none;">		
										<img src="<spring:message code="content.url"/>/images/ajax.gif"/>							
									</div>								
									<input class="button rosy" type="button" name="checkout" id="checkout" value="Pay" onclick="payment('<spring:url value="/checkout/pay" htmlEscape="true"/>', 'checkoutForm')"/>
								</td>
							</tr>						
						</c:if>
					</table>
					</form:form>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>