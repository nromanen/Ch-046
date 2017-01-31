package ua.cv.tim.dto;

import ua.cv.tim.model.Role;

/**
 * Created by vyach on 15.01.2017.
 */
public class UserDTO {

	private String uuid;
	private String login;
	private String email;
	private String allianceName;
	private Role role;

	public UserDTO() {
	}

	public UserDTO(String uuid, String login, String email, String alliance, Role role) {
		this.uuid = uuid;
		this.login = login;
		this.email = email;
		this.allianceName = alliance;
		this.role = role;
	}

	public UserDTO(String uuid, String login, String email, String alliance) {
		this.uuid = uuid;
		this.login = login;
		this.email = email;
		this.allianceName = alliance;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAllianceName() {
		return allianceName;
	}

	public void setAllianceName(String allianceName) {
		this.allianceName = allianceName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb
				.append("UserDTO: { uuid: ").append(uuid).append(", ")
				.append("login: ").append(login).append(", ")
				.append("email: ").append(email).append(", ")
				.append("role: ").append(role).append(", ")
				.append("allianceName: ").append(allianceName).append(" } ");
		return sb.toString();
	}
}
