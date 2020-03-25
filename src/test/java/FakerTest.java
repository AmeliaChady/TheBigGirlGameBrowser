import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FakerTest {
    @Test
    public void fakeTest(){
        assertEquals(4, Faker.fake(2));
        assertEquals(6, Faker.fake(3));
        assertFalse(4 == Faker.fake(7));
    }
}
