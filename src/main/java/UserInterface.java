
public class UserInterface {
    private String fileSelected;
    private String altKey;
    public Programcontrol programcontrol = new Programcontrol();

    public UserInterface(String[] input) {
        partitionUserInput(input);
    }
    public void partitionUserInput(String[] input){
        fileSelected = input[0];
        altKey = input[1];
    }
    public void transferFileSelected(){
        System.out.println(programcontrol.fetchFile(fileSelected));
    }
    public void transferFileSelectedWithAltKey(){
        System.out.println(programcontrol.fetchFile(fileSelected,altKey));
    }
}
