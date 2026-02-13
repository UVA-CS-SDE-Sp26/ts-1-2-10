import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class FileHandler {

    public String[] readFile(){
        //returns String aray of all files in the data directory by name
        String path = "./data";
        File directory = new File(path);

        String[] files = directory.list();

        return files;
    }

    public String readFile(String fileName) throws FileNotFoundException{
        //returns the contents of the specified file
        String path = "./data/" + fileName;
        String contents = "";

        File file = new File(path);
        Scanner readIn = new Scanner(file);

        while(readIn.hasNextLine()){
            contents += readIn.nextLine() + "\n";
        }

        if (!contents.equals("")){
            contents = contents.substring(0, contents.length()-1);
        }

        readIn.close();

        return contents;
    }
}
