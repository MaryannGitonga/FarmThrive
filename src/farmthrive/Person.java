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
public class Person {
    private String ID;
    private String firstName;
    private String lastName;
    private String contact;
    private String address;
    
    public Person(){
       this.ID = "Unknown";
       this.firstName = "Unknown";
       this.lastName = "Unknown";
       this.address = "Unknown";
       this.contact = "Unknown";
    }
   
    public Person(String ID, String firstName, String lastName, String contact, String address){
       this.ID = ID;
       this.firstName = firstName;
       this.lastName = lastName;
       this.contact = contact;
       this.address = address;
    }
   
    public String getID(){
       return ID;
    }
   
    public void setID(String ID){
       this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
   
    public String toString(){
        return this.ID + ", " + this.firstName + ", " + this.lastName + ", " + this.address + ", " + this.contact;
    }
}
