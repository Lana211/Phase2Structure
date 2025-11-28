/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phase2structure;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
public class ordersManager {
    
    public static Scanner input = new Scanner(System.in);
    public static AVLTree<Integer,Order> orders = new AVLTree<Integer,Order>();
    
//==============================================================
    public AVLTree <Integer, Order> getordersData() {
        return orders;
    }
    
//==============================================================
    public ordersManager(String fName , AVLTree<Integer , Customer> customers) {
        try {
            File docsfile = new File(fName);
            Scanner reader = new Scanner(docsfile);
            String line = reader.nextLine();
            
            while(reader.hasNext()) {
                line = reader.nextLine();
                String [] data = line.split(","); 
                int oid = Integer.parseInt(data[0]);
                int cid = Integer.parseInt(data[1]);
                
                String pp = data[2].replaceAll("\"", "");
                String [] p = pp.split(";");
                Integer [] pids = new Integer [p.length];
             
                int i = 0;
                while (i < pids.length) {
                    pids[i] = Integer.parseInt(p[i].trim());
                    i++;
                }
                double price = Double.parseDouble(data[3]);
                String date = data[4];
                String status = data[5];
                        
                Order order = new Order(oid, cid, pids, price, date, status);
                orders.insert(oid,order);
                
                if (customers.find(cid))
                    {
                        Customer c = customers.retrieve();
                        c.PlaceNew(oid);
                        customers.update(c);
                    }
            }
            reader.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //==============================================================
    public int cancelOrder(int oid) {
       
       
            if ( orders.find(oid)) {
                if (orders.retrieve().getStatus().compareToIgnoreCase("cancelled") == 0) {
                    System.out.println("Order " + orders.retrieve().getoId() + " was cancelled before");
                    return 2;
                }
                    
                Order ordera = orders.retrieve(); 
               ordera.setStatus("cancelled");  
                orders.update(ordera);
                return 1; 
            }
            else
             System.out.println("could not find Order ID");
        
        return 0;
    }
    
    //==============================================================
public boolean UpdateOrder(int orderID) {
    // البحث عن الطلب في الشجرة باستخدام المفتاح (orderID)
    if (!orders.find(orderID)) {
        System.out.println("Invalid order ID");
        return false;
    }
    
    // استرجاع بيانات الطلب
    Order obj = orders.retrieve();
    
    // التحقق من حالة الطلب
    if (obj.getStatus().compareToIgnoreCase("cancelled") == 0) {
        System.out.println("Cannot update status for cancelled orders");
        return false;
    }
    
    // عرض الحالة الحالية وطلب الحالة الجديدة
    System.out.println("Status of order is " + obj.getStatus());
    System.out.print("Please enter new status (pending/shipped/delivered/cancelled): ");
    String str = input.next().toLowerCase();
    
    // التحقق من صحة الحالة المدخلة
    while (!str.equals("pending") && !str.equals("shipped") && 
           !str.equals("delivered") && !str.equals("cancelled")) {
        System.out.print("Invalid status. Enter (pending/shipped/delivered/cancelled): ");
        str = input.next().toLowerCase();
    }
    
    // تحديث الحالة
    obj.setStatus(str); // أو obj.status = str;
    orders.update(obj); // تحديث البيانات في الشجرة
    
    return true;
}
    //==================================================================    
    public Order searchOrderID(int orderID) {
    if (orders.find(orderID)) {
        return orders.retrieve();
    }
    System.out.println("Invalid order ID");
    return null;
}

    //==================================================================    
    public AVLTree<Date, Order> BetweenTwoDates(String date1, String date2) {
    // تحويل التواريخ من String إلى LocalDate
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate Ldate1 = LocalDate.parse(date1, formatter);
    LocalDate Ldate2 = LocalDate.parse(date2, formatter);
    
    // استخدام الـ method الجاهزة للبحث بين التاريخين
    AVLTree<Date, Order> ordersbetweenDates = orders.intervalSearchDate(Ldate1, Ldate2);
    
    // طباعة النتائج
    if (!ordersbetweenDates.empty()) {
        System.out.println("Orders between " + date1 + " and " + date2 + ":");
        System.out.println("=".repeat(50));
        ordersbetweenDates.printData();
        System.out.println("=".repeat(50));
    } else {
        System.out.println("No orders found between these dates.");
    }
    
    return ordersbetweenDates;
}
    //==================================================================    
   public boolean Checkorderid(int oid) {
    return orders.find(oid);
}
}