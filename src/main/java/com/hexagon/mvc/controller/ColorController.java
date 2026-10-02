package com.hexagon.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hexagon.mvc.model.ColorManager;

// 이 클래스는 오직 생산과 관련한 요청을 처리하는 컨트롤러임

/*
 * 이 클래스는 오직 색상과 관련한 요청을 처리하는 컨트롤러
 * MVC와 Model2의 차이점
 * 모델2는 JavaEE 기술로 구현한 MVC패턴을 의미
 * 
 * M - 순수 java가 담당
 * V - jsp가 담당
 * C - 요청을 받아야 하고, 로직 객체에 일을 시킬수 있는 능력이 있는 자
 * */
@WebServlet("/color")
public class ColorController extends HttpServlet{
	ColorManager colorManager = new ColorManager();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 전송한 색상 파라미터 받기
		String color = request.getParameter("color");
		if(color==null)color=""; // 최초에는 전송된 파라미터가 없으므로, 디폴트값을 강제로 부여
		
		// 별도로 분리시켜놓은 로직인 model 객체를 이용해보자
		/*
		 기존 jsp 코드에서 컨트롤러의 역할에 해당하는 코드를 이 컨트롤럴 클래스로 옮겨오는 것까지 성공했으나
		 jsp가 디자인에 사용할 msg 변수를 Controller가 보유하고 있기 때문에 어떻게든지, msg를 jsp가 사용할 수 있도록 처리
		 
		 이 문제를 해결하려면 javaEE 애플리케이션의 데이터의 Scope(생존범위)
		 1. request 스코프 - 요청이 끝날때까지는 유지되는 scope (보통은 이거를 많이 사용함) 단순 데이터를 결과 페이지에 보여주는게 목적이면 request가 적합
		 2. session 스코프 - 로그인이 필요없는 서비스인 경우 session 객체를 사용하면 메모리 낭비
		 3. application 스코프 - 너무 오래 유지되므로, 많은 데이터가 누적될 가능성이 높다
		 */

		// 별도로 분리 시켜놓은 로직이 model 객체를 이용해보자
		String msg = colorManager.getAdvice(color);
		
		// session 에 데이터를 심으면, session을 사용할 수 있는 동안은 꺼내서 쓸수 있다
		// 1) 브라우저 프로세스를  종료하고 새롭게 들어올때는 쿠키가 없으므로 세션 접근 못함(그 이전까지는 사용가능)
		// 2) 브라우저 프로세스를 종료하지 않더라고 일정 시간동안 재요청이 없는경우 (즉 서버에서 지칭한 기간 동안 재요청이 없는 경우 사용하지 않는 것으로 간주하여 세션 무효화
		
		// 새션은 요청이 들어올때마다 무조건 만들어지는게 아님, 메모리 효율상 개발자가 session을 건드리는 코드를 만나면 이때 생성
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg); // session 은 map을 구현했으므로, key-value쌍으로 관리됨, 닫는 브레이스를 만나서 변수만 죽지 데이터는 살아있음
		
		// tomcat서버에 배포된 웹애플리케이션이 가동되는 시점에 생성되는 ServletContext(jsp의 application 내장 객체)에도 session과 동일하게 데이터를
		// 심을 수 있음. ServletContext 객체는 tomcat서버를 끝날때가지 생명력이 유지되는 scope를 가지고 있다
		/*
		 * javaEE api (자료형)	jsp내장객체
		 * HttpServletRequest	request
		 * HttpServletResponse	response
		 * PrintWriter			out
		 * HttpSession			session
		 * ServletContext		application
		 * */
//		ServletContext context = request.getServletContext(); // 웹사이트와 생명을 함께하는 객체인 ServletContext 얻기
//		context.setAttribute(msg, msg);

		request.setAttribute("msg", msg);
		
		// 결과 페이지인 view 보여주기
		// 아래의 메서드의 매개변수값으로 브라우저가 재접속할 url 을 적으면됨
		//response.sendRedirect("/color/main.jsp"); // js의 location.href=와 완전 동일
		
		// 우리가 선택한 저장소는 request이므로, 이 요청에 대한 응답을 절대로 해서는 안되며 main.jsp로 포위당해야 request가 죽지 않고 유지됨
		RequestDispatcher dis = request.getRequestDispatcher("/color/main.jsp");
		dis.forward(request, response); // forwarding 시, 새로운 request, response를 만드는게 아니라, 현재 요청에 사용된 request, response가 그대로 살아서 전달됨
		
		
	}
}
