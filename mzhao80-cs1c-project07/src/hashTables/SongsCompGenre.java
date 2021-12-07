package hashTables;

import cs1c.*;

import java.util.*;

/**
 * Comparable wrapper class for SongEntry based on genre.
 */
public class SongsCompGenre implements Comparable<String> {
    /**
     * Genre of song
     */
    String genre;
    /**
     * List of songs of given genre
     */
    ArrayList<SongEntry> songs;

    /**
     * Constructor for SongsCompGenre
     *
     * @param genre Genre of this object
     * @param songs List of all songs belonging to this genre
     */
    public SongsCompGenre(String genre, ArrayList<SongEntry> songs) {
        this.genre = genre;
        this.songs = songs;
    }

    /**
     * Adds a new song of this object's genre
     *
     * @param e Song of this genre to be added
     */
    public void addSong(SongEntry e) {
        if (e.getGenre().equals(genre)) {
            songs.add(e);
        } else {
            System.out.println("Incorrect genre.");
        }
    }

    /**
     * Accessor for genre name
     *
     * @return this genre name
     */
    public String getName() {
        return genre;
    }

    /**
     * Accessor for list of songs of this genre
     *
     * @return ArrayList of SongEntries of this genre
     */
    public ArrayList<SongEntry> getData() {
        return songs;
    }

    /**
     * Compares this genre to another genre lexixographically
     *
     * @param o Genre name
     * @return an integer representing lexicographical comparison of the two genres
     */
    @Override
    public int compareTo(String o) {
        return genre.compareTo(o);
    }

    /**
     * @param obj An object to be compared (should be a String genre)
     * @return True if the genres are the same, false if not
     * @throws NoSuchElementException
     */
    @Override
    public boolean equals(Object obj) throws NoSuchElementException {
        if (obj instanceof String) {
            return genre.equals(obj);
        }
        throw new NoSuchElementException();
    }

    /**
     * HashCode of this genre
     *
     * @return HashCode of this genre
     */
    @Override
    public int hashCode() {
        return genre.hashCode();
    }

    /**
     * String representation of this SongsCompGenre object
     *
     * @return String representation of this SongsCompGenre object
     */
    @Override
    public String toString() {
        return genre + "\t" + songs.size();
    }
}
