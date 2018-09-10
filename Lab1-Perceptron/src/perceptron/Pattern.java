package perceptron;
/**
 * A wrapper for a StringList, plus the pathname of the file it came from
 * @author levenick
 */
public class Pattern {
    /**
     * The list of Strings in this Pattern
     */
    StringList list; 

    public StringList getList() {
        return list;
    }
    String pathname;  // pathname this file, for use in the header
    
    /**
     * Default constructor just instantiates the StringList
     */
    Pattern() {
        list = new StringList();
    }

    /**
     * Reads the lines of a file passed into a StringList, each line is stored as a String
     * @param absolutePath - path of the file to read
     */
    Pattern(String absolutePath) {
       this();
       pathname = absolutePath;
       MyReader mr = new MyReader(pathname);

       while (mr.hasMoreData()) {
            list.add(mr.giveMeTheNextLine());
        }    
       mr.close();
    }

    /**
     * Pastes together all the Strings in the StringList
     * @return them
     */
    @Override
    public String toString() {
        String returnMe = header();
                
        for (String nextString : list) {
            returnMe += "\n\t" + nextString;
        }
        
        return returnMe;
    }

    /**
     * Builds a simple header for output
     * @return the pathname between lines of *'s
     */
    private String header() {
        return bar() + pathname  + bar();
    }

    /**
     * 
     * @return a bunch of *'s with \n before and after
     */
    private String bar() {
        return "\n" + "********************************************************************"+ "\n" ;
    }
    
    
}
