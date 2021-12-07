package hashTables;

import cs1c.*;

import java.util.*;

/**
 * Creates and populates two hash tables of type FHhashQPwFind class.
 */
public class TableGenerator {
    /**
     * List of all genre names included in HashTable of song genres
     */
    private ArrayList<String> genreNames;
    /**
     * HashTable based on song IDs
     */
    private FHhashQPwFind<Integer, SongCompInt> tableOfIDs;
    /**
     * HashTable based on song genres
     */
    private FHhashQPwFind<String, SongsCompGenre> tableOfSongGenres;

    /**
     * Constructor for TableGenerator, initializing all attributes
     */
    public TableGenerator() {
        genreNames = new ArrayList<>();
        tableOfIDs = new FHhashQPwFind<>();
        tableOfSongGenres = new FHhashQPwFind<>();
    }

    /**
     * Creates HashTable based on song IDs
     *
     * @param allSongs all songs to be included in HashTable
     * @return HashTable based on song IDs
     */
    public FHhashQPwFind<Integer, SongCompInt> populateIDtable(SongEntry[] allSongs) {
        for (SongEntry song : allSongs) {
            tableOfIDs.insert(new SongCompInt(song));
        }
        return tableOfIDs;
    }

    /**
     * Creates HashTable based on song genres
     *
     * @param allSongs all songs to be included in HashTable
     * @return HashTable based on song genres
     */
    public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] allSongs) {
        for (SongEntry song : allSongs) {
            if (genreNames.contains(song.getGenre())) {
                tableOfSongGenres.find(song.getGenre()).addSong(song);
            } else {
                genreNames.add(song.getGenre());
                ArrayList<SongEntry> songList = new ArrayList<>();
                songList.add(song);
                tableOfSongGenres.insert(new SongsCompGenre(song.getGenre(), songList));
            }
        }
        return tableOfSongGenres;
    }

    /**
     * Accessor for list of included genre names
     *
     * @return ArrayList of genre names
     */
    public ArrayList<String> getGenreNames() {
        return genreNames;
    }
}
