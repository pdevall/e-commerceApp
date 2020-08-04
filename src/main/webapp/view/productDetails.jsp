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
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr valign="top">
						<td width="300px" align="center" valign="top">
							<img src="<spring:message code="content.url"/><c:out value="${productDetails.bigImageUrl}"/>" title="<c:out value="${productDetails.productName}"/>" alt="<c:out value="${productDetails.productName}"/>">
						</td>
						<td width="480px" align="left" valign="top">
							<div id="productDetails">
								<table width="480px" cellpadding="0" cellspacing="0" class="productInnerTable">
									<tr>
										<td colspan="2" valign="top">
											<font class="heading"><c:out value="${productDetails.productName }"/>
												<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">	
													<br/>	
													<font class="springLabel"><spring:message code="${productDetails.subCategory.springLabel}" text=""/></font>
												</c:if>	
											</font>											
										</td>
									</tr>
									<tr height="20px">
										<td colspan="2">										
											<br/>
										</td>
									</tr>
									<tr>
										<td>
											<c:choose>
												<c:when test="${productDetails.productDiscount != null && productDetails.productDiscount.discountPercent != null}">
													<table>
														<tr>
															<td>
																<font>Was:&nbsp;</font>
															</td>
															<td>
																<font><del>Rs. <c:out value="${productDetails.productCost}"/></del></font>
															</td>
														</tr>
														<tr>
															<td>
																<font>Discount:&nbsp;</font>
															</td>
															<td>
																<font class="discount"><c:out value="${productDetails.productDiscount.discountPercent}"/>%</font>
															</td>
														</tr>
														<tr>
															<td>
																<font>Price:&nbsp;</font>
															</td>
															<td>
																<font class="price">Rs. <c:out value="${productDetails.productCostAfterDiscount}"/></font>
															</td>
														</tr>
													</table>
												</c:when>
												<c:otherwise>
													<font>Price: &nbsp;</font><font class="price">Rs. <c:out value="${productDetails.productCost}"/></font>
													<br/>
												</c:otherwise>												
											</c:choose>	
											<font>(Price is inclusive of all taxes)</font>				
										</td>										
										<td style="border-left:solid 1px #060">
											&nbsp;&nbsp;&nbsp;&nbsp;																					
										</td>
										<td valign="top">
											<c:choose>
											<c:when test="${ productDetails.productStatusType != null}">
												<font class="stockRed"><c:out value="${productDetails.productStatusType.status }"/></font>
											</c:when>
											<c:otherwise>
												<font class="stock">In Stock</font>
											</c:otherwise>
											</c:choose>
											<br>
											<font>Delivered in&nbsp;</font><font style="font-weight: bold;">24 to 48 hours.</font>
										</td>
									</tr>
									<tr>
										<td colspan="3">
											<br/>
										</td>
									</tr>
									<tr>
										<td colspan="3" align="center">
											<font style="font-weight: bold; font-size: 14px">Quantity:&nbsp;</font>
											<select id="productDetailsQuantity">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</select>
										</td>
									</tr>
									<tr>
										<td colspan="3">
											<br/>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="right">
							      			<input name="buy" style="width: 50px;" onclick="buy('<spring:url value="/shopkart/add2kart/product/" htmlEscape="true"/><c:out value="${productDetails.productId }"/>?productQuantity=')" class="cartButton" type="button" value="Buy">							     													
										</td>
										<td align="left">
											&nbsp;&nbsp;<input type="button" style="" class="cartButton"  id="addtoglist" value="Add to gList" onclick="addToGListAjax('<spring:url value="/glist/addgList/product/" htmlEscape="true"/><c:out value="${productDetails.productId }"/>')"/>
										</td>
									</tr>
									<tr>
										<td colspan="3">
											<br/>
										</td>
									</tr>									
									<tr>
										<td colspan="3" align="left">
											<font class="deliveryLabel">FREE Delivery to Home with a minimum purchase of Rs. 499/-</font>
										</td>
									</tr>									
									<tr>
										<td colspan="3">
											<br/>
										</td>
									</tr>																										
									<c:if test="${productDetails.productDiscount != null && productDetails.productDiscount.allOffers !=null && productDetails.productDiscount.allOffers}">
										<c:if test="${productDetails.productDiscount.productOfferName != null &&  productDetails.productDiscount.productOfferName != ''}">
											<tr>
												<td colspan="3" style="border:solid 1px #060" width="100%">
		 											<img style="vertical-align: middle;" src="<spring:message code="content.url"/>/images/special_offer.png">&nbsp; <b>Free &nbsp;<c:out value="${productDetails.productDiscount.productOfferName }"/></b>
												</td>
											</tr>
										</c:if>
									</c:if>
								</table>								
							</div>
						</td>
					</tr>
				</table>
				<c:if test="${categoryProductDetails != null && categoryProductDetails.products != null && fn:length(categoryProductDetails.products) > 0 }">
					<br/>
					<br/>
					<br/>
					<font style="font-weight: bold; font-size: 14px">Similar Products in the Category &nbsp;<img src="<spring:message code="content.url"/>/images/loading.gif" id="ajaxImage" style="display:none;"></font>					
					<table width="760px" id="similarResults">
					<tr>
					<c:if test="${categoryProductDetails.previousPage >= 0 }">
					<td>						
						<img src="<spring:message code="content.url"/>/images/arrow-left-small.png" style="cursor: pointer;" onclick="pagination('<spring:url value="/pd" htmlEscape="true"/>/ajax/category/<c:out value="${categoryProductDetails.id}"/>','<c:out value="${categoryProductDetails.previousPage }"/>', 'false')"/>
					</td>
					</c:if>
					<td>
					<div class="resultsMainContent" style="width:700px;border-top: 1px solid #C39C4E;">
						<div class="boxContent">
							<c:choose>							
								<c:when test="${categoryProductDetails != null && categoryProductDetails.products != null && fn:length(categoryProductDetails.products) > 0 }">
									<table>
										<tr>
											<c:forEach items="${categoryProductDetails.products}" varStatus="childGridRow" var="product">
												<td valign="top">
													<table>
														<tr height="175px" >
															<td  width="180px" align="center" valign="top">
																<a href="<spring:url value="/pd" htmlEscape="true"/>/<c:out value="${product.productName}"/>/product/<c:out value="${product.productId }"/>"><img src="<spring:message code="content.url"/><c:out value="${product.smallImageUrl}"/>" title="<c:out value="${product.productName}"/>" alt="<c:out value="${product.productName}"/>"></a>
															</td>
														</tr>
														<tr>
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
													</table>
												</td>
											</c:forEach>
										</tr>
									</table>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					</td>
					<c:if test="${categoryProductDetails.nextPage < categoryProductDetails.totalNumberOfPages}">
					<td>
						<img src="<spring:message code="content.url"/>/images/arrow-right-small.png" style="cursor: pointer;" onclick="pagination('<spring:url value="/pd" htmlEscape="true"/>/ajax/category/<c:out value="${categoryProductDetails.id}"/>','<c:out value="${categoryProductDetails.nextPage }"/>', 'true')"/>
					</td>
					</c:if>
					</tr>					
					</table>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>