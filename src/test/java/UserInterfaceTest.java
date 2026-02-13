import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    private static UserInterface testInterface;
    @BeforeEach
    public void initializeUI(){
        String[] userInput = new String[]{"filename01","abc"};
        testInterface = new UserInterface(userInput);
    }

    @Test
    void testPartitionUserInput() {
        assertTrue(testInterface.getFileSelected().equals("filename01"));
        assertTrue(testInterface.getAltKey().equals("abc"));
    }

    @Test
    void testGetFileSelected() {
        assertTrue(testInterface.getFileSelected().equals("filename01"));
    }

    @Test
    void testGetAltKey() {
        assertTrue(testInterface.getAltKey().equals("abc"));
    }
}