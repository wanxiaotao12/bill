$(document).ready(function(){
		$("#form").validate({
            rules : {
        			acctId:"required",
    				cashFlag:"required",
    				bookCateg:"required",
					balanceFlag:"required",
					amountIndex:"required",
					comingFunc:"required"
    			}
        });
});