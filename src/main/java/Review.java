public class Review {
    private int rating;
    private String comment;

    public Review(int rateIn, String commentIn){
        if (!(rateIn >= 1 && rateIn <= 5)){
            throw new IllegalArgumentException("Invalid rating");
        }
        else if(!(commentIn.length() >= 1 && commentIn.length() <= 10000)){
            throw new IllegalArgumentException("Invalid comment");
        }
        rating = rateIn;
        comment = commentIn;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
