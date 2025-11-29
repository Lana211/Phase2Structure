
package phase2structure;



import java.io.File;
import java.util.Scanner;

/*public class productsManager {

    public static Scanner input = new Scanner(System.in);

    
    public static AVLTree<Integer, Product> productsIDs = new AVLTree<Integer, Product>();

    
    public AVLTree<Integer, Product> getproductsIDs() {
        return productsIDs;
    }

    //==============================================================
    // CONSTRUCTOR – load from file into AVL
    //==============================================================
    public productsManager(String fileName) {
        try {
            File docsfile = new File(fileName);
            Scanner reader = new Scanner(docsfile);
            String line = reader.nextLine(); // skip header

            while (reader.hasNext()) {
                line = reader.nextLine();
                String[] data = line.split(",");

                int pid = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                double price = Double.parseDouble(data[2].trim());
                int stock = Integer.parseInt(data[3].trim());

                Product product = new Product(pid, name, price, stock);
                productsIDs.insert(pid, product); // AVL INSERT
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //==================================================================
    // ADD PRODUCT
    //==================================================================
    public void addProduct() {
        System.out.println("Enter product ID:");
        int pid = input.nextInt();

        
        while (productsIDs.find(pid)) {
            System.out.println("Product ID already exists. Enter another:");
            pid = input.nextInt();
        }

        System.out.println("Enter product name:");
        String name = input.next();

        // Price validation
        double price = 0;
        boolean validPrice = false;
        while (!validPrice) {
            System.out.println("Price:");
            try {
                price = input.nextDouble();
                if (price < 0) {
                    System.out.println("Price must be >= 0");
                } else {
                    validPrice = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid price. Enter numeric value:");
                input.nextLine();
            }
        }

        // Stock validation
        int stock = 0;
        boolean validStock = false;
        while (!validStock) {
            System.out.println("Stock:");
            try {
                stock = input.nextInt();
                if (stock < 0) {
                    System.out.println("Stock must be >= 0");
                } else {
                    validStock = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid stock. Enter numeric value:");
                input.nextLine();
            }
        }

        Product p = new Product(pid, name, price, stock);
        productsIDs.insert(pid, p); 
        System.out.println("Product '" + name + "' with ID " + pid + " has been added successfully.");
    
    }

    //==================================================================
    // SEARCH BY ID
    //==================================================================
    public Product searchProducID() {
        if (productsIDs.empty()) {
            System.out.println("Empty products data.");
        } else {
            System.out.println("Enter product ID:");
            int productID = input.nextInt();

            if (productsIDs.find(productID)) {
                Product p = productsIDs.retrieve();
                System.out.println(p);
                return p;
            }
        }
        System.out.println("No such product ID.");
        return null;
    }

    //==================================================================
    // SEARCH BY NAME 
    //==================================================================
    public Product searchProducName() {
        if (productsIDs.empty()) {
            System.out.println("Empty Products data");
            return null;
        }

        System.out.println("Enter product name:");
        input.nextLine();
        String name = input.nextLine();

        LinkedList<Product> list = productsIDs.inOrdertraverseData();

        if (list.empty()) {
            System.out.println("No such product name");
            return null;
        }

        list.findFirst();
        while (!list.last()) {
            if (list.retrieve().getName().compareToIgnoreCase(name) == 0) {
                System.out.println("Product found:");
                System.out.println(list.retrieve());
                return list.retrieve();
            }
            list.findNext();
        }

        if (list.retrieve().getName().compareToIgnoreCase(name) == 0) {
            System.out.println("Product found:");
            System.out.println(list.retrieve());
            return list.retrieve();
        }

        System.out.println("No such product name");
        return null;
    }

    //==================================================================
    // REMOVE PRODUCT 
    //==================================================================
    public Product removeProduct() {
        if (productsIDs.empty()) {
            System.out.println("Empty products data.");
            return null;
        }

        System.out.println("Enter product ID:");
        int productID = input.nextInt();

        if (productsIDs.find(productID)) {
            Product p = productsIDs.retrieve();

            
            p.setStock(0);
            productsIDs.update(p);

            System.out.println("Product with ID " + productID + " has been removed successfully.");
            return p;
        }

        System.out.println("No such product ID.");
        return null;
    }

    //==================================================================
    // UPDATE PRODUCT
    //==================================================================
    public Product updateProduct() {
        if (productsIDs.empty()) {
            System.out.println("Empty products data");
            return null;
        }

        System.out.println("Enter product ID:");
        int productID = input.nextInt();

        if (!productsIDs.find(productID)) {
            System.out.println("No such product ID.");
            return null;
        }

        Product p = productsIDs.retrieve();

        System.out.println("1. Update name");
        System.out.println("2. Update price");
        System.out.println("3. Update stock");
        System.out.println("Enter your choice:");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("New name:");
                p.setName(input.next());
                break;

            case 2:
                System.out.println("New price:");
                double price = input.nextDouble();
                while (price < 0) {
                    System.out.println("Price must be >= 0:");
                    price = input.nextDouble();
                }
                p.setPrice(price);
                break;

            case 3:
                System.out.println("New stock:");
                int stock = input.nextInt();
                while (stock < 0) {
                    System.out.println("Stock must be >= 0:");
                    stock = input.nextInt();
                }
                p.setStock(stock);
                break;

            default:
                System.out.println("Bad choice.");
        }

        productsIDs.update(p);
        System.out.println("Product with ID " + productID + " has been updated successfully.");
        return p;
    }

    //==================================================================
    // OUT OF STOCK PRODUCTS
    //==================================================================
   public void Out_Of_Stock_Products() {
    System.out.println("\n========== OUT OF STOCK PRODUCTS ==========");

    if (productsIDs == null || productsIDs.empty()) {
        System.out.println("No products loaded in the system.");
        return;
    }

   
    productsIDs.inOrdertraverseOutStock();
}


 

   
    public boolean checkProductID(int PID) {
        return productsIDs.find(PID);
    }


    public Product getProducts(int PID) {
        if (productsIDs.find(PID))
            return productsIDs.retrieve();
        return null;
    }

   

    //==================================================================
    // Price range search 
    //==================================================================
    public LinkedList<Product> getPriceRange(double min, double max) {
        return productsIDs.intervalSearchprice(min, max);
    }

   
}*/


