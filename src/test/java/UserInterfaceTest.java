import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserInterfaceTest{
    @Mock
        private UserInterface testInterface;
    @Mock
        private ProgramControl programControl;

    @BeforeEach
    public void initializeUI(){
        String[] userInput = {"01","abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"};
        testInterface = new UserInterface(userInput);
        testInterface.setProgramControl(programControl);
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
        when(programControl.fetchFile(testInterface.getFileSelected())).thenReturn("File content");
        assertEquals("File content", testInterface.transferFileSelected());
        verify(programControl).fetchFile(testInterface.getFileSelected());
    }

    @Test
    void transferFileSelectedWithAltKey() throws FileNotFoundException {
        when(programControl.fetchFile(testInterface.getFileSelected(),testInterface.getAltKey())).thenReturn("Content");
        assertEquals("Content", testInterface.transferFileSelectedWithAltKey());
        verify(programControl).fetchFile(testInterface.getFileSelected(),testInterface.getAltKey());

    }

    @Test
    void transfer() {
        String[] expected = new String[]{"list"};
        when(programControl.fetchFile()).thenReturn(expected);
        assertEquals(expected, testInterface.transfer());
        verify(programControl).fetchFile();
    }

}
