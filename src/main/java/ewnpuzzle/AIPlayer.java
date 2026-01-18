/*
 * Name: YANG CHENGLIN
 * Matric No: 24236290
 * File: AIPlayer.java
 */
package ewnpuzzle;

import java.util.*;

/*
 * AIPlayer is a concrete implementation of the Player class.
 * It represents a heuristic-based AI that evaluates all possible moves
 * and selects the move with the highest score.
 */
public class AIPlayer extends Player {

    /*
     * Constructs an AIPlayer with a predefined player name.
     * The name is passed to the superclass Player.
     */
    public AIPlayer() {
        super("AI Player");
    }

    /*
     * Chooses the best move based on the current dice roll and game state.
     * The method evaluates all legal moves and selects the one
     * with the highest heuristic score.
     *
     * @param dice  the value rolled by the dice, determining possible moves
     * @param state the current GameState containing board and piece information
     * @return a Move object representing the selected move, or null if no valid move exists
     */
    @Override
    public Move chooseMove(int dice, GameState state) {

        // Retrieve all pieces that can be moved given the dice value
        List<Integer> candidates = state.getMovablePieces(dice);
        if (candidates.isEmpty()) return null;

        int bestPiece = -1;
        int bestDest = -1;
        int bestScore = Integer.MIN_VALUE;

        // Evaluate every possible (piece, destination) combination
        for (int piece : candidates) {
            List<Integer> moves = state.getAvailableMoves(piece);
            for (int dest : moves) {

                // Compute heuristic score for the current move
                int score = evaluateMove(piece, dest, state);

                // Update the best move if a higher score is found
                if (score > bestScore) {
                    bestScore = score;
                    bestPiece = piece;
                    bestDest = dest;
                }
            }
        }

        // Return null if no valid move was selected
        if (bestPiece == -1 || bestDest == -1) return null;

        // Encapsulate the selected move using the Move object
        return new Move(bestPiece, bestDest);
    }

    /**
     * Evaluates a move using a heuristic scoring function.
     * Higher scores indicate more desirable moves.
     *
     * @param piece the piece being moved
     * @param dest  the destination position encoded as an integer
     * @param state the current GameState
     * @return an integer score representing the quality of the move
     */
    private int evaluateMove(int piece, int dest, GameState state) {

        // Decode destination into row and column
        int r = dest / 10;
        int c = dest % 10;

        // Heuristic: positions closer to (0, 0) are preferred
        int score = 100 - (r + c);

        // Bonus if the move involves the target piece
        if (piece == state.getTargetPiece()) score += 50;

        // Large bonus for reaching the winning position
        if (dest == 0) score += 1000;

        return score;
    }
}