public class productsManager {

  

    public static AVLTree<Integer, Product> productsIDs = new AVLTree<Integer, Product>();

    public AVLTree<Integer, Product> getproductsIDs() {
        return productsIDs;
    }

    //==============================================================
    // CONSTRUCTOR – load from file into AVL
    //==============================================================
    public productsManager(String fileName) {
        try {
            File docsfile = new File(fileName);
            Scanner reader = new Scanner(docsfile);
            String line = reader.nextLine(); // skip header

            while (reader.hasNext()) {
                line = reader.nextLine();
                String[] data = line.split(",");

                int pid = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                double price = Double.parseDouble(data[2].trim());
                int stock = Integer.parseInt(data[3].trim());

                Product product = new Product(pid, name, price, stock);
                productsIDs.insert(pid, product); // AVL INSERT
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Main.input.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                Main.input.nextLine(); 
            }
        }
    }

   
    private double readDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Main.input.nextDouble();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
                Main.input.nextLine();
            }
        }
    }

    //==================================================================
    // ADD PRODUCT
    //==================================================================
    public void addProduct() {

        
        int pid = readInt("Enter product ID: ");

        while (productsIDs.find(pid)) {
            System.out.println("Product ID already exists. Enter another:");
            pid = readInt("Enter product ID: ");
        }

        System.out.print("Enter product name: ");
        Main.input.nextLine(); 
        String name = Main.input.nextLine();

        // Price validation
        double price;
        while (true) {
            price = readDouble("Price: ");
            if (price < 0) {
                System.out.println("Price must be >= 0");
            } else break;
        }

        // Stock validation
        int stock;
        while (true) {
            stock = readInt("Stock: ");
            if (stock < 0) {
                System.out.println("Stock must be >= 0");
            } else break;
        }

        Product p = new Product(pid, name, price, stock);
        productsIDs.insert(pid, p);
        System.out.println("Product '" + name + "' with ID " + pid + " has been added successfully.");
    }

    //==================================================================
    // SEARCH BY ID
    //==================================================================
    public Product searchProducID() {
        if (productsIDs.empty()) {
            System.out.println("Empty products data.");
            return null;
        }

        int productID = readInt("Enter product ID: ");

        if (productsIDs.find(productID)) {
            Product p = productsIDs.retrieve();
            return p;
        }

        System.out.println("No such product ID.");
        return null;
    }

    //==================================================================
    // SEARCH BY NAME 
    //==================================================================
    public Product searchProducName() {
        if (productsIDs.empty()) {
            System.out.println("Empty Products data");
            return null;
        }

        System.out.print("Enter product name: ");
        Main.input.nextLine();
        String name = Main.input.nextLine();

        LinkedList<Product> list = productsIDs.inOrdertraverseData();

        if (list.empty()) {
            System.out.println("No such product name");
            return null;
        }

        list.findFirst();
        while (!list.last()) {
            if (list.retrieve().getName().compareToIgnoreCase(name) == 0) {
                return list.retrieve();
            }
            list.findNext();
        }

        if (list.retrieve().getName().compareToIgnoreCase(name) == 0) {
            return list.retrieve();
        }

        System.out.println("No such product name");
        return null;
    }

    //==================================================================
    // REMOVE PRODUCT 
    //==================================================================
    public Product removeProduct() {
        if (productsIDs.empty()) {
            System.out.println("Empty products data.");
            return null;
        }

        int productID = readInt("Enter product ID: ");

        if (productsIDs.find(productID)) {
            Product p = productsIDs.retrieve();

            p.setStock(0);
            productsIDs.update(p);

            System.out.println("Product with ID " + productID + " has been removed successfully.");
            return p;
        }

        System.out.println("No such product ID.");
        return null;
    }

    //==================================================================
    // UPDATE PRODUCT
    //==================================================================
    public Product updateProduct() {
        if (productsIDs.empty()) {
            System.out.println("Empty products data");
            return null;
        }

        int productID = readInt("Enter product ID: ");

        if (!productsIDs.find(productID)) {
            System.out.println("No such product ID.");
            return null;
        }

        Product p = productsIDs.retrieve();

        System.out.println("1. Update name");
        System.out.println("2. Update price");
        System.out.println("3. Update stock");
        int choice = readInt("Enter your choice: ");

        switch (choice) {
            case 1:
                System.out.print("New name: ");
                Main.input.nextLine();
                p.setName(Main.input.nextLine());
                break;

            case 2:
                double price;
                while (true) {
                    price = readDouble("New price: ");
                    if (price < 0) {
                        System.out.println("Price must be >= 0:");
                    } else break;
                }
                p.setPrice(price);
                break;

            case 3:
                int stock;
                while (true) {
                    stock = readInt("New stock: ");
                    if (stock < 0) {
                        System.out.println("Stock must be >= 0:");
                    } else break;
                }
                p.setStock(stock);
                break;

            default:
                System.out.println("Bad choice.");
        }

        productsIDs.update(p);
        System.out.println("Product with ID " + productID + " has been updated successfully.");
        return p;
    }

    //==================================================================
    // OUT OF STOCK PRODUCTS
    //==================================================================
    public void Out_Of_Stock_Products() {
        System.out.println("\n========== OUT OF STOCK PRODUCTS ==========");

        if (productsIDs == null || productsIDs.empty()) {
            System.out.println("No products loaded in the system.");
            return;
        }

        productsIDs.inOrdertraverseOutStock();
    }

    public boolean checkProductID(int PID) {
        return productsIDs.find(PID);
    }

    public Product getProducts(int PID) {
        if (productsIDs.find(PID))
            return productsIDs.retrieve();
        return null;
    }

    //==================================================================
    // Price range search 
    //==================================================================
    public LinkedList<Product> getPriceRange(double min, double max) {
        return productsIDs.intervalSearchprice(min, max);
    }
}
