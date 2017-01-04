package ua.cv.tim.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rmochetc on 29.12.2016.
 */

@Entity
public class Alliance {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "alliance")
    private List<Player> players;


    @JoinColumn(name = "leader")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User leader;

    public Alliance() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }


    @Override
    public String toString() {
        return "Alliance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
