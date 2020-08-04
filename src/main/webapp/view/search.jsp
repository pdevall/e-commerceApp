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
										<c:out value="Search"/>
									</font>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="resultsAltMainContent">
					<div class="boxContent">
						<table width="780px" class="shoppingCart">
	  				    <c:choose>
		  				    	<c:when test="${searchForm.products != null && fn:length(searchForm.products) > 0 }">
								     <c:forEach items="${searchForm.products}" varStatus="gridRow" var="frontendProduct">
							    		<tr>
											<td class="shoppingCartTdFirst" width="550px" align="left" style="text-align: left;">
												<font style="font-weight: bold;"><c:out value="${frontendProduct.productName}"/></font>
												<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
												<br>
												<font class="springLabel"><spring:message code="${frontendProduct.subCategory.springLabel}" text=""/></font>
												</c:if>
											</td>
											<td width="230px" class="${gridRow.index % 2 == 0 ? 'shoppingCartTdAlt' : 'shoppingCartTd'}">
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
											    <c:choose>
												<c:when test="${ frontendProduct.productStatusType != null}">
													<font class="stockRed"><c:out value="${frontendProduct.productStatusType.status }"/></font>
												</c:when>
												<c:otherwise>
													<font class="stock">In Stock</font>
												</c:otherwise>
											    </c:choose>
											</td>
										</tr>
										<tr>
										 	<td colspan="2">
												<hr/>
											</td>
									   </tr>
									</c:forEach>
									<tr>
										<td align="left" colspan="1"> 
											<c:if test="${searchForm.previousPage >= 0}">											
												<a href="<spring:url value="/search/searchProducts/dept/" htmlEscape="true"/><%=(Long) request.getAttribute("department") %>/searchString/<%=(String) request.getAttribute("searchString") %>?pageNumber=<c:out value="${searchForm.previousPage}"/>&pageBoolean=false"><img src="<spring:message code="content.url"/>/images/arrow-left-small.png" style="cursor: pointer;"/></a>
											</c:if>
										</td>
										<td align="right" colspan="2">
											<c:if test="${searchForm.nextPage < searchForm.totalNumberOfPages}"> 
												<a href="<spring:url value="/search/searchProducts/dept/" htmlEscape="true"/><%=(Long) request.getAttribute("department") %>/searchString/<%=(String) request.getAttribute("searchString") %>?pageNumber=<c:out value="${searchForm.nextPage}"/>&pageBoolean=true"><img src="<spring:message code="content.url"/>/images/arrow-right-small.png" style="cursor: pointer;"/></a>
											</c:if>
										</td>										
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="4" align="center">						
											<font class="deliveryLabel">Results not found for the Search criteria.</font>
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