/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexapawn;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author sophiaanderson
 */
public class Learner implements Serializable {

    static BoardMovePair lastMove;
    static Board lastBoard;
    static BoardMovePairList memory = new BoardMovePairList();

    static void trim(MoveList list, Board theBoard) {
        MoveList toRemove = new MoveList();
        BoardMovePair testMove;
        for (Move move : list) {
            testMove = new BoardMovePair(theBoard, move);
            if (memory.contains(testMove)) {
                toRemove.add(move); //finds all the ones to remove
            }
        }
        for (Move move : toRemove) { //actually removes them
            list.remove(move);
        }//done like this to avoid concurrent modification exception

    }

    private static void setMemory(BoardMovePairList memory) {
        Learner.memory = memory;
    }

    private BoardMovePairList getMemory() {
        return memory;
    }

    static void weAreDoomed_so_dontDoThatAgain() {
        memory.add(lastMove);
        System.out.println("I'm learning");
        System.out.println(memory.size());
    }

    BoardMovePair getLastMoveChosen() {
        return this.lastMove;
    }

    static void setLastMoveChosen(Board theBoard, Move get) {
        lastMove = new BoardMovePair(theBoard, get);
    }

    @Override
    public String toString() {
        String returnMe = "I can't make these moves" + memory.toString();
        return returnMe;
    }

    public static void main(String[] args) {
        int[][] board1 = new int[][]{{1, 0, 1}, {0, 1, 0}, {-1, -1, -1}};
        Board test = new Board(board1);
        //creates a board where the human has already played 
        MoveList list = test.generateAllLegalMoves();
        //generates all legal bot moves for that board
        BoardMovePairList bmpList = new BoardMovePairList();
        for (Move nextMove : list) {
            bmpList.add(new BoardMovePair(test, nextMove));
        }
        System.out.println("bmpList = " + bmpList);

        System.out.println("\n\nNow to test if we can use contains");
        Board anotherB = new Board(board1);
        Move m = new Move(2, 0, 1, 0);
        BoardMovePair badMove = new BoardMovePair(anotherB, m);
        if (bmpList.contains(badMove)) {
            System.out.println("found it!");
        } else {
            System.out.println("fuuuuuuuuk");
        }
    }

    public static void save() {
        try {
            String file = "brain";
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(memory);
            out.close();
        } catch (Exception e) {
            System.out.println("oops " + e);
        }

    }

    public static void load() {

        try {
            String file = "brain";
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            setMemory((BoardMovePairList) (in.readObject()));
            in.close();
        } catch (Exception e) {
            System.out.println("oops " + e);
        }

    }
}
