
var initOrderCheck = function(){

	initTimePicker(); // 时间控件初始
	
	$("#ModifyModal").on("hidden.bs.modal", function() {  
	    $(this).removeData("bs.modal");  
	}); 
	
	var discount = null;
	if(modifyOrder){
		discount = modifyOrder.custInfo.levelInfo.lDiscount;
	}
	var computeDscount = function (value){
		if(discount && discount!=null&&value){
        	var afterDiscountAmt = value * discount;
        	$("#ModifyModal").find('.modal-body input[name="afterDiscountAmt"]').val(afterDiscountAmt);
        	var userAmt = Number($("#ModifyModal").find('.modal-body input[name="userAmt"]').val());
        	var deposits = Number($("#ModifyModal").find('.modal-body input[name="deposits"]').val());
        	if(userAmt>0 && userAmt - afterDiscountAmt>=0){
        		$("#ModifyModal").find('.modal-body input[name="payAmount"]').val(afterDiscountAmt);
        	}else if(userAmt>0 && userAmt - afterDiscountAmt<0){
        		$("#ModifyModal").find('.modal-body input[name="payAmount"]').val(userAmt);
        	}else if(userAmt==0){
        		$("#ModifyModal").find('.modal-body input[name="payAmount"]').val(deposits);
        	}
        	
    	}
	}
	var totalAmtElement = $("#ModifyModal").find('.modal-body input[name="totalAmt"]');
	totalAmtElement.keyup(function(){
		var value = totalAmtElement.val();
		 if(discount && discount!=null&&value){
        	var afterDiscountAmt = value * discount;
        	$("#ModifyModal").find('.modal-body input[name="afterDiscountAmt"]').val(afterDiscountAmt);
        	computePayAmount();
	    }
	 });
	 
	
	var computePayAmount = function(){
		
		var afterDiscountAmt = Number($("#ModifyModal").find('.modal-body input[name="afterDiscountAmt"]').val());
    	var userAmt = Number($("#ModifyModal").find('.modal-body input[name="userAmt"]').val());
    	var deposits = Number($("#ModifyModal").find('.modal-body input[name="deposits"]').val());
    	if(userAmt>0 && userAmt - afterDiscountAmt>=0){
    		$("#ModifyModal").find('.modal-body input[name="payAmount"]').val(afterDiscountAmt);
    	}else if(userAmt>0 && userAmt - afterDiscountAmt<0){
    		$("#ModifyModal").find('.modal-body input[name="payAmount"]').val(userAmt);
    	}else if(userAmt==0){
    		$("#ModifyModal").find('.modal-body input[name="payAmount"]').val(deposits);
    	}
	}
	
	var oWechatNoElement = $("#ModifyModal").find('.modal-body input[name="oWechatNo"]');
	var oWechatNoExists = true;
	
    $('#modifyDataFrom').bootstrapValidator({
        message: '输入无效,请重新输入',
        group:'.form-group',
        trigger: 'keyup focus',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	oWechatNo: {
                message: '客户微信号输入无效',
                validators: {
                    notEmpty: {
                        message: '客户微信号不能为空'
                    },
                    callback: {
                        message: '客户微信号不存在,请添加客户信息或查证后再录入',
                        callback:function(value, validator,$field){
                        	if(!oWechatNoExists){
                        		checkWechatNo();
                        	}
							return oWechatNoExists;
                        }
                    }
                }
            },
        	contact: {
                message: '联系人输入无效',
                validators: {
                    notEmpty: {
                        message: '联系人不能为空'
                    }
                }
            },
            productList: {
                message: '产品列表信息输入无效',
                validators: {
                    notEmpty: {
                        message: '产品列表不能为空'
                    }
                }
            },
            address: {
                message: '地址输入无效',
                validators: {
                    notEmpty: {
                        message: '地址不能为空'
                    }
                }
            },
            userAmt: {
                message: '会员余额异常',
                callback: {
                    callback:function(value, validator,$field){
						
						return true;
                    }
                }
            },
            totalAmt: {
                message: '总金额输入无效',
                validators: {
                    notEmpty: {
                        message: '总金额不能为空'
                    },
                    greaterThan: {
                        value: 1,
                		message: '总金额必须大于0'
                    },
                    numeric: {message: '总金额只能输入数字'},
	                callback: {
                        callback:function(value, validator,$field){
                        	var orderStatus = $("#ModifyModal").find('.modal-body select[name="orderStatus"] option:selected').val();
            				if(orderStatus==0){
            					//computeDscount(value);
            					validator.updateStatus('deposits', 'NOT_VALIDATED').validateField('deposits'); 
            					validator.updateStatus('cashOnDeliveryAmt', 'NOT_VALIDATED').validateField('cashOnDeliveryAmt'); 
            				}
							return true;
                        }
                    }
                }
            },
            deposits: {
                message: '定金输入无效',
                validators: {
                    numeric: {message: '定金只能输入数字'},
                    callback: {
                        callback:function(value, validator,$field){
							var orderStatus = $("#ModifyModal").find('.modal-body select[name="orderStatus"] option:selected').val();
							
							if(Number(orderStatus)==3){
								return true;
							}
							
                        	var userAmt = $("#ModifyModal").find('.modal-body input[name="userAmt"]').val();
                        	if(userAmt==0){
                        		$("#ModifyModal").find('.modal-body input[name="payAmount"]').val(value);
                        	}
							
							if(userAmt==0&&(value<=0||value=='')){
								return  {
			                        valid: false,
			                        message: '会员余额为0时,定金必填,且不可为0'
			                    };
							}
							
							var cashOnDeliveryAmt = Number($("#ModifyModal").find('.modal-body input[name="cashOnDeliveryAmt"]').val());
							var afterDiscountAmt = Number($("#ModifyModal").find('.modal-body input[name="afterDiscountAmt"]').val());
							if(afterDiscountAmt>Number(value)+cashOnDeliveryAmt){
								return  {
			                        valid: false,
			                        message: '会员余额为0,定金+到付金额不能小于折扣金额'
			                    };
							}else{
								validator.updateStatus('cashOnDeliveryAmt', 'VALID');
							}
							
			            	
							return true;
                        }
                    }
                }
            },
            cashOnDeliveryAmt: {
                message: '到付金额输入无效',
                validators: {
                	numeric: {message: '到付金额只能输入数字'},
                    callback: {
                        callback:function(fieldValue, validator,$field){
							var userAmt = Number($("#ModifyModal").find('.modal-body input[name="userAmt"]').val());
							var afterDiscountAmt = Number($("#ModifyModal").find('.modal-body input[name="afterDiscountAmt"]').val());
							var deposits = Number($("#ModifyModal").find('.modal-body input[name="deposits"]').val());
							var value = Number(fieldValue);
							var orderStatus = $("#ModifyModal").find('.modal-body select[name="orderStatus"] option:selected').val();
							
							if(Number(orderStatus)==3){
								return true;
							}
							if(userAmt==0 && afterDiscountAmt>value+deposits){
								validator.updateStatus('deposits', 'NOT_VALIDATED').validateField('deposits'); 
								return  {
			                        valid: false,
			                        message: '会员余额为0时,定金+到付金额不能小于折扣金额'
			                    };
							}else{
								validator.updateStatus('deposits', 'VALID');
							}
							
							if(userAmt==0 && afterDiscountAmt==0 && value<=0){
								return  {
			                        valid: false,
			                        message: '会员余额为0时,到付金额必填,且不可为0'
			                    };
							}else if(userAmt>0 && (userAmt-afterDiscountAmt)<0){
								//$("#ModifyModal").find('.modal-body input[name="cashOnDeliveryAmt"]').val(afterDiscountAmt - userAmt);
								return  {
			                        valid: value >= afterDiscountAmt - userAmt - deposits,
			                        message: '会员余额不足时,到付金额必填,且到付金额=折扣金额-会员余额'
			                    };
							}
							
							return true;
                        }
                    }
                }
            },
            afterDiscountAmt: {
                message: '折扣金额输入无效',
                validators: {
                	numeric: {message: '折扣只能输入数字'},
                    callback: {
                        callback:function(value, validator,$field){
							var userAmt = $("#ModifyModal").find('.modal-body input[name="userAmt"]').val();
							var afterDiscountAmt = $("#ModifyModal").find('.modal-body input[name="afterDiscountAmt"]').val();
							
							validator.updateStatus('cashOnDeliveryAmt', 'NOT_VALIDATED').validateField('cashOnDeliveryAmt'); 
							computePayAmount();
							return true;
                        }
                    }
                }
            },
            oPhone: {
                message: '无效手机号',
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号长度必须是由11位数字组成'
                    },
                    regexp: {
                        regexp: /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/,
                        message: '手机号格式不正确'
                    }
                }
            }
        }
    }).on('error.form.bv',function(){
	     $(".has-error:visible:first").find(":input").focus();
   });
    
   var checkWechatNo = function(){
    	var params = {oWechatNo:oWechatNoElement.val()};
    	$.ajax({
    		url: checkWechatNoURL,
    		data:params,
    		type: 'get',
    		cache : false,
    		async : false,
    		dataType: 'json',
    		success: function(data) {
    			oWechatNoExists = data!=null;
    			if(oWechatNoExists){
    				discount = data.levelInfo.lDiscount;
    				$("#ModifyModal").find('.modal-body input[name="contact"]').val(data.custName);
    				$("#ModifyModal").find('.modal-body input[name="address"]').val(data.address);
    				$("#ModifyModal").find('.modal-body input[name="oPhone"]').val(data.custPhone);
    				$("#ModifyModal").find('.modal-body input[name="userAmt"]').val(data.amt.toFixed(2));
    				
    				$("#ModifyModal").find('.modal-body input[name="deposits"]').val(""); 
    				$("#ModifyModal").find('.modal-body input[name="cashOnDeliveryAmt"]').val(""); 
    				$("#ModifyModal").find('.modal-body input[name="afterDiscountAmt"]').val(""); 
    				
    				var bootstrapValidator = $("#modifyDataFrom").data("bootstrapValidator");
    				bootstrapValidator.updateStatus('contact', 'NOT_VALIDATED').validateField('contact'); 
    				bootstrapValidator.updateStatus('address', 'NOT_VALIDATED').validateField('address'); 
    				bootstrapValidator.updateStatus('oPhone', 'NOT_VALIDATED').validateField('oPhone'); 
    				bootstrapValidator.updateStatus('oWechatNo', 'VALID'); 
    				 
    				var orderStatus = $("#ModifyModal").find('.modal-body select[name="orderStatus"] option:selected').val();
    				if(orderStatus==0){
    					if(data.amt.toFixed(2)>0){
        					$("#ModifyModal").find('.modal-body input[name="deposits"]').attr("readonly","true");
    					}else{
    						$("#ModifyModal").find('.modal-body input[name="deposits"]').removeAttr("readonly");
    					}
    					
    					var totalAmt = $("#ModifyModal").find('.modal-body input[name="totalAmt"]').val();
        				computeDscount(totalAmt);
    				}
    			}
    		},error:function(data){
    			oWechatNoExists = false;
    			var bootstrapValidator = $("#modifyDataFrom").data("bootstrapValidator");
    			$('#modifyDataFrom').data('bootstrapValidator').resetForm();
    			bootstrapValidator.updateStatus('oWechatNo', 'INVALID');
    		}
    	});
	}
	
	oWechatNoElement.keyup(checkWechatNo);
	var oldValue = 0;
	$("#ModifyModal").find('.modal-body select[name="orderStatus"]').click(function(){
		var orderStatus = $("#ModifyModal").find('.modal-body select[name="orderStatus"] option:selected').val();
		var payAmountObj = $("#ModifyModal").find('.modal-body input[name="payAmount"]');
		if(oldValue==0){
			oldValue = Number(payAmountObj.val());
		}
		
		if(Number(orderStatus)==3){
			var cashOnDeliveryAmt = Number($("#ModifyModal").find('.modal-body input[name="cashOnDeliveryAmt"]').val());
			payAmountObj.val((oldValue + cashOnDeliveryAmt).toFixed(2));
		}else{
			payAmountObj.val(oldValue.toFixed(2));
		}
	});
	
}