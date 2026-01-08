/*
 * Name: ANG KAI XIN
 * Matric No: 23113456
 * File: GameMain.java
 * Description: Entry point for the EWN Puzzle Simulator.
 */

package ewnpuzzle;
import java.util.*;

public class GameMain {
    
    public static void main(String[] args) {

        // ============================
        //      CONFIGURATION
        // ============================
        String inputFile = "/level1.txt";   // change if needed
        String outputFile = "moves.txt";

        // ============================
        //      LOAD GAME DATA
        // ============================
        int target = GameLoader.loadTargetPiece(inputFile);
        int[] startPositions = GameLoader.loadInitialPositions(inputFile);
        int[] dice = GameLoader.loadDiceSequence(inputFile);

        GameState state = new GameState(startPositions, target);

        // ============================
        //      SELECT PLAYER
        // ============================
        // Change player type here:
        // Player player = new HumanPlayer();
        // Player player = new RandomPlayer();
        Player player = new AIPlayer();

        // ============================
        //      GAME LOOP
        // ============================
        List<int[]> history = new ArrayList<>();

        for (int i = 0; i < dice.length; i++) {

            if (state.isGameEnded()) break;

            int diceRoll = dice[i];

            // Determine which piece moves
            int piece = state.getPieceFromDice(diceRoll);
            if (piece == -1) continue;

            // Player chooses where to move
            int moveSquare = player.choosePiece(diceRoll, state);
            if (moveSquare == -1) continue;

            // Apply move
            state.movePiece(piece, moveSquare);

            // Save board state AFTER move
            history.add(state.getPositions());
        }

        // ============================
        //      WRITE OUTPUT
        // ============================
        GameLoader.writeOutput(
                outputFile,
                player.getPlayerName(),
                dice,
                target,
                startPositions,
                history
        );

        System.out.println("Game finished.");
        System.out.println("Output written to " + outputFile);
    }
}
