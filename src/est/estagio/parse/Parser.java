package est.estagio.parse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import est.estagio.models.Player;
import est.estagio.models.Game;
public class Parser {


    public static void start(int op) {

        try {
            List<Game> games = new ArrayList<>();
            ParserFunctions pF = new ParserFunctions();
            File info = new File("C:\\Users\\LP\\IdeaProjects\\quakeParser\\src\\est\\estagio\\games.log");
            Scanner file = new Scanner(info);
            int contGames=1;

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
                }

                /*Se encontrar ClientUserInfoChanged: Tratar a informaçao, Criar/Alterar o jogador
                 e adicionar na lista de players */
                else if(buffer.contains("ClientUserinfoChanged")) {

                    String playerName = pF.treatName(buffer);
                    int idPlayer = pF.treatId(buffer);

                    Player newPlayer = new Player(playerName,idPlayer);
                    Game playingGame = games.get(contGames-1);
                    // se a lista de players do jogo estiver vazia, adicionar direto na lista
                    if(playingGame.getPlayers().isEmpty()){
                        playingGame.getPlayers().add(newPlayer);
                    }
                    // se a lista de players nao estiver vazia, checar se o player ja existe
                    else {

                        boolean add = pF.buscarPlayer(playingGame,playerName);
                        // se o player nao existir, adicionar na lista
                        if(add) {
                            playingGame.getPlayers().add(newPlayer);
                        }

                    }

                }
                /*Se encontrar Kill: Tratar as informaçoes de quem matou e morreu e adicionar ao respectivo player */
                else if(buffer.contains("Kill")){
                        String[] kills = pF.treatKill(buffer);
                        Game g = games.get(contGames-1);
                        // caso o jogador que matou for o <world>(1022) diminuir kill do player que morreu
                        if(kills[1].equals("1022")){
                            Player morreu = pF.findPlayerById(kills[2],g);
                            //checar se a kill do player é 0 para não ficar kill negativa
                            if(morreu.getKills()!=0) {
                                morreu.setKills(morreu.getKills()-1);
                            }

                            g.setTotalKills(g.getTotalKills()+1);
                        }
                        // se não for o <world> que matou, achar o player q matou e dar +1 kill
                        else {
                            Player matou = pF.findPlayerById(kills[1],g);
                            matou.setKills(matou.getKills()+1);
                            g.setTotalKills(g.getTotalKills()+1);
                        }

                }

            }
            file.close();
            if(op==0) {
                games.forEach((n) -> n.printGame());
            } else if(op>games.size()) {
                System.out.println("Jogo não Existe!");
            } else {
                games.get(op-1).printGame();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found / Arquivo nao encontrado.");
        }


    }
}
