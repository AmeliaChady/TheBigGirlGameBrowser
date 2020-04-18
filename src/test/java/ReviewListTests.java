import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewListTests {

    @Test
    public void constructorTest(){
        ReviewList robsReviews = new ReviewList("Robert");
        assertEquals("Robert", robsReviews.getAuthor());
        assertEquals(0,robsReviews.getReviews().size());
    }

    @Test
    public void addReviewTest(){
        ReviewList robsReviews = new ReviewList("Robert");
        assertEquals(0,robsReviews.getReviews().size());
        String fakeID = "P90X";
        robsReviews.addReview(fakeID);
        assertEquals(1,robsReviews.getReviews().size());

        assertThrows(IllegalArgumentException.class, ()->robsReviews.addReview(fakeID));
    }

    @Test
    public void removeReviewTest(){
        ReviewList robsReviews = new ReviewList("Robert");
        assertEquals(0,robsReviews.getReviews().size());
        assertThrows(IllegalArgumentException.class, ()-> robsReviews.removeReview("ID01"));
        assertEquals(0,robsReviews.getReviews().size());

        String fakeID = "P90X";
        robsReviews.addReview(fakeID);
        assertEquals(1,robsReviews.getReviews().size());
        assertThrows(IllegalArgumentException.class, ()-> robsReviews.removeReview("ID01"));
        assertEquals(1,robsReviews.getReviews().size());

        robsReviews.removeReview("P90X");
        assertEquals(0,robsReviews.getReviews().size());
    }
}
