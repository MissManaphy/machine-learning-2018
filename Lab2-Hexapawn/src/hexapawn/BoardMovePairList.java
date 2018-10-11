/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexapawn;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author sophiaanderson
 */
public class BoardMovePairList extends ArrayList<BoardMovePair> implements Serializable{
    
    @Override
    public String toString() {
        String returnMe = "\nMoveList:\n";

        for (BoardMovePair nextSet : this) {
            returnMe += "\t" + nextSet.toString() + "\n";
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