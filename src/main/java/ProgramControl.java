import java.io.FileNotFoundException;

public class ProgramControl {
    private final FileHandler fileHandler;
    private final Cipher cipher;

    public ProgramControl() {
        this(new FileHandler(), new Cipher());
    }

    ProgramControl(FileHandler fileHandler, Cipher cipher) {
        this.fileHandler = fileHandler;
        this.cipher = cipher;
    }

    public String[] fetchFile() {
        return fileHandler.readFile();
    }

    public String fetchFile(String fileName) throws FileNotFoundException {
        String text;
        try{
            int number = Integer.parseInt(fileName);
            text = fileHandler.readFile(number);
            String[] files = fetchFile();
            String file = files[number - 1];
            if(file.endsWith("txt"))
                return text;

            cipher.loadKey();
            return cipher.decipher(text);
        }
        catch (NumberFormatException e) {
            text = fileHandler.readFile(fileName);
            if(text.endsWith("txt"))
                return text;

            cipher.loadKey();
            return cipher.decipher(text);
        }
    }

    public String fetchFile(String fileName, String key) throws FileNotFoundException {
        String text;
        try{
            int number = Integer.parseInt(fileName);
            text = fileHandler.readFile(number);
            String[] files = fetchFile();
            String file = files[number - 1];
            if(file.endsWith("txt"))
                return text;

            cipher.getAltKey(key);
            return cipher.decipher(text);
        }
        catch (NumberFormatException e) {
            text = fileHandler.readFile(fileName);
            if (text.endsWith("txt"))
                return text;

            cipher.getAltKey(key);
            return cipher.decipher(text);
        }
    }
}
