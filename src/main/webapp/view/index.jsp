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
		<div id="contentBody" style="float:left;">
			<div id="results">
				<c:if test="${sessionScope.home_page_categories != null && sessionScope.home_page_categories.homePageCategories != null && fn:length(sessionScope.home_page_categories.homePageCategories) > 0 }">
					<c:forEach items="${sessionScope.home_page_categories.homePageCategories}" varStatus="mainGridRow" var="results">
						<div class="${mainGridRow.index % 2 == 0 ? 'resultsAltHeadingContent' : 'resultsHeadingContent'}">
							<div class="divHeading">
								<table>
									<tr>
										<td colspan="2" class="firstChild">
											<font>
												<c:out value="${results.department.departmentName}"/> : <c:out value="${results.name}"/>
											</font>
										</td>
										<td colspan="2" class="secondChild">
											<a href="<spring:url value="/gk" htmlEscape="true"/>/<c:out value="${results.name}"/>/category/<c:out value="${results.id }"/>"> View All</a>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="${mainGridRow.index % 2 == 0 ? 'resultsAltMainContent' : 'resultsMainContent'}">
							<div class="boxContent">
								<c:choose>							
									<c:when test="${results.products != null && fn:length(results.products) > 0 }">
										<table>
											<tr>
												<c:forEach items="${results.products}" varStatus="childGridRow" var="product">
													<td valign="top">
														<table>
															<tr height="175px" >
																<td  width="${mainGridRow.index % 2 == 0 ? '180px' : '190px'}" align="center" valign="top">
																	<a href="<spring:url value="/pd" htmlEscape="true"/>/<c:out value="${product.productName}"/>/product/<c:out value="${product.productId }"/>"><img src="<spring:message code="content.url"/><c:out value="${product.smallImageUrl}"/>" title="<c:out value="${product.productName}"/>" alt="<c:out value="${product.productName}"/>"></a>
																</td>
															</tr>
															<tr>
																<td  width="${mainGridRow.index % 2 == 0 ? '180px' : '190px'}" align="center" style="word-wrap:break-word;" valign="top">
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
														</table>
													</td>
													<c:if test="${mainGridRow.index % 2 == 0 && fn:length(results.products) > (childGridRow.index + 1) }">
														<td width="5px" style="border-right: 2px solid #C39C4E;">
															&nbsp;
														</td> 
													</c:if>
												</c:forEach>
											</tr>
										</table>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<br>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>