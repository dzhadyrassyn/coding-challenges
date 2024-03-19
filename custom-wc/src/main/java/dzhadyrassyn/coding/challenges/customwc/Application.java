package dzhadyrassyn.coding.challenges.customwc;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    @Option(names = {"-w"}, description = "The file whose words to count")
    private File wordsCountFile;

    @Option(names = {"-m"}, description = "The file whose characters to count")
    private File characterCountFile;

//    @Parameters(index = "0", description = "The file whose bytes, lines, words to count")
//    private File parameterFile;

    @Override
    public Integer call() throws Exception {
        if (bytesCountFile != null) {
            validateFile(bytesCountFile);
            long bytesCount = bytesCountFile.length();
            System.out.printf("%d\t%s\n", bytesCount, bytesCountFile.getName());
        }
        if (linesCountFile != null) {
            validateFile(linesCountFile);
            BufferedReader reader = new BufferedReader(new FileReader(linesCountFile));
            long cnt = reader.lines().count();
            System.out.printf("%d\t%s\n", cnt, linesCountFile.getName());
        }
        if (wordsCountFile != null) {
            validateFile(wordsCountFile);
            BufferedReader reader = new BufferedReader(new FileReader(wordsCountFile));
            long cnt = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String trim = line.trim();
                if (!trim.isEmpty()) {
                    cnt += trim.split("\\s+").length;
                }
            }
            System.out.printf("%d\t%s\n", cnt, wordsCountFile.getName());
        }
        if (characterCountFile != null) {
            validateFile(characterCountFile);
            BufferedReader reader = new BufferedReader(new FileReader(wordsCountFile));
            long cnt = 0;
            while(reader.read() != -1) {
                ++cnt;
            }
            System.out.printf("%d\t%s\n", cnt, characterCountFile.getName());
        }
        return 0;
    }

    private void validateFile(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName() + " does not exist");
        } else if(!file.isFile()) {
            throw new FileNotFoundException(file + " is not a file");
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Application()).execute(args);
        System.exit(exitCode);
    }
}
