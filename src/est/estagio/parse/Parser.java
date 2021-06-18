package est.estagio.parse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import est.estagio.models.Game;
public class Parser {


    public void start() {

        try {
            List<Game> jogos = new ArrayList<>();
            File info = new File("game.log");
            Scanner file = new Scanner(info);
            int contGames=1;
            int contPlayers =0;

            while(file.hasNext()) {
                String buffer = file.nextLine();
                //Se encontrar InitGame: Criar um novo jogo e adicionar a lista de jogos
                if(buffer.contains("InitGame")) {
                    Game game = new Game();
                    game = game.startGame(contGames);
                    jogos.add(game);
                }
                //Se encontrar Shutdown: Incrementar o contador de jogos e zerar a qtd de players
                else if (buffer.contains("ShutdownGame")) {
                    contGames=contGames+1;
                    contPlayers = 0;

                }

            }
            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found / Arquivo nao encontrado.");
        }


    }
}
