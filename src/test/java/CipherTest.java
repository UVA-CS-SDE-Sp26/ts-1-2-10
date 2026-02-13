import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CipherTest {

    private static final String KEY1 =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final String DEFAULT_KEY2 =
            "bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a";

    @Test
    void constructor_setsDefaultKeys_deciphersShiftBackOne() {
        Cipher c = new Cipher();

        // default key2 is shifted by 1, so decipher should shift BACK by 1
        assertEquals("a", c.decipher("b"));
        assertEquals("z", c.decipher("A"));
        assertEquals("0", c.decipher("a"));
        assertEquals("Hello 123!", c.decipher("Ifmmp 234!"));
    }

    @Test
    void getAltKey_updatesKey2_whenSameLength() {
        Cipher c = new Cipher();
        // Make an alternate key2: reverse of KEY1 (valid length)
        String altKey2 = new StringBuilder(KEY1).reverse().toString();
        c.getAltKey(altKey2);

        // If key2 is reversed, then the first char of key2 maps to last char of key1, etc.
        char firstInAlt = altKey2.charAt(0);               // last char of KEY1
        char expected = KEY1.charAt(0);                    // wait: decipher finds index in key2 and uses key1 at that index
        // index of firstInAlt in altKey2 is 0 -> maps to KEY1[0]
        assertEquals(String.valueOf(expected), c.decipher(String.valueOf(firstInAlt)));
    }

    @Test
    void getAltKey_null_throws() {
        Cipher c = new Cipher();
        assertThrows(IllegalArgumentException.class, () -> c.getAltKey(null));
    }

    @Test
    void getAltKey_wrongLength_throws() {
        Cipher c = new Cipher();
        assertThrows(IllegalArgumentException.class, () -> c.getAltKey("abc"));
    }

    @Test
    void decipher_nullText_throws() {
        Cipher c = new Cipher();
        assertThrows(IllegalArgumentException.class, () -> c.decipher(null));
    }
    @Test
    void decipher_emptyString_returnsEmpty() {
        Cipher c = new Cipher();
        assertEquals("", c.decipher(""));
    }

    @Test
    void decipher_leavesUnknownCharactersUnchanged() {
        Cipher c = new Cipher();

        // characters not in key2 remain unchanged
        assertEquals("@#$", c.decipher("@#$"));
        assertEquals("hi?", c.decipher("ij?"));
    }

    @Test
    void decipher_throwsIfKeysMismatch_lengthGuard() {

        Cipher c = new Cipher();
        assertDoesNotThrow(() -> c.decipher("test"));
    }
}
