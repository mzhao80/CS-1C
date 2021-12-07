package queues;

import cs1c.SongEntry;

import java.util.*;
import java.io.*;

/**
 * Manages three objects of class Queue where a Queue object represents a playlist. Possible valid playlists
 * are "favorites", "lounge" and "road trip", called favoritesPL, loungePL and roadTripPL respectively.
 */
public class Jukebox {
    /**
     * Simulates the playlist referred to as "favorites" in the input file.
     */
    private Queue<SongEntry> favoritePL;
    /**
     * Simulates the playlist referred to as "road trip" in the input file.
     */
    private Queue<SongEntry> roadTripPL;
    /**
     * Simulates the playlist referred to as "lounge" in the input file.
     */
    private Queue<SongEntry> loungePL;

    /**
     * Constructor to initialize playlists.
     */
    public Jukebox() {
        favoritePL = new Queue<>("favorites");
        roadTripPL = new Queue<>("road trip");
        loungePL = new Queue<>("lounge");
    }

    /**
     * Reads the test file and then adds songs to one of the three queues based on the first song found.
     *
     * @param requestedFile the name of the test file to read from.
     * @param allSongs      the array of songs read from the JSON file.
     */
    public void fillPlaylists(String requestedFile, SongEntry[] allSongs) {
        try {
            Scanner scan = new Scanner(new File(requestedFile)).useDelimiter("\n");
            while (scan.hasNext()) {
                String[] currentSong = scan.next().split(",");
                for (SongEntry song : allSongs) {
                    if (song.getTitle().equals(currentSong[1])) {
                        switch (currentSong[0]) {
                            case "favorites":
                                favoritePL.enqueue(song);
                                break;
                            case "road trip":
                                roadTripPL.enqueue(song);
                                break;
                            case "lounge":
                                loungePL.enqueue(song);
                                break;
                        }
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Invalid File Name");
            e.printStackTrace();
        }
    }

    /**
     * Accessor for the favorites playlist
     *
     * @return a Queue of SongEntries representing the favorites playlist
     */
    public Queue<SongEntry> getFavoritePL() {
        return favoritePL;
    }

    /**
     * Accessor for the road trip playlist
     *
     * @return a Queue of SongEntries representing the road trip playlist
     */
    public Queue<SongEntry> getRoadTripPL() {
        return roadTripPL;
    }

    /**
     * Accessor for the lounge playlist
     *
     * @return a Queue of SongEntries representing the lounge playlist
     */
    public Queue<SongEntry> getLoungePL() {
        return loungePL;
    }
}
