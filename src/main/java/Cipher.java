import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cipher {

    private String key1;
    private String key2;

    public Cipher() {
        key1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        key2 = "bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a";
    }

    public void getAltKey(String altKey) throws FileNotFoundException {
        key2 = altKey;
    }

    public String decipher(String text) {
        StringBuilder output = new StringBuilder();

        if (key1.length() != key2.length()) {
            throw new IllegalArgumentException("Key lengths do not match. Cannot decipher.");
        }
        for (int i = 0; i < text.length(); i++) {
            char curr = text.charAt(i);

            int index = key2.indexOf(curr);

            if (index != -1) {
                output.append(key1.charAt(index));
            } else {
                output.append(curr);
            }
        }
        return output.toString();

    }
}
