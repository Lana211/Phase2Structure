package phase2structure;

import java.io.File;
import java.util.Scanner;

public class reviewsManager {


    public static LinkedList<Review> reviews = new LinkedList<Review>();

   
    //==============================================================
    public LinkedList<Review> getAllReviews() {
        return reviews;
    }

   
    //==============================================================
    public reviewsManager(String fileName, AVLTree<Integer, Product> products) {
        try {
            File file = new File(fileName);
            Scanner read = new Scanner(file);
            String line = read.nextLine(); 

            while (read.hasNext()) {
                line = read.nextLine();
                String[] data = line.split(",");

                int rID = Integer.parseInt(data[0].trim());
                int pID = Integer.parseInt(data[1].trim());
                int cID = Integer.parseInt(data[2].trim());
                int rating = Integer.parseInt(data[3].trim());
                String comment = data[4];

                Review newReview = new Review(rID, pID, cID, rating, comment);
                reviews.insert(newReview);
            }
            read.close();

        } catch (Exception ex) {
            System.out.println("Error loading reviews: " + ex.getMessage());
        }
    }

    
    //==============================================================
    public Review addNewReview(int customerNum, int productNum) {

        
        int newID = Main.readInt("Enter a new Review ID: ");

        while (checkReviewID(newID)) {
            System.out.println("This ID already exists. Try another one:");
            newID = Main.readInt("Enter a new Review ID: ");
        }

        int rate;
        while (true) {
            rate = Main.readInt("Rate the product (1 to 5): ");
            if (rate >= 1 && rate <= 5) break;
            System.out.println("Rating must be between 1 and 5.");
        }

        System.out.println("Write your comment:");
        Main.input.nextLine();           
        String comment = Main.input.nextLine();

        Review r = new Review(newID, productNum, customerNum, rate, comment);

        reviews.findFirst();
        reviews.insert(r);

        System.out.println("Review added successfully!");
        return r;
    }

    
    //==============================================================
    public void updateReview() {
        if (reviews.empty()) {
            System.out.println("No reviews available to edit.");
            return;
        }

        int targetID = Main.readInt("Enter the Review ID you want to modify: ");

        while (!checkReviewID(targetID)) {
            System.out.println("Review not found! Enter a valid ID:");
            targetID = Main.readInt("Enter the Review ID you want to modify: ");
        }

        
        reviews.findFirst();
        while (!reviews.last()) {
            if (reviews.retrieve().getReviewId() == targetID) break;
            reviews.findNext();
        }

        if (reviews.retrieve().getReviewId() == targetID) {
            Review chosen = reviews.retrieve();
            reviews.remove();

            System.out.println("Choose what you want to update:");
            System.out.println("1. Change Rating");
            System.out.println("2. Edit Comment");
            int option = Main.readInt("Enter your choice: ");

            switch (option) {
                case 1: {
                    int newRating;
                    while (true) {
                        newRating = Main.readInt("Enter a new rating (1 to 5): ");
                        if (newRating >= 1 && newRating <= 5) break;
                        System.out.println("Rating must be between 1 and 5.");
                    }
                    chosen.setRating(newRating);
                }
                break;

                case 2: {
                    System.out.println("Enter your new comment:");
                    Main.input.nextLine();   
                    String newComment = Main.input.nextLine();
                    chosen.setComment(newComment);
                }
                break;

                default:
                    System.out.println("Invalid choice! No changes applied.");
            }

            reviews.insert(chosen);
            System.out.println("Review (" + chosen.getReviewId() + ") updated successfully:");
            System.out.println(chosen);
        }
    }

   
    //==============================================================
    public boolean checkReviewID(int id) {
        if (!reviews.empty()) {
            reviews.findFirst();
            for (int i = 0; i < reviews.size(); i++) {
                if (reviews.retrieve().getReviewId() == id)
                    return true;
                reviews.findNext();
            }
        }
        return false;
    }
}
