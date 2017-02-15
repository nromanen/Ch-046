package ua.cv.tim.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by vyach on 27.01.2017.
 */
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

	private String uuid;
	private String email;
	private List<Role> roles;
	private Race race;
	private List<Village> villages;
	private Alliance alliance;


	public AuthorizedUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public AuthorizedUser setRoles(List<Role> roles) {
		this.roles = roles;
		return this;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public List<Village> getVillages() {
		return villages;
	}

	public void setVillages(List<Village> villages) {
		this.villages = villages;
	}

	public Alliance getAlliance() {
		return alliance;
	}

	public void setAlliance(Alliance alliance) {
		this.alliance = alliance;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb
				.append("Authorized user: { uuid: ").append(uuid).append(", ")
				.append("login: ").append(getUsername()).append(", ")
				.append("email: ").append(email).append(", ")
				.append("roles: [ ").append(roles).append(" ], ")
				.append("race: ").append(race).append(", ")
				.append("villages: [ ").append((villages != null) ? villages : null).append(" ] , ")
				.append("alliance : ").append((alliance != null) ? alliance : null).append(", ");
		return sb.toString();
	}
}
