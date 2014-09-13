// JavaScript Document
function tabChange(){
	$("table tr:odd td").not(".adMore").css("background-color","#fff");
	$("table tr:even td").not(".adMore").css("background-color","#fafafa");
	/*鼠标移入移除*/
	var bg="";
	var otr=$("table tr td").not(".adMore");
	otr.hover(function(){
		bg=$(this).css("background-color");
		$(this).parent().find("td").css("background-color","#f3f3f3");
		},function(){
			$(this).parent().find("td").css("background-color",bg);
			});
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
/*显示层*/
var tcStr="";
var tcContent=$('<div></div>');
var tcTitle="";
var dlList="";
var content1='<dl><dt>账号：</dt><dd><input type="text" value="" class="input140"  /><em class="red" style="display:none"> *用户名错误 </em></dd></dl>'+
				'<dl><dt>时间：</dt><dd><input type="text" value="" class="calendar" id="datepicker2"/><em class="red" style="display:none"> *密码错误 </em></dd></dl>'+
				'<dl><dt>类型：</dt><dd>'+
						'<select class="select160">'+
							'<option>111</option>'+
							'<option>222</option>'+
							'<option>333</option>'+
						'</select>'+
					'</dd>'+
				 '</dl>'+
				 '<dl><dt>内容：</dt><dd>'+
						'<textarea name="" cols="" rows="" class="textArea1" ></textarea>'+
				 '</dd></dl>';
var content2='<dl><dt>账号：</dt><dd>小百度</dd></dl>'+
				'<dl><dt>时间：</dt><dd>22222</dd></dl>'+
				'<dl><dt>类型：</dt><dd>2013/04/05</dd></dl>'+
				'<dl><dt>内容：</dt><dd>许多许多许许多许多许多许多许多许多许多许多许多许多许多许多许多许多许多许多许多许多许多许多许多许多许多许多多'+
				'</dd></dl>';				 
function showMore(num,content){
	showMask();
	if(num==1){
		tcStr='<div class="tc_title">'+
					'<h3>编辑</h3>'+
					'<a href="javascript:;" onclick="closeMore()"></a>'+
				'</div>'+
				'<div class="tc_cont edit_cont">'+content+
				 '</div>'+
				 '<div class="tc_dl_footer">'+
					'<p><input type="button" class="button_Green" value="保 存" id="sumitBtn" onclick=""/><input type="button" class="button_Grey" value="关 闭" /></p>'+
				 '</div>';
				tcContent.addClass("tc tc_edit").css("display","block");
				tcContent.html(tcStr);
				$(document.body).append(tcContent);	
			/*追加弹出层内容*/
				var submitBtn=$("#sumitBtn");
				$(submitBtn).click(function(){
					$(".tc").find("em").css("display","inline-block");
					})
			}
	else if(num==2){
		tcStr='<div class="tc_title">'+
				'<h3>查看</h3>'+
				'<a href="javascript:;" onclick="closeMore()"></a>'+
				'</div>'+
					 '<div class="tc_cont edit_cont">'+content+
					 '</div>'+
					 '<div class="tc_dl_footer">'+
						'<p><input type="button" class="button_Grey" value="关 闭" onclick="closeMore()"></a></p>'+
					 '</div>';
					tcContent.addClass("tc tc_check").css("display","block");
					tcContent.html(tcStr);
					$(document.body).append(tcContent);
		}
	else if(num==3){
		tcStr='<div class="tc_title">'+
					'<h3>提示信息</h3>'+
					'<a href="javascript:;" onclick="closeMore()"></a>'+
				 '</div>'+
				 '<div class="tc_cont del_cont">'+
					'<p class="tcenter">是否要删除该账户？</p>'+
				 '</div>'+
				 '<div class="tc_dl_footer">'+
					'<p class="tcenter"><input type="button" class="button_Green" value="确 定"/><input type="button" class="button_Grey" value="取 消" /></p>'+
				 '</div>';
				tcContent.addClass("tc tc_del").css("display","block");
				tcContent.html(tcStr);
				$(document.body).append(tcContent);
				
		}
	else if(num==4)
		{
			$(".au_add").css("display","block");
		}			
	else if(num==5)
	{
		$(".small").css("display","block");
	}	
	}
function closeMore(){
	closeMask();
	$(tcContent).attr("class","").empty().remove();
	$(".tc").css("display","none");
}	

/*权限——新建角色 重命名及删除*/
var deMentLi=$(".department").find("li");
$(deMentLi).hover(function(){
		$(this).addClass("on");
	},function(){
		$(this).removeClass("on");
	})
$(deMentLi).find("em").hover(function(){
	$(this).next("div").css("display","block");
	},function(){
	$(this).next("div").css("display","none");	
})
$(deMentLi).find("div").hover(function(){
	$(this).css("display","block");
	},function(){
	$(this).css("display","none");
	})	
/* * */

/*入驻申请账户切换功能*/
function changeAccount(){
	var oCk1=$("#ck1");
	var oBtn1=$("#btn1");
	$("#ck1_div").css("display","none");
	$("#nock1_div").css("display","none");
	$(oCk1).click(function(){
		if(($(this).attr("checked"))=="checked"){
			$("#ck1_div").css("display","block");
			$("#nock1_div").css("display","none");
			$(oBtn1).attr("disabled","disabled").addClass("button_Grey").css("cursor","default");
			}
		else{
			$("#ck1_div").css("display","none");
			$("#nock1_div").css("display","none");
			$(oBtn1).removeAttr("disabled").removeClass("button_Grey").css("cursor","pointer");
			}	
		})
	$(oBtn1).click(function(){
		$("#ck1_div").css("display","none");
		$("#nock1_div").css("display","block");
		})	
}

/*增加结算设置选择条件改变切换结果*/
/*增加收费方式效果*/
jQuery.extend({
	costAnalysis:function(){
		var index;
		var cost_Analysis_label=$(".costAnalysis dd > label");
		$(cost_Analysis_label).click(function(){
			index=$(this).index();
			$(this).parents(".costAnalysis").nextAll().css("display","none");
			$(".count_method"+(index)).css("display","block");
		})
	}
})
$.costAnalysis();
/*结算方式*/	 
jQuery.extend({
	close_acc:function(){
		var close_acc_sec=$("#close_acc select");
		$(close_acc_sec).change(function(){
			var checkValue=$(close_acc_sec).val();
			if(checkValue=="实时结算"){
				$("#close_acc_cycle,#close_acc_time").css("display","none");
				}
			else{
				$("#close_acc_cycle,#close_acc_time").css("display","block");
				}	 
			})	
	}
})
$.close_acc();
		