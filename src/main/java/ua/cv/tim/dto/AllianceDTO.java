package ua.cv.tim.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Admin on 03.01.17.
 */

public class AllianceDTO  {

    private String allianceUuid;

    private String leaderUuid;



    @NotEmpty
    @Length(min = 3, max = 10)
    private String name;

    @NotEmpty
    @Length(min = 3, max = 10)
    private String leaderLogin;

    @Email
    private String leaderEmail;

    public AllianceDTO() {
    }

    public AllianceDTO(String name, String leaderLogin, String leaderEmail) {
        this.name = name;
        this.leaderLogin = leaderLogin;
        this.leaderEmail = leaderEmail;
    }

    public AllianceDTO(String allianceUuid, String leaderUuid, String name, String leaderLogin, String leaderEmail) {
        this(name, leaderLogin, leaderEmail);
        this.allianceUuid = allianceUuid;
        this.leaderUuid = leaderUuid;
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

    public String getAllianceUuid() {
        return allianceUuid;
    }

    public void setAllianceUuid(String allianceUuid) {
        this.allianceUuid = allianceUuid;
    }

    public String getLeaderUuid() {
        return leaderUuid;
    }

    public void setLeaderUuid(String leaderUuid) {
        this.leaderUuid = leaderUuid;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("AllianceDTO{allianceUuid='")
                .append(allianceUuid)
                .append("', leaderUuid='")
                .append(leaderUuid)
                .append("', name='")
                .append(name)
                .append("', leaderLogin='")
                .append(leaderLogin)
                .append("', leaderEmail='")
                .append(leaderEmail)
                .append("'}")
                .toString();
       }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllianceDTO that = (AllianceDTO) o;

        if (allianceUuid != null ? !allianceUuid.equals(that.allianceUuid) : that.allianceUuid != null) return false;
        if (leaderUuid != null ? !leaderUuid.equals(that.leaderUuid) : that.leaderUuid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (leaderLogin != null ? !leaderLogin.equals(that.leaderLogin) : that.leaderLogin != null) return false;
        return leaderEmail != null ? leaderEmail.equals(that.leaderEmail) : that.leaderEmail == null;
    }

    @Override
    public int hashCode() {
        int result = allianceUuid != null ? allianceUuid.hashCode() : 0;
        result = 31 * result + (leaderUuid != null ? leaderUuid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (leaderLogin != null ? leaderLogin.hashCode() : 0);
        result = 31 * result + (leaderEmail != null ? leaderEmail.hashCode() : 0);
        return result;
    }
}
