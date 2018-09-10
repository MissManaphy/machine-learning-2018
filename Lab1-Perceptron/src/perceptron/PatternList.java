/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;
import java.util.ArrayList;

/**
 *
 * @author levenick
 */
public class PatternList extends ArrayList<Pattern>{

    @Override
    public String toString() {
        String returnMe = "PatternList{";
        
        for (Pattern nextPattern : this) {
            returnMe += "\n\t" + nextPattern.toString();
        }
        
        return returnMe;
    }
    
}
