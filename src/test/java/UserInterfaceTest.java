import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    private static UserInterface testInterface;
    @BeforeEach
    public void initializeUI(){
        String[] userInput = new String[]{"01","abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"};
        testInterface = new UserInterface(userInput);
    }

    @Test
    void testPartitionUserInput() {
        assertEquals("01", testInterface.getFileSelected());
        assertEquals("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", testInterface.getAltKey(),"Name");
    }

    @Test
    void testGetFileSelected() {
        assertEquals("01", testInterface.getFileSelected());
    }

    @Test
    void testGetAltKey() {
        assertEquals("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", testInterface.getAltKey());
    }

    @Test
    void transferFileSelected() throws FileNotFoundException {
        String content = "Carnivore, later renamed DCS1000, was a system implemented by the Federal Bureau of Investigation (FBI) that was\n" +
                "designed to monitor email and electronic communications. It used a customizable packet sniffer that could monitor all\n" +
                "of a target user's Internet traffic. Carnivore was implemented in October 1997. By 2005 it had been replaced with\n" +
                "improved commercial software.";
        assertEquals(content, testInterface.transferFileSelected());
    }

    @Test
    void transferFileSelectedWithAltKey() throws FileNotFoundException {
        String content = "Dbsojwpsf, mbufs sfobnfe EDT2aaa, xbt b tztufn jnqmfnfoufe cz uif Gfefsbm Cvsfbv pg Jowftujhbujpo (GCJ) uibu xbt\n" +
                "eftjhofe up npojups fnbjm boe fmfduspojd dpnnvojdbujpot. Ju vtfe b dvtupnjAbcmf qbdlfu tojggfs uibu dpvme npojups bmm\n" +
                "pg b ubshfu vtfs't Joufsofu usbggjd. Dbsojwpsf xbt jnqmfnfoufe jo Pdupcfs 2008. Cz 3aa6 ju ibe cffo sfqmbdfe xjui\n" +
                "jnqspwfe dpnnfsdjbm tpguxbsf.";
        assertEquals(content, testInterface.transferFileSelectedWithAltKey());
    }

    @Test
    void transfer() {
        String[] file = {"carnivore.cip", "carnivore.txt", "cointelpro.cip", "cointelpro.txt"};
        assertArrayEquals(file, testInterface.transfer(), "Name of files should be the same as those in the directory \"data/\".");
    }

}
