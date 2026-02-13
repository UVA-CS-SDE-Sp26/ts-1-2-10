import java.io.IOException;

/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[]args){
        if (args.length==0){
            throw new IllegalArgumentException("Not enough arguments provided");
        }
        UserInterface UI = new UserInterface(args);
        if(args.length ==1){
            System.out.println(UI.transferFileSelected());
        }
        else if(args.length == 2){
            System.out.println(UI.transferFileSelectedWithAltKey());
        }
    }
}
