$(document).ready(function(){
	$("#form").validate({
        rules : {
        	businessType:"required",
        	productType:"required",
        	tradeType:"required",
        	entityId:"required",
        	subjectId1:"required",
        	debitId1:"required",
			}
        });
		
	
	$("#subjectId1").autocomplete("/rcSubject/findSubTmpIdOrNam.do",
	 {
	 		minChars:4,
            width : 300, // 提示的宽度，溢出隐藏
            max : 15,// 显示数量
            autoFill : false,
            //scroll : false, // 当结果集大于默认高度时是否使用卷轴显示
            highlight : false,
            highlightItem: true,
            scroll : true,
            matchContains : true,
            multiple :false,

            formatItem: function(row, i, max) {
                return '<span style="color:gray;padding-right:10px;"> ' + row.name + '</span> <span style="color:#005EA7">[' + row.id + ']</span>';
            },
            formatMatch: function(row, i, max) {
                return row.name + row.id;
            },
            formatResult: function(row) {
                return row.id;
            },
            parse:function(data) {//解释返回的数据，把其存在数组里
                var array=eval(data);
                var parsed = [];
                if(array == null)
                {
                    return parsed;
                }
                for (var i = 0; i < array.length; i++) {
                    parsed[i] = {
                        data: array[i],
                        value: array[i].id,
                        result: array[i].name
                    };
                }
                return parsed;
            }
        }
	 
	 ).result(function(event, row, formatted) {
               $(this).val(row.id);
					
    });
	$("#subjectId2").autocomplete("/rcSubject/findSubTmpIdOrNam.do",
	 {
	 		minChars:4,
            width : 300, // 提示的宽度，溢出隐藏
            max : 15,// 显示数量
            autoFill : false,
            //scroll : false, // 当结果集大于默认高度时是否使用卷轴显示
            highlight : false,
            highlightItem: true,
            scroll : true,
            matchContains : true,
            multiple :false,

            formatItem: function(row, i, max) {
                return '<span style="color:gray;padding-right:10px;"> ' + row.name + '</span> <span style="color:#005EA7">[' + row.id + ']</span>';
            },
            formatMatch: function(row, i, max) {
                return row.name + row.id;
            },
            formatResult: function(row) {
                return row.id;
            },
            parse:function(data) {//解释返回的数据，把其存在数组里
                var array=eval(data);
                var parsed = [];
                if(array == null)
                {
                    return parsed;
                }
                for (var i = 0; i < array.length; i++) {
                    parsed[i] = {
                        data: array[i],
                        value: array[i].id,
                        result: array[i].name
                    };
                }
                return parsed;
            }
        }
	 
	 ).result(function(event, row, formatted) {
               $(this).val(row.id);
    });		
				
	$(function(){
	    $("#businessType").change(function() {ajaxSelectProductType()});
 		$("#productType").change(function() {ajaxSelectTradeType()});
		$("#tradeType").change(function() {tradeTypeChange()});
     });
	
});
	function ajaxSelectProductType(){
		
		var businessType = jQuery("#businessType").val();
		if (businessType ==null || businessType == '') {
		    jQuery("#productType").html('<option value="">请选择</option>');
    		jQuery("#tradeType").html('<option value="">请选择</option>');
			return;
		}
        jQuery.ajax({
            type : 'POST',
            url: '../rcBizParam/selectProductType.do?bizType=' + businessType,
            dataType: "text",
            success: function(data, textStatus) {
                var dataObj=eval("("+data+")");
                var target = $("#productType");
                target.html('<option value="">请选择</option>');
                jQuery.each(dataObj, function(i,item){
                    target.append('<option value="'+item.productType+'">'+item.productNam+'</option>');
                });
            }
        });
    }	
	function ajaxSelectTradeType(){
		jQuery("#tradeType").html('<option value="">请选择</option>');
		var productType = jQuery("#productType").val();
		if (productType ==null || productType == '') {
    		jQuery("#tradeType").html('<option value="">请选择</option>');
			return;
		}
		
        jQuery.ajax({
            type : 'POST',
            url: '../rcBizParam/selectTradeType.do?productType=' + productType,
            dataType: "text",
            success: function(data, textStatus) {
                var dataObj=eval("("+data+")");
                var target = $("#tradeType");
                target.html('<option value="">请选择</option>');
                jQuery.each(dataObj, function(i,item){
                    target.append('<option value="'+item.tradeType+'">'+item.tradeNam+'</option>');
                });
            }
        });
    }	
	
	function tradeTypeChange() {
		var tradeTypeValue = jQuery("#tradeType").val();
		if (tradeTypeValue == null || tradeTypeValue == '') {
			jQuery("#subject2").hide();
			jQuery("#subjectId2").val('');
			jQuery("#debitId2").val('-1');
			jQuery('#subjectId2').rules("remove");
			jQuery('#debitId2').rules("remove");
			return;
		} else {
    		jQuery.ajax({
                type : 'POST',
                url: '../rcJournalRule/getReseivePayFlagByTradeType.do?tradeType=' + tradeTypeValue,
                 dataType: 'json',
                success: function(data) {
					if (data.state == 'ok') {
						if (data.reseivePayFlag == '1') {
							jQuery("#subject2").hide();
                			jQuery("#subjectId2").val('');
                			jQuery("#debitId2").val('-1');
                			jQuery('#subjectId2').rules("remove");
                			jQuery('#debitId2').rules("remove");
							return;
						} else {
							jQuery("#subject2").show();
							jQuery('#subjectId2').rules("add",{required:true});
							jQuery('#debitId2').rules("add",{required:true});
						}
					} else {
						alert(data.msg);
					}
                },
    			error: function(data, status, e){  
    	            alert(e); 
    	         }
            });
		}
	}
	
	function confirmClick() {
		var businessTypeValue = jQuery("#businessType").val();
		if (businessTypeValue == null || businessTypeValue == '') {
			alert('请选择业务类型');
			return;
		}
		var productTypeValue = jQuery("#productType").val();
		if (productTypeValue == null || productTypeValue == '') {
			alert('请选择产品类型');
			return;
		}
		var tradeTypeValue = jQuery("#tradeType").val();
		if (tradeTypeValue == null || tradeTypeValue == '') {
			alert('请选择交易类型');
			return;
		} 
		var entityIdValue = jQuery("#entityId").val();

		if (entityIdValue == null || entityIdValue == '') {
			alert('请选择公司名称');
			return;
		} 
		var subjectId1Value = jQuery("#subjectId1").val();
		if (subjectId1Value == null || subjectId1Value == '') {
			alert('请输入科目代码');
			return;
		} 
		var form = $("#form");
		jQuery.ajax({
            type : 'POST',
            url: '../rcJournalRule/checkDataBeforeSaveAdd.do',
			data: {businessType:businessTypeValue,productType:productTypeValue,tradeType:tradeTypeValue,subjectId1:subjectId1Value},
             dataType: 'json',
            success: function(data) {
				if (data.state == 'ok') {
					 form.submit();
				} else {
					alert(data.msg);
				}
            },
			error: function(data, status, e){  
	            alert(e); 
	         }
        });
	}