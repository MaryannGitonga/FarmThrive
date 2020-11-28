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
public class Consumer extends Person{
    
    
    public Consumer(){
      super();
    }
   
    public Consumer(String ID, String firstName, String lastName, String contact, String address){
       super(ID, firstName, lastName, contact, address);
    }
   
   
    public String toString(){
        return super.toString();
    }
}
