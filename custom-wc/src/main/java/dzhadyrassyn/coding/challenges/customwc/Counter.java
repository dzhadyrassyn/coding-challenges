package dzhadyrassyn.coding.challenges.customwc;

import java.io.File;
import java.io.IOException;

public abstract class Counter {

    private final File file;

    public Counter(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public abstract long count() throws IOException;

    public abstract void print(long amount);
}
