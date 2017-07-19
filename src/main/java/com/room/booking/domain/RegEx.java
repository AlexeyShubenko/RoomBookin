package com.room.booking.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

	public static boolean checkValidLogin(String login) {
		Pattern p = Pattern.compile("^[A-z]+$");
        Matcher m = p.matcher(login);
		if(m.matches())return true;
		else return false;	
	}
	
	public static boolean checkValidTelNumber(String number){
		String condition = "^\\+(380)\\([0-9]{2}\\)[0-9]{7}$";
		Pattern p = Pattern.compile(condition);
		Matcher m = p.matcher(number);
		if(m.matches())return true;
		else return false;
	}
	
	public static boolean checkValidHomeNumber(String number){
		if(number!=""){
			if(number.matches("^[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}$")||number.matches("^[0-9]{6}"))return true;
			else return false;
		} else return true;
	}
	
}
