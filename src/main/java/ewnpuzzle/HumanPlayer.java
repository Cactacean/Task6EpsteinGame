package ewnpuzzle;
import java.util.*;

public class HumanPlayer extends Player {

    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer() {
        super("Human Player");
    }

    //@Override
    public int choosePiece(int dice, GameState state) {

        // Step 1: Find which piece gets to move
        int piece = state.getPieceFromDice(dice);
        if (piece == -1) {
            System.out.println("No piece available to move!");
            return -1;
        }

        System.out.println("\nDice rolled: " + dice);
        System.out.println("Moving piece: " + piece);

        // Step 2: Get available moves
        List<Integer> moves = state.getAvailableMoves(piece);

        if (moves.isEmpty()) {
            System.out.println("This piece cannot move!");
            return -1;
        }

        // Step 3: Show options to user
        System.out.println("Available moves:");
        for (int i = 0; i < moves.size(); i++) {
            System.out.println((i + 1) + ") " + moves.get(i));
        }

        // Step 4: User chooses which square to move to
        int choice = -1;
        while (choice < 1 || choice > moves.size()) {
            System.out.print("Choose move (1-" + moves.size() + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                choice = -1; // loop again
            }
        }

        // Step 5: Return the chosen square
        return moves.get(choice - 1);
    }
}
