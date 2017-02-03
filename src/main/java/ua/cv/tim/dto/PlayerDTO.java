package ua.cv.tim.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;
import ua.cv.tim.model.Alliance;
import ua.cv.tim.model.Race;
import ua.cv.tim.model.Village;

import javax.persistence.*;
import java.util.List;

/**
 * Created by okunetc on 17.01.2017.
 */
public class PlayerDTO {

    @NotEmpty
    private String login;

    @Enumerated(EnumType.STRING)
    private Race race;


    private List<Village> villages;

    @JsonIgnoreProperties("players")
    private Alliance alliance;

    public PlayerDTO() { }

    public PlayerDTO(String login, Race race, List<Village> villages, Alliance alliance) {
        this.login = login;
        this.race = race;
        this.villages = villages;
        this.alliance = alliance;
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

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "login='" + login + '\'' +
                ", race=" + race +
                ", villages=" + villages +
                ", alliance=" + alliance +
                '}';
    }
}
