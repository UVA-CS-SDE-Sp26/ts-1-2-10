import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cipher {

    private String key1;
    private String key2;

    public void loadKey() throws FileNotFoundException {
        getAltKey("ciphers/key.txt");
    }

    public void getAltKey(String altKey) throws FileNotFoundException {
        File file = new File(altKey);
        Scanner scan = new Scanner(file);
        if (!scan.hasNextLine()) {
            throw new FileNotFoundException("Key file is empty");
        }
        if (scan.hasNextLine()) {
            key1 = scan.nextLine();
        }
        if (scan.hasNextLine()) {
            key2 = scan.nextLine();
        }
        else{
            key2 = key1;
            key1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        }
        scan.close();
    }

    public String decipher(String text) {
        StringBuilder output = new StringBuilder();

        if (key1.length() != key2.length()) {
            throw new IllegalArgumentException("Key lengths do not match");
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
