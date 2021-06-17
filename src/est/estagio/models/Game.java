package est.estagio.models;
import est.estagio.models.Player;
import java.util.List;

public class Game {

    private String name;
    private int totalKills;
    private List<Player> players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Game(String name, int totalKills, List<Player> players) {
        this.name = name;
        this.totalKills = totalKills;
        this.players = players;
    }
}