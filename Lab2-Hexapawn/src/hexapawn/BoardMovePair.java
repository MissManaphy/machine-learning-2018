/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexapawn;

import java.io.Serializable;

/**
 *
 * @author sophiaanderson
 */
public class BoardMovePair implements Serializable{
    
    protected Board theBoard;
    protected Move theMove;
    //---------------------------// Initalizers //----------------------------//
    public BoardMovePair(){}
    
    public BoardMovePair(Board board, Move move)
    {
        this();
        setTheBoard(board);
        setTheMove(move);
    }
    
    //-----------------------------// Setters //------------------------------//
    public void setTheBoard(Board board) {
        this.theBoard = board;
    }

    public void setTheMove(Move move) {
        this.theMove = move;
    }
    //-----------------------------// Getters //------------------------------//
    public Board getTheBoard() {
        return this.theBoard;
    }

    public Move getTheMove() {
        return this.theMove;
    }
    //----------------------------// Overrides //-----------------------------//
    @Override
    public boolean equals (Object o)
    {
        BoardMovePair bmp = (BoardMovePair) o;
        return this.getTheMove().equals(bmp.getTheMove()) && this.getTheBoard().equals(bmp.getTheBoard());
    }
    
    //------------------------------// Output //------------------------------//
    @Override
    public String toString ()
    {
        String returnMe = "";
        returnMe += "This is the Board: " + theBoard.toString() 
                + "\nand this is the move" + theMove.toString() + "\n";
        return returnMe;
    }
    
    //-----------------------------// Testing //------------------------------//
    public static void main(String[] args)
    {
        Board b = new Board();
        MoveList list = b.generateAllLegalMoves();
        BoardMovePairList bmpList = new BoardMovePairList();
        for (Move nextMove : list) {
            bmpList.add(new BoardMovePair(b, nextMove));
        }
        System.out.println("bmp = " + bmpList);
        Board anotherB = new Board();
        Move m = new Move (0,1,1,1);
        BoardMovePair bmp = new BoardMovePair(anotherB, m);
        if(bmpList.contains(bmp))
        {
            System.out.println("got it!");
        }
        else
        {
            System.out.println("Awwww fuck....");
        }
    }
    
}
