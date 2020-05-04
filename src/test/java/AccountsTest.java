import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AccountsTest {
    @Test
    void isEmailValidTest(){
        assertTrue(Accounts.isEmailValid( "a@b.com"));
        assertFalse( Accounts.isEmailValid(""));


        //prefix
        //dashes valid
        // Equivalence Class - Dashes in prefix not on edge
        // Border Case - Yes (1 away from right side)
        assertTrue(Accounts.isEmailValid("abc-d@mail.com"));

        //dashes invalid (not followed by letter or number)
        // Equivalence Class - Dash on right edge of prefix
        // Border Case - Yes (Single case)
        assertFalse(Accounts.isEmailValid("abc-@mail.com"));

        //new
        //Dash in middle, not border
        assertTrue(Accounts.isEmailValid("abc-de@mail.com"));

        //dash in middle, left border
        assertTrue(Accounts.isEmailValid("a-bc@mail.com"));

        //dash on left edge
        assertTrue(Accounts.isEmailValid("-ed@mail.com"));
        //end

        //underscores valid
        // Equivalence Class - Underscore in prefix not on edge
        // Border Case - No
        assertTrue(Accounts.isEmailValid("abc_def@mail.com"));

        //underscores invalid (not followed by letter or number)
        // Equivalence Class - Underscore on right edge of prefix
        // Border Case - Yes (Single case)
        assertFalse(Accounts.isEmailValid("abc_@mail.com"));

        //new
        //dash in middle, left border
        assertTrue(Accounts.isEmailValid("a_def@mail.com"));

        //dash on right edge
        assertFalse(Accounts.isEmailValid("abc_@mail.com"));

        //dash on left edge
        assertTrue(Accounts.isEmailValid("_def@mail.com"));
        //end

        //periods valid
        // Equivalence Class - period in prefix, not on edge
        // Border Case - No
        assertTrue(Accounts.isEmailValid("abc.def@mail.com"));

        //periods invalid (not followed by letter or number)
        // Equivalence Class - period on right edge of prefix
        // Border Case - Yes (Single case)
        assertFalse(Accounts.isEmailValid("abc.@mail.com"));

        //new
        //period in middle, left border
        assertTrue(Accounts.isEmailValid("a.def@mail.com"));

        //period on right edge
        assertFalse(Accounts.isEmailValid("abc.@mail.com"));

        //period on left edge
        assertTrue(Accounts.isEmailValid(".def@mail.com"));
        //end

        //special symbols invalid
        // Equivalence Class - * in prefix
        // Border Case - No
        assertFalse(Accounts.isEmailValid("abc*de@mail.com"));
        //new
        //special character right edge
        assertFalse(Accounts.isEmailValid("abc*@mail.com"));

        //special character right border
        assertFalse(Accounts.isEmailValid("abc*d@mail.com"));

        //special character left edge
        assertFalse(Accounts.isEmailValid("*de@mail.com"));

        //special character left border
        assertFalse(Accounts.isEmailValid("a*de@mail.com"));

        //special character double border
        assertFalse(Accounts.isEmailValid("c*d@mail.com"));

        //special character double edge
        assertFalse(Accounts.isEmailValid("*@mail.com"));
        //end

        // Equivalence Class - @ in prefix
        // Border Case - No
        assertFalse(Accounts.isEmailValid("abc@de@mail.com"));
        // Equivalence Class - ( in prefix
        // Border Case - No
        assertFalse(Accounts.isEmailValid("abc(de@mail.com"));
        // Equivalence Class - % in prefix
        // Border Case - No
        assertFalse(Accounts.isEmailValid("abc%de@mail.com"));
        // Equivalence Class - $ in prefix
        // Border Case - No
        assertFalse(Accounts.isEmailValid("abc$de@mail.com"));

        //new
        //special character
        assertFalse(Accounts.isEmailValid("abc!def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc@def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc#def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc$def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc%def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc^def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc&def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc(def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc)def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc+def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc=def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc'def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc;def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc,def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc[def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc]def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc{def@mail.com"));

        //special character
        assertFalse(Accounts.isEmailValid("abc}def@mail.com"));
        //end

        // Missing alot of border cases
        // Missing equivalence cases for other special characters

        //domain

        //special characters invalid
        // Equivalence Class - % in suffix
        // Border Case - No
        assertFalse(Accounts.isEmailValid("abc@mai%l.com"));
        // Equivalence Class - ^ in suffix
        // Border Case - No
        assertFalse(Accounts.isEmailValid("abc@mai^l.com"));
        // Equivalence Class - * in suffix
        // Border Case - No
        assertFalse(Accounts.isEmailValid("abc@mai*l.com"));
        // Equivalence Class - * in suffix
        // Border Case - No
        assertFalse(Accounts.isEmailValid("abc@mai*l.com")); // Duplicate btw

        //valid end of domain (two or more characters after ".")
        // Equivalence Class - address suffixes allowed
        // Border Case - ??
        assertTrue(Accounts.isEmailValid("abcdef@mail.com"));
        // Equivalence Class - address suffixes allowed
        // Border Case - ??
        assertTrue(Accounts.isEmailValid("abcdef@mail.co"));
        // Equivalence Class - address suffixes allowed
        // Border Case - ??
        assertTrue(Accounts.isEmailValid("abcdef@mail.net"));

        //invalid end of domain (less than two characters after ".")
        // Equivalence Class - address suffixes not allowed
        // Border Case - ??
        assertFalse(Accounts.isEmailValid("abcdef@mail.c"));
        // Equivalence Class - address suffixes not allowed
        // Border Case - ??
        assertFalse(Accounts.isEmailValid("abcdef@mail."));
        // Equivalence Class - address suffixes not allowed
        // Border Case - ??
        assertFalse(Accounts.isEmailValid("abcdef@mail.l"));

        //needs period after @
        // Equivalence Class - suffix contains .
        // Border Case - No
        assertTrue(Accounts.isEmailValid("abcdef@mail.com"));
        // Equivalence Class - suffix doesn't contain .
        // Border Case - No
        assertFalse(Accounts.isEmailValid("abcdef@mailcom"));

        // A lot of border cases missing
        // Not sure about equivalence classes missing
    }

    @Test
    public void isUsernameValidTest(){
        //less than minimum characters
        assertEquals(false, Accounts.isUsernameValid(""));

        //exact amount of minimum characters, 1
        assertEquals(true, Accounts.isUsernameValid("k"));

        //1 more than max characters
        assertEquals(false, Accounts.isUsernameValid("123456789012345678901234567890123"));

        //more than max characters
        assertEquals(false, Accounts.isUsernameValid("12345678901234567890123456789012345"));

        //exact amount of max characters, 128
        assertEquals(true, Accounts.isUsernameValid("12345678901234567890123456789012"));

        //valid number of characters
        assertEquals(true, Accounts.isUsernameValid("user123"));

        //has an invalid character in middle
        assertEquals(false, Accounts.isUsernameValid("abcd'ef"));

        //invalid character at the beginning
        assertEquals(false, Accounts.isUsernameValid("'abcdef"));

        //invalid character at the end
        assertEquals(false, Accounts.isUsernameValid("abcdef'"));

        //has multiple invalid characters
        assertEquals(false, Accounts.isUsernameValid("a#b'c\"d.e,f"));

        //has multiple invalid characters
        assertEquals(false, Accounts.isUsernameValid("a#b'c\"d.e,f23;"));

        //#
        assertEquals(false, Accounts.isUsernameValid("abcd#ef3"));

        //,
        assertEquals(false, Accounts.isUsernameValid("abcd,ef3"));

        //'
        assertEquals(false, Accounts.isUsernameValid("abcd'ef3"));

        //"
        assertEquals(false, Accounts.isUsernameValid("abcd\"ef3"));

        //;
        assertEquals(false, Accounts.isUsernameValid("abcd;ef3"));

        //within character limit, all valid characters
        assertEquals(true, Accounts.isUsernameValid("the_best-Username111"));
    }

    @Test
    public void isPasswordValidTest(){
        //less than minimum characters
        assertEquals(false, Accounts.isPasswordValid(""));

        //exact amount of minimum characters, 1
        assertEquals(true, Accounts.isPasswordValid("k"));

        //1 more than max characters
        assertEquals(false, Accounts.isPasswordValid("T1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklZxcvbnm1234567890qwertyuiop"));

        //more than max characters
        assertEquals(false, Accounts.isPasswordValid("Two1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklZxcvbnm1234567890qwertyuiop"));

        //exact amount of max characters, 128
        assertEquals(true, Accounts.isPasswordValid("1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklZxcvbnm1234567890qwertyuiop"));

        //has an invalid character in middle
        assertEquals(false, Accounts.isPasswordValid("abcd'ef3"));

        //invalid character at the beginning
        assertEquals(false, Accounts.isPasswordValid("'abcdef56"));

        //invalid character at the end
        assertEquals(false, Accounts.isPasswordValid("abcd4t4ef'"));

        //has multiple invalid characters
        assertEquals(false, Accounts.isPasswordValid("a#b'c\"d.e,f23;"));

        //#
        assertEquals(false, Accounts.isPasswordValid("abcd#ef3"));

        //,
        assertEquals(false, Accounts.isPasswordValid("abcd,ef3"));

        //'
        assertEquals(false, Accounts.isPasswordValid("abcd'ef3"));

        //"
        assertEquals(false, Accounts.isPasswordValid("abcd\"ef3"));

        //;
        assertEquals(false, Accounts.isPasswordValid("abcd;ef3"));

        //within character limit, all valid characters
        assertEquals(true, Accounts.isPasswordValid("the_best-Username111"));
    }

    @Test
    public void isCharacterValidTest(){
        //# invalid
        assertEquals(false, Accounts.isCharacterValid('#'));

        //, invalid
        assertEquals(false, Accounts.isCharacterValid(','));

        //' invalid
        assertEquals(false, Accounts.isCharacterValid('\''));

        //" invalid
        assertEquals(false, Accounts.isCharacterValid('"'));

        //; invalid
        assertEquals(false, Accounts.isCharacterValid(';'));

        //lowercase letter valid
        assertEquals(true, Accounts.isCharacterValid('a'));

        //uppercase letter valid
        assertEquals(true, Accounts.isCharacterValid('K'));

        //number valid
        assertEquals(true, Accounts.isCharacterValid('9'));
    }
}
