$(function(){
	$("#f1").hide();
	$("#f2").hide();
	var loginName = getCookie("loginName");
	if(loginName!=null)
	{
		//如果有的话就隐藏第二个，否则隐藏第一个
		$("#f1").show();
		$("#loginName").val(loginName);
		$("#auto").val("auto");
		$("#f1 input[type='checkbox']").attr("checked","checked");
		$("#f1 button").click();
	}else
	{
		$("#f2").show();
	}
});
//获取cookie
function getCookie(objName)
{
	if (document.cookie.length>0)
	{
		var arrStr = document.cookie.split("; ");
		for(var i = 0;i < arrStr.length;i ++)
		{
	        var temp = arrStr[i].split("=");
	        if(temp[0] == objName) 
	        return unescape(temp[1]);
	   } 
	} 
}