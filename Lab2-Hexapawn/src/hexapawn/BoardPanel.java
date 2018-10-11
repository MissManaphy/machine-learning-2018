package hexapawn;

import java.awt.*;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author sophiaanderson Sep 24, 2018 12:58:24 PM
 */
public class BoardPanel extends javax.swing.JPanel {

    private Board theBoard;
    private BoardFrame theFrame;
    private AI_butReallyJustRandom theAI;
    private Monte opponent;
    boolean monte = true;
    private boolean continuous = false;

    /**
     * Creates new form BoardPanel
     */
    public BoardPanel() {
        initComponents();
    }

    BoardPanel(BoardFrame f) {
        this();
        theFrame = f;
        reset();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        theBoard.paint(g);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resetButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        playBot = new javax.swing.JButton();
        winrate = new javax.swing.JLabel();
        humanPlay = new javax.swing.JButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        setLayout(null);

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        add(resetButton);
        resetButton.setBounds(10, 0, 78, 29);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        add(saveButton);
        saveButton.setBounds(130, 0, 75, 29);

        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        add(loadButton);
        loadButton.setBounds(200, 0, 75, 29);

        playBot.setText("PlayBot");
        playBot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBotActionPerformed(evt);
            }
        });
        add(playBot);
        playBot.setBounds(330, 0, 80, 29);

        winrate.setText("Winrate");
        add(winrate);
        winrate.setBounds(530, 10, 180, 16);

        humanPlay.setText("Play as Black");
        humanPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                humanPlayActionPerformed(evt);
            }
        });
        add(humanPlay);
        humanPlay.setBounds(400, 0, 110, 29);
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (theBoard.gameOver()) {
            System.out.println("game over!!");
            theFrame.setTitle("Game over!!");
        }
        int x = evt.getX();
        int y = evt.getY();
//        System.out.print("  x = " + x);
//        System.out.println("y = " + y);

        theBoard.handlePressed(x, y);
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if (theBoard.gameOver()) {
            System.out.println("game over!!");
            theFrame.setTitle("Game over!!");
            return;
        }
        int x = evt.getX();
        int y = evt.getY();
//        System.out.print("  x = " + x);
//        System.out.println("y = " + y);

        boolean legalMove = theBoard.handleReleased(x, y);
        repaint();
        if (legalMove) {
            if (theBoard.gameOver()) {
                theFrame.setTitle("Game over!!");
            } else {
                theAI.yourTurn();
            }
        }
        repaint();
    }//GEN-LAST:event_formMouseReleased

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        continuous = false;
        reset();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        Learner.save();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        Learner.load();
    }//GEN-LAST:event_loadButtonActionPerformed

    private void playBotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBotActionPerformed
        theAI.yourTurn();
        continuous = true;
    }//GEN-LAST:event_playBotActionPerformed

    private void humanPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_humanPlayActionPerformed
//        monte = false;
//        reset();
        System.out.println("I don't actually do anything right now");
    }//GEN-LAST:event_humanPlayActionPerformed

    void reset() {
        theBoard = new Board();
        winrate.setText(Board.Winrate());
        if (monte) {
            opponent = new Monte(theBoard, this);
            theAI = new AI_butReallyJustRandom(theBoard, this);
            opponent.setOpponent(theAI);
            theAI.setOpponent(opponent);
            Learner.load();
            theAI.start();
            opponent.start();
            if (continuous) {
                opponent.yourTurn();
            }
        } else {
            theAI = new AI_butReallyJustRandom(theBoard, this);
            Learner.load();
            theAI.start();
        }

        theFrame.setTitle("Black to play!");
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton humanPlay;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton playBot;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel winrate;
    // End of variables declaration//GEN-END:variables

}
