<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
		#container{
			width:400px;
			height:200px;
			margin:0px auto 0px;
			text-align: center;
		}
		table{
			width:400px;
			height:200px;
		}
	</style>
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<form action="${pageContext.request.contextPath }/FileUploadServlet" method="post" enctype="multipart/form-data">
			<input type="file" name="filename"/>
			<input type="submit" value="提交"/>
		</form>
	</div>
</body>
</html>