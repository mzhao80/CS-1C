package quicksortRecursion;

import cs1c.TimeConverter;

import java.util.*;
import java.io.*;

/**
 * Driver class for FHSort. Tests various recursion limits and array sizes to determine trends and optimums.
 *
 * @author Michael Zhao, Foothill College
 */
public class QuicksortRecursionLimitTest {
    /**
     * Tests various recursion limits and array sizes and records the runtimes to a CSV file
     *
     * @param args Not used
     * @throws IOException If file cannot be created successfully
     */
    public static void main(String[] args) throws IOException {
        long beginTime = System.nanoTime();
        Random random = new Random();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("quicksortLimitData.csv")));
        for (int limit = 2; limit <= 300; limit = limit + 2) {
            pw.append(",").append(String.valueOf(limit));
        }
        for (int size = 20000; size <= 10020000; size = size + 500000) {
            long thisBeginTime = System.nanoTime();
            Integer[] array = random.ints(size).boxed().toArray(Integer[]::new);
            pw.append("\n").append(String.valueOf(size));
            System.out.print("Current size: " + size);
            for (int limit = 2; limit <= 300; limit = limit + 2) {
                FHsort.setRecursionLimit(limit);
                long startTime;
                long[] estimatedTime = new long[3];
                for (int i = 0; i < 3; i++) {
                    Integer[] arrayCopy = array.clone();
                    startTime = System.nanoTime();
                    FHsort.quickSort(arrayCopy);
                    estimatedTime[i] = System.nanoTime() - startTime;
                }
                pw.append(",").append(String.valueOf((estimatedTime[0] + estimatedTime[1] + estimatedTime[2]) / 3000000.0));
            }
            System.out.println(", Elapsed Time/Size: " + TimeConverter.convertTimeToString(System.nanoTime() - thisBeginTime) + ", Running Total: " + TimeConverter.convertTimeToString(System.nanoTime() - beginTime));
        }
        System.out.println("Done. Total Elapsed Time: " + TimeConverter.convertTimeToString(System.nanoTime() - beginTime));
        pw.close();
    }
}