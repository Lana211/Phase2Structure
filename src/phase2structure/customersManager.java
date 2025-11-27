/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phase2structure;



import java.io.File;
import java.util.Scanner;

public class customersManager {

   
    public static Scanner input = new Scanner(System.in);
    public static AVLTree<Integer,Customer> customer = new AVLTree<Integer,Customer>();
   public static AVLTree<String, Customer> customersName = new AVLTree<String, Customer> ();
    public customersManager(String readFile) {
        try {
            File custmerFile = new File(readFile);
            Scanner read = new Scanner(custmerFile);
            String line = read.nextLine();

            while (read.hasNext()) {
                line = read.nextLine();
                String[] cust = line.split(",");
                Customer customers = new Customer(Integer.parseInt(cust[0]), cust[1], cust[2]);
                customer.insert( customers.getCustomerID(),customers);
              customersName.insert( customers.getName(),customers);
            }
            read.close();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    public AVLTree<Integer,Customer> getcustomersData( )
    {
        return customer ;
    
    }
   public void RegisterNewCustomer(){
        Customer customers =new Customer();
 
    
     System.out.println("enter Customer ID");
     int customerID = input.nextInt();
     
    if(check(customerID))
     customers.setCustomerID(customerID);
      while(!check(customerID)) {   
         System.out.println("invalid id please enter another one");  
           customerID = input.nextInt();
      }  
customers.setCustomerID(customerID);

 System.out.println("enter customer name");
  input.nextLine();
 String name=input.nextLine();
      customers.setName(name);
              
   System.out.println("enter customer email");
   String email=input.nextLine();
 
   customers.setEmail(email);
   
    customer.insert(customers.getCustomerID(),customers);
     customersName.insert( customers.getName(),customers);
     System.out.println("Customer has been added successfully");  
}

   
    public void Ohistory() {

        if (customer.empty()) {
            System.out.println("no customer yet");
            return;
        }

        System.out.println("enter customer ID");
        int customerID = input.nextInt();
 if(!customer.find(customerID)){
        System.out.println("Customer not found");
        return;
 }

      AVLTree<Integer,Integer> order = new AVLTree<Integer,Integer>();
    order= customer.retrieve().getOrder();
      if( order.empty()){
          System.out.println("no order yet");
          return;
      }
 
  order.displayAll();
 
    }

    public boolean check(int customerID) {
        if (customer.empty()) {
            return true;
        } else 
            if(customer.find(customerID))
               return false;
            
            return true;
    }
    
    public void ListCustomerByName(){
      customersName.displayAll();
           
    }
    public void ListCustomerByID(){
      customer.displayAll();
           
    }
  


   public Customer getCustomersId() {

   
    if (customer.empty()) {
        System.out.println("No customers available");
        return null;
    }

    while (true) {
        
        int customerID = input.nextInt();
         
            if (customer.find(customerID)) {
                System.out.println(customer.retrieve());
                return customer.retrieve();
            }

         System.out.println("Customer not found, please enter a valid ID:");
        }
    
    

      
        
       
}


}
