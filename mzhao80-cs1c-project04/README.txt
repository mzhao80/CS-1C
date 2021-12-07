project folder:
mzhao80-cs1c-project04/


Brief description of submitted files:

src/queues/MyTunes.java
    - Partially simulates the digital jukebox TouchTunes, using a queue which holds playlists.

src/queues/Jukebox.java
    - Manages three objects of type Queue<SongEntry> where a Queue object represents a playlist. Possible valid
    playlists are "favorites", "lounge" and "road trip", called favoritesPL, loungePL and roadTripPL respectively.

src/queues/Queue.java
    - Objects of type Queue manage items in a singly linked list that can enqueue() items to the end and dequeue() items
     from the front of the queue.
        - Node class instantiates Node objects used in the Queue.
        - QueueIterator class implements an iterator to iterate over the Queue.

src/cs1c/TimeConverter.java
    - Times the runtime of other programs

src/cs1c/MillionSongDataSubset.java
    - Parses a JSON file of song metadata and creates SongEntry lists.

src/cs1c/SongEntry.java
    - Object containing metadata of songs from the Million Song Dataset

resources/RUN.txt
    - console outputs of MyTunes.java

resources/tunes.txt
    - Provided test case for non-empty queues

resources/tunes_truncated.txt
    - Provided test case for empty queues

resources/inputFile01.txt
    - Test case for playlist with multiple of the same song.

resources/inputFile02.txt
    - Test case for empty playlist.

resources/music_genre_subset.json
    - Subset of the Million Song Dataset

README.txt
    - description of submitted files
