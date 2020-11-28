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
public class Produce {
    private String ID;
    private String name;
    private String type;
    private String price;
    private String quantity;
    private String farmer;
    
//    public Produce(){
//        this.ID = "";
//        this.name = "Unknown";
//        this.type = "Unknown";
//        this.price = "Unknown";
//        this.quantity = "Unknown";
//        this.farmer = "Uknown";
//    }
    
    public Produce(String ID, String name, String type, String price, String quantity, String farmer){
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.farmer = farmer;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }
    
    public String toString(){
        return this.ID + "," + this.name + "," + this.type + "," + this.price + "," + this.quantity;
    }
}
