package model;

import java.io.Serializable;

/**
 *
 * @author Andrej
 */
public class Model implements Serializable {
    private String input;
    
    public Model(){
    }
    
    public String getInput(){
        return input;
    }
    
    public void setInput(String input){
        this.input = input;
    }
    
}
