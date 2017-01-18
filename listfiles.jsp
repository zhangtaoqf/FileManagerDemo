<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</head>
<body>
	<div id="container">
		<table border="1">
			<tr>
					<td>文件名</td><td>类型</td>
			</tr>
		
			<c:forEach var="data" items="${files }">
				<tr>
					<td>${data }</td><td><a href="${pageContext.request.contextPath }/DownloadServlet?filename=${data }">下载</a></td>
				</tr>
			</c:forEach>
			
			<tr>
					<td colspan="2" style="text-align: right;padding-right: 50px;"><a href="${pageContext.request.contextPath }/fileupload.jsp">文件上传</a></td>
			</tr>
		</table>
	</div>

	
</body>
</html>