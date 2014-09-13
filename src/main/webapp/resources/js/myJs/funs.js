// JavaScript Document
//页面效果

	//切换店铺效果
	var shop_qx=$(".shop_qx");
	$(shop_qx).hover(
		function(){
			$(this).children("div").slideDown();
		},
		function(){
			$(this).children("div").slideUp();
			})
	//补充资质 初始化默认业务资质选中
	var inps1=$("#supBox").find("input[type='radio']");
	var sups=$("#supBox .sup")
	//alert(inps1.length);
	var index1;
	$(inps1).click(function(){
		if($(this).attr("value")=="补充业务资质")
		{
			$(sups).eq(0).css("display","block").siblings(".sup").css("display","none");
			}
		else{
			$(sups).eq(0).css("display","none").siblings(".sup").css("display","block");
			}		
	})
	//设置订单信息页面
	var icons_edit=$(".icons_edit");
	$(icons_edit).click(function(){
			$(this).css("display","none")
			.siblings("input").css("display","block").focus();
		})
		
	//添加商品详情页 重选类目//	
	var doAgain=$(".doAgain");
	$(doAgain).hover(
		function(){
			$(this).siblings("img").css("display","block").siblings(".pop_tips1").css("display","block");
		},
		function(){
			$(this).siblings("img").css("display","none").siblings(".pop_tips1").css("display","none");
		});
	//基金管理页面弹出层效果
	var xgBtn=$(".xg");
	$(xgBtn).click(function(){
		showMask();
		$(".pop_jijinGL").css("display","block");
	})
	var closeBtn1=$(".close1");
	$(closeBtn1).click(function(){
		closeMask();
		$(".pop_jijinGL").css("display","none");
	})
	/*该处设计后台数据的增删改查*/
	var del1=$(".del1");
	$(del1).click(function(){
		$(this).parents("tr").remove();
		})
	//保单查询页面债务
	var zwMore=$(".zwMore");
	$(zwMore).hover(function(){
		$(this).parent("div").addClass("pr");
		},function(){
		$(this).parent("div").removeClass("pr");	
			})
	
	//添加商品 添加详情页面 选择基金经理
	var choose2=$(".choose2");
	$(choose2).click(function(){
		//alert(1);
		showMask();
		$(".pop_chooseManger").css("display","block");
		})
	$(".pop_chooseManger .pop_header a").click(function(){
		closeMask();
		$(".pop_chooseManger").css("display","none");
		})	
	//商品审核详情查看页面
	var xq=$(".xq");
	$(xq).click(function(){
		showMask();
		$(".pop_shjl").css("display","block");
		})
	$(".pop_shjl .pop_header a").click(function(){
		closeMask();
		$(".pop_shjl").css("display","none");
		})
	$(".pop_shjl .pop_footer input").click(function(){
		closeMask();
		$(".pop_shjl").css("display","none");
		})	
	//在售页面弹出店内分类
	var bj=$(".bj");
	$(bj).click(function(){
		showMask();
		$(".pop_sepKinds").css("display","block");
		})
	$(".pop_sepKinds .pop_header a").click(function(){
		closeMask();
		$(".pop_sepKinds").css("display","none");
		})
	$(".pop_sepKinds .pop_footer input").click(function(){
		closeMask();
		$(".pop_sepKinds").css("display","none");
		})
	/*店铺公告弹窗*/
	var addAd=$(".green");
	$(addAd).click(function(){
		showMask();
		$(".pop_changeInfos").css("display","block");
		})
	$(".pop_changeInfos .pop_header a").click(function(){
		closeMask();
		$(".pop_changeInfos").css("display","none");
		})	
	$(".pop_changeInfos .pop_footer input").click(function(){
		closeMask();
		$(".pop_changeInfos").css("display","none");
		})
	//我的店铺——查询进度 详情弹层
	var xq2=$(".xq2");
	$(xq2).click(function(){
		showMask();
		$(".pop_picc").css("display","block");
		})
	$(".pop_picc .pop_header a").click(function(){
		closeMask();
		$(".pop_picc").css("display","none");
		})		
	$(".pop_picc .pop_footer input").click(function(){
		closeMask();
		$(".pop_picc").css("display","none");
		})	
	/*新建员工弹出JS*/
	  var oaddAu=$(".w1 label");
	  $(oaddAu).hover(function(){
		  $(this).addClass("pr");
		  },function(){
		 $(this).removeClass("pr"); 
			  })
	/*修改权限弹层*/		  
				  					
	//添加商品 添加详情页面 选择店内分类，店内分标准分类
	var choose=$(".choose");
	var index;
	var array=[];
	$(".choose").click(function(){
		index=$(".choose").index(this);
		//alert(index+"__");
		showMask();
		$(".pop_fbfl").css("display","block");
		var ocheck=$(this).parent().siblings(".pop_content").find("input");
		array=[];
		$(".pop_fbfl").find("input[name='check']").each(function() {
            	$(this).attr("checked",false);
        	});	
		})

	//复选框内容输出	
	var closeBtn=$(".pop_header a");
	var subtn=$(".pop_fbfl .subtn");
	var cancle=$(".pop_fbfl .cancle");
	$(closeBtn).click(function(){
		$(this).parents(".pop_fbfl").css("display","none");
		closeMask();
	})
	$(cancle).click(function(){
		$(this).parents(".pop_fbfl").css("display","none");
		closeMask();
		})
	$(subtn).click(function(){
		var ocheck=$(this).parents(".pop_footer").siblings(".pop_content").find("input");
		for(var i=0;i<ocheck.length;i++)
			{
				//alert(i);
				if(ocheck[i].checked==true)
				{
					//alert(ocheck[i].checked);
					//alert(ocheck[i].name)
					array.push(ocheck[i].value);
					}
			}
		$(this).parents(".pop_fbfl").css("display","none");
		closeMask();
		//alert(index);
		if(array.length > 0)
		{
			$(choose).eq(index).css("display","none");
			var olist1=$(".list1").eq(index);
			olist1.css("display","block");
			for(var j=0;j<array.length;j++)
			{
				$(olist1).children("ul").append("<li>"+array[j]+"<a href='javascrip:;' onclick='del(this)'>删除</a></li>");		
				}
		}	
	})
	function del(obj){
		if(array.length==1)
		{
			$(choose).eq(index).css("display","block");
			}
		$(obj).parent().remove();
		array.pop();
	}

