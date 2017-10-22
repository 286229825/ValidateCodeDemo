<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/jquery-3.2.1.min.js"></script>
<script>
	<!-- 刷新图片 -->
	function changeImg(){
		var imgSrc=$("#imgObj");
		var src=imgSrc.attr("src");
		imgSrc.attr("src",changeUrl(src));
	}
	<!-- 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳  -->
	function changeUrl(url){
        var timestamp = (new Date()).valueOf();  
        var index = url.indexOf("?",url);  
        if (index > 0) {  
            url = url.substring(index, url.indexOf(url, "?"));  
        }  
        if ((url.indexOf("&") >= 0)) {  
            url = url + "×tamp=" + timestamp;  
        } else {  
            url = url + "?timestamp=" + timestamp;  
        }  
        return url;  
	}
</script>
</head>
<body>	
	<form action="/validateCodeDemo/checkValidateCode" method="post">
		<table>
			<tr>
				<td>验证码：</td>
				<td><input id="validateCode" name="validateCode" type="text"></td>
				<td>
					<img id="imgObj" alt="验证码" src="/validateCodeDemo/validateCode" onclick="changeImg()">
					<a href="#" onclick="changeImg()">换一张</a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="提交">
					<font color="red">${Request["message"]?default("")}   </font>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>