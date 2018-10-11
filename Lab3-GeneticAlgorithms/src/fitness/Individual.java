package fitness;

/**
 * @author levenick Sep 28, 2018 8:22:14 AM
 */
public class Individual implements Evaluable, Comparable {

    int fitness;
    byte[] dna;
    int runningSum;

    public Individual() {
        dna = new byte[L];
        init();
    }
    
    public Individual(byte[] data) {
        dna = data;
        fitness = Environment.eval(this);       // so it has a fitness to start off
    }

    void init() {
        for (int i = 0; i < L; i++) {
            dna[i] = (byte) Util.rand(2);
        }
        fitness = Environment.eval(this);       // so it has a fitness to start off
    }

    @Override
    public String toString() {
        return "Ind: " + Environment.printFitnessFunctionName()
                + " fitness=" + fitness + ", dna=" + format(dna);
    }

    public static void main(String[] args) {    // trying to get a handle on how well enumberation would do...
        Environment.setState(Environment.Mu.countOnes);        // set the fitness function

        Individual anInd = null;
        Individual best = new Individual();
        for (long i = 0; i < 1000000000; i++) {
            if (i % 1000000 == 0) {                 // every million tries, print a .
                System.out.print(".");
            }
            if (best.getFitness() == 100) {         // see how long it takes to find all ones; this is simply true for the others
                System.out.println("Victory!!");
                break;
            }
            anInd = new Individual();               // create a random ind
            if (anInd.getFitness() > best.getFitness()) {   // if it's better, remember it and report
                best = anInd;
                System.out.println("i=" + i + " best.getFitness() = " + best.toString());
            }
        }
        System.out.println("done...");
    }

    @Override
    public byte[] getDNA() {
        return dna;
    }

    public void setDNA(byte[] strand) {
        dna = strand;
    }

    @Override
    public int getFitness() {
        return fitness;
    }
    
    public int currentFitness()
    {
        return Environment.eval(this); 
    }

    @Override
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public Evaluable myClone() {
        Individual copy = new Individual();
        byte[] copyStrand = new byte[L];
        byte[] original = this.getDNA();
        for (int i = 0; i < L; i++) {
            copyStrand[i] = original[i];
        }
        copy.setFitness(this.getFitness());
        copy.setDNA(copyStrand);
        return copy; //gotta deep copy this motherfucker
    }

    private String format(byte[] dna) { // make the byte[] into a String
        String returnMe = "";
        for (byte nextByte : dna) {
            returnMe += "" + nextByte;
        }
        return returnMe;
    }

    public static void mainAgain(String[] args) {
        Environment.setState(Environment.Mu.countOnes);
        for (int i = 0; i < 10; i++) {
            Individual ind = new Individual();
            System.out.println("ind = " + ind);
            Individual clone = (Individual) ind.myClone();
            System.out.println("clone = " + clone);
            System.out.println("But they're not like.... the same pointer right?");
            if (ind.equals(clone)) {
                System.out.println("mmmmmmmmmmmmmmmmmmmmmmmm");
            } else {
                System.out.println("oh thats good, thats good");
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        if (this.fitness == ((Individual) o).fitness)
            return 0;
        if (this.fitness > ((Individual) o).fitness)
            return -1;
        return 1;            
    }
}