//遮罩显示隐藏
	function showMask(){
		var oScreenWidth=$(document).width();
		var oScreenHeight=$(document).height();
		var oDiv=$(".mask");
		$(oDiv).css({"width":oScreenWidth,"height":oScreenHeight,"display":"block","position":"absolute","zIndex":"9998"});
	}
	function closeMask(){
		$(".mask").css("display","none");
		}	
	function showDiv(){
		showMask();
		$(".pop_chooseManger").css("display","block");
			
		}
	function closeDiv(obj){
		closeMask();
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
	
		
//选择类目效果
var json_earth = {Names:[{id:'1',name:'China'},{id:'2',name:'America'}]};

var json_china = {Names:[{id:'1_1',name:'四川'},{id:'1_2',name:'湖北'}]};
var json_america = {Names:[{id:'2_1',name:'纽约'},{id:'2_2',name:'华盛顿'}]};

var json_china_provinceSC={Names:[{id:'1_1_1',name:'广元'},{id:'1_1_2',name:'成都'},{id:'1_1_3',name:'重庆'}]};
var json_china_provinceHC={Names:[{id:'1_2_1',name:'襄樊'},{id:'1_2_2',name:'武汉'},{id:'1_2_3',name:'黄石'}]};

//初始化函数，为第一个DIV加载数据
function init(){
	for (var i = 0; i < json_earth.Names.length; i++) {
		document.getElementById('ul_a').innerHTML += 
		"<li id=\'"+json_earth.Names[i].id+"\' onclick=getData(\'"+json_earth.Names[i].id+"\',\'"+json_earth.Names[i].name+"\')>"+json_earth.Names[i].name+"</li>"
	};
}
//点击第一个div中的li，给第二个DIV赋值
function getData(id,name){
	document.getElementById('ul_b').innerHTML = "";
	//alert(document.getElementById(name))
	//alert(id+"***"+name)
	document.getElementById('Infos1').innerHTML="";
	document.getElementById('Infos2').innerHTML="";
	document.getElementById('Infos3').innerHTML="";
	document.getElementById('Infos1').innerHTML = name;
	var child=document.getElementById(id).parentNode.childNodes;
	for(var i=0;i<child.length;i++){
		child.item(i).removeAttribute("class");
	}
	document.getElementById(id).className="cur";
	if(id=="1"){
		for(var i = 0; i<json_china.Names.length; i++){
			document.getElementById('ul_b').innerHTML += 
			"<li id=\'"+json_china.Names[i].id+"\'  onclick=getData_last(\'"+json_china.Names[i].id+"\',\'"+json_china.Names[i].name+"\')>"+json_china.Names[i].name+"</li>"
		}
	}else if(id=="2"){
		for(var i = 0; i<json_america.Names.length; i++){
			document.getElementById('ul_b').innerHTML += 
			"<li id=\'"+json_america.Names[i].id+"\'  onclick=getData_last(\'"+json_america.Names[i].id+"\',\'"+json_america.Names[i].name+"\')>"+json_america.Names[i].name+"</li>"
		}
	}
}
//点击第二个div中的li，给第三个DIV赋值
function getData_last(id,name){
	//alert(id+"**"+name)
	document.getElementById('ul_c').innerHTML = "";
	document.getElementById('Infos2').innerHTML = name;
	var child=document.getElementById(id).parentNode.childNodes;
	for(var i=0;i<child.length;i++){
		child.item(i).removeAttribute("class");
		}
	document.getElementById(id).className="cur";
	if(id=="1_1")
	{
		for (var i = 0; i < json_china_provinceSC.Names.length; i++) {
			document.getElementById('ul_c').innerHTML +=
			"<li id=\'"+json_china_provinceSC.Names[i].id+"\' onclick=getData_laster(\'"+json_china_provinceSC.Names[i].id+"\',\'"+json_china_provinceSC.Names[i].name+"\')>"+json_china_provinceSC.Names[i].name+"</li>"
		};
	}
	else if(id=="1_2"){
		for (var i = 0; i < json_china_provinceHC.Names.length; i++) {
			document.getElementById('ul_c').innerHTML +=
			"<li id=\'"+json_china_provinceHC.Names[i].id+"\' onclick=getData_laster(\'"+json_china_provinceHC.Names[i].id+"\',\'"+json_china_provinceHC.Names[i].name+"\')>"+json_china_provinceHC.Names[i].name+"</li>"
		};
	}

}
//点击第三个div中的li,将结果追加到infos3中
function getData_laster(id,name){
	//alert(id+"======="+name);
	document.getElementById("Infos3").innerHTML=name;
	var child=document.getElementById(id).parentNode.childNodes;
	for(var i=0;i<child.length;i++){
		child.item(i).removeAttribute("class");
		}
	document.getElementById(id).className="cur";
	//alert(document.getElementById(id).className)
	}
/**/
var deMentLi=$(".depar dl dd").find("li");
$(deMentLi).hover(function(){
		$(this).addClass("on");
	},function(){
		$(this).removeClass("on");
	})
/*监控聊天记录*/
var json_send= {Names:[{id:'1',name:'徐菲菲'},{id:'2',name:'李静'},{id:'3',name:'金喜善'},{id:'4',name:'李宗贤'}]};

var json_receive1 = {Names:[{id:'1_1',name:'徐菲菲1'},{id:'1_2',name:'徐菲菲2'},{id:'1_3',name:'徐菲菲3'},{id:'1_4',name:'徐菲菲4'},{id:'1_5',name:'徐菲菲5'},{id:'1_6',name:'徐菲菲6'},{id:'1_7',name:'徐菲菲7'},{id:'1_8',name:'徐菲菲8'},{id:'1_9',name:'徐菲菲9'}]};
var json_receive2 = {Names:[{id:'2_1',name:'李静'},{id:'2_2',name:'李静2'}]};
var json_receive3 = {Names:[{id:'3_1',name:'金喜善1'},{id:'3_2',name:'金喜善2'}]};
var json_receive4 = {Names:[{id:'4_1',name:'李宗贤1'},{id:'4_2',name:'李宗贤2'},{id:'4_3',name:'李宗贤3'},{id:'4_4',name:'李宗贤4'},{id:'4_5',name:'李宗贤5'},{id:'4_6',name:'李宗贤6'},{id:'4_7',name:'李宗贤7'},{id:'4_8',name:'李宗贤8'},{id:'4_9',name:'李宗贤9'},{id:'4_10',name:'李宗贤10'},{id:'4_11',name:'李宗贤11'},{id:'4_12',name:'李宗贤12'},{id:'4_13',name:'李宗贤13'},{id:'4_14',name:'李宗贤14'}]};


//初始化函数，为第一个DIV加载数据
function init1(){
	
	document.getElementById('chat_a').innerHTML="";
	document.getElementById('chat_b').innerHTML = "";
	for (var i = 0; i < json_send.Names.length; i++) {
		document.getElementById('chat_a').innerHTML += 
		"<li id=\'"+json_send.Names[i].id+"\' onclick=getData1(\'"+json_send.Names[i].id+"\',\'"+json_send.Names[i].name+"\')>"+json_send.Names[i].name+"</li>"
	};
}
//点击第一个div中的li，给第二个DIV赋值
function getData1(id,name){
	document.getElementById('chat_b').innerHTML = "";
	var child=document.getElementById(id).parentNode.childNodes;
	for(var i=0;i<child.length;i++){
		child.item(i).removeAttribute("class");
	}
	document.getElementById(id).className="cur";
	if(id=="1"){
		for(var i = 0; i<json_receive1.Names.length; i++){
			document.getElementById('chat_b').innerHTML += 
			"<li id=\'"+json_receive1.Names[i].id+"\' onclick=getData1_laster(\'"+json_receive1.Names[i].id+"\',\'"+json_receive1.Names[i].name+"\')>"+json_receive1.Names[i].name+"</li>"
		};
	}else if(id=="2"){
		for(var i = 0; i<json_receive2.Names.length; i++){
			document.getElementById('chat_b').innerHTML += 
			"<li id=\'"+json_receive2.Names[i].id+"\' onclick=getData1_laster(\'"+json_receive2.Names[i].id+"\',\'"+json_receive2.Names[i].name+"\')>"+json_receive2.Names[i].name+"</li>"
		};
	}
	else if(id=="3"){
		for(var i = 0; i<json_receive3.Names.length; i++){
			document.getElementById('chat_b').innerHTML += 
			"<li id=\'"+json_receive3.Names[i].id+"\' onclick=getData1_laster(\'"+json_receive3.Names[i].id+"\',\'"+json_receive3.Names[i].name+"\') >"+json_receive3.Names[i].name+"</li>"
		}
	}
	else if(id=="4"){
		for(var i = 0; i<json_receive4.Names.length; i++){
			document.getElementById('chat_b').innerHTML += 
			"<li id=\'"+json_receive4.Names[i].id+"\' onclick=getData1_laster(\'"+json_receive4.Names[i].id+"\',\'"+json_receive4.Names[i].name+"\')>"+json_receive4.Names[i].name+"</li>"
		}
	}
}

//点击第三个div中的li,将结果追加到infos3中
function getData1_laster(id,name){
	var child=document.getElementById(id).parentNode.childNodes;
	for(var i=0;i<child.length;i++){
		child.item(i).removeAttribute("class");
		}
	document.getElementById(id).className="cur";
	//alert(document.getElementById(id).className)
	}	