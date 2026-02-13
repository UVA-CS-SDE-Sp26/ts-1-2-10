import java.io.FileNotFoundException;
import java.lang.classfile.instruction.ExceptionCatch;

public class UserInterface {
    private String fileSelected;

    public String getFileSelected() {
        return fileSelected;
    }

    public String getAltKey() {
        return altKey;
    }

    private String altKey;
    public ProgramControl programcontrol = new ProgramControl();

    public UserInterface(String[] input) {
        partitionUserInput(input);
    }
    public void partitionUserInput(String[] input){
        try{
            if(input.length == 1) {
                fileSelected = input[0];
            }
        } catch(Exception e){
            System.out.println("An error occurred. " + e.getMessage());
        }
        if(input.length==2){
            altKey = input[1];
        }
    }
    public String transferFileSelected() throws FileNotFoundException {
        return programcontrol.fetchFile(fileSelected);
    }
    public String transferFileSelectedWithAltKey() throws FileNotFoundException {
        return programcontrol.fetchFile(fileSelected,altKey);
    }
    public String[] transfer(){
        return programcontrol.fetchFile();
    }
}
