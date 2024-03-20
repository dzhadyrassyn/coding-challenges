package dzhadyrassyn.coding.challenges.customwc;

import java.io.File;

public class BytesCounter extends Counter {
    public BytesCounter(File file) {
        super(file);
    }

    @Override
    public long count() {
        return getFile().length();
    }

    @Override
    public void print(long amount) {
        System.out.printf("bytes count\t%d\t%s\n", amount, getFile().getName());
    }
}
