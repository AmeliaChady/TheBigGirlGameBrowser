import java.util.ArrayList;
import java.util.List;

public class ReviewList {
    private String author;
    private List<String> reviews;

    public ReviewList(String author){
        this.author = author;
        this.reviews = new ArrayList<>();
    }

    /**
     * After a user has written a review, ID should be added to authors list
     * @param ID
     */
    public void addReview(String ID){
        reviews.add(ID);
    }

    public void removeReview(String ID) {
        if (reviews.size()==0){
            throw new IllegalArgumentException("No Reviews to remove");
        }
        else{
            boolean success = reviews.remove(ID);
            if (success == false){
                throw new IllegalArgumentException("Review not found");
            }
        }
    }

    /**
     * Get Authors name
     * @return author
     */
    public String getAuthor(){
        return author;
    }

    /**
     * Get's the id's of all of authors reviews
     * @return list of reviews
     */
    public List<String> getReviews(){
        return reviews;
    }
}
