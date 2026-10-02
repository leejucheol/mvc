package com.hexagon.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * 2. 요청을 분석 (url를 통해 분석한다 즉 클라언트가 원하는게 무엇인지 판단하는 단서)
 * 3. 일시킴(모델 영역에_)
 * 4. 결과페이지로 가져갈 것이 있을때만 결과 저장(request  예)
 * 5. 결과 보여주기 (jsp에서)
 * */
@WebServlet("/")
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
		// 2딘계 - 요청을 분석한다
		
		String uri = request.getRequestURI();
		System.out.println("요청받음"+uri);
		
		/*
		 * 문제점) 엔터프라이즈급 개발에서는 요청의 수가 상당히 많아, 만일 모든 요청마다 1:1 대응하는 if 문으로 처리한다면
		 * 		유지보수성이 오히려 떨어짐
		 * 
		 * 해결 과제) 1. if문을 이 소스에서 아예 제거해야함 - Command Pattern (각 요청을 객체로 캡슐화하여 처리하는 개발 패턴) - GOF가 명명함
		 * 			2. 요청의 수가 늘어나더라도, 두번다시는 이 클래스를 열고 편집하지 않는다.(클래스 코드 열지 않고 외부의 설정 파일(xml, propeties)로 요청을 관리한다)
		 * */
		
		
		if(uri.equals("/color")) { // 색상에 대한 판단 요청
			ColorController controller = new ColorController();
			controller.execute(request, response);
			
			// 컩트롤러 업무 5단계
			// request에 데이터를 담아놓았으므로, sendRedirect하면 안되고,
			// 요청을 유지해야 하는 forward를 써야함
			RequestDispatcher dis = request.getRequestDispatcher("/color/main.jsp");
			dis.forward(request, response); // forwarding 실행
		} else if(uri.equals("/blood")){ // 혈액형에 대한 판단 요청..
			BloodController bcontroller = new BloodController();
			bcontroller.execute(request, response);
			
			RequestDispatcher dis = request.getRequestDispatcher("/blood/main.jsp");
			dis.forward(request, response); // fowarding 실행
		}
		
	}
}
