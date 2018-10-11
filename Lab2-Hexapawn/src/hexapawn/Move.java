package hexapawn;

import java.io.Serializable;

public class Move implements Comparable<Move>, Serializable{

    protected int fromRow;
    protected int fromCol;
    protected int toRow;
    protected int toCol;
    private double winPercentage;

    public Move() {

    }   //empty default constructor

    @Override
    public boolean equals(Object other) {
        Move that = (Move) other;
        return this.fromRow == that.fromRow && fromCol == that.fromCol
                && toRow == that.toRow && toCol == that.toCol;
    }

    public Move(int fromRow, int fromCol, int toRow, int toCol) {   //initializing constructor
        this();   // invoke the default constructor
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    public int getFromRow() {
        return fromRow;
    }

    public int getFromCol() {
        return fromCol;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToCol() {
        return toCol;
    }

    public void setFromRow(int fromRow) {
        this.fromRow = fromRow;
    }

    public void setFromCol(int fromCol) {
        this.fromCol = fromCol;
    }

    public void setToRow(int toRow) {
        this.toRow = toRow;
    }

    public void setToCol(int toCol) {
        this.toCol = toCol;
    }

    public boolean onBoard() {
        return onBoard(fromCol) && onBoard(toCol) && onBoard(fromRow) && onBoard(toRow);
    }

    public static boolean onBoard(int x) {
        return x >= 0 && x < Board.getSize();
    }

    public String toString() { 
        String returnMe = "A Move: with value:" + this.getWinPercentage();
        returnMe += "\tfromRow=" + getFromRow();
        returnMe += "\tfromCol=" + getFromCol();
        returnMe += "\ttoRow=" + getToRow();
        returnMe += "\ttoCol=" + getToCol();
        return returnMe;
    } // toString()

    public static void main(String[] args) {
        Move m1 = new Move(0, 0, 1, 0);
        Move m2 = new Move(0, 0, 1, 0);

        if (m1.equals(m2)) {
            System.out.println("yes!");
        } else {
            System.out.println("no...");
        }

        MoveList list = new MoveList();
        list.add(m2);
        if (list.contains(m1)) {
            System.out.println("contains");
        } else {
            System.out.println("does not... :(");
        }
    }

    /**
     * @return the winPercentage
     */
    public double getWinPercentage() {
        return winPercentage;
    }

    /**
     * @param winPercentage the winPercentage to set
     */
    public void setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
    }

    @Override
    public int compareTo(Move that) {
        if (that.getWinPercentage() > this.getWinPercentage()) {
            return 1;
        } else {
            return -1;
        }
    }
}  // Move
