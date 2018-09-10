/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;

import java.io.File;

/**
 *
 * @author sophiaanderson
 */
public class PTron {

    private final String ZPATH = "/z";
    private final String PIPATH = "/pi";
    private final String WPATH = "/w";
    private final String BASEPATH = "./ptronData";
    private final int N = 20;
    private int THETA = 1;
    private int ETA = 1;

    private String path = "./ptronData/pi";
    private Boolean verbose = true;

    private PatternList list = new PatternList();
    private InputList inputs = new InputList();
    private int[][] weights = new int[N][N];
    private boolean done = false;
    private int loops = 0;

    public PTron() {
    }

    void setTheta(int newTheta) {
        THETA = newTheta;
    }

    void setEta(int newEta) {
        ETA = newEta;
    }

    void read(File directory) {
        if (verbose) {
            System.out.println("\nInputting from: " + directory + "\n");
        }

        java.io.File[] FileList = directory.listFiles();

        //make the pattern list, only used to make an input list
        for (int i = 0; i < FileList.length; i++) {
            System.out.println("trial " + i);
            if (FileList[i].isFile()) {
                if (verbose) {
                    System.out.println("reading... " + FileList[i]);
                }
                list.add(new Pattern(FileList[i].getAbsolutePath()));
            } else {
                System.out.println("FileList[i] = " + FileList[i] + " is NOT a file!");
            }
        }

        //make the input list from above pattern list
        for (int i = 0; i < list.size(); i++) {
            inputs.add(new Input(list.get(i)));
        }
    }

    void run() {
        loops = 0;
        read(new File(path));
        int catagory = 0;
        while (!done) {
            loops++;
            done = true;
            for (Input input : inputs) {
                catagory = catagorize(input);
                if (catagory != input.t) {
                    done = false;
                    learn(input, catagory, input.t);
                }
            }
        }
    }

    void setZPath() {
        path = BASEPATH + ZPATH;
    }

    void setPiPath() {
        path = BASEPATH + PIPATH;
    }

    void setVerbose() {
        verbose = !verbose;
    }

    void setwPath() {
        path = BASEPATH + WPATH;
    }

    private void learn(Input input, int O, int T) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                weights[i][j] += (T - O) * input.detectors[i][j] * ETA;
            }
        }
    }

    private int catagorize(Input input) {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                total += input.detectors[i][j] * weights[i][j];
            }
        }
        if (total > THETA) {
            return 1;
        }
        return 0;
    }

    String getWeights() {
        String returnMe = "Weights{\n";

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                returnMe += " " + weights[i][j];
            }
            returnMe += "\n ";
        }

        return returnMe + "}";
    }

    String singleOutput() {
        return "Theta = " + THETA + "\n "
                + "ETA = " + ETA + "\n\n"
                + getWeights() + "\n "
                + "Number of Loops = " + loops;
    }
    
    String multiOutput()
    {
        return "Theta = " + THETA + "\n "
                + "Eta = " + ETA + "\n "
                + "Number of Loops = " + loops;
        
        
    }

    void clearMatrix() {
        list = new PatternList();
        inputs = new InputList();
        weights = new int[N][N];
        done = false;
        loops = 0;
        
    }
}
