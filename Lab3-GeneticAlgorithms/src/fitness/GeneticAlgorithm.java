package fitness;

import java.util.Collections;

/**
 *
 * @author sophiaanderson
 */
public class GeneticAlgorithm {
    
    static IndividualList population;

    //Testing arrays
    static boolean testing = true;
    static int[] testPops = new int[]{10, 100, 1000, 10000};
    static double[] testMuta = new double[]{0.1, 0.01, 0.001};
    static int[] testCross = new int[]{1, 2, 3}; //the rand is to simulate N point crossover

    static int popSize = 100;
    static double mutaRate = 0;
    static boolean done = false;
    static double percentage = .03;
    static int crossNum = 1;
    static int poolSize = (int) (popSize * percentage);
    
    public static void main(String[] args) {
        if (testing) {
            testRun();
        } else {
            standardRun();
        }
    }
    
    private static void setPopSize(int size) {
        popSize = size;
    }
    
    private static int getPopSize() {
        return popSize;
    }
    
    private static void populate() {
        population = new IndividualList();
        for (int i = 0; i < popSize; i++) {
            population.add(new Individual());
        }
    }
    
    private static IndividualList selectMatingPool(IndividualList options) {
        int sum = 0;
        for (Individual option : options) {
            sum += option.getFitness();
            option.runningSum = sum;
        }
        
        IndividualList sets = new IndividualList();
        for (int i = 0; i < poolSize; i++) {
            int z = 0;
            int r = Util.rand(sum);
            while (options.get(z).runningSum < r) {
                z++;
            }
            sets.add((Individual) options.get(z).myClone());
            
        }
        return sets;
    }
    
    private static IndividualList nCrossover(IndividualList parents, int numCrossover) {
        IndividualList children = new IndividualList();
        
        for (int i = 0; i < parents.size() - 1; i += 2) { //only mates pairs. If there's a solo then it doesn't breed
            int split = 0;
            Individual parentOneCopy = (Individual) parents.get(i).myClone(); //copies of the parents
            Individual parentTwoCopy = (Individual) parents.get(i + 1).myClone(); //so there's no pointer issues
            Individual standInCopy = (Individual) parents.get(i + 1).myClone();
            int cross = (int) (parentOneCopy.getDNA().length / numCrossover); //split dna into sections

            byte[] standIn = standInCopy.getDNA();
            byte[] dnaOne = parentOneCopy.getDNA();
            byte[] dnaTwo = parentTwoCopy.getDNA();
            
            for (int k = 0; k < numCrossover; k++) {
                split += Util.rand(cross); //will make a right moving swapover point

                for (int j = split; j < standIn.length; j++) { //swaps the dna from the swap point over to the right
                    dnaTwo[j] = dnaOne[j];
                    dnaOne[j] = standIn[j];
                    for (int l = 0; l < dnaTwo.length; l++) { //resets the stand-in to the second dna strand w/out pointer issues
                        standIn[l] = dnaTwo[l];
                    }
                }
            }
            children.add(new Individual(dnaOne)); //adds the two new children to the pool
            children.add(new Individual(dnaTwo));
        }
        return children;
    }
    
    private static IndividualList singleCrossover(IndividualList parents) {
        IndividualList children = new IndividualList();
        for (int i = 0; i < parents.size() - 1; i += 2) { //only mates pairs. If there's a solo then it doesn't breed

            Individual parentOneCopy = (Individual) parents.get(i).myClone(); //copies of the parents
            Individual parentTwoCopy = (Individual) parents.get(i + 1).myClone(); //so there's no pointer issues
            Individual standInCopy = (Individual) parents.get(i + 1).myClone();
            int split = Util.rand(parentOneCopy.getDNA().length);
            
            byte[] standIn = standInCopy.getDNA();
            byte[] dnaOne = parentOneCopy.getDNA();
            byte[] dnaTwo = parentTwoCopy.getDNA();
            
            for (int j = split; j < standIn.length; j++) { //currently just swaps it at one point
                dnaTwo[j] = dnaOne[j];
                dnaOne[j] = standIn[j];
            }
            
            children.add(new Individual(dnaOne)); //adds the two new children to the pool
            children.add(new Individual(dnaTwo));
        }
        return children;
    }
    
    private static void mutate(IndividualList offspring) { ///need to fix mutate to work with double mutaRate
        for (Individual individual : offspring) {
            for (byte b : individual.getDNA()) {
                int i = Util.rand((int) Math.ceil(1 / mutaRate));
                if (i == 0) { //1 in x chance to randomly generate 0, therefore 1/x chance to mutate
                    b = swap(b);
                }
            }
            individual.setFitness(individual.currentFitness());
        }
    }
    
    private static String byteString(byte[] bleh) {
        String returnMe = "";
        for (byte nextByte : bleh) {
            returnMe += "" + nextByte;
        }
        return returnMe;
    }
    
    private static void sort() {
        Collections.sort(population);
    }
    
    private static void replace(IndividualList offspring) {
        int i = population.size() - 1;
        for (Individual individual : offspring) { //this is the replace code, should move it somewhere else
            population.remove(i);
            population.add(individual);
            i--;
        }
    }
    
    private static byte swap(byte b) {
        if (b == 0) {
            return 1;
        }
        return 0;
    }
    
    private static void standardRun() {
        populate();
        sort();
        int z = 0;
        while (z < 500) {
            IndividualList parents = selectMatingPool(population);
            IndividualList offspring = singleCrossover(parents);
            mutate(offspring);
            replace(offspring);
            z++;
            sort();
        }
        System.out.println("population = \n" + population); //final population
    }
    
    private static void testRun() {
        for (Environment.Mu nextMu : Environment.Mu.values()) {
            Environment.setState(nextMu);
            System.out.println(nextMu);
            for (int testPopSize : testPops) {
                for (double testMutaRate : testMuta) {
                    for (int testNumCross : testCross) { // not trying dif fitnesses rn
                        setVals(testPopSize, testMutaRate, testNumCross);
                        doTest();
                    }
                }                
            }
        }
        
    }
    
    private static void setVals(int testPopSize, double testMutaRate, int testNumCross) {
        popSize = testPopSize;
        mutaRate = testMutaRate;
        crossNum = testNumCross;
    }
    
    private static void doTest() {
        done = false;
        int generations = 0;
        populate();
        sort();
        while (!done) {
            IndividualList parents = selectMatingPool(population);
            IndividualList offspring = nCrossover(parents, crossNum);
            mutate(offspring);
            replace(offspring);
            sort();
            generations++;
            amIfinished(generations);
            
        }
        System.out.println("generations = " + generations
                + " maximum = " + population.get(0).currentFitness()
                + " popSize = " + popSize
                + " mutaRate = " + mutaRate
                + " crossNum = " + crossNum);
    }
    
    private static void amIfinished(int generations) {
        if(null != Environment.getState())
            switch (Environment.getState()) {
            case countOnes:
                done =  (population.get(0).currentFitness() == 100 || generations == 100000);
                break;
            case mystery:
                done = (population.get(0).currentFitness() == 1999999999 || generations == 100000);
                break;
            case rr:
                done = (population.get(0).currentFitness() == 123456 || generations == 100000);
                break;
            default:
                break;
        }
    }
    
    private static boolean allEqual() {
        int i = population.get(0).currentFitness();
        for (Individual individual : population) {
            if (individual.getFitness() != i) {
                return false;
            }
        }
        return true;
        
    }
    
}
