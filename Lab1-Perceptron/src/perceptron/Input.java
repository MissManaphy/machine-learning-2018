package perceptron;
/**
 *
 * @author levenick
 */
public class Input {

    int t;
    int[][] detectors;
    final int N = 20;

    Input() {
        detectors = new int[N][N];
    }
    
     @Override
    public String toString() {
        String returnMe = "Input{\n";
        
         for (int i = 0; i < N; i++) {
             for (int j = 0; j < N; j++) {
                 returnMe += " " + detectors[i][j];
             }
             returnMe += "\n";
         }
         returnMe += "T=" + t + "\n\n";
         
        return returnMe;
    }
    
    
    Input(Pattern aPattern) {
        this();
        //System.out.println("aPattern = " + aPattern);
        String s = aPattern.getList().get(N);
        if (s.toLowerCase().charAt(0) == 'y') {  // first get the answer and store it
            t = 1;
        }
        
        for (int row = 0; row < N; row++) {     // convert each of the first N rows to 0 or 1 and store
            String nextString = aPattern.getList().get(row);
            int col = 0;                        // start in the first column

            for (int i = 0; i < N; i++) {
                char ch = nextString.charAt(i);
                int detector = 0;               // default 0
                if (ch == '*') {                // stars are 1
                    detector = 1;
                }
                detectors[row][col] = detector;
                col++;                          // next column
            }
        }
    }

}