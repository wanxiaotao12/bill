function contract_tab(id){
	for(var i = 1; i < 9; i++){
		if(id == i){
			$('#tab' + id).attr('class','font14 up lengyun up');
			$('#div' + i).attr('style','display:block;');
		}else{
			$('#tab' + i).attr('class','lengyun');
			$('#div' + i).attr('style','display:none;');
			
		}
	}

}

//级联联动省、市、区
//id  表单id
//code省市代码
function getCommenCity(id,code,value){
	if(code!=""){
		jQuery.ajax({
			dataType : "json",
			url : "/merchant/getCommenCityJson.do",
			type : "post",
			data:{"code":code},
			cache:false,
			success : function(data, textStatus) {
				var options="<option value='' selected='selected' >请选择</option>";
				 jQuery.each(data,function(d){
					if(data[d].id==value){
						options += "<option value='"+data[d].id+"' selected='selected' >"+data[d].idDesc+"</option>";
					}else{
						options += "<option value='"+data[d].id+"' >"+data[d].idDesc+"</option>";
					}
				});
				$("#"+id).html(options);
			},
			error:function(x,e,c){
				alert("查询失败！");
			} 

		});
	}
}
