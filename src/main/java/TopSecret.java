/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[]args){
        try{
            if(args.length == 0){
                System.out.println("List of all files: ");
                String[] list = UI.transfer();
                String output = "";
                for(int i = 0; i < list.length; i++){
                    output += String.format("%02d", i+1) + " " + list[i] + "\n";
                }
                if (output.equals("")){
                    System.out.println("No files found.");
                }
                System.out.println(output);
            }
            else if(args.length == 1){
                UserInterface UI = new UserInterface(args);
                System.out.println(UI.transferFileSelected());
            }
            else if(args.length == 2){
                UserInterface UI = new UserInterface(args);
                System.out.println(UI.transferFileSelectedWithAltKey());
            }
            else{
                System.out.println("Invalid number of arguments.");
            }
        }
        catch(Exception e){
            System.out.println("An error occurred. " + e.getMessage());
        }
    }
}
