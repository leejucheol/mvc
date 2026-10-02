<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 클라이언트가 전송한 색상 파라미터 받기
	String color = request.getParameter("color");
	if(color==null)color=""; // 최초에는 전송된 파라미터가 없으므로, 디폴트값을 강제로 부여
	
	//out.print("The color user send: " + color);
	
	String msg=null;
	
	switch(color){
	case "red": msg="Let just do it"; break;
	case "blue": msg="Sick"; break;
	case "black": msg="Quietly"; break;
	case "white": msg="Peace"; break;
	default: msg="You have not choose yet now"; 
	}
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
			action:"/color/main.jsp"
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
				<select class="form-control" name="color">
					<option>Chose the color you want</option>
					<option value="red"<%if(color.equals("red")){%> selected<%}%>>red</option>
					<option value="blue"<%if(color.equals("blue")){%> selected<%}%>>blue</option>
					<option value="black"<%if(color.equals("black")){%> selected<%}%>>black</option>
					<option value="white"<%if(color.equals("white")){%> selected<%}%>>white</option>
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