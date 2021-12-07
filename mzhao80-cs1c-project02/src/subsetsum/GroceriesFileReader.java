package subsetsum;

import java.io.*;
import java.util.*;

/**
 * An object of type GroceriesFileReader can be used to read in a grocery list file (in CSV format) and extract a list of just the prices.
 */
public class GroceriesFileReader {
    /**
     * Reads in a file and adds the prices into a list of the prices of groceries.
     *
     * @param filePath The input file to parse.
     * @return An ArrayList (doubles) of prices extracted from the groceries file.
     */
    public ArrayList<Double> readFile(String filePath) {
        ArrayList<Double> priceOfGroceries = new ArrayList<>();
        try {
            File groceryList = new File(filePath);
            Scanner fileReader = new Scanner(groceryList);
            while (fileReader.hasNextLine()) {
                priceOfGroceries.add(Double.parseDouble(fileReader.nextLine().split(",")[1]));
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return priceOfGroceries;
    }
}
