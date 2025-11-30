/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phase2structure;

//s3

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Order {
    int oId;
    int customerRef;
    AVLTree <Integer,Integer> products = new AVLTree <Integer,Integer> ();  
    double total_price;
    LocalDate date;
    String status; 

    public Order() {
        this.oId = 0;
        this.customerRef = 0;
        this.total_price = 0;
        this.status = "";
        this.date = LocalDate.now();
    }

    public Order(int oId, int customerRef, Integer [] pids, double total_price, String date, String status) {
        this.oId = oId;
        this.customerRef = customerRef;
        this.total_price = total_price;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
        
        this.status = status;
        
        int i = 0;
        while (i < pids.length) {
            this.products.insert(pids[i],pids[i]);
            i++;
        }
    }
    

    public int getoId() {
        return oId;
    }

    public int getCustomerRef() {
        return customerRef;
    }

    public AVLTree<Integer,Integer> getProducts() {
        return products;
    }

    public double getTotal_price() {
        return total_price;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setOrderId(int oId) {
        this.oId = oId;
    }

    public void setcustomerRef(int customerRef) {
        this.customerRef = customerRef;
    }

    public void setProducts(int pid) {
        this.products.insert(pid,pid);
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
   
    public void setStatus(String status) {
        this.status = status;
    }

    public void addProduct (Integer product) {
        products.insert(product,product);
    }

 public boolean deleteProduct(Integer P) {
    return products.removeKey(P);
}
   
    @Override
  public String toString() {
    String str = "\nOrder{" 
            + "orderId=" + oId
            + ", customerRef=" + customerRef 
            + ", totalPrice=" + total_price 
            + ", status=" + status
            + ", date=" + date;
    
    if (!products.empty()) {
        str += " (products: " + products.toString() + ")";
    }
    
    str += "}";
    return str;        
}
}