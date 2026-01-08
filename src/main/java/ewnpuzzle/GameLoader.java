/*
 * Name: ANG KAI XIN
 * Matric No: 23113456
 * File: GameLoader.java
 * Description: Loads game data from level files and prints ASCII board display.
 */

package ewnpuzzle;
import java.io.*;
import java.util.*;

public class GameLoader {

    private static BufferedReader getReader(String resource) throws Exception {
    InputStream is = GameLoader.class.getResourceAsStream(resource);
    if (is == null) {
        throw new FileNotFoundException(
            "Resource not found: " + resource +
            " (Make sure it is in src/main/resources)"
        );
    }
    return new BufferedReader(new InputStreamReader(is));
}

    public static int loadTargetPiece(String resource) {
        try (BufferedReader br = getReader(resource)) {
            return Integer.parseInt(br.readLine().trim());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int[] loadInitialPositions(String resource) {
        int[] positions = new int[6];
        try (BufferedReader br = getReader(resource)) {
            br.readLine();
            String[] parts = br.readLine().trim().split("\\s+");
            for (int i = 0; i < 6; i++) {
                positions[i] = Integer.parseInt(parts[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return positions;
    }

    public static int[] loadDiceSequence(String resource) {
        try (BufferedReader br = getReader(resource)) {
            br.readLine();
            br.readLine();
            String[] parts = br.readLine().trim().split("\\s+");
            int[] dice = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                dice[i] = Integer.parseInt(parts[i]);
            }
            return dice;
        } catch (Exception e) {
            e.printStackTrace();
            return new int[0];
        }
    }

    public static void writeOutput(
            String filename,
            String playerName,
            int[] dice,
            int target,
            int[] start,
            List<int[]> history) {

        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {

            out.println(playerName);

            for (int d : dice) out.print(d + " ");
            out.println();

            out.println(target);

            for (int p : start) out.print(p + " ");
            out.println();

            for (int[] state : history) {
                for (int p : state) out.print(p + " ");
                out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
