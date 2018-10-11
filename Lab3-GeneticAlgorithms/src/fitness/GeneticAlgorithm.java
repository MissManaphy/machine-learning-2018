package fitness;

import java.util.Collections;

/**
 *
 * @author sophiaanderson
 */
public class GeneticAlgorithm {

    static IndividualList population;
    
    //Testing arrays
    static boolean testing = false;
    static int[] testPops = new int[]{10, 100, 1000, 10000};
    static double[] testMuta = new double[]{0.001, 0.01, 0.1};
    static int[] testCross = new int[]{1, 2, Util.rand(20)}; //the rand is to simulate N point crossover
    
    
    static int popSize = 10000; 
    static boolean done = false;
    static double percentage = .03;
    static int poolSize = (int) (popSize*percentage);

    public static void main(String[] args) {
        if(testing)
        {
            testRun();
        }
        standardRun();
    }
    
    private static void setPopSize(int size)
    {
        popSize = size;
    }
    
    private static int getPopSize()
    {
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

    private static IndividualList singleCrossover(IndividualList parents) {
        IndividualList children = new IndividualList();
        for (int i = 0; i < parents.size()-1; i+=2) { //only mates pairs. If there's a solo then it doesn't breed
        
        Individual parentOneCopy = (Individual) parents.get(i).myClone(); //copies of the parents
        Individual parentTwoCopy = (Individual) parents.get(i+1).myClone(); //so there's no pointer issues
        Individual standInCopy = (Individual) parents.get(i+1).myClone();
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

    private static void mutate(IndividualList offspring) { ///need to fix mutate to do proper 
        int i;
        for (Individual individual : offspring) {
            for (byte b : individual.getDNA()) {
                i = Util.rand(11); //randomness factor 1-10
                if (i > 9) { //10% chance to mutate
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
        int i = population.size()-1;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
