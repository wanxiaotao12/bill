    function addCur(roleId){
    	$("li.role").removeClass('cur');
    	$("#"+roleId).addClass('cur');
    }
    
	//添加角色
    function toAddRole() {
        var divContent = "<div class='addDepartment'><input type='text' id='roleId' name='roleNam' value='新角色名称' class='input140' onblur='valiadRoleName()'/></div>";
        $("#div").append(divContent);
		$("#roleId").focus();
    }
    
    function valiadRoleName(){
    	if($("#roleId").val() == ""){
    		window.location.reload();
    	}else{
    		jQuery.ajax({
    	        	type : "POST",
    	        	url : "/privilege/findRoleByRoleName.do",
    	        	dataType : "text",
    	        	async : true,
    	        	data : {'roleName':$("#roleId").val().trim()},
    	        	success : function(data){
    	        		if(data == "success"){
    	        			addRole();
    	        		}
    	        		else{
    	        			alert("当前角色名称已被占用！");
    	        		}
    	        	}
    	        });
    	}
       
       }
    
    function addRole() {
    	if(confirm("确定添加角色: "+$("#roleId").val()+" ?")) {
            var form = $("#myform");
            form.attr("action","/privilege/addRole.do");
            form.submit();
        } else {
            $("#roleId").html("");
            window.location.reload();
        }
    }
	
    //重命名角色
    function toModifyRoleNam(roleId,roleName) {
		var divContent = "<div class='addDepartment' style='margin-left: -20px;'><input type='text' id='TMP_ID_"+roleId+"' name='TMP_NAME_"+roleId+"' value='" + roleName + "' class='input140' style='width: 110px;' onblur='modifyRoleNam(\""+roleId+"\",this.value)'/></div>";
        $("#ROLE_"+roleId).text('');
		$("#ROLE_"+roleId).html(divContent);
		$("#TMP_ID_"+roleId).focus();
	}
    function modifyRoleNam(roleId,roleName) {
		$("#ROLE_"+roleId).html('');
		$("#ROLE_"+roleId).text(roleName);
 
		jQuery.ajax({
            type: "POST",
			data : {'roleId':roleId,'roleName':roleName},
            url: "/privilege/modifyRoleName.do",
            success: function(msg){
                if(msg=='N'){
                    alert("重命名角色失败!");
                }
                window.location.reload();
            }
        });
    }
    
    function valiadRole(roleId, roleNam){
    jQuery.ajax({
    	type : "POST",
    	url : "/privilege/findUsersByRoleId.do",
    	dataType : "text",
    	async : true,
    	data : {'roleId':roleId},
    	success : function(data){
    		data = eval("("+data+")");
    		
    		if(data.result == "success"){
    			delRole(roleId, roleNam);
    		}
    		else{
    			alert("当前角色被如下用户使用："+data.users);
    		}
    	}
    });
    }
    //删除角色
	 function delRole(roleId, roleNam){
            if(confirm("确定删除此角色: "+ roleNam +"?")) {
			 jQuery.ajax({
                type: "POST",
				data:{"roleId":roleId},
                url: "/privilege/delRole.do",
                success: function(msg){
                    if(msg=='N'){
                        alert("删除角色失败!");
						window.location.reload();
                    }
                }
            });
			$("#ROLE_"+roleId).remove();
        }
    }

	 //当前角色编号
	var curRoleId;
	
	//根据角色查询权限
    function showAuthByRole(roleId) {
    	addCur(roleId);
        curRoleId = roleId;
        jQuery.ajax({
            type: "POST",
            url:"/privilege/findAuthByRole.do?roleId="+roleId,
            success: function(zNodes){
                if(zNodes==null || zNodes==""){
                    $("#au_set_cont").html("<b> 没有分配权限</b>");
                } else {
                    jQuery.fn.zTree.init($("#au_set_cont"), setting2, eval(zNodes));
                }
            }
        });
    }
	
    //初始化权限
	function showAuth() {
        if(curRoleId!=null && curRoleId!=""){
            jQuery.ajax({
                type: "POST",
                url:"/privilege/initAuth.do?roleId="+curRoleId ,
                success: function(zNodes){
                    jQuery.fn.zTree.init($("#au_chage"), setting, eval(zNodes));
                    showMore(4);
                }
            });
        } else {
            alert("请选择一个角色!");
        }
    }
	
	//保存权限设置
    function saveAuth() {
        var zTree = $.fn.zTree.getZTreeObj("au_chage");
        var checkCount = zTree.getCheckedNodes(true);
        var checkedNodes = "";
        for(var i=0;i<checkCount.length;i++){
            checkedNodes+=checkCount[i].id+",";
        }
        if(checkedNodes!=""){
            jQuery.ajax({
                type: "POST",
                url:"/privilege/editRoleAuth.do?checkedNodes="+checkedNodes+"&roleId="+curRoleId ,
                success: function(data){
                    closeMore(4);
                    showAuthByRole(curRoleId);
                }
            });
       	}
    }
	
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
	
    var setting2 = {
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    