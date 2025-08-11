package system_design.lld.chess;

public class Move {
    private Cell from;
    private Cell to;
    private Player player;

    public Move(Cell from, Cell to) {
        this.from = from;
        this.to = to;
    }

    public Cell getFrom() { return from; }

    public Cell getTo() { return to; }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
