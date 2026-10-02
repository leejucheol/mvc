package com.hexagon.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexagon.mvc.model.BloodManager;

public class BloodController {
	BloodManager manager = new BloodManager();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 3단계 - 일시키기
		String blood = request.getParameter("bloodType");
		String msg = manager.getAdvice(blood);
		
		// 4단계 - 결과저장
		request.setAttribute("msg", msg); 
	}
	
}
