package ua.cv.tim.dto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ua.cv.tim.model.UuidEntity;


/**
 * Created by Admin on 03.01.17.
 */
public class AllianceDTO extends UuidEntity {

    @NotEmpty
    private String name;

    @NotEmpty
    private String leaderLogin;

    @NotEmpty
    private String leaderEmail;

    public AllianceDTO(String name, String leaderLogin, String leaderEmail) {
        prePersist();
        this.name = name;
        this.leaderLogin = leaderLogin;
        this.leaderEmail = leaderEmail;
    }

    public AllianceDTO() {
       prePersist();
    }

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
