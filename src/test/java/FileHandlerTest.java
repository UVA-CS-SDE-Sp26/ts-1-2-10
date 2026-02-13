import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    @Test
    void readFileTest(){
        FileHandler fileHandler = new FileHandler();
        String[] file = {"carnivore.cip", "carnivore.txt", "cointelpro.cip", "cointelpro.txt"};
        String[] result = fileHandler.readFile();
        Arrays.sort(file);
        Arrays.sort(result);
        assertArrayEquals(file, result, "Name of files should be the same as those in the directory \"data/\".");
    }

    @Test
    void readFileWithCarnivoreTXTFileTest() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        String content = "Carnivore, later renamed DCS1000, was a system implemented by the Federal Bureau of Investigation (FBI) that was\n" +
                "designed to monitor email and electronic communications. It used a customizable packet sniffer that could monitor all\n" +
                "of a target user's Internet traffic. Carnivore was implemented in October 1997. By 2005 it had been replaced with\n" +
                "improved commercial software.";
        String expected = fileHandler.readFile("carnivore.txt");
        assertEquals(content, expected, "Content of carnivore.txt should match what the fileHandler returns.");
    }

    @Test
    void readFileWithCarnivoreCIPFileTest() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        String content = "Dbsojwpsf, mbufs sfobnfe EDT2aaa, xbt b tztufn jnqmfnfoufe cz uif Gfefsbm Cvsfbv pg Jowftujhbujpo (GCJ) uibu xbt\n" +
                "eftjhofe up npojups fnbjm boe fmfduspojd dpnnvojdbujpot. Ju vtfe b dvtupnjAbcmf qbdlfu tojggfs uibu dpvme npojups bmm\n" +
                "pg b ubshfu vtfs't Joufsofu usbggjd. Dbsojwpsf xbt jnqmfnfoufe jo Pdupcfs 2008. Cz 3aa6 ju ibe cffo sfqmbdfe xjui\n" +
                "jnqspwfe dpnnfsdjbm tpguxbsf.";
        String expected = fileHandler.readFile("carnivore.cip");
        assertEquals(content, expected, "Content of carnivore.cip should match what the fileHandler returns.");
    }

    @Test
    void readFileWithCointelproTXTFileTest() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        String content = "COINTELPRO (a syllabic abbreviation derived from Counter Intelligence Program) was a series of covert and illegal\n" +
                "projects conducted between 1956 and 1971 by the United States Federal Bureau of Investigation (FBI) aimed at\n" +
                "surveilling, infiltrating, discrediting, and disrupting American political parties and organizations that the FBI\n" +
                "perceived as subversive.";
        String expected = fileHandler.readFile("cointelpro.txt");
        assertEquals(content, expected, "Content of cointelpro.txt should match what the fileHandler returns.");
    }

    @Test
    void readFileWithCointelproCIPFileTest() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        String content = "DPJOUFMQSP (b tzmmbcjd bccsfwjbujpo efsjwfe gspn Dpvoufs Joufmmjhfodf Qsphsbn) xbt b tfsjft pg dpwfsu boe jmmfhbm\n" +
                "qspkfdut dpoevdufe cfuxffo 2067 boe 2082 cz uif Vojufe Tubuft Gfefsbm Cvsfbv pg Jowftujhbujpo (GCJ) bjnfe bu\n" +
                "tvswfjmmjoh, jogjmusbujoh, ejtdsfejujoh, boe ejtsvqujoh Bnfsjdbo qpmjujdbm qbsujft boe pshbojAbujpot uibu uif GCJ\n" +
                "qfsdfjwfe bt tvcwfstjwf.";
        String expected = fileHandler.readFile("cointelpro.cip");
        assertEquals(content, expected, "Content of cointelpro.cip should match what the fileHandler returns.");
    }

    @Test
    void readFileException() {
        FileHandler fileHandler = new FileHandler();
        assertThrows(FileNotFoundException.class, () -> {
            fileHandler.readFile("doesNotExist.txt");
        }, "Asking to read a file that does not exist should throw a FileNotFoundException.");
    }

    @Test
    void readFileWithNumberLast() throws FileNotFoundException{
        FileHandler fileHandler = new FileHandler();
        String content = "COINTELPRO (a syllabic abbreviation derived from Counter Intelligence Program) was a series of covert and illegal\n" +
                "projects conducted between 1956 and 1971 by the United States Federal Bureau of Investigation (FBI) aimed at\n" +
                "surveilling, infiltrating, discrediting, and disrupting American political parties and organizations that the FBI\n" +
                "perceived as subversive.";
        String expected = fileHandler.readFile(4);
        assertEquals(content, expected, "When given a file number the output should be the relating file.");
    }

    @Test
    void readFileWithNumberFirst() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        String content = "Dbsojwpsf, mbufs sfobnfe EDT2aaa, xbt b tztufn jnqmfnfoufe cz uif Gfefsbm Cvsfbv pg Jowftujhbujpo (GCJ) uibu xbt\n" +
                "eftjhofe up npojups fnbjm boe fmfduspojd dpnnvojdbujpot. Ju vtfe b dvtupnjAbcmf qbdlfu tojggfs uibu dpvme npojups bmm\n" +
                "pg b ubshfu vtfs't Joufsofu usbggjd. Dbsojwpsf xbt jnqmfnfoufe jo Pdupcfs 2008. Cz 3aa6 ju ibe cffo sfqmbdfe xjui\n" +
                "jnqspwfe dpnnfsdjbm tpguxbsf.";
        String expected = fileHandler.readFile(0);
        assertEquals(content, expected, "When given a file number the output should be the relating file.");
    }

    @Test
    void readFileExceptionWithNumber() {
        FileHandler fileHandler = new FileHandler();
        assertThrows(FileNotFoundException.class, () -> {
            fileHandler.readFile(5);
        }, "Asking to read a file that does not exist should throw a FileNotFoundException.");
    }

    @Test
    void readFileExceptionWithNegativeNumber() {
        FileHandler fileHandler = new FileHandler();
        assertThrows(FileNotFoundException.class, () -> {
            fileHandler.readFile(-1);
        }, "Asking to read a file that does not exist should throw a FileNotFoundException.");
    }


}
