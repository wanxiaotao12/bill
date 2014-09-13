;(function($){
	jQuery.extend({
		_slidDown:function(btnId,id){
			$(id).slideDown();
			$(btnId).html("隐藏详情");
		},
		_slidUp:function(btnId,id){
			$(id).slideUp();
			$(btnId).html("展开详情");
		}
	})
})($);

$("#moreInfo").click(function(){
	if($(this).html()=="展开详情")
	{
		$._slidDown("#moreInfo","#infoList");
	}
	else{
		$._slidUp("#moreInfo","#infoList");	
	}
})	

var loadingProgressBar;

  //关闭提示
jQuery("#a_close").click(function(){
    jQuery(".log").css("display","none");
});
	
$("#fileToUpload").click(function(){
	jQuery(".log").css("display","none");
	ajaxFileUpload($("#myfile").val());
});

function ajaxFileUpload(myfile) {
	if (myfile == null || myfile == '') {
		alert('请选择文件');
		return false;
	}

	if (loadingProgressBar == null || typeof loadingProgressBar == 'undefined' ) {
		this.loadingProgressBar = new com
                        .jd.core.widget.LoadingProgressBar('正在导入...');
	} else {
		this.loadingProgressBar.open();
	}
	
	jQuery.ajaxFileUpload({
		url: '/rcSubject/rcSubjectImp.do?myfile='+ myfile,
        secureuri: false,
        fileElementId: 'myfile',
        dataType: 'json',
        data:{"date":new Date()},
        success: function (data, status) {
			loadingProgressBar.close();
	        if(data.state == 'ok' || data.state == 'fail'){

				jQuery("#successCount").html("<p>成功导入：<em class=\"green\" >" + data.successCount + "</em>条</p>");
				jQuery("#failCount").html("<p>失败导入：<em class=\"green\" >" + data.failCount + "</em>条</p>");
				//jQuery("#detailMsg").append("<li>" + data.detailMsg + "</li>");
				jQuery("#detailMsg").val(data.detailMsg);
	            jQuery(".log").css("display","block");
	        } else{
	        	alert("上传失败！");
	        }
        },
	    error: function (data, status, e) {
			loadingProgressBar.close();
	        alert(e);
	    }
	})
	return false;
}
/*显示遮罩*/	
function showMask(){
		var oScreenWidth=$(document).width();
		var oScreenHeight=$(document).height();
		//alert(oScreenWidth+"====="+oScreenHeight);
		var oDiv=$(".mask");
		$(oDiv).css({"width":oScreenWidth,"height":oScreenHeight,"display":"block","position":"absolute"});
	}
function closeMask(){
	var oDiv=$(".mask");
	$(oDiv).css("display","none");
	}