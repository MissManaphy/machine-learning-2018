/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness;

import java.util.ArrayList;

/**
 *
 * @author sophiaanderson
 */
public class IndividualList extends ArrayList<Individual> {
    
    @Override
    public String toString() {
        String returnMe = "";
        for (Individual dna : this) {
            returnMe += dna.toString() + "\n";
        }
        return returnMe;
    }
    
    
    
}
