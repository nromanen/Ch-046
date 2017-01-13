package ua.cv.tim.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.OrderBy;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
 public class Player extends UuidEntity implements Serializable {

    @JoinColumn(name = "user_uuid")
    @OneToOne(targetEntity = User.class)
    @JsonIgnoreProperties("player")
    private User user;

    @Enumerated(EnumType.STRING)
    private Race race;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "player")
    @OrderBy("population")
    private List<Village> villages;

    @ManyToOne
    @JoinColumn(name = "alliance_id")
    private Alliance alliance;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}