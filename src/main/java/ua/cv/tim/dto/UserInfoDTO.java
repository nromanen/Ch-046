package ua.cv.tim.dto;

/**
 * Created by vyach on 15.01.2017.
 */
public class UserInfoDTO {

	private String uuid;
	private String login;
	private String email;
	private String alliance;

	public UserInfoDTO() {}

	public UserInfoDTO(String uuid, String login, String email, String alliance) {
		this.uuid = uuid;
		this.login = login;
		this.email = email;
		this.alliance = alliance;
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

	public String getAlliance() {
		return alliance;
	}

	public void setAlliance(String alliance) {
		this.alliance = alliance;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb
				.append("UserInfoDTO: { login: ").append(login).append(", ")
				.append("email: ").append(email).append(", ")
				.append("alliance: ").append(alliance).append(" } ");
		return sb.toString();
	}
}
