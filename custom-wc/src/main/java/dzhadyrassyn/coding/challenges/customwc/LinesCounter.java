package dzhadyrassyn.coding.challenges.customwc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LinesCounter extends Counter {
    public LinesCounter(File file) {
        super(file);
    }

    @Override
    public long count() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(getFile()));
        return reader.lines().count();
    }

    @Override
    public void print(long amount) {
        System.out.printf("lines count\t%d\t%s\n", amount, getFile().getName());
    }
}
