<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="/taglibs/c.tld" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/taglibs/fn.tld" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Groceries Kart: Online Grocery Shopping: Rice, Lentils, Cooking oil etc.</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="<spring:message code="content.url"/>/css/grocerieskart-main.css" type="text/css" />
<link rel="stylesheet" href="<spring:message code="content.url"/>/css/user-account.css" type="text/css" />
<link rel="stylesheet" href="<spring:message code="content.url"/>/css/button.css" type="text/css" />
<link rel="stylesheet" href="<spring:message code="content.url"/>/css/tooltip.css" type="text/css" />
<link rel="stylesheet" type="text/css" media="all" href="<spring:message code="content.url"/>/js/date-picker/jsDatePick_ltr.min.css" />
<script type="text/JavaScript" src="<spring:message code="content.url"/>/js/json2.js">
</script>
<script type="text/JavaScript" src="<spring:message code="content.url"/>/js/jquery.js">
</script>
<script type="text/javascript" src="<spring:message code="content.url"/>/js/jquery-plugin/blockUI.js"></script>
<script type="text/javascript">
	 var staticURL = '<spring:message code="content.url"/>';
	 var springUrl = '<spring:url value="/" htmlEscape="true"/>';
     var jq = jQuery.noConflict();
     jq.ajaxSetup({ cache:false });
     jq.blockUI.defaults.css.border = '3px solid #F3F781';
     jq.blockUI.defaults.css.textAlign = 'left';
</script>
<script type="text/javascript" src="<spring:message code="content.url"/>/js/jquery-plugin/postJSON.js"></script>
<script type="text/javascript" src="<spring:message code="content.url"/>/js/jquery-plugin/serialize-plugin.js"></script>
<script type="text/javascript" src="<spring:message code="content.url"/>/js/date-picker/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="<spring:message code="content.url"/>/js/groceries-kart.js"></script>
</head>
<body>
<% String searchString = (String) request.getAttribute("searchString");
	Long department = (Long) request.getAttribute("department");%>
<form id="shoppingKartForm" action="" method="get">
	<input type="hidden" id="productQuantity" name="productQuantity">
