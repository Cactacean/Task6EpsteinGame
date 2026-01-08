package ewnpuzzle;

public abstract class Player {

    protected String name;

    public Player(String name) {
        this.name = name;
    }

    // Every player must decide which piece to move
    public abstract int choosePiece(int dice, GameState state);

    // Player name for output
    public String getPlayerName() {
        return name;
    }
}
