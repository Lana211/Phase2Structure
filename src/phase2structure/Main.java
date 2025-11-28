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

public class Main {
    
    
    public static Scanner input = new Scanner (System.in);

    public static productsManager pdata  = new productsManager("prodcuts.csv");
    public static AVLTree<Integer, Product>  products;

    public static customersManager cdata = new customersManager("customers.csv");
    public  static AVLTree<Integer, Customer> customers;

     public static ordersManager odata = new ordersManager("orders.csv" , cdata.getCustomer());
     public static AVLTree<Integer, Order>  orders ;

     public static reviewsManager rdata= new reviewsManager("reviews.csv", pdata.getproductsIDs());
     public static LinkedList <Review>  reviews;
 

    
    //ead data from files for all the 4 data structures
    public static void loadData() {
        System.out.println("Loading data...");
        products = pdata.getproductsIDs();
        customers = cdata.getCustomer();
        orders = odata.getordersData();
        reviews = rdata.getAllReviews();
        
        } 
//--------------------------

   public static void productsMenu() {
    int choice;

    System.out.println("ــــــــــProducts Menu ــــــــ");
    System.out.println("1. Add a new product");
    System.out.println("2. Remove a product");
    System.out.println("3. Update product");
    System.out.println("4. Search product by ID");
    System.out.println("5. Search product by Name");
    System.out.println("6. Track all out-of-stock products");
    System.out.println("7. List All Products Within a Price Range");
    System.out.println("8. Return to main menu");
    System.out.println("Please enter your choice:");

        try {
            choice = input.nextInt();

            while (true) {
                if (choice == 1) {
                    pdata.addProduct();
                    break;
                } else if (choice == 2) {
                    pdata.removeProduct(); // No product will be removed, stock will just be set to zero.
                    break;
                } else if (choice == 3) {
                    pdata.updateProduct();
                    break;
                } else if (choice == 4) {
                    Product pro = pdata.searchProducID();
                    if (pro != null) {
                        System.out.println("Product found: " + pro);
                    } else {
                        System.out.println("No product found with the given ID.");
                    }
                    break;
                } else if (choice == 5) {

                    input.nextLine();
                    Product pro = pdata.searchProducName();
                    if (pro != null) {
                        System.out.println("Product found: " + pro);
                    }
                    break;
                } else if (choice == 6) {
                    pdata.Out_Of_Stock_Products();
                    break;
                } else if (choice == 7) {
                    double minPrice, maxPrice;

                    do {
                        System.out.println("Enter range :");
                        System.out.print("minPrice : ");
                        minPrice = input.nextDouble();
                        System.out.print("maxPrice : ");
                        maxPrice = input.nextDouble();

                        if (minPrice >= maxPrice) {
                            System.out.println("Re-enter range [minPrice less than maxPrice] :");
                        }
                    } while (minPrice >= maxPrice);

                    LinkedList<Product> data = pdata.getPriceRange(minPrice, maxPrice);

                    if (data.empty()) {
                        System.out.println("No products in this range");
                    } else {
                        data.print();
                    }

                    break;

                } else if (choice == 8) {
                    System.out.println("Returning to main menu...");
                    return;
                } else {
                    System.out.println("Invalid choice! Please select a valid option between 1 and 8.");
                    break;
                }
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            input.nextLine();
        }
    }


    //=================================================================
// REVIEWS MENU
//=================================================================
public static void ReviewsMenu() {
    int choice;

    System.out.println("ــــــــــ Review Menu ــــــــ");
    System.out.println("1. Add a new review");
    System.out.println("2. Edit an existing review");
    System.out.println("3. Get the average rating for a product");
    System.out.println("4. Top 3 Most Reviewed Products");
    System.out.println("5. Top 3 Highest Rated Products");
    System.out.println("6. Common products with rating > 4 between 2 customers");
    System.out.println("7. Show all customers who reviewed a product (sorted)");
    System.out.println("8. Return to Main menu");
    System.out.print("Enter your choice: ");

    try {
        choice = input.nextInt();

        while (true) {

            if (choice == 1) {
                addReviewPrompt();
                break;
            }

            else if (choice == 2) {
                rdata.updateReview();
                break;
            }

            else if (choice == 3) {
                System.out.print("Enter product ID to get an average rating: ");
                int pid = input.nextInt();

                while (!pdata.checkProductID(pid)) {
                    System.out.println("The product ID is invalid. Please try again:");
                    pid = input.nextInt();
                }

                float AVG = avgRating(pid);
                System.out.println("The average rating for product ID " + pid + " is: " + AVG);
                break;
            }

            else if (choice == 4) {
                top3MostReviewedProducts();
                break;
            }

            else if (choice == 5) {
                top3HighestRatedProducts();
                break;
            }

            else if (choice == 6) {
                System.out.print("Enter the first customer's ID: ");
                Customer cid1 = cdata.getCustomersId();

                System.out.print("Enter the second customer's ID: ");
                Customer cid2 = cdata.getCustomersId();

                commonProducts(cid1.getCustomerID(), cid2.getCustomerID());
                break;
            }
            else if (choice == 7) {
                System.out.print("Enter product ID: ");
                int pid = input.nextInt();

                while (!pdata.checkProductID(pid)) {
                    System.out.println("Invalid product ID. Try again:");
                    pid = input.nextInt();
                }

                int sortChoice;

                while (true) {
                    System.out.println("Choose sorting type:");
                    System.out.println("1. Sort by Rating");
                    System.out.println("2. Sort by Customer ID");
                    sortChoice = input.nextInt();

                    if (sortChoice == 1 || sortChoice == 2) {
                        break;
                    }

                    System.out.println("Invalid sorting choice. Please enter 1 or 2.");
                }

                if (sortChoice == 1) {
                    showCustomersForProductByRating(pid);
                } else {
                    showCustomersForProductByCustomerID(pid);
                }
                break;
            }



            else if (choice == 8) {
                System.out.println("Returning to Main menu...");
                break;
            }

            else {
                System.out.println("Invalid choice. Please try again.");
                break;
            }
        }

    } catch (java.util.InputMismatchException e) {
        System.out.println("Invalid input! Please enter a valid number.");
        input.nextLine();
    }
}



//=================================================================
// 1) ADD NEW REVIEW
//=================================================================
public static void addReviewPrompt() {
        System.out.print("Enter the Customer ID: ");
        int customerId = input.nextInt();
        while (cdata.check(customerId)) {// updated (check)
            System.out.println("Customer ID not available. Please enter again:");
            customerId = input.nextInt();
        }

        System.out.print("Enter the Product ID: ");
        int productId = input.nextInt();
        while (!pdata.checkProductID(productId)) {
            System.out.println("Product ID not available. Please enter again:");
            productId = input.nextInt();
        }

        Review review = rdata.addNewReview(customerId, productId);
        System.out.println("Review (ID: " + review.getReviewId() + ") added successfully for Product " + review.getProduct()
                + " by Customer " + review.getCustomer() + " with Rating: " + review.getRating() + " and Comment: " + review.getComment());
    }



//=================================================================
// 2) AVERAGE RATING FOR PRODUCT
//=================================================================
public static float avgRating(int productId) {
        int reviewCount = 0;
        float totalRating = 0;

        reviews.findFirst();
        while (!reviews.last()) {
            if (reviews.retrieve().getProduct() == productId) {
                reviewCount++;
                totalRating += reviews.retrieve().getRating();
            }
            reviews.findNext();
        }

        if (reviews.retrieve().getProduct() == productId) {
            reviewCount++;
            totalRating += reviews.retrieve().getRating();
        }

        if (reviewCount == 0) {
            System.out.println("No reviews found for Product ID: " + productId);
            return 0;
        }
        return (totalRating / reviewCount);
    }



//=================================================================
// 3) TOP 3 MOST REVIEWED PRODUCTS
//=================================================================
public static void top3MostReviewedProducts() {

    LinkedPQ<Product> pq = new LinkedPQ<Product>();
    LinkedList<Product> allProducts = products.inOrdertraverseData();

    if (allProducts.empty()) {
        System.out.println("No products available.");
        return;
    }

    allProducts.findFirst();
    while (true) {

        Product p = allProducts.retrieve();
        int reviewCount = 0;

        reviews.findFirst();
        while (true) {
            if (reviews.retrieve().getProduct() == p.getProductId())
                reviewCount++;

            if (reviews.last()) break;
            reviews.findNext();
        }

        if (reviewCount > 0)
            pq.enqueue(p, reviewCount);

        if (allProducts.last()) break;
        allProducts.findNext();
    }

    System.out.println("Top 3 Most Reviewed Products:");
    for (int i = 1; i <= 3 && pq.length() > 0; i++) {
        PQElement<Product> top = pq.serve();
        System.out.println("Product " + i + " - ID: " + top.data.getProductId()
                + " | " + top.data.getName()
                + " | Reviews: " + top.priority);
    }
}



//=================================================================
// 4) TOP 3 BY AVERAGE RATING
//=================================================================
public static void top3HighestRatedProducts() {

    LinkedPQ<Product> pq = new LinkedPQ<Product>();
    LinkedList<Product> allProducts = products.inOrdertraverseData();

    if (allProducts.empty()) {
        System.out.println("No products available.");
        return;
    }

    allProducts.findFirst();
    while (true) {
        Product p = allProducts.retrieve();
        float avg = avgRating(p.getProductId());
        if (avg > 0)
            pq.enqueue(p, avg);

        if (allProducts.last()) break;
        allProducts.findNext();
    }

    System.out.println("Top 3 Highest Rated Products:");
    for (int i = 1; i <= 3 && pq.length() > 0; i++) {
        PQElement<Product> top = pq.serve();
        System.out.println("Product " + i + " - ID: " + top.data.getProductId()
                + " | " + top.data.getName()
                + " | Avg Rating: " + top.priority);
    }
}



//=================================================================
// 5) COMMON PRODUCTS WITH AVG RATING ≥ 4
//=================================================================
public static void commonProducts(int cid1, int cid2) {
        LinkedList<Integer> customer1Products = new LinkedList<Integer>();
        LinkedList<Integer> customer2Products = new LinkedList<Integer>();
        
        if (reviews.empty()) {
        System.out.println("No reviews available.");
        return;  
    }
        
       
        reviews.findFirst();
        while (!reviews.last()) {
            if (reviews.retrieve().getCustomer() == cid1) {
                customer1Products.insert(reviews.retrieve().getProduct());
            }
            if (reviews.retrieve().getCustomer() == cid2) {
                customer2Products.insert(reviews.retrieve().getProduct());
            }
            reviews.findNext();
        }

        System.out.println("Customer " + cid1 + " reviewed products: ");
        customer1Products.print();
        System.out.println("Customer " + cid2 + " reviewed products: ");
        customer2Products.print();

        LinkedPQ<Product> commonProducts = new LinkedPQ<Product>();

        customer1Products.findFirst();
        for (int m = 0; m < customer1Products.size(); m++) {
            int productId = customer1Products.retrieve();
            
            customer2Products.findFirst();
            for (int n = 0; n < customer2Products.size(); n++) {
                if (productId == customer2Products.retrieve()) {
                    float avgRating = avgRating(productId);
                    if (avgRating >= 4) {
                        Product p = pdata.getProducts(productId);
                        commonProducts.enqueue(p, avgRating);
                    }
                }
                customer2Products.findNext();
            }
            customer1Products.findNext();
        }

        if (commonProducts.length() > 0) {
            System.out.println("Common products with rating > 4:");
            while (commonProducts.length() > 0) {
                PQElement<Product> product = commonProducts.serve();
                System.out.println("Product ID: " + product.data.getProductId() + " | " + product.data.getName()
                        + " | Avg Rating: " + product.priority);
            }
        } else {
            System.out.println("No common products with a rating greater than 4 between the two customers.");
        }
    }


//=================================================================
// SHOW ALL CUSTOMERS WHO REVIEWED A PRODUCT — SORTED BY RATING
//=================================================================
public static void showCustomersForProductByRating(int pid) {

    if (reviews.empty()) {
        System.out.println("No reviews available.");
        return;
    }

    LinkedPQ<Review> pq = new LinkedPQ<Review>();

    reviews.findFirst();
    while (true) {
        Review r = reviews.retrieve();
        if (r.getProduct() == pid) {
            pq.enqueue(r, r.getRating());  // priority = rating
        }
        if (reviews.last()) break;
        reviews.findNext();
    }

    if (pq.length() == 0) {
        System.out.println("No reviews found for this product.");
        return;
    }

    System.out.println("Customers who reviewed product (" + pid + ") sorted by RATING:");
    while (pq.length() > 0) {
        PQElement<Review> rr = pq.serve();
        Review r = rr.data;

        System.out.println("Customer ID: " + r.getCustomer()
                + " | Rating: " + r.getRating()
                + " | Comment: " + r.getComment());
    }
}
 
//=================================================================
// SHOW ALL CUSTOMERS WHO REVIEWED A PRODUCT — SORTED BY CUSTOMER ID
//=================================================================
public static void showCustomersForProductByCustomerID(int pid) {

    if (reviews.empty()) {
        System.out.println("No reviews available.");
        return;
    }

    LinkedPQ<Review> pq = new LinkedPQ<Review>();

    reviews.findFirst();
    while (true) {
        Review r = reviews.retrieve();
        if (r.getProduct() == pid) {
            pq.enqueue(r, r.getCustomer());  // priority = customer ID
        }
        if (reviews.last()) break;
        reviews.findNext();
    }

    if (pq.length() == 0) {
        System.out.println("No reviews found for this product.");
        return;
    }

    System.out.println("Customers who reviewed product (" + pid + ") sorted by CUSTOMER ID:");
    while (pq.length() > 0) {
        PQElement<Review> rr = pq.serve();
        Review r = rr.data;

        System.out.println("Customer ID: " + r.getCustomer()
                + " | Rating: " + r.getRating()
                + " | Comment: " + r.getComment());
    }
}


    public static void OrdersMenu() {
    System.out.println("ــــــــــ Order Menu ــــــــ");
    System.out.println("(1) Search for an Order");
    System.out.println("(2) Cancel an Order");
    System.out.println("(3) Update Order Status");
    System.out.println("(4) Find Orders in Date Range");
    System.out.println("(5) Place New Order");  
    System.out.println("(6) Return to Main Menu");
    System.out.print("\n→ Enter option: ");
    
    try {
        int opt = input.nextInt();
        
        while (true) {
            if (opt == 1) {
                searchForOrder();   
                break;
            } 
            else if (opt == 2) {
                cancelAnOrder();
                break;
            } 
            else if (opt == 3) {
                updateOrderStatus();
                break;
            } 
            else if (opt == 4) {
                findOrdersInDateRange();
                break;
            } 
            else if (opt == 5) {
                placeNewOrder();
                break;
            }
            else if (opt == 6) {
                System.out.println("Going back to main menu...\n");
                break;
            } 
            else {
                System.out.println("Invalid option! Please choose 1-6.");
                break;
            }
        }
    } catch (java.util.InputMismatchException e) {
        System.out.println("Invalid input! Please enter a valid number.");
        input.nextLine();  
    }
}

// ✅ صح
private static void searchForOrder() {
    System.out.print("\nEnter Order ID: ");
    int orderId = input.nextInt();
    
    Order foundOrder = odata.searchOrderID(orderId);
    
    if (foundOrder != null) {
        System.out.println("\nOrder Located:");
        System.out.println(foundOrder);
    } else {
        System.out.println("\nOrder with ID " + orderId + " does not exist.");
    }
}

// ✅ المصحح للـ AVL Tree
private static void cancelAnOrder() {
    System.out.print("\nOrder ID to cancel: ");
    int orderID = input.nextInt();
    
    Order orderToCancel = odata.searchOrderID(orderID);
    
    while (orderToCancel == null) {
        System.out.print("Not found, re-enter ID: ");
        orderID = input.nextInt();
        orderToCancel = odata.searchOrderID(orderID);
    }
    
    int result = odata.cancelOrder(orderID);
    
    if (result == 1) {
        // تحديث العميل - استخدام AVL Tree
        int customerID = orderToCancel.getCustomerRef();
        if (cdata.getCustomer().find(customerID)) {
            Customer c = cdata.getCustomer().retrieve();
            c.removeOrder(orderToCancel.getoId());
            cdata.getCustomer().update(c);  // ← update
        }
        
        // تحديث المخزون - استخدام inOrdertraverseData
        if (!orderToCancel.getProducts().empty()) {
            LinkedList<Integer> productList = orderToCancel.getProducts().inOrdertraverseData();
            
            productList.findFirst();
            while (!productList.last()) {
                int productId = productList.retrieve();
                
                if (pdata.getproductsIDs() .find(productId)) {
                    Product p = pdata.getproductsIDs() .retrieve();
                    p.addStock(1);
                    pdata.getproductsIDs() .update(p);
                }
                
                productList.findNext();
            }
            
            // العنصر الأخير
            int lastProductId = productList.retrieve();
            if (pdata.getproductsIDs() .find(lastProductId)) {
                Product p = pdata.getproductsIDs() .retrieve();
                p.addStock(1);
                pdata.getproductsIDs() .update(p);
            }
        }
        
        System.out.println("\nOrder (" + orderToCancel.getoId() + ") cancelled successfully");
    }
}

// ✅ المصحح للـ AVL Tree
public static void placeNewOrder() {
    Order newOrder = new Order();
    double totalAmount = 0;
    
    System.out.print("\nOrder ID: ");
    int id = input.nextInt();
    
    while (odata.Checkorderid(id)) {
        System.out.print("ID taken, enter new one: ");
        id = input.nextInt();
    }
    newOrder.setOrderId(id);
    
    System.out.print("Customer ID: ");
    int customerId = input.nextInt();
    
    while (cdata.check(customerId)) {
        System.out.print("Customer doesn't exist, re-enter: ");
        customerId = input.nextInt();
    }
    newOrder.setcustomerRef(customerId);
    
    boolean addingProducts = true;
    
    while (addingProducts) {
        System.out.print("Product ID: ");
        int productId = input.nextInt();
        
        // التحقق من المنتج
        while (true) {
            if (!pdata.getproductsIDs() .find(productId)) {
                System.out.print("Invalid Product ID. Enter again: ");
                productId = input.nextInt();
                continue;
            }
            
            Product p = pdata.getproductsIDs() .retrieve();
            if (p.getStock() == 0) {
                System.out.println("This product is out of stock!");
                System.out.print("Enter another Product ID: ");
                productId = input.nextInt();
                continue;
            }
            
            break;
        }
        
        // إضافة المنتج
        if (pdata.getproductsIDs() .find(productId)) {
            Product p = pdata.getproductsIDs() .retrieve();
            
            if (p.getStock() > 0) {
                p.setStock(p.getStock() - 1);
                pdata.getproductsIDs() .update(p);  // ← update
                
                newOrder.addProduct(productId);
                totalAmount += p.getPrice();
                
                System.out.println("Product included");
            }
        }
        
        System.out.print("Add more? (Y/N): ");
        char response = input.next().charAt(0);
        
        if (response != 'y' && response != 'Y') {
            addingProducts = false;
        }
    }
    
    newOrder.setTotal_price(totalAmount);
    
    System.out.print("Date (dd/MM/yyyy): ");
    String dateInput = input.next();
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate orderDate = LocalDate.parse(dateInput, formatter);
        newOrder.setDate(orderDate);
    } catch (Exception e) {
        System.out.println("Invalid date format. Using today's date.");
        newOrder.setDate(LocalDate.now());
    }
    
    System.out.print("Status (pending/shipped/delivered/cancelled): ");
    String statusInput = input.next().toLowerCase();
    
    while (!statusInput.equals("pending") && !statusInput.equals("shipped") && 
           !statusInput.equals("delivered") && !statusInput.equals("cancelled")) {
        System.out.print("Invalid status. Enter (pending/shipped/delivered/cancelled): ");
        statusInput = input.next().toLowerCase();
    }
    newOrder.setStatus(statusInput);
    
    // إضافة الطلب - معاملين!
    odata.getordersData().insert(id, newOrder);
    
    // تحديث العميل
    if (!cdata.check(customerId)) {
        Customer c = cdata.getCustomer().retrieve();
        c.PlaceNew(id);
        cdata.getCustomer().update(c);  // ← update
    }
    
    System.out.println("\nOrder added successfully");
    System.out.println(newOrder);
}

// ✅ صح
private static void updateOrderStatus() {
    System.out.print("\n→ Enter Order ID: ");
    int orderId = input.nextInt();
    
    boolean updated = odata.UpdateOrder(orderId);
    
    if (updated) {
        System.out.println("\nOrder status modified successfully.");
    } else {
        System.out.println("\nUnable to modify order status.");
    }
}

// ✅ المصحح
private static void findOrdersInDateRange() {
    System.out.println("\nDate Format: dd/MM/yyyy");
    
    System.out.print("Enter start date: ");
    String startDate = input.next();
    
    System.out.print("Enter end date: ");
    String endDate = input.next();
    
    try {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("Orders from " + startDate + " to " + endDate + ":");
        System.out.println("─".repeat(50));
        
        AVLTree<Date, Order> filteredOrders = odata.BetweenTwoDates(startDate, endDate);
        
        if (filteredOrders.empty()) {
            System.out.println("No orders found in this date range.");
        }
        
        System.out.println("─".repeat(50));
        
    } catch (Exception e) {
        System.out.println("Invalid date format. Please use dd/MM/yyyy");
    }
}
// 
 public static void CustomersMenu() {
    int choice;

    
    System.out.println("ــــــــــ Customers Menu ــــــــ");
    System.out.println("1. Register new customer");
    System.out.println("2. Search for customer by ID");
    System.out.println("3. Place a new order");
    System.out.println("4. View order history");
    System.out.println("5. View customer reviews");
    System.out.println("6.Display All Customer Ordered by name");
    System.out.println("7.Display All Customer Ordered by ID");
    System.out.println("5. Return to Main Menu");
    System.out.print("Enter your choice: ");

    try {
        
        choice = input.nextInt();

        while (true) {
            if (choice == 1) {
               cdata.RegisterNewCustomer();
                break;
            } else if (choice == 2) {
               cdata. getCustomersId();
                break;
             } else if (choice == 3) {
                placeNewOrder();
                break;
            } else if (choice == 4) {
             cdata.Ohistory ();
                break;
                } else if (choice == 5) {
            allCustomereviews();
                break;
            } else if (choice == 6) {
               cdata.ListCustomerByName();
                break;
            } else if (choice == 7) {
                cdata.ListCustomerByID();
                break;
                 } else if (choice == 8) {
                System.out.println("Returning to Main menu...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
                break;
            }
        }
    } catch (java.util.InputMismatchException e) {
        System.out.println("Invalid input! Please enter a valid number.");
        input.nextLine();  // Clear the invalid input from the buffer
    }
}

public static LinkedList<Review> allCustomereviews(){
     LinkedList<Review> review = new LinkedList<Review>();
     System.out.print("Enter Customer ID: ");
    int customerId = input.nextInt();
     
     while (cdata.check(customerId)) {
        System.out.print("Customer doesn't exist, re-enter: ");
        customerId = input.nextInt();
    }
     reviews.findFirst();
     while(!reviews.last()){
         if(reviews.retrieve().getCustomer()==customerId)
             review.insert(reviews.retrieve());
         
         reviews.findNext();
     }
      if(reviews.retrieve().getCustomer()==customerId)
             review.insert(reviews.retrieve());
     
      if(review.empty())
           System.out.print("No reviews found for this customer. ");
      else{
          review.findFirst();
            while( !review.last()){
               System.out.println(review.retrieve());
                review.findNext();
          }
           System.out.print(review.retrieve());
               }
      
      return review;
 }

    
//-------------------------
  public static void main(String[] args) {
    
    loadData();
    
    int choice = 0;
    
    do {
        try {
            System.out.println("ـــــــــــــ");
            System.out.println("1. Products");
            System.out.println("2. Customers");
            System.out.println("3. Orders");
            System.out.println("4. Reviews");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");
            choice = input.nextInt();
            
            switch (choice) {
                case 1:
                    productsMenu();  
                    break;
                case 2:
                    CustomersMenu();  
                    break;
                case 3:
                    OrdersMenu();  
                    break;
                case 4:
                    ReviewsMenu();  
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;  
                default:
                    System.out.println("Bad choice, Try again");
            }
            
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number between 1-5.");
            input.nextLine();  
            choice = 0;  
        }
        
    } while (choice != 5);
}
  }

///88888
