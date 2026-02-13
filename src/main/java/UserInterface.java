
public class UserInterface {
    private String fileSelected;

    public String getFileSelected() {
        return fileSelected;
    }

    public String getAltKey() {
        return altKey;
    }

    private String altKey;
    public Programcontrol programcontrol = new Programcontrol();

    public UserInterface(String[] input) {
        partitionUserInput(input);
    }
    public void partitionUserInput(String[] input){
        fileSelected = input[0];
        if(input.length==2){
            altKey = input[1];
        }
    }
    public String transferFileSelected(){
        return programcontrol.fetchFile(fileSelected);
    }
    public String transferFileSelectedWithAltKey(){
        return programcontrol.fetchFile(fileSelected,altKey);
    }
}
