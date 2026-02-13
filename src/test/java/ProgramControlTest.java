import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgramControlTest {
    @Mock
    private FileHandler fileHandler;

    @Mock
    private Cipher cipher;

    private ProgramControl programControl;

    @BeforeEach
    void setUp() {
        programControl = new ProgramControl(fileHandler, cipher);
    }

    @Test
    void testFetchFile_NoArgs() {
        String[] list = {"filea.txt", "fileb.txt"};
        when(fileHandler.readFile()).thenReturn(list);
        assertEquals(list, programControl.fetchFile());

        verify(fileHandler).readFile();
    }

    @Test
    void testFetchFile_OneArg() throws FileNotFoundException {
        String fileName = "test.txt";
        when(fileHandler.readFile(fileName)).thenReturn("Ciphered File Content");
        when(cipher.decipher(fileName)).thenReturn("Deciphered File Content");
        assertEquals("Deciphered File Content", programControl.fetchFile(fileName));

        verify(fileHandler).readFile(fileName);
        verify(cipher).decipher("Ciphered File Content");
    }

    @Test
    void testFetchFile_OneArgInt() throws FileNotFoundException {
        String fileName = "1";
        when(fileHandler.readFile(Integer.parseInt(fileName))).thenReturn("Ciphered File Content");
        when(cipher.decipher("Ciphered File Content")).thenReturn("Deciphered File Content");
        assertEquals("Deciphered File Content", programControl.fetchFile(fileName));

        verify(fileHandler).readFile(fileName);
        verify(cipher).decipher("Ciphered File Content");
    }

    @Test
    void testFetchFile_OneArgNull() throws FileNotFoundException {
        when(fileHandler.readFile(null)).thenThrow(new FileNotFoundException());
        assertThrows(FileNotFoundException.class, () -> programControl.fetchFile(null));
    }

    @Test
    void testFetchFile_TwoArgsNullKey() throws FileNotFoundException {
        String fileName = "test.txt";
        when(fileHandler.readFile(fileName)).thenReturn("Ciphered File Content");
        when(cipher.decipher("Ciphered File Content")).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> programControl.fetchFile(fileName, null));
    }

    @Test
    void testFetchFile_TwoArgsNullFileName() throws FileNotFoundException {
        String key = "bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a";
        when(fileHandler.readFile(null)).thenThrow(new FileNotFoundException());
        assertThrows(FileNotFoundException.class, () -> programControl.fetchFile(null, key));
    }

    @Test
    void testFetchFile_BothArgs() throws FileNotFoundException {
        String fileName = "test.txt";
        String key = "bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a";
        when(fileHandler.readFile(fileName)).thenReturn("Ciphered File Content");
        when(cipher.decipher("Ciphered File Content")).thenReturn("Deciphered File Content");
        assertEquals("Deciphered File Content", programControl.fetchFile(fileName, key));

        verify(fileHandler).readFile(fileName);
        verify(cipher).decipher("Ciphered File Content");
    }
}