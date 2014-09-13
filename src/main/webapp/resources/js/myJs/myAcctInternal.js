$(document).ready(function(){
	$("#newStaffForm").validate({
		rules : {
			acctTyp :{required:true},
			acct:"required",
			acctNam:"required",
			email : {
				email : true
			}
		},
		messages : {
			email : {
				email : "请输入正确的email地址"
			}
		},
		debug : false
	});
//	jQuery.validator.addMethod("userMobile", function(value, element) {
//		var tel = /^[0-9]{11,}$/;
//		return this.optional(element) || (tel.test(value));
//	}, "请输入11位手机号.");
//	jQuery.validator.addMethod("userId", function(value, element) {
//		var tel = /^[a-z0-9A-Z%&'',;=?$x22]{4,20}$/;
//		return this.optional(element) || (tel.test(value));
//	}, "4-20位字符，可使用字母、数字或符号的组合.");
//	jQuery.validator.addMethod("pwd", function(value, element) {
//		var tel = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
//		return this.optional(element) || (tel.test(value));
//	}, "由字母加数字至少两种以上字符组成的6-20位半角字符，区分大小写.");
});

function showAuth(){
	var roleIds = "";
	$(".auth").each(function(){
		var obj = $(this);
		if(obj.attr("checked")=="checked"){
			roleIds += "&roleIds="+obj.val();
		};
	});
	if(roleIds==""){
		$.fn.zTree.destroy("#treeDemo");
		return ;
	}
	$.ajax({
		 dataType:"json",
		 url: "getRolesAuth.do?"+roleIds,
		 type:"GET",
		 success: function(data){
			 $.fn.zTree.destroy("#treeDemo");
			 $.fn.zTree.init($("#treeDemo"), setting, data);
		 },
		 error: function(x,e,c){
			 alert("查询失败！");
		 }
	 });
	
}

var setting = {
	data: {
		simpleData: {
			enable: true,
		}
	},
	view : {
		showIcon :false
	}
};
