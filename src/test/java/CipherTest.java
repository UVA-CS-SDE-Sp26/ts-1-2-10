import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
import java.io.IOException;

public class CipherTest {

    private static void writeKeyFile(Path path, String line1, String line2) throws IOException {
        Files.createDirectories(path.getParent());
        Files.writeString(path, line1 + System.lineSeparator() + line2);
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
}