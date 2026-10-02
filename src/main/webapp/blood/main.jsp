<%@page import="com.hexagon.mvc.model.BloodManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%! BloodManager bloodManager = new BloodManager(); %>
<%
	// 클라이언트가 전송한 색상 파라미터 받기
	String bloodType = request.getParameter("bloodType");
	String type = request.getParameter("type");
	if(bloodType==null)bloodType=""; // 최초에는 전송된 파라미터가 없으므로, 디폴트값을 강제로 부여
	if (type == null) { // '=' 이 아니라 '=='
		type = "RH+";   // 기본값
	}
	//out.print("The color user send: " + color);
	
	String msg = bloodManager.getAdvice(bloodType);
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
			action:"/blood/main.jsp"
			method:"POST"; // text만 전송
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
				<select class="form-control" name="type">
					<option>Chose the blood type you have</option>
					<option value="RH+"<%if(type.equals("RH+")){%> selected<%}%>>RH+</option>
					<option value="RH-"<%if(type.equals("RH-")){%> selected<%}%>>RH-</option>
				</select>
			</div>

			<div class="form-group">
				<select class="form-control" name="bloodType">
					<option>Chose the blood type you have</option>
					<option value="A"<%if(bloodType.equals("A")){%> selected<%}%>>A</option>
					<option value="B"<%if(bloodType.equals("B")){%> selected<%}%>>B</option>
					<option value="O"<%if(bloodType.equals("O")){%> selected<%}%>>O</option>
					<option value="AB"<%if(bloodType.equals("AB")){%> selected<%}%>>AB</option>
				</select>
			</div>
			<button class="btn btn-info" id="bt">Result</button>
		</form>

		<h3>
			Result : <br> 
			<%= msg%>
		</h3>
	</div>

</body>
</html>