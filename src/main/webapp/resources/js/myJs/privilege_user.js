	//新增用户
	 function addUser(optType){
			var form = $("#myform");
			$("#optType").val(optType);
			form.submit();
	    }
   //编辑员工
	function edit(userId){
		window.parent.location.href = "/privilege/editUser.do?userId="+userId;
	}
	
	//启用或停用
	function modifyState(state,userId,userName){
		if(confirm("确定更改：" + userName + " 的状态吗？")){
			jQuery.ajax({
				type : "POST",
				url : "/privilege/modifyState.do",
				data : {userId : userId,state : state},
				success : function(data){
					if(data == "success"){
						alert("更改成功！");
						window.location.reload();
					}
					else{
						alert("系统繁忙，请稍后再试！");
					}
				}
			});
		}
	}
	
	//验证员工的erp是否已经被占用
    function valiaddUser(optType){
        if($("#userId").val() == ""){
             	alert("ERP账号不能为空");
             	return false;
        }
        if($("#userName").val() == ""){
            	alert("操作员不能为空");
            	return false;
        }
        if($("#departName").val() == ""){
        	alert("部门不能为空");
        	return false;
    	}
        jQuery.ajax({
        	type : "POST",
        	url : "/privilege/valiaddUser.do",
        	dataType : "text",
        	async : true,
        	data : {userId:$("#userId").val()},
        	success : function(data){
        		if(data == "success"){
        			addUser(optType);
        		}
        		else{
        			alert("ERP账号已经被占用！");
        		}
        	}
        });
    }
	
	//取消
    function cancel(){
       window.location.href="/privilege/userManager.do";
    }
	
	//删除员工
	function del(userId,userName){
		if(confirm("确定删除：" + userName + "？")){
			jQuery.ajax({
				type : "POST",
				url : "/privilege/deleteUser.do",
				data : {userId : userId},
				success : function(data){
					if(data == "success"){
						alert("删除成功！");
						window.location.reload();
					}
					else{
						alert("系统繁忙，请稍后再试！");
					}
				}
			});
		}
	}
	
	
	var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onClick: onClick
		}
	};
	
	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		if (!check) alert("只能选择叶子部门...");
		return check;
	}
	
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		v = "";
		vID="";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			vID += nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (vID.length > 0 ) vID = vID.substring(0, vID.length-1);
		var cityObj = $("#departName");
		cityObj.attr("value", v);
		var cityIDObj = $("#departId");
		cityIDObj.attr("value", vID);
	}

	function showMenu() {
		var cityObj = $("#departName");
		var cityOffset = $("#departName").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}

	$(document).ready(function(){
		jQuery.ajax({
            type: "POST",
            url:"/privilege/showDeptTreeUI.do",
            success: function(zNodes){
                if(zNodes==null || zNodes==""){
                    $("#treeDemo").html("<b> 未初始化部门结构</b>");
                } else {
                	jQuery.fn.zTree.init($("#treeDemo"), setting, eval(zNodes));
                }
            }
        });
	});
	