package hexapawn;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author levenick Feb 6, 2016 2:18:08 PM
 */
public class MoveList extends ArrayList<Move> implements Serializable{

    public String toString() {
        String returnMe = "\nMoveList:\n";

        for (Move nextMove : this) {
            returnMe += "\t" + nextMove + "\n";
        }

        return returnMe;
    }

    public static void main(String[] args) {
        Board aBoard = new Board();
        System.out.println("aBoard = " + aBoard);
        MoveList list = aBoard.generateAllLegalMoves();

        System.out.println("list = " + list);
    }
}
