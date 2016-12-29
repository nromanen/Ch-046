package com.ss.validation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ss.dao.DBConnector;

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

	public boolean isNameEmpty(String firstname) {
		if (firstname.isEmpty()) {
			error = "field can not be empty";
			return false;
		}

		else
			return true;
	}

	public boolean nameMatches(String name, String namePattern) {

		Pattern p = Pattern.compile(namePattern);
		Matcher m = p.matcher(name);
		return m.matches();
	}

	public boolean isExist(String lastname, String firstname) {
		int count = 0;
		try {
			PreparedStatement ps = DBConnector.getConnection()
					.prepareStatement("select count(*) as c from teachers  where lastname=? and firstname=?");
			ps.setString(1, lastname);
			ps.setString(2, firstname);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (count > 0) {
			return true;
		} else
			return false;

	}

}