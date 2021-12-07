package hashTables;

import cs1c.*;

/**
 * Comparable wrapper class for SongEntry based on ID
 */
public class SongCompInt implements Comparable<Integer> {
    /**
     * SongEntry data contained
     */
    SongEntry song;

    /**
     * Constructor for SongCompInt
     *
     * @param song SongEntry to be contained
     */
    public SongCompInt(SongEntry song) {
        this.song = song;
    }

    /**
     * Returns the difference of the given ID from this ID
     *
     * @param o Other ID number
     * @return the difference of the given ID from this ID
     */
    @Override
    public int compareTo(Integer o) {
        return ((Integer) song.getID()).compareTo(o);
    }

    /**
     * Checks if the two IDs are equal
     *
     * @param obj Other integer ID
     * @return True if IDs are equal, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Integer) {
            return song.getID() == (Integer) obj;
        }
        return false;
    }

    /**
     * HashCode for this song's ID
     *
     * @return HashCode for this song's ID
     */
    @Override
    public int hashCode() {
        return song.getID();
    }

    /**
     * String representation of this SongCompInt
     *
     * @return String representation of this SongCompInt
     */
    @Override
    public String toString() {
        return song.toString();
    }
}
