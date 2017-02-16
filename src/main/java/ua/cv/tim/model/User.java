package ua.cv.tim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vyach on 28.12.2016.
 */
@NamedQueries({
		@NamedQuery(
				name = "GET_USER_BY_LOGIN",
				query = "from User u where u.login = :login"
		),
		@NamedQuery(
				name = "GET_USER_BY_MAIL_AND_UUID",
				query = " FROM User u WHERE u.email=:mail and u.uuid != :uuid"
		),
		@NamedQuery(
				name = "GET_USER_BY_MAIL",
				query = " FROM User u WHERE u.email=:mail"
		),
		@NamedQuery(
				name = "GET_USER_BY_ALLIANCENAME",
				query = "select u from User u where u.player.alliance.name = :name"
		),
		@NamedQuery(
				name = "GET_USER_BY_ID",
				query = "select u FROM User u WHERE u.id=:id"
		),
		@NamedQuery(
				name = "GET_USER_BY_LOGIN_AND_UUID",
				query = "select u from User u where u.login = :login and u.uuid != :uuid"
		),
		@NamedQuery(
				name = "GET_ALL_FROM_USER",
				query = "FROM User"
		)
})
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
				.append("roles: ").append(roles);

		if (player != null) {
			sb
					.append(", race: ").append(player.getRace()).append(", ")
					.append(", alliance: ").append((player.getAlliance() != null) ? player.getAlliance() : null).append(", ")
					.append(", villages: ").append((player.getVillages() != null) ? player.getVillages() : null).append(" }");
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		User user = (User) o;

		if (login != null ? !login.equals(user.login) : user.login != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		if (roles != null ? !roles.equals(user.roles) : user.roles != null) return false;
		return player != null ? player.equals(user.player) : user.player == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (roles != null ? roles.hashCode() : 0);
		result = 31 * result + (player != null ? player.hashCode() : 0);
		return result;
	}
}