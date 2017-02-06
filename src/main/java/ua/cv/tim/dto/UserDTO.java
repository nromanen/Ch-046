package ua.cv.tim.dto;

/**
 * Created by vyach on 15.01.2017.
 */
public class UserDTO {

	private String uuid;
	private String login;
	private String email;
	private String alliance;
	private boolean isLeader;

	public UserDTO() {
	}

	public UserDTO(String uuid, String login, String email, String alliance, boolean isLeader) {
		this.uuid = uuid;
		this.login = login;
		this.email = email;
		this.alliance = alliance;
		this.isLeader = isLeader;
	}

	public UserDTO(String uuid, String login, String email, String alliance) {
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

	public boolean getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb
				.append("UserDTO: { uuid: ").append(uuid).append(", ")
				.append("login: ").append(login).append(", ")
				.append("email: ").append(email).append(", ")
				.append("isLeader: ").append(isLeader).append(", ")
				.append("alliance: ").append(alliance).append(" } ");
		return sb.toString();
	}
}
