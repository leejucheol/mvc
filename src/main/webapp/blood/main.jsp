<%@page import="com.hexagon.mvc.model.BloodManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
			action:"/blood",
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
				<select class="form-control" name="type">
					<option>Chose the blood type you have</option>
					<option value="RH+">RH+</option>
					<option value="RH-">RH-</option>
				</select>
			</div>

			<div class="form-group">
				<select class="form-control" name="bloodType">
					<option>Chose the blood type you have</option>
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="O">O</option>
					<option value="AB">AB</option>
				</select>
			</div>
			<button class="btn btn-info" id="bt">Result</button>
		</form>

		<h3>
			Result : <br> 
			<%=request.getAttribute("msg")%>
		</h3>
	</div>

</body>
</html>