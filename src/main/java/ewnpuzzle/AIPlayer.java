package ewnpuzzle;

import java.util.*;

public class AIPlayer extends Player {

    public AIPlayer() {
        super("AI Player");
    }

    //@Override
    public int choosePiece(int dice, GameState state) {

        // Step 1: Which piece must move?
        int piece = state.getPieceFromDice(dice);
        if (piece == -1) return -1;

        // Step 2: Get all legal moves
        List<Integer> moves = state.getAvailableMoves(piece);
        if (moves.isEmpty()) return -1;

        // Step 3: Choose best move based on heuristic
        int bestMove = moves.get(0);
        int bestScore = Integer.MIN_VALUE;

        for (int move : moves) {
            int score = evaluateMove(piece, move, state);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    // ============================
    //        HEURISTIC
    // ============================
    //
    // Higher score = better move
    //
    // Heuristic idea:
    // - Prefer moves that increase row+col
    // - Strong bonus if this is the target piece
    // - Strong bonus if move reaches 77
    //

    private int evaluateMove(int piece, int move, GameState state) {

        int r = move / 10;
        int c = move % 10;

        int score = r + c; // closer to (7,7) = higher

        // Bonus if this is the target piece
        // (target piece index = targetPiece - 1)
        // We infer target by checking goal condition impact
        if (isTargetPiece(piece, state)) {
            score += 20;
        }

        // Huge bonus if reaching goal
        if (move == 77) {
            score += 1000;
        }

        return score;
    }

    // Helper to detect if piece is target piece
    private boolean isTargetPiece(int piece, GameState state) {
        // Target piece is the only one whose reaching 77 ends the game
        int[] before = state.getPositions();
        int index = piece - 1;
        return before[index] != -1;
    }
}
