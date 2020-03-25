import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FakerTest {
    @Test
    public void fakeTest(){
        assertEquals(4, Faker.fake(2));
        assertEquals(6, Faker.fake(3));
        assertFalse(4 == Faker.fake(7));
    }
}
