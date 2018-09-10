/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab0.io_mechanics;

/**
 *
 * @author sophiaanderson
 */
public class PatternList extends java.util.ArrayList<Pattern>
{
    // PatternList is now a synonym for java.util.ArrayList<Pattern> (almost)
    
    @Override
    public String toString() {
        String returnMe = "Full Directory:";
        
        for (Pattern nextP : this) {
            returnMe += "\n" + nextP.toString();
        }
        
        return returnMe;
    }
    
} 
