package ua.cv.tim.dto;

/**
 * Created by Micro on 2/13/2017.
 */
public class CredentialsDTO {
    private String login;
    private String password;

    public CredentialsDTO() {
    }

    public CredentialsDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

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
}
