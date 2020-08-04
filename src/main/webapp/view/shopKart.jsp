<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="/taglibs/c.tld" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/taglibs/c.tld" prefix="c" %>
<%@ taglib uri="/taglibs/fn.tld" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Groceries Kart</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="<spring:message code="content.url"/>/css/grocerieskart-main.css" type="text/css" />
</head>
<body>
<div id="sidebar">
	<div>
		<div id="vertmenu" style="min-height: 500px;"> 
			<h1>Shopping Cart</h1>
			<ul>
				<li style="padding: 5px 0px 2px 11px;">
					<table>
						<tr>
			   				<th class="shoppingCartHead">Prod Name</th>
			   				<th class="shoppingCartHead">Qty</th>
			   				<th class="shoppingCartHead">Price</th>
		  				</tr>
												
					    <c:choose>
					    	<c:when test="${sessionScope.shoppingCartForm.shoppedProducts != null && fn:length(sessionScope.shoppingCartForm.shoppedProducts) > 0 }">
					     		<c:forEach items="${sessionScope.shoppingCartForm.shoppedProducts}" varStatus="gridRow" var="frontendProduct" begin="${sessionScope.shoppingCartForm.startNumber }" end="${sessionScope.shoppingCartForm.endNumber }" step="1">
									<tr>
										<td>
											<font style="font-weight: bold;font-size: 9px;" ><c:out value="${frontendProduct.productName}"/></font>
										</td>										
										<td align="center">
											<font style="font-weight: bold;font-size: 9px;" ><c:out value="${frontendProduct.frontendQuantity}"/></font>
										</td>
										<td align="right">
										<c:choose>
											<c:when test="${frontendProduct.productCostAfterDiscount != null }">
												&nbsp;&nbsp;<span><font class="price" style="font-size: 9px;">Rs.&nbsp;<c:out value="${frontendProduct.productCostAfterDiscount}"/></font></span>
											</c:when>
											<c:otherwise>
												&nbsp;&nbsp;<span><font class="price" style="font-size: 9px;">Rs.&nbsp;<c:out value="${frontendProduct.productCost}"/></font></span>
											</c:otherwise>
										</c:choose>
										</td>
									</tr>
					     		</c:forEach>
					     	</c:when>
					    </c:choose>
					 </table>
				</li>
			</ul>
		</div>
		<table width="180px">
		<c:if test="${sessionScope.shoppingCartForm.globalDiscount != null }">
			<tr>
			<td colspan="2">
			<font class="deliveryLabel">Addl. discount (<c:out value="${sessionScope.shoppingCartForm.globalDiscount.discountPercent}"/>)</font>
			</td>
			</tr>
		</c:if>
		<tr><td>
		<font class="deliveryLabel">Total Cost:</font>
		</td>
		<td align="right">
		<font class="price">Rs.&nbsp;<c:out value="${sessionScope.shoppingCartForm.totalShoppingCostWithGlobalDiscount}"/></font>
		</td>
		</tr>
		<tr><td colspan="2" align="center">
			<input name="view" style="width: 150px;" onclick="addToCart('<spring:url value="/shopkart/showkart/shoppingKart" htmlEscape="true"/>')" class="cartButton" type="button" value="View / Modify Cart">
		</td></tr>
		</table>
	</div>
</div>
</body>
</html>