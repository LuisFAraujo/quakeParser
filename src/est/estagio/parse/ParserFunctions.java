package est.estagio.parse;

import est.estagio.models.Game;
import est.estagio.models.Player;

public class ParserFunctions {
    //função para achar um player dentro de um jogo dado o id do player
    private Player findPlayer(String id, Game g) {
        int idPlayer = Integer.parseInt(id);
        int contador = 0;
        while(contador<g.getPlayers().size()) {
            if(g.getPlayers().get(contador).getId() ==(idPlayer)){
                Player p = g.getPlayers().get(contador);
                return p;
            }
            contador++;
        }
        return null;
    }
    // função para limpar a string deixando apenas o nome do player
    private String clearName(String buffer){
        //Tratamento da linha para encontrar o nome do jogador
        String[] tratamento = buffer.split("n\\\\");

        String[] tratamento2 = tratamento[1].split("\\\\t");
        String playerName = tratamento2[0];
        return playerName;
    }
    //função para limpar a string deixando apenas o id do player e transformar a String em int
    private int clearId(String buffer) {
        String[] tratamento = buffer.split("n\\\\");
        String[] tratamento2 = tratamento[0].split(": ");
        int idPlayer = Integer.parseInt(tratamento2[1].replace(" ",""));
        return idPlayer;
    }
    //função para tratar a string retornando um array com o id dos players q matou/morreu
    private String[] clearKill(String buffer) {
        String[] tratamento = buffer.split(":");
        String[] kills = tratamento[2].split(" ");
        return kills;
    }
    //função para encontrar um player de um jogo pelo nome
    private boolean buscar(Game playingGame, String playerName) {
        int contador =0;
        while(contador<playingGame.getPlayers().size()) {
            if(playingGame.getPlayers().get(contador).getName().equals(playerName)){
                return false;
            }
            contador++;
        }
        return true;
    }

    public Player findPlayerById(String id, Game g) {
        return findPlayer(id,g);
    }
    public String treatName(String buffer) {
        return clearName(buffer);
    }
    public int treatId(String buffer) {
        return clearId(buffer);
    }
    public String[] treatKill(String buffer) {
        return clearKill(buffer);
    }
    public boolean buscarPlayer(Game playingGame, String playerName) {
        return buscar(playingGame, playerName);
    }
}
