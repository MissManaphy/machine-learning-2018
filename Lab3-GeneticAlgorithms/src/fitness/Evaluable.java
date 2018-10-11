package fitness;

/**
 *
 * @author levenick
 */
public interface Evaluable {
    public static final int L=100;  // the length of the chromosome
    
    public byte[] getDNA();
    public int getFitness();
    public void setFitness(int fitness);
    public Evaluable myClone();
}