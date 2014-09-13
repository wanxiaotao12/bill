var number_regx=/^[0-9]*$/;

/**
 * 数字验证
 * @param inId
 */
function number_validate(inId) {
	
	try {
		var keyCode = $("#"+inId).val();
		if(keyCode.length == 0){
			return;
		}
		var flag = number_regx.test(keyCode);
		if (!flag) {
			 alert("请输入数字!");
		}
	} catch (e) {
		alert(e);
	}
	
}
