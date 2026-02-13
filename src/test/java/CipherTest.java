import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
import java.io.IOException;

public class CipherTest {

    private static void writeKeyFile(Path path, String line1, String line2) throws IOException {
        Files.createDirectories(path.getParent());
        Files.writeString(path, line1 + System.lineSeparator() + line2);
    }
    private static void writeOneLineKeyFile(Path path, String line1) throws IOException {
        Files.createDirectories(path.getParent());
        Files.writeString(path, line1 + System.lineSeparator());
    }

    private static void writeEmptyFile(Path path) throws IOException {
        Files.createDirectories(path.getParent());
        Files.writeString(path, "");
    }

    @Test
    void loadKey_defaultPath() throws Exception {
        Path defaultPath = Paths.get("ciphers", "key.txt");
        writeKeyFile(defaultPath, "abcdefghij", "chajfncksi");
        Cipher cipher = new Cipher();
        cipher.loadKey();
        assertEquals("abc", cipher.decipher("xyz"));
    }

    @Test
    void loadKey_usesDefaultPath() throws Exception {
        Cipher cipher = new Cipher();
        cipher.loadKey();
        assertNotNull(cipher.decipher(""));
    }

    @Test
    void getAltKey_loadsAlternateKey() throws Exception {
        Path altPath = Paths.get("build", "altkey.txt");
        writeKeyFile(altPath, "123", "789");
        Cipher cipher = new Cipher();
        cipher.getAltKey(altPath.toString());
        assertEquals("123", cipher.decipher("789"));
    }

    @Test
    void decrypt_Success() throws Exception {
        Path keyPath = Paths.get("build", "testkey.txt");
        writeKeyFile(keyPath, "abc", "xyz");

        Cipher cipher = new Cipher();
        cipher.getAltKey(keyPath.toString());

        String result = cipher.decipher("xyz");
        assertEquals("abc", result);
    }

    @Test
    void decipher_emptyInput_returnsEmpty() throws Exception {
        Path keyPath = Paths.get("build", "k_empty_input.txt");
        writeKeyFile(keyPath, "abc", "xyz");

        Cipher cipher = new Cipher();
        cipher.getAltKey(keyPath.toString());

        assertEquals("", cipher.decipher(""));
    }

    @Test
    void decipher_charsNotInKey2_staySame() throws Exception {
        Path keyPath = Paths.get("build", "k_unknown_chars.txt");
        writeKeyFile(keyPath, "abc", "xyz");

        Cipher cipher = new Cipher();
        cipher.getAltKey(keyPath.toString());

        // '!' and ' ' are not in key2 ("xyz"), so they should remain unchanged
        assertEquals("a! b", cipher.decipher("x! y"));
    }

    @Test
    void getAltKey_emptyFile_throwsFileNotFoundException() throws Exception {
        Path keyPath = Paths.get("build", "k_empty.txt");
        writeEmptyFile(keyPath);

        Cipher cipher = new Cipher();
        assertThrows(java.io.FileNotFoundException.class, () -> cipher.getAltKey(keyPath.toString()));
    }
    void getAltKey_oneLineFile_usesFallbackMapping() throws Exception {
        Path keyPath = Paths.get("build", "k_one_line.txt");
        // One line means: your code sets key2 = that line,
        // and key1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
        writeOneLineKeyFile(keyPath, "xyz");

        Cipher cipher = new Cipher();
        cipher.getAltKey(keyPath.toString());

        // index of 'x' in key2 ("xyz") is 0, so maps to key1.charAt(0) which is 'a'
        assertEquals("abc", cipher.decipher("xyz"));
    }

    @Test
    void decipher_keyLengthMismatch_throwsIllegalArgumentException() throws Exception {
        Path keyPath = Paths.get("build", "k_mismatch.txt");
        writeKeyFile(keyPath, "abcd", "xy"); // different lengths

        Cipher cipher = new Cipher();
        cipher.getAltKey(keyPath.toString());

        assertThrows(IllegalArgumentException.class, () -> cipher.decipher("x"));
    }

    @Test
    void decipher_withoutLoadingKeys_throwsSomeException() {
        Cipher cipher = new Cipher();

        // Ideally: IllegalStateException if you add a guard in Cipher.decipher()
        // Right now: it will likely throw NullPointerException because key1/key2 are null.
        assertThrows(RuntimeException.class, () -> cipher.decipher("abc"));
    }


}
