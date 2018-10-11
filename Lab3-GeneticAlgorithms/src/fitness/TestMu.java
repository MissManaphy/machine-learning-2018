package fitness;

/**
 * @author levenick Oct 1, 2018 1:36:59 PM
 */

public class TestMu {
    public static void main(String[] args) {
        Environment.setState(Environment.Mu.countOnes);
        System.out.println(Environment.printFitnessFunctionName());
        Environment.setState(Environment.Mu.mystery);
        System.out.println(Environment.printFitnessFunctionName());
        Environment.setState(Environment.Mu.rr);
        System.out.println(Environment.printFitnessFunctionName());
    }
}