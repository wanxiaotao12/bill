$(document).ready(function(){
	$("#form").validate({
        rules : {
    			entityId:"required",
				subjectId1:"required",
				debitId1:"required",
			}
        });
		
	$(function() {
        var reseivePayFlag = jQuery('#reseivePayFlag').val();
    	if (reseivePayFlag == '1') {
    		jQuery("#subject2").hide();
    	} else {
    		jQuery("#subject2").show();
			jQuery('#subjectId2').rules("add",{required:true});
			jQuery('#debitId2').rules("add",{required:true});
    	}
    })
	
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
});

	function confirmClick() {
        var subIdValue1 = $("#subjectId1").val();
		if (subIdValue1 == null || subIdValue1 == '') {
			alert('请输入科目代码');
			return ;
		}
		var subIdValue2 = $("#subjectId2").val();

        var form = $("#form");
        jQuery.ajax({
            url: "/rcJournalRule/checkDataBeforeSaveEdit.do",
            type: "POST",
			dataType: 'json',
            data: {subjectId1:subIdValue1,subjectId2:subIdValue2},
            success: function(data) {
                if(data.state =='err'){
                    alert(data.msg);
					reutrn;
                } 
                form.submit();
            },
			error: function(data, status, e){  
	                alert(e); 
	         }
        });
    }