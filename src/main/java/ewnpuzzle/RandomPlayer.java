package ewnpuzzle;
import java.util.*;

public class RandomPlayer extends Player {

    private Random rand = new Random();

    public RandomPlayer() {
        super("Random Player");
    }

    //@Override
    public int choosePiece(int dice, GameState state) {
        // Step 1: Convert dice to actual moveable piece
        int piece = state.getPieceFromDice(dice);
        if (piece == -1) return -1;

        // Step 2: Get all available moves
        List<Integer> moves = state.getAvailableMoves(piece);
        if (moves.isEmpty()) {
            return -1; // no moves possible
        }

        // Step 3: Randomly pick a move square
        return moves.get(rand.nextInt(moves.size()));
    }
}
