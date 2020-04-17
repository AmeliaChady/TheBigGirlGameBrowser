public class Review {
    private int rating;
    private String summary;

    public Review(int rateIn, String commentIn){
        if (!(rateIn >= 1 && rateIn <= 5)){
            throw new IllegalArgumentException("Invalid rating");
        }
        else if(!(commentIn.length() >= 1 && commentIn.length() <= 10000)){
            throw new IllegalArgumentException("Invalid summary");
        }
        rating = rateIn;
        summary = commentIn;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
