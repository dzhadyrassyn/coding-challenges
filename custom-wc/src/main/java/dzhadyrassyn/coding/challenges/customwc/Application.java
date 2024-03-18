package dzhadyrassyn.coding.challenges.customwc;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

@Command(
        name = "wc",
        mixinStandardHelpOptions = true,
        version = "wc 1.0",
        description = "Custom wc tool"
)
public class Application implements Callable<Integer> {

    @Option(names = {"-c"}, description = "The file whose byte to count")
    private File bytesCountFile;

    @Option(names = {"-l"}, description = "The file whose lines to count")
    private File linesCountFile;

    @Override
    public Integer call() throws Exception {
        validateFile();

        long bytesCount = bytesCountFile.length();
        System.out.printf("%d\t%s\n", bytesCount, bytesCountFile.getName());
        return 0;
    }

    private void validateFile() throws FileNotFoundException {
        if (!bytesCountFile.exists()) {
            throw new FileNotFoundException(bytesCountFile.getName() + " does not exist");
        } else if(!bytesCountFile.isFile()) {
            throw new FileNotFoundException(bytesCountFile.getName() + " is not a file");
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Application()).execute(args);
        System.exit(exitCode);
    }
}
