<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/taglibs/c.tld" prefix="c" %>
<%@ taglib uri="/taglibs/fn.tld" prefix="fn" %>
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
					<c:if test="${category != null}">
						<div class="resultsAltHeadingContent">
							<div class="divHeading">
								<table>
									<tr>
										<td colspan="4" class="firstChild">
											<font>
												<c:out value="${category.name}"/>
											</font>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="resultsAltMainContent">
							<div class="boxContent">
								<c:if test="${errorObject != null}">
									<div class="errorMsg">
										<p><font color="red"><c:out value="${errorObject}"/></font></p>
									</div>
								</c:if>
								<c:choose>							
									<c:when test="${category.products != null && fn:length(category.products) > 0 }">
										<table id="productsByCategoryTable" width="100%">
											<c:forEach items="${category.products}" varStatus="childGridRow" var="product">
												<c:if test="${childGridRow.index + 1 == 1 || (childGridRow.index + 1) % 4 == 0}">
													<tr>
												</c:if>
													<td valign="top">
														<table>
															<tr height="175px" >
																<td  width="180px" align="center" valign="top">
																	<a href="<spring:url value="/pd" htmlEscape="true"/>/<c:out value="${product.productName}"/>/product/<c:out value="${product.productId }"/>">
																		<img src="<spring:message code="content.url"/><c:out value="${product.smallImageUrl}"/>" title="<c:out value="${product.productName}"/>" alt="<c:out value="${product.productName}"/>">
																	</a>
																</td>
															</tr>
															<tr height="100px">
																<td  width="180px" align="center" style="word-wrap:break-word;" valign="top">
																	<a href="<spring:url value="/pd" htmlEscape="true"/>/<c:out value="${product.productName}"/>/product/<c:out value="${product.productId }"/>">
																		<font>
																			<c:out value="${product.productName}"/>
																		</font>
																	</a>
																	<c:if test="${product.messageTextExist}">
																		<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">	
																			<br/>	
																			<font style="color:#993333;font-weight: bold;"><spring:message code="${product.subCategory.springLabel}" text=""/></font>
																		</c:if>
																	</c:if>																	
																	<br>
																	<c:choose>
																		<c:when test="${product.productDiscount != null && product.productDiscount.allOffers !=null && product.productDiscount.allOffers}">
																			<font class="discountCost"><del>Rs. <c:out value="${product.productCost}"/></del>
																			(<c:out value="${product.productDiscount.discountPercent}"/>% off 
																			<c:if test="${product.productDiscount.productOfferName != null &&  product.productDiscount.productOfferName != ''}">and free 
																			<c:out value="${product.productDiscount.productOfferName }"/></c:if>)
																			</font>
																			<br/>
																			<font class="cost">Rs. <c:out value="${product.productCostAfterDiscount}"/></font>																		
																		</c:when>
																		<c:when test="${product.productDiscount != null && product.productDiscount.discountPercent != null}">
																			<font class="discountCost"><del>Rs. <c:out value="${product.productCost}"/></del>
																			(<c:out value="${product.productDiscount.discountPercent}"/>% off)
																			</font>
																			<br/>
																			<font class="cost">Rs. <c:out value="${product.productCostAfterDiscount}"/></font>																		
																		</c:when>
																		<c:when test="${product.productDiscount.productOfferName != null &&  product.productDiscount.productOfferName != ''}">
																			<font class="discountCost">																			 
																			   free <c:out value="${product.productDiscount.productOfferName }"/>
																			</font>
																			<br/>
																			<font class="cost">Rs. <c:out value="${product.productCost}"/></font>																		
																		</c:when>																		
																		<c:otherwise>
																			<font class="cost">Rs. <c:out value="${product.productCost}"/></font>
																		</c:otherwise>
																	</c:choose>
																</td>
															</tr>
															<tr>
																<td valign="top">
																	<input type="button" class="viewAllButton" id="addtocart" value="Add to Cart" onclick="addToCartAjax('<spring:url value="/shopkart/ajax/add2kart/product/" htmlEscape="true"/><c:out value="${product.productId }"/>')"/>&nbsp;<input type="button" class="viewAllButton" id="addtoglist" value="Add to gList" onclick="addToGListAjax('<spring:url value="/glist/addgList/product/" htmlEscape="true"/><c:out value="${product.productId }"/>')"/>
																</td>
															</tr>
														</table>
													</td>
												<c:if test="${(childGridRow.index + 1) % 3 == 0 || (childGridRow.index + 1) == fn:length(category.products)}">
													</tr>
													<tr>
														<td colspan="3" valign="top">
															<hr/>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</table>
										<c:if test="${startNumber != null}">
											
											<table width="100%" id="fetchResultsTable">
												<tr>
													<td valign="top" align="center" width="100%">
														<input type="button" class="button rosy" id="fetchResults" value="Fetch More" onclick="fetchProductsByCategory('<c:out value="${startNumber}"/>', '<spring:url value="/gk" htmlEscape="true"/>/ajax/<c:out value="${category.name}"/>/category/<c:out value="${category.id }"/>')"/>
													</td>
												</tr>										
											</table>
											
										</c:if>
									</c:when>
									<c:otherwise>
										<font class="deliveryLabel">Results not found.</font>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>