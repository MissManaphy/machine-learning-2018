package fitness;

import Fitnesses.*;

/**
 * An interface to the various fitness functions
 * 
 * @author levenick Oct 1, 2018 1:19:40 PM
 */
public class Environment {

    static String printFitnessFunctionName() {
        return printMe();
    }

    public static enum Mu { // so we can use these identifiers to select which fitness we are using
        countOnes, mystery, rr
    };

    public static Mu state = Mu.countOnes;  // default

    public static Mu getState() {
        return state;
    }

    public static void setState(Mu state) {
        Environment.state = state;
    }

    public static int eval(Evaluable it) {
        switch (state) {
            case countOnes:
                return CountOnes.getValue(it);
            case mystery:
                return Mystery.getValue(it);
            case rr:
                return Fitness4.getValue(it);
            default: {
                System.out.println("Oops!");
                System.exit(7);
            }
        }
        return -1;  // never!
    }

    public static String printMe() {
        return "Using fitness: " + whichFitness();
    }

    private static String whichFitness() {  // so we can see which fitness the Environment is using
        switch (state) {
            case countOnes:
                return "countOnes";
            case mystery:
                return "mystery";
            case rr:
                return "rr";
            default: {
                System.out.println("Oops!");
                System.exit(7);
            }
        }
        return "xxx";  // never!    
    }

    public static void main(String[] args) {    // just checking!

        Environment.setState(Mu.countOnes);
        System.out.println(Environment.printMe());
        Environment.setState(Mu.mystery);
        System.out.println(Environment.printMe());
        Environment.setState(Mu.rr);
        System.out.println(Environment.printMe());
    }
}