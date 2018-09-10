package perceptron;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author levenick
 */
class InputList extends ArrayList<Input>{
  @Override
    public String toString() {
        String returnMe = "InputList{";
        
        for (Input nextInput : this) {
            returnMe += "\n\t" + nextInput.toString();
        }
        return returnMe;
    }
       
}