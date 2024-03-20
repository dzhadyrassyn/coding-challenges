package dzhadyrassyn.coding.challenges.customwc;

import java.io.*;

public class WordsCounter extends Counter {
    public WordsCounter(File file) {
        super(file);
    }

    @Override
    public long count() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(getFile()));
        long cnt = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String trim = line.trim();
            if (!trim.isEmpty()) {
                cnt += trim.split("\\s+").length;
            }
        }
        return cnt;
    }

    @Override
    public void print(long amount) {
        System.out.printf("words count\t%d\t%s\n", amount, getFile().getName());
    }
}
