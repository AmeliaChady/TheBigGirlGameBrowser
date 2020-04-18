import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void gameConstructorTest(){
        Game game = new Game();

        assertEquals("testGame", game.getTitle());
        assertEquals("This is a test to create a new game object", game.getDescription());
        assertNotNull(game.getDevelopers());

        Game newGame = new Game("daBears", "Sheila");

        assertEquals("daBears", newGame.getTitle());
        assertEquals("No Description Given", newGame.getDescription());
        assertNotNull(newGame.getDevelopers());

        Game describedGame = new Game("badTitle", "Bad Description", "Kelly");
        assertEquals("badTitle", describedGame.getTitle());
        assertEquals("Bad Description", describedGame.getDescription());
        assertNotNull(describedGame.getDevelopers());
    }

    @Test
    public void changeDescriptionTest(){

        Game game = new Game("daBears", "Wendell");

        assertEquals("No Description Given", game.getDescription());

        String aDescription = "Hey Fans! Checkout this awesome 'New Game' that I just released on the Big Girls Game Browser!!!";

        game.changeDescription(aDescription);

        assertEquals(aDescription, game.getDescription());
    }

    @Test
    public void changeTitleTest(){

        Game game = new Game("badTitle", "Bad Description", "Winston");

        assertEquals("badTitle", game.getTitle());

        game.changeTitle("goodTitle");

        assertEquals("goodTitle", game.getTitle());
    }

    @Test
    public void enumTest(){

        Game game = new Game("title", "description", "Jimmy", Status.ACCEPTED);

        assertEquals(Status.ACCEPTED, game.getStatus()); //checks that the fullfull constructor works

        game.changeStatus(Status.PENDING);
        assertEquals(Status.PENDING, game.getStatus()); //checks assignment

        game.changeStatus(Status.REJECTED);
        assertEquals(Status.REJECTED, game.getStatus());//checks assignment

        game = new Game("title", "description", "Carter");

        assertEquals(Status.PENDING, game.getStatus()); //Checks other constructors default assignment

        game.changeStatus(Status.LIMBO);
        assertEquals(Status.LIMBO, game.getStatus());//checks assignment
    }

    @Test
    public void addDeveloperTest(){

        List<String> developers = new ArrayList<>();
        developers.add("Fluffy");
        developers.add("Nut");
        developers.add("Face");

        Game multiDevGame = new Game("FlufferNutter",
                "The adventures of a cat and their Penutbutter", developers, Status.ACCEPTED);
        assertEquals(developers, multiDevGame.getDevelopers());

        String thatGuy = "Pual";

        multiDevGame.addDeveloper(thatGuy);
        developers.add(thatGuy);

        assertEquals(developers, multiDevGame.getDevelopers());
    }

    @Test
    public void gameHoldsReviewsTest(){
        List<String> reviews = new ArrayList<>();

        Review r1 = new Review(1, "Bad Game!", "Kerry", "Animal Crossing New Horizons");
        Review r2 = new Review(2, "Boring Game", "kb", "acnh");
        Review r3 = new Review(3, "It's a Game...", "kab", "Animal Crossing");
        Review r4 = new Review(4, "Yeah I liked this game", "Kerry Anne", "Animal Horizons");
        Review r5 = new Review(5,"WOW Best Game ever", "kerby", "Crossing Horizons");

        reviews.add(r1.getID());
        reviews.add(r2.getID());
        reviews.add(r3.getID());
        reviews.add(r4.getID());
        reviews.add(r5.getID());

        Game game = new Game("testGame", "a game for Tests", reviews, "Robert");

        assertEquals(reviews, game.getReviews());
        assertEquals("ID01", game.getReviews().get(0));

        Review r6 = new Review(3, "I kinda liked this but I didn't either", "coolGuy", "coolGame");
        game.addReview(r6.getID());

        assertEquals(6, game.getReviews().size());
    }
}
