import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgramcontrolTest {
    @Mock
    private Filehandler filehandler;
    private Cipher cipher;

    private Programcontrol programcontrol;

    @BeforeEach
    void setUp() {
        programcontrol = new Programcontrol(filehandler, cipher);
    }

    @AfterEach
    void tearDown() {
        programcontrol = null;
    }

    @Test
    void testFetchFile_NoArgs() {
        when((String) filehandler.readFile()).thenReturn("A list");
        assertEquals("A list", programcontrol.fetchFile());
        verify(filehandler).readFile();
    }

    @Test
    void testFetchFile_OneArg() {
        String fileName = "test.txt";
        when((String) filehandler.readFile(fileName)).thenReturn("General deciphered File Content");
        assertEquals("Ciphered File Content", programcontrol.fetchFile(fileName));
        verify(filehandler).readFile(fileName);
    }

    @Test
    void testFetchFile_OneArgNull() {
        when(filehandler.readFile(null)).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> programcontrol.fetchFile(null));
        verify(filehandler).readFile(null);
    }

    @Test
    void testFetchFile_TwoArgsNullKey() {
        String fileName = "test.txt";
        when(filehandler.readFile(fileName, null)).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> programcontrol.fetchFile(fileName, null));
        verify(filehandler).readFile(fileName, null);
    }

    @Test
    void testFetchFile_TwoArgsNullFileName() {
        String key = "bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a";
        when(filehandler.readFile(null, key)).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> programcontrol.fetchFile(null, key));
        verify(filehandler).readFile(null, key);
    }

    @Test
    void testFetchFile_BothArgs() {
        String fileName = "test.txt";
        String key = "bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a";
        when((String) filehandler.readFile(fileName, key)).thenReturn("Deciphered File Content");
        assertEquals("Deciphered File Content", programcontrol.fetchFile(fileName, key));
        verify(filehandler).readFile(fileName, key);
    }
}