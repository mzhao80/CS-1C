package subsetsum;

import cs1c.*;

import java.util.*;

/**
 * Contains methods to solve the subset sum and related problems.
 */
public class SubsetSum {

    /**
     * Finds subset of groceries that is closest to meeting the user's budget.
     *
     * @param shoppingList ArrayList of prices (doubles)
     * @param budget       The desired value that the sum of subset of prices must sum closest to without exceeding.
     * @return An ArrayList (double) of prices that sum most closely to the budget without exceeding it.
     */
    public static ArrayList<Double> findSubset(ArrayList<Double> shoppingList, double budget) {
        if (findFullSum(shoppingList) <= budget) {
            return shoppingList; //return entire list if its total value does not exceed the budget.
        }
        ArrayList<ArrayList<Double>> combinations = new ArrayList<>(); //list of generated subsets
        combinations.add(new ArrayList<>()); //start with the empty set
        ArrayList<Double> sums = new ArrayList<>(); //list of sums of each subset; indices are 1-to-1 with combinations
        sums.add((double) 0); //empty set has sum 0
        int size = 1; //combinations begins with one element, the empty set
        double tempSum = 0;
        for (Double d : shoppingList) {
            for (int i = 0; i < size; i++) { //only consider subsets that existed prior to this iteration
                tempSum = sums.get(i) + d;
                if (tempSum < budget) {
                    ArrayList<Double> toAdd = new ArrayList<>(combinations.get(i));
                    toAdd.add(d); //new subset is the old subset plus the new element
                    combinations.add(toAdd); //add new subset to our list of subsets
                    sums.add(tempSum); //sum of new subset = sum of old subset + value of new element
                } else if (tempSum == budget) {
                    ArrayList<Double> toReturn = new ArrayList<>(combinations.get(i));
                    toReturn.add(d);
                    return toReturn; //return any subset whose value equals the budget
                }
            }
            size = combinations.size(); //update size to include all newly generated subsets during the next iteration
        }
        return combinations.get(findMaxIndex(sums, size));
    }

    /**
     * Finds the total value of the entire shopping list.
     *
     * @param shoppingList ArrayList of prices (doubles)
     * @return Total value of the entire shopping list.
     */
    public static double findFullSum(ArrayList<Double> shoppingList) {
        double fullSum = 0;
        for (Double d : shoppingList) {
            fullSum += d;
        }
        return fullSum;
    }

    /**
     * Finds the list index of the subset that is closest to meeting the target budget without exceeding it.
     *
     * @param sums ArrayList (doubles) of the sums of each generated subset.
     * @param size Total number of generated subsets.
     * @return The list index of the subset which most closely sums to the target budget without exceeding it.
     */
    public static int findMaxIndex(ArrayList<Double> sums, int size) {
        double currMax = 0;
        int maxIndex = size - 1;
        for (int i = size - 1; i >= 0; i--) { //iterating over list of sums in reverse
            if (sums.get(i) > currMax) {
                currMax = sums.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Finds subset of songs that is closest in duration to meeting the target duration without exceeding it.
     *
     * @param songList ArrayList of SongEntries
     * @param duration The desired duration that the sum of subset of durations must sum closest to without exceeding.
     * @return An ArrayList of SongEntries that sum most closely to the duration without exceeding it.
     */
    public static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry> songList, double duration) {
        if (findFullDuration(songList) <= duration) {
            return songList; //return entire list if its total length does not exceed the max duration.
        }
        ArrayList<ArrayList<SongEntry>> combinations = new ArrayList<>(); //list of generated subsets
        combinations.add(new ArrayList<>()); //start with the empty set
        ArrayList<Integer> sums = new ArrayList<>(); //list of sums of each subset; indices are 1-to-1 with combinations
        sums.add(0); //empty set has sum 0
        int size = 1; //combinations begins with one element, the empty set
        int tempSum = 0;
        for (SongEntry s : songList) {
            for (int i = 0; i < size; i++) { //only consider subsets that existed prior to this iteration
                tempSum = sums.get(i) + s.getDuration();
                if (tempSum < duration) {
                    ArrayList<SongEntry> toAdd = new ArrayList<>(combinations.get(i));
                    toAdd.add(s); //new subset is the old subset plus the new element
                    combinations.add(toAdd); //add new subset to our list of subsets
                    sums.add(tempSum); //sum of new subset = sum of old subset + value of new element
                } else if (tempSum == duration) {
                    ArrayList<SongEntry> toReturn = new ArrayList<>(combinations.get(i));
                    toReturn.add(s);
                    return toReturn; //return any subset whose value equals the target duration
                }
            }
            size = combinations.size(); //update size to include all newly generated subsets during the next iteration
        }
        return combinations.get(findMaxSongIndex(sums, size));
    }

    /**
     * Finds the total duration of the entire song list.
     *
     * @param songList ArrayList of SongEntries
     * @return Total duration of the entire song list.
     */
    public static double findFullDuration(ArrayList<SongEntry> songList) {
        int fullSum = 0;
        for (SongEntry s : songList) {
            fullSum += s.getDuration();
        }
        return fullSum;
    }

    /**
     * Finds the list index of the subset that is closest to meeting the target duration without exceeding it.
     *
     * @param sums ArrayList (integers) of the sums of each generated subset.
     * @param size Total number of generated subsets.
     * @return The list index of the subset that is closest to meeting the target duration without exceeding it.
     */
    public static int findMaxSongIndex(ArrayList<Integer> sums, int size) {
        int currMax = 0;
        int maxIndex = size - 1;
        for (int i = size - 1; i >= 0; i--) { //iterating over list of sums in reverse
            if (sums.get(i) > currMax) {
                currMax = sums.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}

