package system_design.lld.chess;

import system_design.lld.chess.piece.Piece;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Game {
    private Board board;
    private Player whitePlayer, blackPlayer, currentPlayer;
    private Stack<Move> moves;
    private GameState state;

    public Game(Player... players) {
        state = GameState.INIT;
        board = new Board();
        whitePlayer = players[0];
        blackPlayer = players[1];
        currentPlayer = whitePlayer;
        moves = new Stack<>();
    }

    public void startGame() {
        state = GameState.START;

        while (state != GameState.END) {
            System.out.println("Please make your move, " + currentPlayer.getName());
            Move move = getPlayerMove(currentPlayer);
            board.movePiece(move);
            moves.push(move);

            if (checkGameStatus()) {
                state = GameState.END;
            }

            //switch turn
            currentPlayer = currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
        }

        displayResult();
    }

    private void displayResult() {
        if (currentPlayer == blackPlayer) {
            System.out.println("White Player " + whitePlayer.getName() + " wins");
        } else {
            System.out.println("Black Player " + blackPlayer.getName() + " wins");
        }
    }

    private boolean checkGameStatus() {
        // checkMate and staleMate condition from board class call
        return false;
    }

    private Move getPlayerMove(Player currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter source row: ");
        int sourceRow = scanner.nextInt();
        System.out.print("Enter source column: ");
        int sourceCol = scanner.nextInt();
        /*System.out.print("Enter destination row: ");
        int destRow = scanner.nextInt();
        System.out.print("Enter destination column: ");
        int destCol = scanner.nextInt(); */

        Piece piece = board.getPiece(sourceRow, sourceCol);
        if (piece == null) {
            System.out.print("Wrong position entered.. no piece available at the position.");
            return getPlayerMove(currentPlayer);
        }

        Cell from = board.getCell(sourceRow, sourceCol);

        List<int[]> possibleMoves = piece.getPossibleMoves(from);
        Move move = new Move(from, new Cell(possibleMoves.get(0)[0], possibleMoves.get(0)[1]));
        move.setPlayer(currentPlayer);
        return move;
    }
}
