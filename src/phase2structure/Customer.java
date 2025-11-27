/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phase2structure;

/**
 *
 * @author juman
 */



import java.util.ArrayList;
import java.util.List;


public class Customer{
    int customerID;
    String name;
    String Email; 
  AVLTree<Integer , Integer> order= new AVLTree<Integer ,Integer>();
  
  public Customer() {
        this.customerID = 0;
        this.name = "";
        this.Email = "";
    
    }

    public Customer(int customerID, String name, String Email) {
        this.customerID = customerID;
        this.name = name;
        this.Email = Email;
      

    }
    
  
    public void PlaceNew ( Integer o ) {
      order.insert(o,o);
    }


    public boolean removeOrder(Integer o) {
    if (order.find(o)) {
       order.removeKey(o);
       return true;
    }
    return false;
}
    

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    } 

    public AVLTree<Integer , Integer> getOrder() {
        return order;
    }
    
    
}
