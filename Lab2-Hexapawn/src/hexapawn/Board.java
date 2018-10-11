package hexapawn;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A board to play hexapawn (or octapawn, or...) on!
 *
 * @author levenick Feb 1, 2016 1:38:37 PM
 */
public class Board implements Cloneable, Serializable {

    final static int SIZE = 4;
    int pressedRow, pressedCol;
    static int redWins, blackWins, totalWins;

    protected int[][] sqs;
    protected int whoseTurn;
    private int pieceWidth, left, right, top, bottom;
    private int EMPTY = 0;
    int X = 1;
    int O = -1;
    private Color X_COLOR = Color.BLACK; //player or Monte
    private Color O_COLOR = Color.RED;

    //---------------------------// Initalizers //----------------------------//
    public Board() {
        sqs = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            sqs[0][i] = X;
            sqs[SIZE - 1][i] = O;
        }
        whoseTurn = X;
    }

    public Board(int[][] test) {
        sqs = test;
        whoseTurn = O;
    }

    //-----------------------------// Getters //------------------------------//
    public int[][] getSqs() {
        return sqs;
    }

    public int getWhoseTurn() {
        return whoseTurn;
    }

    static int getSize() {
        return SIZE;
    }

    //-----------------------------// Setters //------------------------------//
    private void setConstants() {
        pieceWidth = 150;
        top = 50;
        left = 30;
        bottom = top + pieceWidth * SIZE;
        right = left + pieceWidth * SIZE;
    }

    public void setSqs(int[][] sqs) {
        this.sqs = sqs;
    }

    public void setWhoseMove(int whoseMove) {
        this.whoseTurn = whoseMove;
    }

    //----------------------------// Overrides //-----------------------------//
    @Override
    public boolean equals(Object other) {
        Board that = (Board) other;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (that.sqs[i][j] != this.sqs[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board clone() {
        Board boardClone = null;

        try {
            boardClone = (Board) super.clone();  // do the shallow copy
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }

        boardClone.sqs = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                boardClone.sqs[i][j] = sqs[i][j];
            }
        }

        return boardClone;
    }

    //-----------------------------// Visuals //------------------------------//
    void paint(Graphics g) {
        setConstants();
        drawHoriz(g);
        drawVert(g);
        drawPieces(g);
    }

    private void drawHoriz(Graphics g) {
        for (int i = 0; i <= SIZE; i++) {
            g.drawLine(left, top + pieceWidth * i, right, top + pieceWidth * i);
        }
    }

    private void drawVert(Graphics g) {
        for (int i = 0; i <= SIZE; i++) {
            g.drawLine(left + i * pieceWidth, top, left + i * pieceWidth, bottom);
        }
    }

    private void drawPieces(Graphics g) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (sqs[row][col] != EMPTY) {
                    if (sqs[row][col] == X) {
                        g.setColor(X_COLOR);
                    } else {
                        g.setColor(O_COLOR);
                    }
                    g.fillOval(left + col * pieceWidth, top + row * pieceWidth, pieceWidth, pieceWidth);
                }
            }
        }
    }

    //------------------------// Resolving Clicks //--------------------------//
    void handlePressed(int x, int y) {
        pressedCol = (x - left) / pieceWidth;
        pressedRow = (y - top) / pieceWidth;
    }

    boolean handleReleased(int x, int y) {
        int col = (x - left) / pieceWidth;
        int row = (y - top) / pieceWidth;

        Move userMove = new Move(pressedRow, pressedCol, row, col);

        System.out.println("userMove = " + userMove);

        if (legalMove(userMove)) {
            playMove(userMove);
            if (gameOver()) {
                announceWinner();
            }
            return true;
        } else {
            complain("not a legal move!!");
            return false;
        }

    }

    //----------------------------// Game Play //-----------------------------//
    public void playMove(Move userMove) {
        if (legalMove(userMove)) {
            play(userMove);
            whoseTurn = -whoseTurn;
        }
    }

    private void play(Move aMove) {
        int fromRow = aMove.getFromRow();
        int fromCol = aMove.getFromCol();
        int toRow = aMove.getToRow();
        int toCol = aMove.getToCol();

        sqs[toRow][toCol] = sqs[fromRow][fromCol];
        sqs[fromRow][fromCol] = EMPTY;
    }

    //---------------------------// Legal Moves //----------------------------//
    private boolean legalMove(Move userMove) {
        if (userMove.onBoard()) {
            if (possible(userMove)) {
                return true;
            } else {
                complain("not possible!!");
            }

        } else {
            complain("please play on the board!");
        }
        return false;
    }

    private void complain(String s) {
        System.out.println("complaining!!  " + s);
    }

    /**
     * A user move is possible if it is a legal move; so... Generate all the
     * legal moves and see if the user move is on the list!
     *
     * @param aMove
     * @return whether the parameter is legal
     */
    private boolean possible(Move aMove) {
        MoveList list = generateAllLegalMoves();
        return list.contains(aMove);
    }

    //-------------------------// Move Generation //--------------------------//
    /**
     * Creates a list of all possible moves for the current player (in
     * whoseTurn)
     *
     * @return list of legal moves
     */
    MoveList generateAllLegalMoves() {
        MoveList returnMe = new MoveList();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (sqs[row][col] == whoseTurn) {
                    addForwardIf(row, col, returnMe);
                    addTakes(row, col, returnMe);
                }
            }
        }
        return returnMe;
    }

    private void addForwardIf(int row, int col, MoveList list) {
        if (!Move.onBoard(row + whoseTurn)) { // is, somehow, the row off the edge??
            System.out.println("I knew it!! (although this *should* be impossible!!");
            System.exit(9898);
        }
        if (sqs[row + whoseTurn][col] == EMPTY) {
            list.add(new Move(row, col, row + whoseTurn, col));
        }
    }

    private void addTakes(int row, int col, MoveList list) {
        addRightTakeIf(row, col, list);
        addLeftTakeIf(row, col, list);
    }

    /**
     * Adds a take move to the right if one exists... Warning! Trick! Uses the
     * fact that whoseMove is +/- 1 to look right or left depending on whose
     * turn it is (i.e. moving forward is up or down (the array!) depending, so
     * right/left is up or down too)
     *
     * @param row - where the piece is
     * @param col - where the piece is
     * @param list - the list of legal moves
     */
    private void addRightTakeIf(int row, int col, MoveList list) {
        addTakeIf(row, col, col - whoseTurn, list);
    }

    private void addLeftTakeIf(int row, int col, MoveList list) {
        addTakeIf(row, col, col + whoseTurn, list);
    }

    void addTakeIf(int row, int col, int toCol, MoveList list) {
        int toRow = row + whoseTurn;
        if (toCol < 0 || toCol >= SIZE) { 
            return;
        }
        if (sqs[toRow][toCol] == -whoseTurn) {
            list.add(new Move(row, col, toRow, toCol));
        }
    }

    //---------------------------// Game Over  //----------------------------//
    public boolean gameOver() {
        return backRow() || noLegalMoves();
    }//gameOver()

    private boolean backRow() {
        if (whoseTurn == X) {
            return checkAny(0);
        } else {
            return checkAny(SIZE - 1);
        }
    }//backRow()

    private boolean noLegalMoves() {
        return generateAllLegalMoves().isEmpty();
    }//noLegalMoves()

    /**
     *
     * @param row
     * @return whether there are any pieces of the other color in the back row
     */
    private boolean checkAny(int row) {
        for (int col = 0; col < SIZE; col++) {
            if (sqs[row][col] == -whoseTurn) {
                return true;
            }
        }
        return false;
    }//checkAny()

    private void announce(String s) {
        //new AnnounceDialog(s);
    }//announce()

    public void announceWinner() {
        if (whoseTurn == X) {
            redWins++;
            totalWins++;
            //announce("Red wins");
        } else {
            blackWins++;
            totalWins++;
            Learner.weAreDoomed_so_dontDoThatAgain(); //the AI loses so it learns
            Learner.save();
            //announce("Black wins!");
        }
    }//announceWinner() 
    
    public static String Winrate()
    {
        String returnMe = "";
        returnMe += "Black : " + blackWins + " ";
        returnMe += "Red : " + redWins;
        
        return returnMe;
        
        
    }
    

    boolean winner() {
        return whoseTurn == X && gameOver(); //true if red wins, and false if black wins
    }

    //-----------------------------// Testing //------------------------------//
    public static void main(String[] args) {
        Board b = new Board();
        System.out.println("b = " + b);
    }//main()

    void huh() {
        System.out.println("sqs = " + sqs);
        System.out.println("sqs.clone() = " + sqs.clone());
    }

    //------------------------------// Output //------------------------------//
    public String toString() {
        String returnMe = "I am a Board: \n";

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                returnMe += ((sqs[i][j] >= 0) ? "  " : " ") + sqs[i][j];
            }
            returnMe += "\n";
        }
        returnMe += "\twhoseMove=" + getWhoseTurn();
        return returnMe;
    } // toString()

}  // Board
