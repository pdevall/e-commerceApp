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
										<c:out value="gList"/>
									</font>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="resultsAltMainContent">
					<div class="boxContent">
					<form:form method="post" action="" id="glistForm" commandName="glistForm">					
						<form:errors path = "*" cssClass="errorMsg" element="ul"/>
						<c:if test="${successMessage != null }">
							<font class="successMsg"><c:out value="${successMessage }"/></font>
						</c:if>
						<input type="hidden" id="pageNumber" name="pageNumber"/>
		  				<input type="hidden" id="pageBoolean" name="pageBoolean"/>
		  				<form:hidden id="actionString" path="actionString"/>					
						<table width="780px" class="shoppingCart">
				   			<tr>
				   				<th class="shoppingCartHead">Select</th>
				   				<th class="shoppingCartHead">Product</th>
				   				<th class="shoppingCartHead">Price</th>
				   				<th class="shoppingCartHead">Quantity</th>
		  				    </tr>
		  				    <c:choose>
		  				    	<c:when test="${glistForm.glistProducts != null && fn:length(glistForm.glistProducts) > 0 }">
								     <c:forEach items="${glistForm.glistProducts}" varStatus="gridRow" var="frontendProduct" begin="${glistForm.startNumber }" end="${glistForm.endNumber }" step="1">
							    		<tr>
							    			<td width="25px" class="${gridRow.index % 2 == 0 ? 'shoppingCartTdAlt' : 'shoppingCartTd'}">
							    				<form:checkbox path="glistProducts[${gridRow.index }].selectedProduct"/>
							    			</td>
											<td class="shoppingCartTdFirst" width="480px" align="left" style="text-align: left;">
												<font style="font-weight: bold;"><c:out value="${frontendProduct.productName}"/></font>
												<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
												<br>
												<font class="springLabel"><spring:message code="${frontendProduct.subCategory.springLabel}" text=""/></font>
												</c:if>
											</td>
											<td width="150px" class="${gridRow.index % 2 == 0 ? 'shoppingCartTdAlt' : 'shoppingCartTd'}">
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
											<td width="125px" class="${gridRow.index % 2 == 0 ? 'shoppingCartTdAlt' : 'shoppingCartTd'}">
												<form:input path="glistProducts[${gridRow.index }].frontendQuantity" onclick="return numbersonly(event)" cssStyle="width:25px;"/>												
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="4">
											<hr/>
										</td>
									</tr>									
									<tr>
										<td align="left" colspan="2"> 
											<c:if test="${glistForm.previousPage >= 0}">											
												<a href="#" onclick="javascript:glistSubmit('<spring:url value="/glist/findGList/nextPrevious" htmlEscape="true"/>', '<c:out value="${glistForm.previousPage}"/>', 'false')"><img src="<spring:message code="content.url"/>/images/arrow-left-small.png" style="cursor: pointer;"/></a>
											</c:if>
										</td>
										<td align="right" colspan="2">
											<c:if test="${glistForm.nextPage < glistForm.totalNumberOfPages}"> 
												<a href="#" onclick="javascript:glistSubmit('<spring:url value="/glist/findGList/nextPrevious" htmlEscape="true"/>', '<c:out value="${glistForm.nextPage}"/>', 'true')"><img src="<spring:message code="content.url"/>/images/arrow-right-small.png" style="cursor: pointer;"/></a>
											</c:if>
										</td>										
									</tr>
									<tr>
										<td align="right" colspan="2"> 
											<input type="button" class="button rosy" onclick="javascript:glistSubmitAction('<spring:url value="/glist/addOrDelete" htmlEscape="true"/>', 'add')" value="Add to Cart">
										</td>
										<td align="left" colspan="2">
											<input type="button" class="button rosy" onclick="javascript:glistSubmitAction('<spring:url value="/glist/addOrDelete" htmlEscape="true"/>', 'delete')" value="Delete from gList">
										</td>										
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="4" align="center">						
											<font class="deliveryLabel">gList is empty.</font>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>	
		  				</table>
					</form:form>		  				
					</div>
				</div>	
			</div>			
		</div>
	</div>
</body>
</html>