import java.io.FileNotFoundException;

public class ProgramControl {
    private FileHandler fileHandler;
    private Cipher cipher;

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
        String text = fileHandler.readFile(fileName);
        return cipher.decipher(text);
    }

    public String fetchFile(String fileName, String key) throws FileNotFoundException {
        String text = fileHandler.readFile(fileName);
        return cipher.decipher(text, key);
    }
}
