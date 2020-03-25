import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FakerTest {
    @Test
    public void fakeTest(){
        assertEquals(4, Faker.fake(2));
    }

}
