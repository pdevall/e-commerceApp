function languageSelect() {
	var lan = document.getElementById('language');
	var strLan = "?lang="+lan.options[lan.selectedIndex].value;
	window.location.href = strLan;
}

function submitForm(formName, url, action) {
	formObj = document.getElementById(formName);
	formObj.action = url;	
	formObj.actionString.value = action;
	formObj.submit();
}

function passwordReset() {
	jq('#messageText').html('');
	jq('#messageText').css({"display":"none"});
	jq('#ajaxImage').css({"display":"none"});
	jq('#ajaxImage').html('');
	jq('#emailAddress').val('');
	jq.blockUI({ message: jq('#resetPopUp'), css: { width: '500px'} });
    jq('#close').click(function() { 
        jq.unblockUI(); 
    }); 

}

function passwordResetAjax(url) {	
	jq('#ajaxImage').css({"display":"block"});
	jq('#messageText').css({"display":"none"});
	jq('#ajaxImage').html('<img src="'+staticURL+'/images/ajax.gif" /><h2>Remote call in progress...</h2>');
    var emailVal = jq('#emailAddress').val();    
    jq.getJSON(url, { email: emailVal }, function(data) {
    	var divTag = '';    	
		var errorObj = data.error;
		var successMessage = data.successMsg;
		if (errorObj != undefined) {			
			jq.each(errorObj, function() {
				divTag = divTag + '<font>' + this + '</font><br>';
			});
			jq('#messageText').addClass('errorMsg');
		} else if (successMessage != undefined) {
			jq.each(successMessage, function() {
				divTag = divTag + '<font size="2">' + this + '</font><br>';
			});
			jq('#messageText').addClass('successMsg');
		}
		jq('#ajaxImage').css({"display":"none"});
		jq('#ajaxImage').html('');
		jq.blockUI({ message: jq('#resetPopUp'), css: { width: '500px'} });
		jq('#messageText').css({"display":"block"});
    	jq('#messageText').html(divTag);
    	jq('#emailAddress').val('');

	});
}

//TODO need to convert to POST JSON

function changePassword() {
	jq('#changePasswordMessageText').html('');
	jq('#changePasswordMessageText').css({"display":"none"});
	jq('#changePasswordAjaxImage').css({"display":"none"});
	jq('#changePasswordAjaxImage').html('');
	jq('#changePasswordEmailAddress').val('');
	jq('#changePasswordCurrentPassword').val('');
	jq('#changePasswordNewPassword').val('');
	jq('#changePasswordConfirmNewPassword').val('');
	jq.blockUI({ message: jq('#changePasswordPopUp'), css: { width: '500px'} });
    jq('#changePasswordClose').click(function() { 
        jq.unblockUI(); 
    }); 

}

function changePasswordAjax(url) {	
	jq('#changePasswordAjaxImage').css({"display":"block"});
	jq('#changePasswordMessageText').css({"display":"none"});
	jq('#changePasswordAjaxImage').html('<img src="'+staticURL+'/images/ajax.gif" /><h2>Remote call in progress...</h2>');
    var emailVal = jq('#changePasswordEmailAddress').val();
    var currentPasswordVal = jq('#changePasswordCurrentPassword').val();
    var newPasswordVal = jq('#changePasswordNewPassword').val();
    var confirmPasswordVal = jq('#changePasswordConfirmNewPassword').val();
    jq.getJSON(url, { emailAddress: emailVal, currentPassword: currentPasswordVal, newPassword: newPasswordVal, confirmNewPassword: confirmPasswordVal }, function(data) {
    	var divTag = '';    	
		var errorObj = data.error;
		var successMessage = data.successMsg;
		if (errorObj != undefined) {			
			jq.each(errorObj, function() {
				divTag = divTag + '<font>' + this + '</font><br>';
			});
			jq('#changePasswordMessageText').addClass('errorMsg');
		} else if (successMessage != undefined) {
			jq.each(successMessage, function() {
				divTag = divTag + '<font size="2">' + this + '</font><br>';
			});
			jq('#changePasswordMessageText').addClass('successMsg');
		}
		jq('#changePasswordAjaxImage').css({"display":"none"});
		jq('#changePasswordAjaxImage').html('');
		jq.blockUI({ message: jq('#changePasswordPopUp'), css: { width: '500px'} });
		jq('#changePasswordMessageText').css({"display":"block"});
    	jq('#changePasswordMessageText').html(divTag);
    	jq('#changePasswordEmailAddress').val('');
    	jq('#changePasswordCurrentPassword').val('');
    	jq('#changePasswordNewPassword').val('');
    	jq('#changePasswordConfirmNewPassword').val('');
    	if (successMessage != undefined) {
    		window.location.href = springUrl +"index.html";
    	}    
	});
}

