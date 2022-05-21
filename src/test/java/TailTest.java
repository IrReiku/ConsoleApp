import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class TailTest {
    final private ByteArrayOutputStream output = new ByteArrayOutputStream();
    final private ByteArrayInputStream input = new ByteArrayInputStream(("This is a sample text.\n" +
            "END\n").getBytes());
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }
    @Test
    public void readFromFileTest() {
        String[] args = {"-n", "5", "Sample.txt", "Sample2.txt"};
        TailLauncher.main(args);
        Assertions.assertEquals("Sample.txt\r\n" +
                "eight\r\n" +
                "nine\r\n" +
                "ten\r\n" +
                "eleven\r\n" +
                "twelve\r\n" +
                "Sample2.txt\r\n" +
                "This is a sample text.\r\n", output.toString());
    }

    @Test
    public void readFromConsoleTest() {
        String[] args = {"-c", "7"};
        System.setIn(input);
        TailLauncher.main(args);
        Assertions.assertEquals(
                "Enter the text:\r\n" +
                        "Enter \"END\" to end the input.\r\n" +
                        "e text.\r\n", output.toString());
        System.setIn(null);
    }
    @AfterEach
    public void returnStreams () {
        System.setOut(null);
    }
}
