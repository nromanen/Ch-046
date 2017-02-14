package ua.cv.tim.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rmochetc on 29.12.2016.
 */

@Entity

public class Alliance extends UuidEntity implements Serializable {


    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "alliance", orphanRemoval = true)
    private List<Player> players;

    public Alliance() {

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
        final StringBuilder sb = new StringBuilder("Alliance{");
        sb.append("uuid='").append(getUuid()).append('\'' );
        sb.append("name='").append(name).append('\'' );
//        sb.append(", players=").append(players);
        sb.append('}' );
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (players != null ? players.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alliance)) return false;
        if (!super.equals(o)) return false;

        Alliance alliance = (Alliance) o;

        if (name != null ? !name.equals(alliance.name) : alliance.name != null) return false;
        return players != null ? players.equals(alliance.players) : alliance.players == null;
    }
}