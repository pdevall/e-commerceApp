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
	<div id="content">
		<div id="contentBody">
			<div id="results">
				<div class="resultsAltHeadingContent">
					<div class="divHeading">
						<table>
							<tr>
								<td colspan="4" class="firstChild">
									<font>
										<c:out value="Shopping Cart"/>
									</font>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="resultsAltMainContent">
					<div class="boxContent">
						<table width="780px" class="shoppingCart">
				   			<tr>
				   				<th class="shoppingCartHead">Product</th>
				   				<th class="shoppingCartHead">Price</th>
				   				<th class="shoppingCartHead">Quantity</th>
		  				    </tr>
		  				    <c:choose>
		  				    	<c:when test="${sessionScope.shoppingCartForm.shoppedProducts != null && fn:length(sessionScope.shoppingCartForm.shoppedProducts) > 0 }">
								     <c:forEach items="${sessionScope.shoppingCartForm.shoppedProducts}" varStatus="gridRow" var="frontendProduct" begin="${sessionScope.shoppingCartForm.startNumber }" end="${sessionScope.shoppingCartForm.endNumber }" step="1">
							    		<tr>
											<td class="shoppingCartTdFirst" width="480px" align="left" style="text-align: left;">
												<font style="font-weight: bold;"><c:out value="${frontendProduct.productName}"/></font>
												<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
												<br>
												<font class="springLabel"><spring:message code="${frontendProduct.subCategory.springLabel}" text=""/></font>
												</c:if>
											</td>
											<td width="160px" class="${gridRow.index % 2 == 0 ? 'shoppingCartTdAlt' : 'shoppingCartTd'}">
												<c:choose>
												<c:when test="${frontendProduct.productCostAfterDiscount != null }">
													<font class="price">Rs.&nbsp;<c:out value="${frontendProduct.productCostAfterDiscount}"/></font>
												</c:when>
												<c:otherwise>
													<font class="price">Rs.&nbsp;<c:out value="${frontendProduct.productCost}"/></font>
												</c:otherwise>
												</c:choose>
												<br>
												<c:if test="${frontendProduct.productDiscount != null && frontendProduct.productDiscount.discountPercent != null}">
													<font style="font-size: 10px;">Discount:</font>&nbsp;<font class="discount" style="font-size: 10px;"><c:out value="${frontendProduct.productDiscount.discountPercent}"/>%</font>
													<br>
													<font style="font-size: 10px;">You will Save(</font><font class="price" style="font-size: 10px;">Rs.&nbsp;<c:out value="${frontendProduct.discountCost }"/></font><font>)</font>
												</c:if>
											</td>
											<td width="140px" class="${gridRow.index % 2 == 0 ? 'shoppingCartTdAlt' : 'shoppingCartTd'}">
												<input style="width:25px;" onkeydown="return numbersonly(event)" type="text" id="frontendQuantity<c:out value="${gridRow.index}"/>" value="<c:out value="${frontendProduct.frontendQuantity}"/>">
												<br>
												<a href="#" onClick="javascript:updateToCart('<spring:url value="/shopkart/update2kart/product/" htmlEscape="true"/><c:out value="${frontendProduct.productId }"/>', '<c:out value="${gridRow.index}"/>')">Update</a>&nbsp;<a href="<spring:url value="/shopkart/delete2kart/product/" htmlEscape="true"/><c:out value="${frontendProduct.productId }"/>">Delete</a>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="3">
											<hr/>
										</td>
									</tr>
									<tr>
										<td align="right">
											<font style="font-weight: bold;">Total Cost:</font>
										</td>
										<td align="center">
											<font style="font-weight: bold;">Rs.&nbsp;<c:out value="${sessionScope.shoppingCartForm.totalShoppingCost }"/></font>
										</td>
										
									</tr>									
									<tr>
										<td align="left" colspan="2"> 
											<c:if test="${sessionScope.shoppingCartForm.previousPage >= 0}">											
												<a href="<spring:url value="/shopkart/nextPrevious?pageNumber=" htmlEscape="true"/><c:out value="${sessionScope.shoppingCartForm.previousPage}"/>&pageBoolean=false"><img src="<spring:message code="content.url"/>/images/arrow-left-small.png" style="cursor: pointer;"/></a>
											</c:if>
										</td>
										<td align="right" colspan="1">
											<c:if test="${sessionScope.shoppingCartForm.nextPage < sessionScope.shoppingCartForm.totalNumberOfPages}"> 
												<a href="<spring:url value="/shopkart/nextPrevious?pageNumber=" htmlEscape="true"/><c:out value="${sessionScope.shoppingCartForm.nextPage}"/>&pageBoolean=true"><img src="<spring:message code="content.url"/>/images/arrow-right-small.png" style="cursor: pointer;"/></a>
											</c:if>
										</td>										
									</tr>
									<tr>
										<td colspan="3">
											<br>
										</td>
									</tr>
									<c:if test="${sessionScope.shoppingCartForm.globalDiscount != null }">
										<tr>
											<td colspan="3">
												<font class="deliveryLabel">*An overall discount (<c:out value="${sessionScope.shoppingCartForm.globalDiscount.discountPercent}"/>) will be applied at checkout.</font>
											</td>
										</tr>
									</c:if>									
									<tr>
										<td align="center" colspan="3">
											<a class="button rosy" href="<spring:url value="/checkout/selectShippingAddress?fromCheckout=1" htmlEscape="true"/>">Proceed to Checkout</a>
										</td>
									</tr>									
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="3" align="center">						
											<font class="deliveryLabel">Shopping Cart is empty.</font>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>			  				    
		  				</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>