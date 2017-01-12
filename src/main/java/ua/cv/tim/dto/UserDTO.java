package ua.cv.tim.dto;

import java.util.List;

import javax.persistence.Column;

import org.hibernate.validator.constraints.NotEmpty;

import ua.cv.tim.model.Role;

public class UserDTO {
	
	public UserDTO() {	}
	
	public UserDTO(String email, String login, String password) {	
		this.login=login;
		this.password=password;
		this.email=email;		
	}
	
	public UserDTO(String login, String password, String email, Role role) {	
		this.login=login;
		this.password=password;
		this.email=email;
		this.role=role;
	}

	 @NotEmpty
	private String login;

	 @NotEmpty
	private String password;

	 @NotEmpty
	private String email;
	 
	private Role role;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}	
}
