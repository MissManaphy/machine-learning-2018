package hexapawn;

/**
 * An "AI" with no intelligence whatsoever! Returns a random move...
 *
 * @author levenick
 */
class AI_butReallyJustRandom extends Thread {

    private boolean timeToMove;
    //private boolean justWent;
    Board theBoard;
    BoardPanel thePanel;
    Monte enemy;
    

    AI_butReallyJustRandom() {
    }

    AI_butReallyJustRandom(Board b, BoardPanel bp) {
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
                Move theMove = selectRandomMoveAndLearn(theBoard);
                //System.out.println("It's rando's time to move");
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

    public Move selectMove(Board theBoard) {
        delay();
        return selectRandomMove(theBoard);
    }

    public Move selectRandomMove(Board theBoard) {
        MoveList list = theBoard.generateAllLegalMoves();
        int r = rand(list.size());
        return list.get(r);
    }
    
    public Move selectRandomMoveAndLearn(Board theBoard) {
        MoveList list = theBoard.generateAllLegalMoves();
        Learner.trim(list, theBoard);         //<------- Second: trim losing moves
        if (list.isEmpty()) {
            Learner.weAreDoomed_so_dontDoThatAgain(); //just play random to lose
            return selectRandomMove(theBoard);
        } else {
            int r = rand(list.size());
            Learner.setLastMoveChosen(theBoard.clone(), list.get(r)); //<-------- remember our last move
            //have to clone so it doesn't accidentally save the pointer to the regular board
            return list.get(r);
        } // else
    }

    public static int rand(int max) {
        return (int) (max * Math.random());
    }

    private void delay() {
        try {
            Thread.sleep(1);
        } catch (Exception ex) {
        }
    }

    void yourTurn() {
        timeToMove = true;
        //justWent = false;
    }
    

    void setOpponent(Monte opponent) {
        enemy = opponent;
    }

}
