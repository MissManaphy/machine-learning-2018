package Fitnesses;

import fitness.Evaluable;


/**
 * @author levenick Feb 24, 2016 2:08:26 PM
 */

public class CountOnes {

    public static int getValue(Evaluable nextInd) {
        int count = 0;

        for (byte nextByte : nextInd.getDNA()) {
            if (nextByte == 1) {
                count++;
            }
        }

        return count;
    }
}