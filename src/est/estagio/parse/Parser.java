package est.estagio.parse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import est.estagio.models.Player;
import est.estagio.models.Game;
public class Parser {


    public static void start() {

        try {
            List<Game> games = new ArrayList<>();
            File info = new File("C:\\Users\\LP\\IdeaProjects\\quakeParser\\src\\est\\estagio\\games.log");
            Scanner file = new Scanner(info);
            int contGames=1;
            int contPlayers =0;

            while(file.hasNext()) {
                String buffer = file.nextLine();
                //Se encontrar InitGame: Criar um novo jogo e adicionar a lista de jogos
                if(buffer.contains("InitGame")) {
                    Game game = new Game();
                    game = game.startGame(contGames);
                    games.add(game);
                }
                //Se encontrar Shutdown: Incrementar o contador de jogos e zerar a qtd de players
                else if (buffer.contains("ShutdownGame")) {
                    contGames=contGames+1;
                    contPlayers = 0;
                }

                /*Se encontrar ClientUserInfoChanged: Tratar a informaçao, Criar/Alterar o jogador
                 e adicionar na lista de players */
                else if(buffer.contains("ClientUserinfoChanged")) {
                    //Tratamento da linha para encontrar o nome do jogador
                    String[] tratamento = buffer.split("n\\\\");
                    String[] tratamento2 = tratamento[1].split("\\\\t");
                    String playerName = tratamento2[0];
                    //Fim do tratamento

                    Player newPlayer = new Player(playerName);
                    Game playingGame = games.get(contGames-1);
                    if(playingGame.getPlayers().isEmpty()){
                        playingGame.getPlayers().add(newPlayer);
                    } else {
                        int contador =0;
                        boolean add = true;
                        while(contador<playingGame.getPlayers().size()) {
                            if(playingGame.getPlayers().get(contador).getName().equals(playerName)){
                                add = false;
                            }
                            contador++;
                        }
                        if(add) {
                            playingGame.getPlayers().add(newPlayer);
                        }

                    }

                }

            }
            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found / Arquivo nao encontrado.");
        }


    }
}
