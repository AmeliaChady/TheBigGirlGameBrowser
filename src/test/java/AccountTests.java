import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTests {

    @Test
    public void AccountConstructorTest(){
        Account acc1 = new Account("berthaIsMyDog", "welovebertha@gmail.com", "berthaberthaberthabertha");
        assertEquals("berthaIsMyDog", acc1.getUsername());
        assertEquals("welovebertha@gmail.com", acc1.getEmail());
        assertEquals("berthaberthaberthabertha", acc1.getPassword());

        Account acc2 = new Account("realHousewivesFan", "realHousewivesFan@yahoo.com", "EleganceFlairAndSavoirfaire");
        assertEquals("realHousewivesFan", acc2.getUsername());
        assertEquals("realHousewivesFan@yahoo.com", acc2.getEmail());
        assertEquals("EleganceFlairAndSavoirfaire", acc2.getPassword());

        //invalid email
        assertThrows(IllegalArgumentException.class, ()-> new Account("uhOh", "not an email", "blooooop"));
    }
}
