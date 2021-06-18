package est.estagio.models;

public class Player {
    private String name;
    private int kills;

    public Player(String name) {
        this.name = name;
        this.kills = 0;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getKills() {

        return kills;
    }

    public void setKills(int kills) {

        this.kills = kills;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
