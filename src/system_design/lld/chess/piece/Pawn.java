package system_design.lld.chess.piece;

import system_design.lld.chess.Cell;
import system_design.lld.chess.Color;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public List<int[]> getPossibleMoves(Cell from) {
        if (from.getPiece() == null || !(from.getPiece() instanceof Pawn)) return List.of();

        Piece piece = from.getPiece();
        boolean isWhite = piece.getColor() == Color.WHITE;
        List<int[]> moves = new ArrayList<>();
        int dir = isWhite ? 1 : -1; // White moves up (1), Black moves down (-1)

        int i = from.getRow(), j = from.getCol();
        // Forward 1 step
        if (isValid(i + dir, j)) moves.add(new int[]{i + dir, j});

        // Forward 2 steps (from starting row)
        if (isWhite && i == 2 && isValid(i + 2 * dir, j))
            moves.add(new int[]{i + 2 * dir, j});
        if (!isWhite && i == 6 && isValid(i + 2 * dir, j))
            moves.add(new int[]{i + 2 * dir, j});

        // Diagonal captures
        if (isValid(i + dir, j - 1)) moves.add(new int[]{i + dir, j - 1});
        if (isValid(i + dir, j + 1)) moves.add(new int[]{i + dir, j + 1});

        return moves;
    }
}
