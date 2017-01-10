package ua.cv.tim.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rmochetc on 29.12.2016.
 */

@Entity
public class Alliance  extends UuidEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "alliance")
    private List<Player> players;

    public Alliance() {
        prePersist();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Alliance{" +
                "name='" + name + '\'' +
                ", players=" + players +
                '}';
    }
}
