$(document).ready(function(){
	$(".button_Green.searchBtn").bind("click",function(){
		$("#formSubmit").submit();
	});
	$( ".calendar" ).datepicker({
	    changeMonth: true,
	    changeYear: true,
	    dateFormat: "yy-mm-dd"
		});
	$("#clearButton").bind("click",function(){
		$("#acct").val("");
		$("#state").val("");
		$("#openDtStart").val("");
		$("#openDtEnd").val("");
		$("#offBalanInd").val("");
		$("#balanceFlag").val("");
		$("#odInd").val("");
		$("#acct").val("");

	});
	$("#addButton").bind("click",function(){
		location.href="../acctMerchant/toAdd.do";
	});
	
	$("#daoruEsc").bind("click",function(){
		hide(".pop_daoru");
	});
	
});
function changeState(acct,state){
	if(confirm("确定销户?")){
		window.location='../acctMerchant/acctMerchantDelete.do?acct='+acct+'&state='+state;
	}
}
//遮罩显示隐藏
	function showMask(){
		 var oScreenWidth=$(document).width();
		var oScreenHeight=$(document).height();
		var oDiv=$(".mask",parent.document);
		//$(oDiv).css({"width":oScreenWidth,"height":oScreenHeight});
		//$(oDiv).css({"width":oScreenWidth,"height":oScreenHeight,"display":"block","position":"absolute","zIndex":"9998"});
	}
	function closeMask(){
		$(".mask").css("display","none");
		}
/*显示隐藏函数*/
function show(obj){
	showMask();
	$(obj).css("display","block");
	}
function hide(obj){
	closeMask();
	$(obj).css("display","none");
	}

function submitCheck(obj){
	var fileName = $($("input",$(obj))[0]).val();
	if(fileName==""){
		alert("请选择文件！");
		return false;
	}
	var v = fileName.split(".");
	if(v.length>0 && v[1]=="csv"){
		return true;
	}else{
		alert("请选择正确的CSV文件！");
		return false;
	}
}