package com.hexagon.mvc.model;

// 이 클래스는 웹이건, 응용이건 상관없이 모든 플랫폼에서 재사용이 가능한 모델객체
// 즉 MVC에서 model을 분리시켜놓자
public class ColorManager {
	
	public String getAdvice(String color) {
		//out.print("The color user send: " + color);
		
		String msg=null;
		
		switch(color){
		case "red": msg="Let just do it"; break;
		case "blue": msg="Sick"; break;
		case "black": msg="Quietly"; break;
		case "white": msg="Peace"; break;
		default: msg="You have not choose yet now"; 
		}
		
		return msg;
	}
}
