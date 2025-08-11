package system_design.lld.chess;

import system_design.lld.chess.piece.*;

public class Board {
    private final Cell[][] board;

    public Board() {
        board = new Cell[8][8];
        Color color = Color.WHITE;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(i, j, color, null);

                if (color == Color.WHITE) color = Color.BLACK;
                else color = Color.WHITE;
            }
        }

        setupPieces();
    }

    private void setupPieces() {
        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Pawn(Color.WHITE));
            board[6][i].setPiece(new Pawn(Color.BLACK));
        }

        // Initialize white pieces
        board[0][0].setPiece(new Rook(Color.WHITE));
        board[0][1].setPiece(new Knight(Color.WHITE));
        board[0][2].setPiece(new Bishop(Color.WHITE));
        board[0][3].setPiece(new Queen(Color.WHITE));
        board[0][4].setPiece(new King(Color.WHITE));
        board[0][5].setPiece(new Bishop(Color.WHITE));
        board[0][6].setPiece(new Knight(Color.WHITE));
        board[0][7].setPiece(new Rook(Color.WHITE));

        // Initialize black pieces
        board[7][0].setPiece(new Rook(Color.BLACK));
        board[7][1].setPiece(new Knight(Color.BLACK));
        board[7][2].setPiece(new Bishop(Color.BLACK));
        board[7][3].setPiece(new Queen(Color.BLACK));
        board[7][4].setPiece(new King(Color.BLACK));
        board[7][5].setPiece(new Bishop(Color.BLACK));
        board[7][6].setPiece(new Knight(Color.BLACK));
        board[7][7].setPiece(new Rook(Color.BLACK));
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public Piece getPiece(int row, int col) {
        return board[row][col].getPiece();
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col].setPiece(piece);
    }

    public synchronized boolean movePiece(Move move) {
        Cell from = move.getFrom(), to = move.getTo();
        Piece piece = board[from.getRow()][from.getCol()].getPiece();
        if (piece == null) return false;

        board[from.getRow()][from.getCol()].setPiece(null);
        board[to.getRow()][to.getCol()].setPiece(piece);
        return true;
    }
}
