import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cipher {

    private static String key1;
    private static String key2;

    public static void loadKey() throws FileNotFoundException {
        File file = new File("ciphers/key.txt");
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            key1 = scanner.nextLine();
        }
        if (scanner.hasNextLine()) {
            key2 = scanner.nextLine();
        }
        scanner.close();
    }

    public static String decrypt(String text) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);

            int index = key2.indexOf(current);

            if (index != -1) {
                output.append(key1.charAt(index));
            } else {
                output.append(current);
            }
        }

        return output.toString();

    }
}
