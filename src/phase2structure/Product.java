
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phase2structure;






public class Product {
    int productId;
    String name;
    double price;
    int stock;
     AVLTree <Integer,Integer> reviews = new AVLTree <Integer,Integer> ();

    public Product() {
        this.productId = 0;
        this.name = "";
        this.price = 0;
        this.stock = 0;
    }

    public Product(int pid, String n, double p, int s) {
        this.productId = pid;
        this.name = n;
        this.price = p;
        this.stock = s;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

   
    public void addStock(int stock) {
        if (stock > 0) {
            this.stock = this.stock + stock;
        }
    }

   
    public void removeStock(int stock) {
        if (stock > 0) {
            this.stock = this.stock - stock;
            if (this.stock < 0)
                this.stock = 0;
        }
    }

    
      public void addReview(Integer R) {
        if (R == null)
            return;

        if (R >= 1 && R <= 5)
            reviews.insert(R,R);
    }

    
     public boolean removeReview( Integer R)
    {
        return reviews.removeKey(R);
    }

    public  AVLTree<Integer,Integer> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", name=" + name + ", price=" + price + ", stock=" + stock + ", reviews=" + reviews + '}';
    }

    
}


