package com.hexagon.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexagon.mvc.model.ColorManager;

// 이 컨트롤러는 Color 요청을 실질적으로 처리하는 하위 컨트롤러

/*
	웹상의 요청을 받는 컨트롤러는 DispatcherServlet이므로, 이 하위 컨트롤러가 서블릿일 필요가 없다
	컨트롤러의 5대 업무중 3단계 1,2단계는 DispatcherServlet이 담당
	
 */

public class ColorController{
	ColorManager manager = new ColorManager();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 3단계 - 알맞는 로직 객체에 일 시키기
		String color = request.getParameter("color");
		String msg = manager.getAdvice(color);

		// 4단계 - View영역에서 보여줄 결과 저장
		request.setAttribute("msg", msg);
	}
}
