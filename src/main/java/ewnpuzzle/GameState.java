package ewnpuzzle;

import java.util.*;

public class GameState {

    private int[] positions;    // index 0 = piece 1, index 5 = piece 6
    private int targetPiece;    // number 1–6
    private boolean gameEnded = false;

    public GameState(int[] startPositions, int targetPiece) {
        this.positions = startPositions.clone();
        this.targetPiece = targetPiece;
    }

    // Copy of positions (protect internal array)
    public int[] getPositions() {
        return positions.clone();
    }

    // Check if a piece is still alive
    public boolean isAlive(int piece) {
        return positions[piece - 1] != -1;
    }

    // Check if target reached 77
    public boolean hasReachedGoal() {
        return positions[targetPiece - 1] == 77;
    }

    // ============================
    //      MOVEMENT LOGIC
    // ============================
    //
    // Allowed moves based on row/column:
    // (r+1, c)     DOWN
    // (r, c+1)     RIGHT
    // (r+1, c+1)   DIAGONAL
    //
    // Can't move outside the board (rows/cols ≤ 7)
    // Can't move onto own piece (unless capturing)
    //

    public List<Integer> getAvailableMoves(int piece) {

        List<Integer> list = new ArrayList<>();

        if (!isAlive(piece)) return list;

        int pos = positions[piece - 1];
        int r = pos / 10;
        int c = pos % 10;

        // 1. Move Down (r+1, c)
        if (r + 1 <= 7) {
            list.add((r + 1) * 10 + c);
        }

        // 2. Move Right (r, c+1)
        if (c + 1 <= 7) {
            list.add(r * 10 + (c + 1));
        }

        // 3. Move Diagonal (r+1, c+1)
        if (r + 1 <= 7 && c + 1 <= 7) {
            list.add((r + 1) * 10 + (c + 1));
        }

        return list;
    }

    // Check if any friendly piece occupies a square
    private boolean occupiedByOwnPiece(int square, int currentPiece) {
        for (int i = 0; i < 6; i++) {
            if ((i + 1) == currentPiece) continue;
            if (positions[i] == square) return true;
        }
        return false;
    }

    // Move a piece to a chosen square (selected by player)
    public void movePiece(int piece, int newSquare) {

        // Capture enemy piece (in this variant: your own pieces can be captured)
        for (int i = 0; i < 6; i++) {
            if (positions[i] == newSquare) {
                positions[i] = -1;  // eliminate
            }
        }

        // Move the piece
        positions[piece - 1] = newSquare;

        // Check goal
        if (hasReachedGoal()) {
            gameEnded = true;
        }
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    // ==================================
    //      APPLY DICE LOGIC
    // ==================================
    //
    // Steps:
    // 1) If rolled piece alive → MUST move it
    // 2) If dead → choose nearest alive number
    // 3) Player (AI/Human/Random) chooses which move to use
    //

    public int getPieceFromDice(int rolled) {

        if (isAlive(rolled)) return rolled;

        // find nearest alive: 3 → check 2 and 4, then 1 and 5, etc.
        for (int offset = 1; offset < 6; offset++) {
            int low = rolled - offset;
            int high = rolled + offset;

            if (low >= 1 && isAlive(low)) return low;
            if (high <= 6 && isAlive(high)) return high;
        }

        return -1; // no pieces left (should not happen)
    }

}
