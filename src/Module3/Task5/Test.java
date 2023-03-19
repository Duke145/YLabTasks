package Module3.Task5;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        File dataFile = new Generator().generate("src\\Module3\\Task5\\data.txt", 375_000_000);
        System.out.println(new Validator(dataFile).isSorted()); // false
        File sortedFile = new Sorter().sortFile(dataFile);
        System.out.println(new Validator(sortedFile).isSorted()); // true
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
