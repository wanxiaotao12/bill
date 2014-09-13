	/*全局zNodes*/
	var zNodes;
	var log, className = {edit:"dark", remove:"dark", rename:"dark", down:"dark", up:"dark", right:"dark"};
	
	/*ztree设置*/
	var setting = {
		view: {
			dblClickExpand: dblClickExpand,
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
//			onClick: onClick,
			beforeDrag: beforeDrag,
			beforeEditName: beforeEditName,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onRename: onRename,
			beforeMouseDown: beforeMouseDown,
			beforeMouseUp: beforeMouseUp,
			beforeRightClick: beforeRightClick,
			onMouseDown: onMouseDown,
			onMouseUp: onMouseUp,
			onRightClick: onRightClick
		}
	};

	$(document).ready(init());
	/*初始化部门*/
	function init(){
		  jQuery.ajax({
	            type: "POST",
	            url:"/privilege/showDeptTree.do",
	            success: function(zNodes){
	                if(zNodes==null || zNodes==""){
	                    $("#dept_set_cont").html("<b> 未初始化部门结构</b>");
	                } else {
	                	jQuery.fn.zTree.init($("#dept_set_cont"), setting, eval(zNodes));
	                	$("#selectAll").bind("click", selectAll);
	                	changeIcon();
	                }
	            }
	        });
	}
	
	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("dept_set_cont");
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
	}
	function changeIcon() {
		var zTree = $.fn.zTree.getZTreeObj("dept_set_cont");
		var treeNode = zTree.getNodesByFilter(filter, true); // true仅查找一个节点
		treeNode.iconSkin="diy";
		zTree.updateNode(treeNode);
	}
	function filter(treeNode) {
	    return (treeNode.level == 0);
	}

	function beforeDrag(treeId, treeNodes) {
		return !(treeNode.level==0);
	}
	function dblClickExpand(treeId, treeNode) {
		return true;
	}
	function showRemoveBtn(treeId, treeNode) {
		return !(treeNode.isParent||(treeNode.level==0));
	}
	function showRenameBtn(treeId, treeNode) {
		return !(treeNode.level==0);
	}
	function beforeMouseDown(treeId, treeNode) {
		className.down = (className.down === "dark" ? "":"dark");
		return (!treeNode || treeNode.down != false);
	}
	function onMouseDown(event, treeId, treeNode) {
	}
	function beforeMouseUp(treeId, treeNode) {
		className.up = (className.up === "dark" ? "":"dark");
		return (!treeNode || treeNode.up != false);
	}
	function onMouseUp(event, treeId, treeNode) {
	}
	function beforeRightClick(treeId, treeNode) {
		className.right = (className.right === "dark" ? "":"dark");
		return (!treeNode || treeNode.right != false);
	}
	function onRightClick(event, treeId, treeNode) {
	}
	function onClick(event, treeId, treeNode) {
		if(!treeNode.isParent){
			alert(treeNode.tId + ", " + treeNode.id);
		}
	};

	/*编辑节点*/
	function beforeEditName(treeId, treeNode) {
			className.edit = (className.edit === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeEditName ][ treeNode.id ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.id);
			var zTree = $.fn.zTree.getZTreeObj("dept_set_cont");
			zTree.selectNode(treeNode);
			return true;
	}
	/*before删除节点*/
	function beforeRemove(treeId, treeNode) {
			className.remove = (className.remove === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeRemove ][ treeNode.id ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
			var zTree = $.fn.zTree.getZTreeObj("dept_set_cont");
			zTree.selectNode(treeNode);
			var flag = true;
			jQuery.ajax({
				async : false,
	            type: "POST",
	            url:"/privilege/validatedeptUser.do",
	            data:{'deptId':treeNode.id},
	            success: function(data){
	                if(data == "failed"){
	                	alert("要删除的部门在使用中！");
	                	flag = false;
	                }
	            }
	        });
			return flag?confirm("确认删除部门 -- " + treeNode.name + " ？"):false;
	}
	/*删除节点*/
	function onRemove(e, treeId, treeNode) {
			showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
			jQuery.ajax({
	            type: "POST",
	            data : {'deptId':treeNode.id},
	            url:"/privilege/delDepartment.do",
	            success: function(data){
	                if(data=="success"){
	                	init();
	                } else {
	                	alert("删除部门失败！");
	                	init();
	                }
	            }
	        });
		}
	/*before重命名节点*/
	function beforeRename(treeId, treeNode, newName, isCancel) {
			className.rename = (className.rename === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeRename ][ treeNode.id ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.id);
			if (newName.length == 0) {
				alert("部门名称不能为空.");
				var zTree = $.fn.zTree.getZTreeObj("dept_set_cont");
				setTimeout(function(){zTree.editName(treeNode)}, 10);
				return false;
			}
			return true;
	}
	/*重命名节点*/
	function onRename(e, treeId, treeNode, isCancel) {
			showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ][ treeNode.name ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
			saveDepartment(treeNode);
	}
	/*添加节点*/
	function addHoverDom(treeId, treeNode) {
		showLog("[ "+getTime()+" addHoverDom ][ treeNode.id ]&nbsp;&nbsp;&nbsp;&nbsp; "+treeNode.id);
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj("dept_set_cont");
			var newNode = {id:'', pId:treeNode.id, name:"新部门名称"};
			zTree.addNodes(treeNode, newNode);
			
			saveDepartment(newNode);
			return false;
		});
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	/*保存新建和重命名操作*/
	function saveDepartment(treeNode) {
		var deptNam,deptId,parDepId;
		deptNam = treeNode.name?treeNode.name:"";
		deptId = treeNode.id?treeNode.id:"";
		parDepId = treeNode.pId? treeNode.pId:"";
		jQuery.ajax({
            type: "POST",
            url:"/privilege/saveDepartment.do",
            data:{'deptNam':deptNam, 'deptId':deptId, 'parDepId':parDepId},
            success: function(data){
                if(data == "success"){
                	init();
                } else {
                	alert("操作失败！");
                }
            }
        });
	}	
	
	
	
	
	
		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
			if(log.children("li").length > 8) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}
