package ua.cv.tim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vyach on 28.12.2016.
 */

@Entity
@Table(name = "users")
public class User extends UuidEntity implements Serializable {

	@Column(nullable = false, unique = true)
	private String login;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "roles")
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	private List<Role> roles;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Player player;

	public User() {		}

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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb
				.append("User: { login: ").append(login).append(", ")
				.append("password: ").append(password).append(", ")
				.append("email: ").append(email).append(", ")
				.append("roles: [ ");

		for (int i = 0; i < roles.size(); i++) {
			if (i != roles.size() - 1) {
				sb.append(roles.get(i).toString()).append(", ");
			} else {
				sb.append(roles.get(i).toString());
			}
		}

		sb.append(" ]");
		return sb.toString();
	}
}
