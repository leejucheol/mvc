<%@page import="com.hexagon.mvc.model.ColorManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 지금부터는 MVC로 철철히 나누어서 개발해야 하는 모델2로 구현해야 하지만
	// View(디자인)만을 담당해야 하는 jsp에 코드에 Controller로의 코드가 섞여 있으므로
	// 디자인을 버리고 다른 디자인 소스로 대체할 경우 , Controller가 함께 날아가 버림
	// 즉 완전히 분리시키지 못한 상태 == 모델 1 방식의 개발이라함

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function getResult() {
		$("#form1").attr({
			action:"/color",
			method:"POST" // text만 전송
		});
		
		$("#form1").submit(); //여기서 전송
	}
	
	$(function() {
		$("#bt").click(()=>{
			getResult();
		});
	});
</script>
</head>
<body>

	<div class="container">
		<form id="form1">
			<div class="form-group">
				<select class="form-control" name="color">
					<option>Chose the color you want</option>
					<option value="red">red</option>
					<option value="blue">blue</option>
					<option value="black">black</option>
					<option value="white">white</option>
				</select>
			</div>
			<button class="btn btn-info" id="bt">Result</button>
		</form>

		<h3>
			Result : <br> 
			<!-- 개발자가 서버를 중지한 적이 없다면 msg는 언제나 꺼내쓸 수 있음 -->
			<%=request.getAttribute("msg")%>
		</h3>
	</div>

</body>
</html>