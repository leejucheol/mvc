package com.hexagon.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexagon.mvc.model.BloodManager;

@WebServlet("/blood")
public class BloodController extends HttpServlet{
	BloodManager bloodManager = new BloodManager();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * JavaEE에서의 Controller 의 5대 역할
		 * 1. 요청을 받는다 (파라미터 받기 등)
		 * 2. 요청을 분석한다 (클라이언트가 무엇을 원하는지 분석, 그리고 적절한 모델 객체를 선택)
		 * 		우리의 경우 BloodController가 이미 Blood관련된 것만 처리하기 때문에 여기서의 분석은 필요없다
		 * 3. 일 시킴 (컨트롤러는 로직을 수행하는 영역이 아니기 때문에 언제나 모델 영역에 일을 시킨다)
		 * 4. 결과 페이지에서 보여줄 것 이 있다면 결과를 저장(request(o), session(x), application(x)
		 * 5. 결과 페이지를 보여준다
		 * */
		
		// 클라이언트가 전송한 색상 파라미터 받기
		String bloodType = request.getParameter("bloodType");
		String type = request.getParameter("type");
		
		if(bloodType==null)bloodType=""; // 최초에는 전송된 파라미터가 없으므로, 디폴트값을 강제로 부여
		if (type == null) { // '=' 이 아니라 '=='
			type = "RH+";   // 기본값
		}
		//out.print("The color user send: " + color);
		
		String msg = bloodManager.getAdvice(bloodType); // 3단계 - 일시키기
		
//		HttpSession session = request.getSession();
//		session.setAttribute("msg", msg);
		
		request.setAttribute("msg", msg); // 4단계 - 결과 정상
		
		// 1) 요청을 끊고 새로 접속하여 보여주기
		// 2) 요청을 유지 Forwarding(포워딩) 하여 보여주기
		RequestDispatcher dis = request.getRequestDispatcher("/blood/main.jsp");
		dis.forward(request, response);
	}
}
