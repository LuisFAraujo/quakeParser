package est.estagio.models;
import est.estagio.models.Player;
import java.util.List;
import java.util.ArrayList;
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

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Game() {
        this.name = "";
        this.totalKills = 0;
        this.players = null;
    }
    //Iniciar o jogo Nome: game_X
    public Game startGame(int num) {
        this.setName("game_"+num);
        this.setPlayers(new ArrayList<>());
        return this;
    }
}