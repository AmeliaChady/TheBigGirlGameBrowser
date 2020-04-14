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

    @Test
    void isEmailValidTest(){
        assertTrue(Account.isEmailValid( "a@b.com"));
        assertFalse( Account.isEmailValid(""));


        //prefix
        //dashes valid
        // Equivalence Class - Dashes in prefix not on edge
        // Border Case - Yes (1 away from right side)
        assertTrue(Account.isEmailValid("abc-d@mail.com"));

        //dashes invalid (not followed by letter or number)
        // Equivalence Class - Dash on right edge of prefix
        // Border Case - Yes (Single case)
        assertFalse(Account.isEmailValid("abc-@mail.com"));

        //new
        //Dash in middle, not border
        assertTrue(Account.isEmailValid("abc-de@mail.com"));

        //dash in middle, left border
        assertTrue(Account.isEmailValid("a-bc@mail.com"));

        //dash on left edge
        assertTrue(Account.isEmailValid("-ed@mail.com"));
        //end

        //underscores valid
        // Equivalence Class - Underscore in prefix not on edge
        // Border Case - No
        assertTrue(Account.isEmailValid("abc_def@mail.com"));

        //underscores invalid (not followed by letter or number)
        // Equivalence Class - Underscore on right edge of prefix
        // Border Case - Yes (Single case)
        assertFalse(Account.isEmailValid("abc_@mail.com"));

        //new
        //dash in middle, left border
        assertTrue(Account.isEmailValid("a_def@mail.com"));

        //dash on right edge
        assertFalse(Account.isEmailValid("abc_@mail.com"));

        //dash on left edge
        assertTrue(Account.isEmailValid("_def@mail.com"));
        //end

        //periods valid
        // Equivalence Class - period in prefix, not on edge
        // Border Case - No
        assertTrue(Account.isEmailValid("abc.def@mail.com"));

        //periods invalid (not followed by letter or number)
        // Equivalence Class - period on right edge of prefix
        // Border Case - Yes (Single case)
        assertFalse(Account.isEmailValid("abc.@mail.com"));

        //new
        //period in middle, left border
        assertTrue(Account.isEmailValid("a.def@mail.com"));

        //period on right edge
        assertFalse(Account.isEmailValid("abc.@mail.com"));

        //period on left edge
        assertTrue(Account.isEmailValid(".def@mail.com"));
        //end

        //special symbols invalid
        // Equivalence Class - * in prefix
        // Border Case - No
        assertFalse(Account.isEmailValid("abc*de@mail.com"));
        //new
        //special character right edge
        assertFalse(Account.isEmailValid("abc*@mail.com"));

        //special character right border
        assertFalse(Account.isEmailValid("abc*d@mail.com"));

        //special character left edge
        assertFalse(Account.isEmailValid("*de@mail.com"));

        //special character left border
        assertFalse(Account.isEmailValid("a*de@mail.com"));

        //special character double border
        assertFalse(Account.isEmailValid("c*d@mail.com"));

        //special character double edge
        assertFalse(Account.isEmailValid("*@mail.com"));
        //end

        // Equivalence Class - @ in prefix
        // Border Case - No
        assertFalse(Account.isEmailValid("abc@de@mail.com"));
        // Equivalence Class - ( in prefix
        // Border Case - No
        assertFalse(Account.isEmailValid("abc(de@mail.com"));
        // Equivalence Class - % in prefix
        // Border Case - No
        assertFalse(Account.isEmailValid("abc%de@mail.com"));
        // Equivalence Class - $ in prefix
        // Border Case - No
        assertFalse(Account.isEmailValid("abc$de@mail.com"));

        //new
        //special character
        assertFalse(Account.isEmailValid("abc!def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc@def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc#def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc$def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc%def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc^def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc&def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc(def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc)def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc+def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc=def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc'def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc;def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc,def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc[def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc]def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc{def@mail.com"));

        //special character
        assertFalse(Account.isEmailValid("abc}def@mail.com"));
        //end

        // Missing alot of border cases
        // Missing equivalence cases for other special characters

        //domain

        //special characters invalid
        // Equivalence Class - % in suffix
        // Border Case - No
        assertFalse(Account.isEmailValid("abc@mai%l.com"));
        // Equivalence Class - ^ in suffix
        // Border Case - No
        assertFalse(Account.isEmailValid("abc@mai^l.com"));
        // Equivalence Class - * in suffix
        // Border Case - No
        assertFalse(Account.isEmailValid("abc@mai*l.com"));
        // Equivalence Class - * in suffix
        // Border Case - No
        assertFalse(Account.isEmailValid("abc@mai*l.com")); // Duplicate btw

        //valid end of domain (two or more characters after ".")
        // Equivalence Class - address suffixes allowed
        // Border Case - ??
        assertTrue(Account.isEmailValid("abcdef@mail.com"));
        // Equivalence Class - address suffixes allowed
        // Border Case - ??
        assertTrue(Account.isEmailValid("abcdef@mail.co"));
        // Equivalence Class - address suffixes allowed
        // Border Case - ??
        assertTrue(Account.isEmailValid("abcdef@mail.net"));

        //invalid end of domain (less than two characters after ".")
        // Equivalence Class - address suffixes not allowed
        // Border Case - ??
        assertFalse(Account.isEmailValid("abcdef@mail.c"));
        // Equivalence Class - address suffixes not allowed
        // Border Case - ??
        assertFalse(Account.isEmailValid("abcdef@mail."));
        // Equivalence Class - address suffixes not allowed
        // Border Case - ??
        assertFalse(Account.isEmailValid("abcdef@mail.l"));

        //needs period after @
        // Equivalence Class - suffix contains .
        // Border Case - No
        assertTrue(Account.isEmailValid("abcdef@mail.com"));
        // Equivalence Class - suffix doesn't contain .
        // Border Case - No
        assertFalse(Account.isEmailValid("abcdef@mailcom"));

        // A lot of border cases missing
        // Not sure about equivalence classes missing
    }

    @Test
    public void isUsernameValidTest(){
        //less than minimum characters
        assertEquals(false, Account.isUsernameValid(""));

        //exact amount of minimum characters, 1
        assertEquals(true, Account.isUsernameValid("k"));

        //1 more than max characters
        assertEquals(false, Account.isUsernameValid("123456789012345678901234567890123"));

        //more than max characters
        assertEquals(false, Account.isUsernameValid("12345678901234567890123456789012345"));

        //exact amount of max characters, 128
        assertEquals(true, Account.isUsernameValid("12345678901234567890123456789012"));

        //valid number of characters
        assertEquals(true, Account.isUsernameValid("user123"));

        //has an invalid character in middle
        assertEquals(false, Account.isUsernameValid("abcd'ef"));

        //invalid character at the beginning
        assertEquals(false, Account.isUsernameValid("'abcdef"));

        //invalid character at the end
        assertEquals(false, Account.isUsernameValid("abcdef'"));

        //has multiple invalid characters
        assertEquals(false, Account.isUsernameValid("a#b'c\"d.e,f"));

        //has multiple invalid characters
        assertEquals(false, Account.isUsernameValid("a#b'c\"d.e,f23;"));

        //#
        assertEquals(false, Account.isUsernameValid("abcd#ef3"));

        //,
        assertEquals(false, Account.isUsernameValid("abcd,ef3"));

        //'
        assertEquals(false, Account.isUsernameValid("abcd'ef3"));

        //"
        assertEquals(false, Account.isUsernameValid("abcd\"ef3"));

        //;
        assertEquals(false, Account.isUsernameValid("abcd;ef3"));

        //within character limit, all valid characters
        assertEquals(true, Account.isUsernameValid("the_best-Username111"));
    }

    @Test
    public void isPasswordValidTest(){
        //less than minimum characters
        assertEquals(false, Account.isPasswordValid(""));

        //exact amount of minimum characters, 1
        assertEquals(true, Account.isPasswordValid("k"));

        //1 more than max characters
        assertEquals(false, Account.isPasswordValid("T1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklZxcvbnm1234567890qwertyuiop"));

        //more than max characters
        assertEquals(false, Account.isPasswordValid("Two1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklZxcvbnm1234567890qwertyuiop"));

        //exact amount of max characters, 128
        assertEquals(true, Account.isPasswordValid("1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklZxcvbnm1234567890qwertyuiop"));

        //has an invalid character in middle
        assertEquals(false, Account.isPasswordValid("abcd'ef3"));

        //invalid character at the beginning
        assertEquals(false, Account.isPasswordValid("'abcdef56"));

        //invalid character at the end
        assertEquals(false, Account.isPasswordValid("abcd4t4ef'"));

        //has multiple invalid characters
        assertEquals(false, Account.isPasswordValid("a#b'c\"d.e,f23;"));

        //#
        assertEquals(false, Account.isPasswordValid("abcd#ef3"));

        //,
        assertEquals(false, Account.isPasswordValid("abcd,ef3"));

        //'
        assertEquals(false, Account.isPasswordValid("abcd'ef3"));

        //"
        assertEquals(false, Account.isPasswordValid("abcd\"ef3"));

        //;
        assertEquals(false, Account.isPasswordValid("abcd;ef3"));

        //within character limit, all valid characters
        assertEquals(true, Account.isPasswordValid("the_best-Username111"));
    }
}
