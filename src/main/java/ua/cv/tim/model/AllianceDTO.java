package ua.cv.tim.model;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Admin on 03.01.17.
 */
public class AllianceDTO {

    private long id;

    @NotEmpty
    private String name;

    private String leaderLogin;

    private String leaderEmail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeaderLogin() {
        return leaderLogin;
    }

    public void setLeaderLogin(String leaderLogin) {
        this.leaderLogin = leaderLogin;
    }

    public String getLeaderEmail() {
        return leaderEmail;
    }

    public void setLeaderEmail(String leaderEmail) {
        this.leaderEmail = leaderEmail;
    }
}