function changeEmail() {
	jq('#changeEmailMessageText').html('');
	jq('#changeEmailMessageText').css({"display":"none"});
	jq('#changeEmailAjaxImage').css({"display":"none"});
	jq('#changeEmailAjaxImage').html('');
	jq('#changeEmailCurrentEmailAddress').val('');
	jq('#changeEmailNewEmailAddress').val('');
	jq('#changeEmailConfirmNewEmailAddress').val('');
	jq('#changeEmailPassword').val('');
	jq.blockUI({ message: jq('#changeEmailPopUp'), css: { width: '500px'} });
    jq('#changeEmailClose').click(function() { 
        jq.unblockUI(); 
    }); 

}

function changeEmailAjax(url) {	
	jq('#changeEmailAjaxImage').css({"display":"block"});
	jq('#changeEmailMessageText').css({"display":"none"});
	jq('#changeEmailAjaxImage').html('<img src="'+staticURL+'/images/ajax.gif" /><h2>Remote call in progress...</h2>');
    var emailVal = jq('#changeEmailCurrentEmailAddress').val();
    var newEmailVal = jq('#changeEmailNewEmailAddress').val();
    var confirmEmailVal = jq('#changeEmailConfirmNewEmailAddress').val();
    var passwordVal = jq('#changeEmailPassword').val();
    jq.getJSON(url, { currentEmailAddress: emailVal, newEmailAddress: newEmailVal, confirmNewEmailAddress: confirmEmailVal, password: passwordVal }, function(data) {
    	var divTag = '';    	
		var errorObj = data.error;
		var successMessage = data.successMsg;
		if (errorObj != undefined) {			
			jq.each(errorObj, function() {
				divTag = divTag + '<font>' + this + '</font><br>';
			});
			jq('#changeEmailMessageText').addClass('errorMsg');
		} else if (successMessage != undefined) {
			jq.each(successMessage, function() {
				divTag = divTag + '<font size="2">' + this + '</font><br>';
			});
			jq('#changeEmailMessageText').addClass('successMsg');
		}
		jq('#changeEmailAjaxImage').css({"display":"none"});
		jq('#changeEmailAjaxImage').html('');
		jq.blockUI({ message: jq('#changeEmailPopUp'), css: { width: '500px'} });
		jq('#changeEmailMessageText').css({"display":"block"});
    	jq('#changeEmailMessageText').html(divTag);
    	jq('#changeEmailPassword').val('');


	});
}

