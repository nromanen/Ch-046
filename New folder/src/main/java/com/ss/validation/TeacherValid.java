package com.ss.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeacherValid {
private String error;
private String namePattern = "[A-Z]{1}[a-z]{2,20}(-[A-Z]{1}[a-z]{2,14})?";

public String getNamePattern() {
	return namePattern;
}

public void setNamePattern(String namePattern) {
	this.namePattern = namePattern;
}

public String getError() {
	return error;
}

public void setError(String error) {
	this.error = error;
}

public boolean isNameEmpty(String firstname){
	if(firstname.isEmpty()){
		 error = "field can not be empty";
		 return false;}   
	
	else return true;
	}

public boolean nameMatches(String name, String namePattern) {
	
	Pattern p = Pattern.compile(namePattern);
	Matcher m = p.matcher(name);
	return m.matches();
}

}