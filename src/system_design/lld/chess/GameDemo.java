package system_design.lld.chess;

public class GameDemo {

    public static void main(String[] args) {
        Player player1 = new Player("Pranav", Color.WHITE);
        Player player2 = new Player("Riya", Color.BLACK);
        Game game = new Game(player1, player2);
        game.startGame();
    }
}
