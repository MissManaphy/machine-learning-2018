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
public class Pattern {
    
    String path = "";
    String content = "";
    String spacer = "*******************************************************************";
    
    public Pattern() {
    }
    
    public Pattern(String filePath)
    {
        path = filePath;
        MyReader mr = new MyReader(path);
        while (mr.hasMoreData()){
            content += "\t" + mr.giveMeTheNextLine() + "\n";
        }
    }
    
//    public void addContent(String fileContent)
//    {
//        content = fileContent;
//    }
//    
    public String toString()
    {
        return spacer + "\n" + path + "\n" + spacer + "\n" + content + "\n" + "\n";
    }
    
    
    
//pattern needs a constructor that is passed a path
    
}
