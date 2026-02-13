import java.io.FileNotFoundException;
import java.lang.classfile.instruction.ExceptionCatch;

public class UserInterface {
    private String fileSelected;
    private ProgramControl programControl = new ProgramControl();

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
            fileSelected = input[0];
        } catch(Exception e){
            System.out.println("An error occured." + e.getMessage());
        }
        if(input.length==2){
            altKey = input[1];
        }
    }
    public void setProgramControl(ProgramControl pc){
        programcontrol=pc;
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
