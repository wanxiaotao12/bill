   //新建员工
	function toAddUser(){
		window.location.href="/privilege/toAddUser.do";
	}
    
	//点击部门，查询部门下的员工
	function toSearchUser(otype,depId){
		if(otype == 3){
			depId=$("#search_erpNo").val();
		}
		var url="/privilege/userManagerRight.do?otype=" + otype + "&depId=" + depId;	
		$("#right").attr("src",url);
	}

	/*全局zNodes*/
	var zNodes;
	var log, className = {edit:"dark", remove:"dark", rename:"dark", down:"dark", up:"dark", right:"dark"};
	
	/*ztree设置*/
	var setting = {
		view: {
			dblClickExpand: dblClickExpand,
			selectedMulti: false
		},
//		edit: {
//			enable: false,
//			editNameSelectAll: true,
//		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: zTreeOnClick
		}
	};

	/*初始化部门*/
	$(document).ready(function(){
		  jQuery.ajax({
	            type: "POST",
	            url:"/privilege/showDeptTreeUI.do",
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
		  var url="/privilege/userManagerRight.do";
		  $("#right").attr("src",url);
	});
	
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
	
		function dblClickExpand(treeId, treeNode) {
			return !(treeNode.level==0);
		}

		function zTreeOnClick(event, treeId, treeNode) {
			var url="/privilege/userManagerRight.do?otype=" + "1"+ "&depId=" + treeNode.id;
			$("#right").attr("src",url);
		}
		function onMouseDown(event, treeId, treeNode) {
			showLog("[ "+getTime()+" onMouseDown ]&nbsp;&nbsp;" + (treeNode?treeNode.name:"root"), "down" );
		}
		function beforeMouseUp(treeId, treeNode) {
			className.up = (className.up === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeMouseUp ]&nbsp;&nbsp;" + (treeNode?treeNode.name:"root"), "up" );
			return (!treeNode || treeNode.up != false);
		}
		function onMouseUp(event, treeId, treeNode) {
			showLog("[ "+getTime()+" onMouseUp ]&nbsp;&nbsp;" + (treeNode?treeNode.name:"root"), "up" );
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
