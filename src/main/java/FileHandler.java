import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class FileHandler {

    private String path;

    public FileHandler(){
        this("./data");
    }

    public FileHandler(String path){
        this.path = path;
    }

    public String[] readFile(){
        //returns String aray of all files in the data directory by name
        String directoryPath = path;
        File directory = new File(directoryPath);

        String[] files = directory.list();

        return files;
    }

    public String readFile(String fileName) throws FileNotFoundException{
        //returns the contents of the specified file
        String filepath = path + "/" + fileName;
        String contents = "";

        File file = new File(filepath);
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

    public String readFile(int fileNumber) throws FileNotFoundException, NullPointerException, ArrayIndexOutOfBoundsException{
        //returns the contents of the specified file
        String directorypath = path + "/";
        File directory = new File(directorypath);

        String[] files = directory.list();

        if(files == null || files.length == 0){
            throw new NullPointerException("Files directory is empty.");
        }
        if (fileNumber > files.length+1 || fileNumber < 0){
            throw new ArrayIndexOutOfBoundsException("File does not exist.");
        }
        Arrays.sort(files);
        File file = new File(directorypath + files[(fileNumber-1)]);
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
