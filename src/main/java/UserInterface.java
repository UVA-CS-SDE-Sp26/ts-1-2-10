public class UserInterface {
    private String fileSelected;
    private String altKey;
    public UserInterface(String[] input) {
        partitionUserInput(input);
    }
    public void partitionUserInput(String[] input){
        fileSelected = input[0];
        altKey = input[1];
    }
    public String getFileSelected(){
        return fileSelected;
    }
    public String getAltKey(){
        return altKey;
    }
}
