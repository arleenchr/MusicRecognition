import java.util.ArrayList;
import java.util.List;

/**
 * Class Solver represents the main solver program for the music recognition
 * @songDatabase the list of songs on the program's database
 * @songToBeMatched the song from user to be matched with every songs on the songDatabase
 */
public class Solver {
    /* attributes */
    private List<Song> songDatabase;
    private Song songToBeMatched;

    /* constructor */
    public Solver(){
        this.songDatabase = new ArrayList<Song>();
        this.songToBeMatched = new Song();
    }
    public Solver(Song songToBeMatched){
        this.songDatabase = new ArrayList<Song>();
        this.songToBeMatched = songToBeMatched;
    }
    public Solver(List<Song> songDatabase, Song songToBeMatched){
        this.songDatabase = songDatabase;
        this.songToBeMatched = songToBeMatched;
    }

    /* getter and setter */
    public List<Song> getSongDatabase(){
        return songDatabase;
    }
    public Song getSongToBeMatched(){
        return songToBeMatched;
    }
    public Song getSongDatabaseIdx(int idx){
        return songDatabase.get(idx);
    }
    public void setSongDatabase(List<Song> songDatabase){
        this.songDatabase = songDatabase;
    }
    public void setSongToBeMatched(Song songToBeMatched){
        this.songToBeMatched = songToBeMatched;
    }

    /* other methods */
    /**
     * Method containsSongDatabase to check whether a song/piece exists in the songDatabase
     * @param song the song/piece to be checked
     * @return true if the database contains the song/piece,
     * false if the database does not contain the song/piece
     */
    public boolean containsSongDatabase(Song song){
        return songDatabase.contains(song);
    }

    /**
     * Method addSongDatabase to add a song/piece to the database
     * @param song the song to be inserted to the database
     */
    public void addSongDatabase(Song song){
        songDatabase.add(song);
    }

    /**
     * Method removeSongDatabase to remove a song/piece from the database
     * @param song the song/piece to be removed
     */
    public void removeSongDatabase(Song song){
        songDatabase.remove(song);
    }

    /**
     * Method removeSongDatabase to remove a song/piece from the database by its index in the database list
     * @param idx the index of the song/piece to be removed
     */
    public void removeSongDatabase(int idx){
        songDatabase.remove(idx);
    }

    /* search matched songs method */

    /**
     * Method findMatch to find all the matching songs/pieces in the database.
     * A song/piece defined as matched if the song/piece contains the pattern of the song in the database,
     * or if the similarity percentage is above 75%.
     * @return a list of songs match the songToBeMatched
     */
    public List<Song> findMatch(){
        List<Song> matchedSongs = new ArrayList<Song>();
        for (Song songDB: songDatabase) {
            Matcher matcher = new Matcher(songToBeMatched, songDB);
            if (matcher.boyerMooreMatch() != -1){
                matchedSongs.add(songDB);
            } else {
                if (matcher.calcSimilarityPercentage() >= 0.75){
                    matchedSongs.add(songDB);
                }
            }
        }
        return matchedSongs;
    }
}
