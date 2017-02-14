package ua.cv.tim.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
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

    public Player(){

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Player: { login: ").append(user.getLogin()).append(", ")
                .append("email: ").append(user.getEmail()).append(", ")
                .append("roles: ").append(user.getRoles()).append(", ")
                .append("race: ").append(race).append(", ")
                .append("alliance: ").append((alliance != null) ? alliance : null).append(", ")
                .append("villages: ").append((villages != null) ? villages : null).append(" }");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Player player = (Player) o;

        if (user != null ? !user.equals(player.user) : player.user != null) return false;
        if (race != player.race) return false;
        if (villages != null ? !villages.equals(player.villages) : player.villages != null) return false;
        return alliance != null ? alliance.equals(player.alliance) : player.alliance == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + (villages != null ? villages.hashCode() : 0);
        result = 31 * result + (alliance != null ? alliance.hashCode() : 0);
        return result;
    }
}