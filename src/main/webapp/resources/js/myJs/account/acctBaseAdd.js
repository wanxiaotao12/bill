$(document).ready(function(){
	$("#form").validate({
        rules : {
        			acct:"required",
					acctTyp:"required",
					acctNam:"required",
					acOrg:"required",
					subjectId:"required",
					subjectName:"required",
					balance:"required number",
					currency:"required number",
					lastBalance:"required number",
					validBalance:"required number",
					aggr:"required number",
					offBalanInd:"required number",
					digest:"required",
					entityIdType:"required",
					entityId:"required",
					entityName:"required"
			}
        });
	$("#subjectId").autocomplete("../rcSubject/findSubTmpIdOrNam.do",
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
				// {id: "10010101", name: "现金-人民币"}
		                    $(this).val(row.id);
		                    $("#subjectName").val(row.name);
		         if($("#bussnessType").val()=="QITA"){//不是其它时
		            		$.ajax({
				       			 dataType: "text",
				       			 url: "acctCheckSubjectTable.do",
				       			 type:"GET",
				       			 data:{subject:$(this).val()},
				       			 success: function(data){
				       				 if(data=="0"){
				       					 if(confirm("您输入的科目表不存在！是否创建？")){
				       						$.ajax({
								       			 dataType: "text",
								       			 url: "createSubjectTable.do",
								       			 type:"GET",
								       			 data:{subject:row.id},
								       			 success: function(data){
								       				 if(data=="1"){
								       					 alert("创建成功！");
								       				 }
								       			 },
								       			 error: function(x,e,c){
								       				 alert("通讯异常!");
								       			 }
						            		});
				       					 }
				       				 }
				       			 },
				       			 error: function(x,e,c){
				       				 alert("通讯异常!");
				       			 }
		            		});
		            		
			 }else{//走业务方式的逻辑
				 
				 $.ajax({
	       			 dataType: "text",
	       			 url: "createSubjectTable.do",
	       			 type:"GET",
	       			 data:{subject:$("#bussnessType").val()},
	       			 success: function(data){
	       				 
	       			 },
	       			 error: function(x,e,c){
	       				 alert("通讯异常!");
	       			 }
        		});
				 
			 }
		                });
	
	
	
		
});
function checkSubjectId(){
	if($("#bussnessType").val()=="QITA"){//不是其它时
		$.ajax({
			 dataType: "text",
			 url: "acctCheckSubjectTable.do",
			 type:"GET",
			 async:false,
			 data:{subject:$("#subjectId").val()},
			 success: function(data){
				 if(data=="0"){
				 if(confirm("您输入的科目表不存在！是否创建？")){
						$.ajax({
			       			 dataType: "text",
			       			 url: "createSubjectTable.do",
			       			 type:"GET",
			       			 async:false,
			       			 data:{subject:$("#subjectId").val()},
			       			 success: function(data){
			       				 if(data=="1"){
			       					 alert("创建成功！");
			       				 }
			       				 return true;
			       			 },
			       			 error: function(x,e,c){
			       				 alert("通讯异常!");
			       			 }
	            		});
					 }else{
   						 alert();
   						return false;
   					 }
				 }
						 return false;
			 },
			 error: function(x,e,c){
				 alert("通讯异常!");
			 }
		 });
		
	}
		return false;
	
}