</form>
<table id="header" cellspacing="0" cellpadding="0" width="100%">
	<tr>
		<td style="width:15%" valign="bottom">	
			<a href="<spring:url value="/index.html" htmlEscape="true"/>"><img src="<spring:message code="content.url"/>/images/gcart.png" style="border: transparent"/></a>		
		</td>
		<td valign="top" align="right" style="width:85%">
			<table cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<a  href="<spring:url value="/index.html" htmlEscape="true"/>" style="color:#FFFAF0;text-decoration: none;" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'">Home</a><span style="color:#FFFAF0;">&nbsp;|&nbsp;</span>
					</td>
					<td valign="top">
						<a class="tooltip" href="<spring:url value="/glist/findGList" htmlEscape="true"/>" style="color:#FFFAF0;text-decoration: none;" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'">gList
						<span class="classic" style="color:black;line-height:20px;width:250px;text-align: left;padding:2px">Manage List of Groceries for easy shopping.</span></a><span style="color:#FFFAF0;">&nbsp;|&nbsp;</span>
					</td>
					<c:choose>
						<c:when test="${sessionScope.userAccountObj == null }">
							<td valign="top">
								<a href="<spring:url value="/useraccount/login.html" htmlEscape="true"/>" style="color:#FFFAF0;text-decoration: none;" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'">Login</a><span style="color:#FFFAF0;">&nbsp;|&nbsp;</span>
							</td>
							<td valign="top">
								<a href="<spring:url value="/useraccount/register.html" htmlEscape="true"/>" style="color:#FFFAF0;text-decoration: none;" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'">Signup</a><span style="color:#FFFAF0;">&nbsp;|&nbsp;</span>
							</td>
						</c:when>
						<c:otherwise>
							<td valign="top">
								<span style="color:#FFFAF0;"><c:out value="${sessionScope.userAccountObj.customerFirstname}"></c:out></span>&nbsp;<a href="<spring:url value="/useraccount/logout.html" htmlEscape="true"/>" style="color:#FFFAF0;text-decoration: none;" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'">(Logout)</a><span style="color:#FFFAF0;">&nbsp;|&nbsp;</span>
							</td>						
						</c:otherwise>
					</c:choose>
					<td valign="top">
						<a href="<spring:url value="/useraccount/myAccount.html" htmlEscape="true"/>" style="color:#FFFAF0;text-decoration: none;" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'">My Account</a><span style="color:#FFFAF0;">&nbsp;|</span>
					</td>
					<td align="right" valign="top">
						<div class="tooltip" style="width:75px;">
							<select onchange="languageSelect()" id="language">			
								<c:choose>
									<c:when test="${sessionScope.locale != null && sessionScope.locale == 'te'}">
							    		<option value="te" selected="selected">Telugu</option>
							    	</c:when>
							    	<c:otherwise>
							    		<option value="te" >Telugu</option>
							    	</c:otherwise>
							    </c:choose> 
							    <c:choose>
									<c:when test="${sessionScope.locale != null && sessionScope.locale == 'en'}">
							    		<option value="en" selected="selected">Hindi</option>
							    	</c:when>
							    	<c:otherwise>
							    		<option value="en" >Hindi</option>
							    	</c:otherwise>
							    </c:choose> 
							</select>
							<span class="classic" style="line-height:10px;width:95px;text-align: left;padding:2px">Menu items change to selected language.</span>
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<div id="headerMenu">
	<table cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
				<ul>
					<li>
						<div class="menu">
							<ul class="mainul">
								<li class="parentli"><p class="menup">Browse All Departments<!--[if gte IE 7]><!--></p><!--<![endif]-->
								<!--[if lte IE 6]><table class="menuTable"><tr><td><![endif]-->
									<ul class="menuul">
										<li class="parentli">
										<p class="drop">
											Grocery<br/>
											<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
												<spring:message code="label.toorDal"/>
											</c:if>
										</p>
									<!--[if lte IE 6]><table><tr><td><![endif]-->
										<ul id="secondul">
											<li><p class="seconddrop">
											Rice, Dals, Spices & more<br/>
											<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
												<spring:message code="label.toorDal"/>
											</c:if>
											</p>
												<ul id="childul">
													<li id="childli">
													<table>
														<tr>
															<td valign="top">
																<table>
																	<tr>
																		<td>
																			<table class="childTable">
																				<tr>
																					<th>
																						<a href="">Rice
																						<br/>
																						<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
																							<spring:message code="label.toorDal"/>
																						</c:if></a>
																					</th>
																				</tr>															
																				<tr>
																					<td>
																						<a href="">Sona Massori
																						<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
																							<spring:message code="label.toorDal"/>
																						</c:if></a>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<a href="">Basmati
																						<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
																							<spring:message code="label.toorDal"/>
																						</c:if>
																						</a>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<%@ include file="dalsandbeans.jsp" %>
																		</td>
																	</tr>
																</table>
															</td>
															<td valign="top">
																<table>
																	<tr>
																		<td>																		
																			<%@ include file="beverages.jsp" %> 
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<%@ include file="nutsandfruits.jsp" %> 
																		</td>
																	</tr>
																</table>
															</td>
															<td valign="top">
																<table>
																	<tr>
																		<td>																		
																			<%@ include file="spicesandmasala.jsp" %> 
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
 													</table>
													</li>
												</ul>
											</li>
											<li><p class="seconddrop">Flour, Cooking Oil & more
											<br/>
											<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
												<spring:message code="label.toorDal"/>
											</c:if></p>
											</li>
										</ul>
										
									<!--[if lte IE 6]></td></tr></table></a><![endif]-->
										</li>
										<li class="parentli"><p class="drop">Baby & Kids
										<br/>
											<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
												<spring:message code="label.toorDal"/>
											</c:if></p>
									<!--[if lte IE 6]><table><tr><td><![endif]-->	
												<ul id="childul">
													<li id="childli1">
													</li>
												</ul>										
									<!--[if lte IE 6]></td></tr></table></a><![endif]-->
										</li>
										<li class="parentli"><p class="drop">Bath & Body
										<br/>
											<c:if test="${sessionScope.locale != null && sessionScope.locale != 'en'}">
												<spring:message code="label.toorDal"/>
											</c:if></p>
									<!--[if lte IE 6]><table><tr><td><![endif]-->									
									<!--[if lte IE 6]></td></tr></table></a><![endif]-->
										</li>
									</ul>
								<!--[if lte IE 6]></td></tr></table></a><![endif]-->
								</li>
							</ul>
						</div>
					</li>
				</ul>
			</td>
			<td valign="top">
				<form id="searchForm">
					<ul>
						<li style="padding: 0 10px 0 10px;">
						  <div class="searchClass">
						  	<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Search</label>
						  </div>
						</li>
						<li>
							<div class="searchClass">
							  	<label>&nbsp;&nbsp;&nbsp;&nbsp;</label><select id="departmentSelect" name="departmentSelect">
							  		<option value="0" <% if (department != null && department == 0) { %>selected<%} %>>All Departments</option>
							  		<option value="100" <% if (department != null && department == 1) { %>selected<%} %>>Grocery</option>
							  	</select>
							</div>
						</li>
						<li>
							<div class="searchClass">
								<label>&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<input type="text" id="searchBox" name="searchBox" style="width:300px;" value="<%if (searchString != null) {%><%= searchString%><%}%>"/>
							</div>
						</li>
						<li>
							<div class="searchClass">								
								<input type="button" class="button rosy" value="Go" style="font-weight:bold;padding:2px 7px 2px 7px;" onclick="searchSubmit()"/>
								<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							</div>
						</li>
						<li>
							<!--[if lte IE 7]><div class="searchClass" style="padding:4px 0px 0px 0px;"><![endif]-->
							<div class="searchClass">
								<c:choose>
									<c:when test="${sessionScope.shoppingCartForm.shoppedProducts != null && fn:length(sessionScope.shoppingCartForm.shoppedProducts) > 0}">
										<c:set var="cartSize" value="${fn:length(sessionScope.shoppingCartForm.shoppedProducts) }"/>
									</c:when>
									<c:otherwise>
									<c:set var="cartSize" value="0"/>
									</c:otherwise>
								</c:choose>							
								<div  class="button orange buttonClass" style="font-weight:bold;padding:7px 7px 7px 7px;"><a style="text-decoration: none; color: #FFFFFF;" href="<spring:url value="/shopkart/showkart/shoppingKart" htmlEscape="true"/>"><span><img src="<spring:message code="content.url"/>/images/cart.png" style="border: transparent;"/>&nbsp;</span>My Cart<span id="cartSpan">(<c:out value="${cartSize}"/>)</span></a></div>
							</div>
						</li>								
					</ul>
				</form>
			</td>
		</tr>
	</table>

</div>

</body>
</html>