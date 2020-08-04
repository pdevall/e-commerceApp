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
										<c:out value="Shipping Address"/>
									</font>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="checkoutContent">
					<form:form action="" id="checkoutForm" commandName="checkoutForm" method="post">
					<form:hidden path="selectedShippingAddrId" id="selectedShippingAddrId"/>
					<table class="checkoutTable">
						<c:if test="${shippingAddresses != null && fn:length(shippingAddresses) > 0}">
						<tr>
							<td colspan="3">
								<p class="checkoutTitle">
									Select the Shipping Address below
								</p>
							</td>
						</tr>
						<c:forEach items="${ shippingAddresses}" var="shipAddr" varStatus="grid">
								<c:if test="${grid.index + 1 == 1 || (grid.index + 1) % 4 == 0}">
								<tr>
								</c:if>
									<td>
										<p class="checkoutPara">
											<c:out value="${shipAddr.shipTo}"></c:out></br>
											<c:out value="${shipAddr.address1}"></c:out><c:if test="${shipAddr.address2 != null && shipAddr.address2 != ''}">,<c:out value="${shipAddr.address2}"/></c:if></br>
											<c:out value="${shipAddr.city}"></c:out></br>
											<c:out value="${shipAddr.postalCode}"></c:out></br>
											<c:out value="${shipAddr.stateName}"></c:out></br>
											<c:out value="${shipAddr.phoneNumber}"></c:out></br>
											<input name="select" style="width: 150px;" onclick="selectAddress('<spring:url value="/checkout/selectShipAddress" htmlEscape="true"/>', '<c:out value="${shipAddr.addressId }"/>')" class="cartButton" type="button" value="Select this Address">
										</p>
									</td>
								<c:if test="${(grid.index + 1) % 3 == 0 || (grid.index + 1) == fn:length(shippingAddresses)}">
								</tr>
								</c:if>
						</c:forEach>						
						</c:if>
						<tr>
							<td colspan="3">
								<c:choose>
									<c:when test="${shippingAddresses != null && fn:length(shippingAddresses) > 0}">
										<p class="checkoutTitle">
											or Enter the new Shipping Address
										</p>							
									</c:when>
									<c:otherwise>
										<p class="checkoutTitle">
											Enter the new Shipping Address
										</p>							
									</c:otherwise>							
								</c:choose>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div>
									<form:errors path = "*" cssClass="errorMsg" element="ul"/>
								</div>
							</td>
						</tr>						
						<tr>
							<td>
								<font class="checkoutInput">Shipping to</font>
							</td>
							<td colspan="2">
								<form:input path="addressForm.shipTo" id="shipTo" size="30"/>
							</td>
						</tr>
						<tr>
							<td>
								<font class="checkoutInput">Address Line</font>
							</td>
							<td colspan="2">
								<form:input path="addressForm.address1" id="address1" size="30"/>
							</td>
						</tr>
						<tr>
							<td>
								<font class="checkoutInput">more Address Line</font>
							</td>
							<td colspan="2">
								<form:input path="addressForm.address2" id="address2" size="30"/>
							</td>
						</tr>
						<tr>
							<td>
								<font class="checkoutInput">City</font>
							</td>
							<td colspan="2">
								<form:select path="addressForm.city">
									<option value="">Select</option>
									<form:options items="${sessionScope.cities }"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td>
								<font class="checkoutInput">State</font>
							</td>
							<td colspan="2">
								<form:select path="addressForm.stateId">
									<option value="0">Select</option>
									<form:options items="${sessionScope.states }" itemLabel="stateName" itemValue="stateId"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td>
								<font class="checkoutInput">Pincode</font>
							</td>
							<td colspan="2">
								<form:input path="addressForm.postalCode" id="postalCode" size="6" maxlength="6" onkeydown="return numbersonly(event)"/>
							</td>
						</tr>
						<tr>
							<td>
								<font class="checkoutInput">Phone Number</font>
							</td>
							<td colspan="2">
								<form:input path="addressForm.phoneNumber" id="phoneNumber" size="10" maxlength="10" onkeydown="return numbersonly(event)"/>(xxxxxxxxxx)<br>
								<font class="price" style="font-size: 10px;">* Landline numbers should be entered with the area code.<br>
								Ex:- 40(area code)-55555555(number).</font>
								
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<br>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<input class="button rosy" type="button" name="payment" id="payment" value="Proceed to Payment" onclick="selectAddress('<spring:url value="/checkout/addShipAddress" htmlEscape="true"/>', '')"/>
							</td>
						</tr>				
											
					</table>
					</form:form>		
				</div>
			</div>
		</div>
	</div>
</body>
</html>