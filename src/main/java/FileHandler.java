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

    public String readFile(int fileNumber) throws FileNotFoundException, NullPointerException, IndexOutOfBoundsException{
        //returns the contents of the specified file
        String path = "./data";
        File directory = new File(path);

        String[] files = directory.list();

        if(files == null){
            throw new NullPointerException("Files directory is empty.");
        }
        if (fileNumber > files.length-1){
            throw new IndexOutOfBoundsException("File does not exist.");
        }
        File file = new File(files[fileNumber-1]);
        Scanner readIn = new Scanner(file);

        String contents = "";

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
