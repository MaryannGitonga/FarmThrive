/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmthrive;

/**
 *
 * @author USER
 */
public class Farmer extends Person{
    
    
    public Farmer(){
        super();
    }
    
    public Farmer(String ID, String firstName, String lastName, String contact, String address){
        super(ID, firstName, lastName, contact, address);
    }

    public String toString(){
        return super.toString();
    }
}
