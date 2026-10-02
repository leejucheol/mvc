package com.hexagon.mvc.model;

public class BloodManager {

	public String getAdvice(String bloodType /*String type*/) {
		String msg=null;
		
		switch(bloodType){
		case "A": msg="Introvert"; break;
		case "B": msg="Free Soul"; break;
		case "O": msg="So kind and scik"; break;
		case "AB": msg="Psyco"; break;
		default: msg="You have not choose yet now"; 
		}
		
//		switch(type){
//		case "Common": msg="Common"; break;
//		case "Unique": msg="Unique"; break;
//		default: msg="You have not choose yet now"; 
//		}
		
		return msg;
		
	}

}
