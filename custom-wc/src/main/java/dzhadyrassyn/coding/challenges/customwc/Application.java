package dzhadyrassyn.coding.challenges.customwc;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.Callable;

@Command(
        name = "wc",
        mixinStandardHelpOptions = true,
        version = "wc 1.0",
        description = "Custom wc tool"
)
public class Application implements Callable<Integer> {

    @Option(names = "-c", description = "The file whose byte to count")
    private File bytesCountFile;

    @Option(names = "-l", description = "The file whose lines to count")
    private File linesCountFile;

    @Option(names = "-w", description = "The file whose words to count")
    private File wordsCountFile;

    @Option(names = "-m", description = "The file whose characters to count")
    private File characterCountFile;

    @Parameters(index = "0", description = "The file whose bytes, lines, words to count")
    private File parameterFile;

    @Override
    public Integer call() throws Exception {

        validateFiles();

        if (parameterFile != null) {
            for(Counter counter: Arrays.asList(new BytesCounter(parameterFile), new LinesCounter(parameterFile), new WordsCounter(parameterFile))) {
                counter.print(counter.count());
            }
        }

        for(Counter counter: Arrays.asList(new BytesCounter(bytesCountFile), new LinesCounter(linesCountFile), new WordsCounter(wordsCountFile), new CharactersCounter(characterCountFile))) {
            if (counter.getFile() != null) {
                counter.print(counter.count());
            }
        }

        return 0;
    }

    private void validateFiles() throws IllegalAccessException, FileNotFoundException {
        for (Field field : getClass().getDeclaredFields()) {
            File file = (File)field.get(this);
            if (file != null) {
                validateFile(file);
            }
        }
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
