/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexapawn;

import java.util.ArrayList;

/**
 *
 * @author levenick
 */
public class Monte extends AI_butReallyJustRandom {

    int iterations = 100;
    AI_butReallyJustRandom randomBot = new AI_butReallyJustRandom();
    private boolean timeToMove;
    //private boolean justWent;
    AI_butReallyJustRandom enemy;
    
    Board theBoard;
    BoardPanel thePanel;

    public Monte() {
    }
    
    Monte(Board b, BoardPanel bp) {
        theBoard = b;
        thePanel = bp;
    }

    @Override
    public void run() {
        for (;;) {
            delay();

            if (timeToMove) {
                timeToMove = false; 
                //justWent = true;
                //System.out.println("It's monte's time to move!");
                Move theMove = analyze(theBoard);

                //Move theMove = selectRandomMoveAndLearn(theBoard);
                theBoard.playMove(theMove);
                //System.out.println(theMove);
                thePanel.repaint();
                if (theBoard.gameOver()) {
                    theBoard.announceWinner();
                    thePanel.reset();
                }
                else
                    enemy.yourTurn();
                
            }
        }
    }

    private void delay() {
        try {
            Thread.sleep(1);
        } catch (Exception ex) {
        }
    }
    
    @Override
    void yourTurn() {
        timeToMove = true;
        //justWent = false;
    }

    //needs to clone the board
    //needs to play moves on both sides of the board
    //maybe create a random bot to play moves for both sides
    private Move analyze(Board theBoard) {
        MoveList list = theBoard.generateAllLegalMoves();
        Move bestMove;
        Board tempBoard;
        ArrayList winlist = new ArrayList();
        int count;
        for (Move move : list) {
            count = 0;
            for (int i = 0; i < iterations; i++) {
                tempBoard = theBoard.clone();
                tempBoard.playMove(move);
                while (!tempBoard.gameOver()) {
                    tempBoard.playMove(randomBot.selectRandomMove(tempBoard));
                }
                if (!tempBoard.winner()) //if monte (theoretically black in this case) wins
                {
                    count++;
                }
            }
            winlist.add(count);
            //System.out.println("");
            //record num wins with this move 
        }
        bestMove = chooseBest(winlist, list);
        return bestMove; //get best move vs the rando player
    }

    private Move chooseBest(ArrayList winlist, MoveList list) {
        int best = 0;
        for (int i = 0; i < winlist.size(); i++)
        {
            if ((int)winlist.get(i) > best)
            {
                best = (int)winlist.get(i);
            }
        }
        
        int loc = winlist.indexOf(best);
        if(loc != -1) //if there's actually a best case and it hasn't gotten into a losing position
        {return list.get(loc); } //return the best move
        return selectMove(theBoard); //otherwise just move randomly
        }   

    void setOpponent(AI_butReallyJustRandom theAI) {
        enemy = theAI;
    }
}   


/*
    for each legal move, nextMove
    iterate 100 times
        tempBoard = clone the board
        play nextMove on tempBoard
        while !tempBoard.gameOver()
            play a random move (?!)
        if I won
            count++
    record number of wins with this move
    play the move with the highest number of wins
            
 */

