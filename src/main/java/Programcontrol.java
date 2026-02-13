import javax.crypto.Cipher;

public class Programcontrol {
    private Filehandler filehandler;
    private Cipher cipher;

    public Programcontrol() {
        this(new Filehandler(), new Cipher());
    }

    Programcontrol(Filehandler filehandler, Cipher cipher) {
        this.filehandler = filehandler;
        this.cipher = cipher;
    }

    public String fetchFile() {
        return filehander.readFile();
    }

    public String fetchFile(String fileName) {
        text = filehandler.readFile(fileName);
        return cipher.decipher(text);
    }

    public String fetchFile(String fileName, String key) {
        text = filehandler.readFile(fileName);
        return cipher.decipher(text, key);
    }
}
