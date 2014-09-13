function ajaxFileUpload(agmtId) {
	alert(agmtId);
	jQuery.ajaxFileUpload({
		url: '/contract/upload.do?agmtId='+ agmtId,
        secureuri: false,
        fileElementId: 'myfile',
        dataType: 'json',
        data:{"date":new Date()},
        success: function (data, status) {
        	alert(data);
	        if(data.msg == 'ok' && data.imgUrl != 'undefined'){
	            var url =  data.imgUrl;
	            alert("上传成功！");
	        }
	        else{
	        	alert("上传失败！");
	        }
        },
	    error: function (data, status, e) {
	        alert(e);
	    }
	})
	return false;
}

function goback(){
	window.location.reload("/contract/getContractIndex.do");
}