function numbersonly(evt) {
	evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function fetchProductsByCategory(startNumber, url) {
	url = url + "?startNumber=" + startNumber;
	var productResults = '';
	jq.blockUI({ message: '<img src="'+staticURL+'/images/ajax.gif" /><h2>Remote call in progress...</h2>' });
	jq.getJSON(url, function(data) {
		if (data != undefined) {
			var products = data.products;
			if (products != undefined) {
				var count = 1;
				jq.each(products, function(key, value) {
					
						if (count == 1 || count % 4 == 0) {
							productResults = productResults +'<tr>';
						}
						productResults = productResults + '<td valign="top">';
						productResults = productResults + '<table>';
						productResults = productResults + '<tr height="175px" >';
						productResults = productResults + '<td  width="180px" align="center" valign="top">';
						productResults = productResults + '<a href="'+'"><img src="'+ staticURL + value.smallImageUrl +  '" title=" ' + value.productName + '" alt="' + value.productName + '"></a>';
						productResults = productResults + '</td>';
						productResults = productResults + '</tr>';
						productResults = productResults + '<tr height="100px">';
						productResults = productResults + '<td  width="180px" align="center" style="word-wrap:break-word;" valign="top">';
						productResults = productResults + '<a href="' + '">';
						productResults = productResults + '<font>' + value.productName + '</font>';
						productResults = productResults + '</a>';
						if (value.messageTextExist) {												
							productResults = productResults + '<br/><font style="color:#993333;font-weight: bold;">' + value.springLabelValue + '</font>';
						}																	
						productResults = productResults + '<br>';
						if (value.productDiscount != null && value.productDiscount.allOffers !=null && value.productDiscount.allOffers) {
							productResults = productResults + '<font class="discountCost"><del>Rs. ' + value.productCost + '</del>';
							productResults = productResults + '(' + value.productDiscount.discountPercent + '% off';
							if (value.productDiscount.productOfferName != null &&  value.productDiscount.productOfferName != '') {
								productResults = productResults + 'and free' + value.productDiscount.productOfferName;
							}
							productResults = productResults + '</font>';
							productResults = productResults + '<br/>';
							productResults = productResults + '<font class="cost">Rs. ' + value.productCostAfterDiscount + '</font>';																	
							} else if (value.productDiscount != null && value.productDiscount.discountPercent != null) {
								productResults = productResults + '<font class="discountCost"><del>Rs. ' + value.productCost + '</del>';
								productResults = productResults + '(' + value.productDiscount.discountPercent + '% off';
								productResults = productResults + '</font>';
								productResults = productResults + '<br/>';
								productResults = productResults + '<font class="cost">Rs. ' + value.productCostAfterDiscount + '</font>';																		
							} else if (value.productDiscount.productOfferName != null &&  value.productDiscount.productOfferName != '') {
								productResults = productResults + '<font class="discountCost">';																			 
								productResults = productResults + 'free' + value.productDiscount.productOfferName;
								productResults = productResults + '</font>';
								productResults = productResults + '<br/>';
								productResults = productResults + '<font class="cost">Rs. ' + value.productCost + '</font>';																		
							} else {								
								productResults = productResults + '<font class="cost">Rs. ' + value.productCost + '</font>';											
							}
						productResults = productResults + '</td>';
						productResults = productResults + '</tr>';
						productResults = productResults + '<tr>';
						productResults = productResults + '<td valign="top">';
						productResults = productResults + '<input type="button" class="viewAllButton" id="addtocart" value="Add to Cart" onclick="addToCartAjax('+"'"+ springUrl+'/shopkart/ajax/add2kart/product/'+value.productId+"'"+')"/>';
						productResults = productResults + '&nbsp;<input type="button" class="viewAllButton" id="addtoglist" value="Add to gList" />';
						productResults = productResults + '</td>';
						productResults = productResults + '</tr>';

						productResults = productResults + '</table>';
						productResults = productResults + '</td>';
						if (count % 3 == 0 || count == data.productLength) {
							productResults = productResults + '</tr>';
							productResults = productResults + '<tr>';
							productResults = productResults + '<td colspan="3" valign="top">';
							productResults = productResults + '<hr/>';
							productResults = productResults + '</td>';
							productResults = productResults + '</tr>';							
						}
						count = count + 1;

				});
				jq('#productsByCategoryTable').append(productResults);
				jq.unblockUI();
			}
			if (data.startNumber > 0 && data.startNumber < data.numOfProductsByCategory) {
				var number = "'" + data.startNumber+ "'";
				var address = "'" +springUrl+ "/gk/ajax/" +data.name+ "/category/" +data.id + "'";
				jq('#fetchResultsTable').html('');
				var buttonTable = '<tr>';
				buttonTable = buttonTable + '<td valign="top" align="center" width="100%">';
				buttonTable = buttonTable + '<input type="button" class="button rosy" id="fetchResults" value="Fetch More" onclick="fetchProductsByCategory(' +number + ',' +address+ ')">';
				buttonTable = buttonTable + '</td>';
				buttonTable = buttonTable + '</tr>';
				jq('#fetchResultsTable').html(buttonTable);

			} else {
				jq('#fetchResults').css({"display":"none"});
			}
		}
	});
}

function updateToCart(url, index) {
	formObj = document.getElementById('shoppingKartForm');
	quantity = document.getElementById('frontendQuantity'+index);
	pq = document.getElementById('productQuantity');
	pq.value = quantity.value;
	formObj.action = url;
	formObj.submit();
}

function addToCart(url) {
	formObj = document.getElementById('shoppingKartForm');
	formObj.action = url;
	formObj.submit();
}

function addToCartAjax(url) {
	
	jq.blockUI({ message: '<img src="'+staticURL+'/images/ajax.gif" /><h2>Remote call in progress...</h2>' });
	jq.getJSON(url, function(data) {
		if (data != undefined) {
			jq('#cartSpan').html('('+ data +')');
			jq.unblockUI();			
			growlUi('<h3 style="text-align: center;">Successfully added to the Cart.</h3>');

		}
	});
}

function addToGListAjax(url) {
	jq.blockUI({ message: '<img src="'+staticURL+'/images/ajax.gif" /><h2>Remote call in progress...</h2>' });
	jq.getJSON(url, function(data) {
		if (data != undefined) {
			jq.unblockUI();
			if (data == -1) {
				message = '<h3 style="text-align: center;">Please Login or Signup to add to the gList.</h3>';
			} else if (data == 0) {
				message = '<h3 style="text-align: center;">This Product is already added to gList.</h3>';	
			} else {
				message = '<h3 style="text-align: center;">Product has been added to the gList successfully.</h3>';
			}
			growlUi(message);

		}
	});
}

function growlUi(message) {
	jq.blockUI({ 
	    message: message, 
	    fadeIn: 700, 
	    fadeOut: 700, 
	    timeout: 2000, 
	    showOverlay: true, 
	    centerY: true, 
	    css: { 
	        width: '350px', 
	        border: 'none', 
	        padding: '5px',
	        height: '50px',
	        backgroundColor: '#009999', 
	        '-webkit-border-radius': '10px', 
	        '-moz-border-radius': '10px', 
	        opacity: .6, 
	        color: '#fff' 
	    } 
	}); 
}

function buy(url) {
	formObj = document.getElementById('shoppingKartForm');
	quantity = document.getElementById('productDetailsQuantity');
	pq = document.getElementById('productQuantity');
	pq.value = quantity.value;
	formObj.action = url;
	formObj.submit();
}


function pagination(url, pageNumber, pageBoolean) {	
	var url = url +"?pageNumber="+pageNumber+"&pageBoolean="+pageBoolean;
	var tableString = '';
    jq.getJSON(url, function(data) {
    	var imageUrl = '<img src="'+staticURL+'/images/ajax.gif" /><h2>Remote call in progress...</h2>';
    	jq('#ajaxImage').css({"display":"inline"});
    	jq('#similarResults').html('');
    	if (data != undefined) {
			tableString = tableString + '<tr>';
			if(data.previousPage >= 0) {
				tableString  = tableString + '<td>';					
				tableString = tableString + '<img src="'+staticURL+'/images/arrow-left-small.png" style="cursor: pointer;" onclick="pagination('+"'"+springUrl+"pd/ajax/category/"+data.id+"','"+data.previousPage+"', 'false')"+'"/>';
				tableString = tableString + '</td>';
			}
			tableString = tableString + '<td>';
			tableString = tableString + '<div class="resultsMainContent" style="width:700px;border-top: 1px solid #C39C4E;">';
			tableString = tableString + '<div class="boxContent">';
			if(data != null) {
				var products = data.products;
				if (products != undefined) {
					tableString = tableString + '<table>';
					tableString = tableString + '<tr>';					
					jq.each(products, function(key, product) {
						tableString = tableString + '<td valign="top">';
						tableString = tableString + '<table>';
						tableString = tableString + '<tr height="175px" >';
						tableString = tableString + '<td  width="180px" align="center" valign="top">';
						tableString = tableString + '<a href="'+springUrl+'/pd/'+product.productName+'/product/'+product.productId+'"><img src="'+staticURL+product.smallImageUrl+'" title="'+product.productName+'" alt="'+product.productName+'"></a>';
						tableString = tableString +	'</td>';
						tableString = tableString +	'</tr>';
						tableString = tableString + '<tr>';
						tableString = tableString + '<td  width="180px" align="center" style="word-wrap:break-word;" valign="top">';
						tableString = tableString + '<a href="'+springUrl+'/pd/'+product.productName+'/product/'+product.productId+'">';
						tableString = tableString + '<font>';
						tableString = tableString + product.productName;
						tableString = tableString + '</font>';
						tableString = tableString + '</a>';
						if (product.springLabelValue != null) {
							tableString = tableString +	'<br/>';	
							tableString = tableString + '<font style="color:#993333;font-weight: bold;">'+product.springLabelValue+'</font>';
						}
						tableString = tableString + '<br>';
						if (product.productDiscount != null && product.productDiscount.allOffers !=null && product.productDiscount.allOffers) {
							tableString = tableString + '<font class="discountCost"><del>Rs. ' + product.productCost + '</del>';
							tableString = tableString + '(' + product.productDiscount.discountPercent + '% off';
							if (product.productDiscount.productOfferName != null &&  product.productDiscount.productOfferName != '') {
								tableString = tableString + 'and free' + product.productDiscount.productOfferName;
							}
							tableString = tableString + '</font>';
							tableString = tableString + '<br/>';
							tableString = tableString + '<font class="cost">Rs. ' + product.productCostAfterDiscount + '</font>';																	
						} else if (product.productDiscount != null && product.productDiscount.discountPercent != null) {
								tableString = tableString + '<font class="discountCost"><del>Rs. ' + product.productCost + '</del>';
								tableString = tableString + '(' + product.productDiscount.discountPercent + '% off';
								tableString = tableString + '</font>';
								tableString = tableString + '<br/>';
								tableString = tableString + '<font class="cost">Rs. ' + product.productCostAfterDiscount + '</font>';																		
						} else if (product.productDiscount.productOfferName != null &&  product.productDiscount.productOfferName != '') {
								tableString = tableString + '<font class="discountCost">';																			 
								tableString = tableString + 'free' + product.productDiscount.productOfferName;
								tableString = tableString + '</font>';
								tableString = tableString + '<br/>';
								tableString = tableString + '<font class="cost">Rs. ' + product.productCost + '</font>';																		
						} else {								
								tableString = tableString + '<font class="cost">Rs. ' + product.productCost + '</font>';											
						}
						tableString = tableString +	'</td>';
						tableString = tableString + '</tr>';
						tableString = tableString + '</table>';
						tableString = tableString + '</td>';
				});
			}
			tableString = tableString +	'</tr>';
			tableString = tableString + '</table>';
			tableString = tableString + '</div>';
			tableString = tableString + '</div>';
			tableString = tableString + '</td>';

			if(data.nextPage < data.totalNumberOfPages) {
				tableString = tableString + '<td>'; 
				tableString = tableString + '<img src="'+staticURL+'/images/arrow-right-small.png" style="cursor: pointer;" onclick="pagination('+"'"+springUrl+"pd/ajax/category/"+data.id+"','"+data.nextPage+"', 'true')"+'"/>';				
				tableString = tableString + '</td>';
			}
			tableString = tableString + '</tr>';					

		}
	    	jq('#ajaxImage').css({"display":"none"});
	    	jq('#similarResults').html(tableString);
		
      }
    });
}

function glistSubmit(url, pageNumber, pageBoolean) {
	formObj = document.getElementById("glistForm");
	formObj.pageNumber.value = pageNumber;
	formObj.pageBoolean.value = pageBoolean;
	formObj.action = url;
	formObj.submit();
}

function glistSubmitAction(url, actionString) {
	formObj = document.getElementById("glistForm");
	formObj.actionString.value = actionString;
	formObj.action = url;
	formObj.submit();
}

function searchSubmit() {
	formObj = document.getElementById("searchForm");
	if (formObj.searchBox.value != '') {
		var dept = formObj.departmentSelect.options[formObj.departmentSelect.selectedIndex].value;
		formObj.action = springUrl+'/search/searchProducts/dept/'+dept+'/searchString/'+formObj.searchBox.value;
		formObj.submit();
	} else {
		alert('Enter the Search String.');
	}	
}

function selectAddress(url, addressId) {
	formObj = document.getElementById("checkoutForm");
	formObj.selectedShippingAddrId.value = addressId;
	formObj.action = url;
	formObj.submit();
}

function payment(url, formId) {
	jq('#errors').css({"display":"none"});
	jq('#showImage').css({"display":"block"});
	formO = document.getElementById(formId);
	var radioArray = formO.paymentType;
	var checked = false;
	for (i=0; i < radioArray.length; i++) {
	   if (radioArray[i].checked){
		  checked = true;
	      break
	   }
	}
	if (checked) {
		var formObj = jq("#"+formId).serializeObject();
		jq.postJSON(url, formObj, function(data) {
			if (data != undefined) {
				if (data == 1) {
					window.location.href = springUrl +"index.html";
				} else if (data == 2) {
					//Net banking url
					window.location.href = springUrl +"index.html";
				} else if (data == 3) {
					//Net banking url
					window.location.href = springUrl +"index.html";				
				} else if (data == 0){
					jq('#showImage').css({"display":"none"});
					jq('#errors').css({"display":"block"});
					jq('#errors').html('<font>Error occured while processing. Please contact customer service.</font>');
				}
			} else {
				jq('#showImage').css({"display":"none"});
				jq('#errors').css({"display":"block"});
				jq('#errors').html('<font>Error occured while processing. Please contact customer service.</font>');
			}
		});
	} else {
		jq('#showImage').css({"display":"none"});
		jq('#errors').css({"display":"block"});
		jq('#errors').html('<font>Select the Payment Method.</font>');
	}
}
