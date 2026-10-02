package com.hexagon.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 대형 애플리케이션에서의 MVC에서는 클라이언트의 요청을 각각의 컨트롤러가 직접 받지 않는다
 * 왜냐면 코드 중복이 발생하고, 예외 처리의 통일성이 없어지는 등 여러가지 유지보수성 문제가 발생
 * 이 클래스가 모든 요청을 다 받아야하므로 서블릿으로 구현함 (jsp로 서블릿이 가능은 하지만 jsp는 View에 사용하기로
 * 했으므로, 서블릿으로 구현해야함
 *  
 * [컨트롤러의 5대 업무처리 프로세스]
 * 1. 요청을 받음
 * 2. 요청을 분석
 * 3. 일시킴(모델 영역에_)
 * 4. 결과페이지로 가져갈 것이 있을때만 결과 저장(request  예)
 * 5. 결과 보여주기 (jsp에서)
 * */
public class DispatcherServlet extends HttpServlet {
	// 이 컨트롤러는 모든 요청을 다 받아야 하므로, 클라이언트의 요청이 어떤 Http Method 방식으로 들어올지 예상할 수 없음
	// 그렇다고 doGet, doPost 양쪽에 코드를 작성하면 애플리케이션이 돌아가는 가지만 코드 중복이 발생
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청받음");
	}
}
