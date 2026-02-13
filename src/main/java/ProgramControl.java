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
        }
        catch (NumberFormatException e){
            text = fileHandler.readFile(fileName);
        }
        return cipher.decipher(text);
    }

    public String fetchFile(String fileName, String key) throws FileNotFoundException {
        String text;
        try{
            int number = Integer.parseInt(fileName);
            text = fileHandler.readFile(number);
        }
        catch (NumberFormatException e){
            text = fileHandler.readFile(fileName);
        }
        cipher.getAltKey(key);
        return cipher.decipher(text);
    }
}
