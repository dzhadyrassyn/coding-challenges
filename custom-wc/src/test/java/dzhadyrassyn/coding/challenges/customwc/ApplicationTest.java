package dzhadyrassyn.coding.challenges.customwc;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void test() {
        Application application = new Application();
        CommandLine commandLine = new CommandLine(application);

        StringWriter sw = new StringWriter();
        commandLine.setOut(new PrintWriter(sw));

        int exitCode = commandLine.execute("settings.gradle", "-w=test.txt");
        assertEquals(0, exitCode);
    }
}