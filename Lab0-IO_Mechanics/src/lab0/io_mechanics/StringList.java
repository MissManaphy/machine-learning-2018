/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab0.io_mechanics;
import java.util.ArrayList;

/**
 *
 * @author levenick
 */
public class StringList extends ArrayList<String>{

    @Override
    public String toString() {
        String returnMe = "a list:";
        
        for (String nextS : this) {
            returnMe += "\n\t" +nextS;
        }
        
        return returnMe;
    }
    
    
    
    public static void main(String[] args) {
        StringList list = new StringList();
        
        list.add("foo");
        list.add("bar");
        list.add("foobar");
        list.add("etc..");
        
        System.out.println("list = " + list.toString());
    }
}
