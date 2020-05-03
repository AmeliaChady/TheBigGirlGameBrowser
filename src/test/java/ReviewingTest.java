import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewingTest {
    public static final String DB_PATH = "src/databases/clearable.db";

    @Test
    public void gameNewReview(){
        Game g = new Game("a", "b", "c");

        // Testing can add normal
        Review r = new Review(5, "sucky game", "jerky jerk");

        g.addReview(r);
        assertEquals(1, g.getReviews().size());
        Review got = g.getReviews().get(0);
        assertEquals(5, got.getRating());
        assertEquals("sucky game", got.getSummary());
        assertEquals("jerky jerk" , got.getAuthor());

        // Testing adding same (copy or not) doesn't add
        Review rCopy = new Review(5, "sucky game", "jerky jerk");
        g.addReview(r);
        assertEquals(1, g.getReviews().size());
        got = g.getReviews().get(0);
        assertEquals(5, got.getRating());
        assertEquals("sucky game", got.getSummary());
        assertEquals("jerky jerk" , got.getAuthor());

        g.addReview(rCopy);
        assertEquals(1, g.getReviews().size());
        got = g.getReviews().get(0);
        assertEquals(5, got.getRating());
        assertEquals("sucky game", got.getSummary());
        assertEquals("jerky jerk" , got.getAuthor());

        // Update game
        Review rUpdate = new Review(5, "okay game", "jerky jerk");
        g.addReview(rUpdate);
        got = g.getReviews().get(0);
        assertEquals(5, got.getRating());
        assertEquals("okay game", got.getSummary());
        assertEquals("jerky jerk" , got.getAuthor());


        // author can't be null (not a review constraint?)
        Review nullauthor= new Review(5, "10", null);
        assertThrows(NullPointerException.class, () -> g.addReview(nullauthor));

        // Testing adding null throws error
        assertThrows(NullPointerException.class, () -> g.addReview(null));
        assertEquals(1, g.getReviews().size());
    }

    /*
    @Test
    public void gameBrowserGameNewReview() throws DataSourceException, IOException {
        SQLiteSource.RunSQL(DB_PATH, "src/scripts/DDL.sql");
        SQLiteSource.RunSQL(DB_PATH, "src/scripts/reviewing_gbgnr.sql");


        GameBrowser gb = new GameBrowser(DB_PATH);
        // Pass Good Args In
        gb.gameNewReview("apple", 5, "frank is best character", "frank");
        assertEquals(1, gb.loadGame("apple").getReviews().size());

        // Pass Bogus Args In (throws exception)
        assertThrows(IllegalArgumentException.class, () -> gb.gameNewReview("dontexist", 5, "a", "bob"));
        assertThrows(IllegalArgumentException.class, () -> gb.gameNewReview("apple", 0, "a", "bob2"));

        // Pass Null Args In (throws exception)
        assertThrows(IllegalArgumentException.class, () -> gb.gameNewReview(null, 5, "a", "bob"));
        assertThrows(IllegalArgumentException.class, () -> gb.gameNewReview("apple", 0, null, "bob2"));

    }
    */

}
