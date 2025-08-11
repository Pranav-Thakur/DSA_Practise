package system_design.lld.chess;

import system_design.lld.chess.piece.Piece;

public class Cell {
    private int row;
    private int col;
    private Color color;
    private Piece piece;

    public Cell(int row, int col) {
        this(row, col, null, null);
    }

    public Cell(int row, int col, Color color, Piece piece) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Color getColor() {
        return color;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
