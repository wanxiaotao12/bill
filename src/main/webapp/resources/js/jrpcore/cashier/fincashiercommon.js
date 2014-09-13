/**
 * 查询下拉时间
 * 
 * @author yx_jd
 */
$(document).ready(function() {

	$(".calendar").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "yyyy-MM-dd"
	});
});

/**
 * 提交
 * 
 * @author yx_jd
 */
$(document).ready(function() {

	$("#clearButton").bind("click", function() {
		$("#acct").val("");
		$("#state").val("");
		$("#openDtStart").val("");
		$("#openDtEnd").val("");
		$("#offBalanInd").val("");
		$("#balanceFlag").val("");
		$("#odInd").val("");
		$("#acct").val("");

	});

	// 提交
	$(".button_Green.searchBtn").bind("click", function() {

//		var t = $("#searchForm").val();
//		$("#formSubmit").attr("action", t);
//		$("#formSubmit").attr("method", "post");
		$("#formSubmit").submit();

	});
	// 导出
	$("#downloadExcel").bind("click", function() {
		
		if (confirm("请确认下载Execl文件。")==true) {
			var t = $("#downloadExeclFile").val();
			var params = $("#formSubmit").serialize(); 
			window.open(t+'?'+params);
		}
	});

	// 打印
	$("#printPdf").bind("click", function() {
		var t = $("#downloadPdfFile").val();
		$("#formSubmit").attr("action", t);
		$("#formSubmit").attr("method", "get");
		$("#formSubmit").submit();
	});
});