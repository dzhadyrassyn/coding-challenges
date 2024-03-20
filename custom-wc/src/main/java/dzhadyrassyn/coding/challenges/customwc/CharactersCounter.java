package dzhadyrassyn.coding.challenges.customwc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CharactersCounter extends Counter {
    public CharactersCounter(File file) {
        super(file);
    }

    @Override
    public long count() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(getFile()));
        long cnt = 0;
        while(reader.read() != -1) {
            ++cnt;
        }
        return cnt;
    }

    @Override
    public void print(long amount) {
        System.out.printf("characters count\t%d\t%s\n", amount, getFile().getName());
    }
}
