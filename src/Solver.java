import java.util.*;

/**
 * Class Solver represents the Solver of the Music Recognition
 *
 * @mainSong the song to be checked
 * @patternSong the pattern to be compared with the mainSong
 */
public class Solver implements IBoyerMoore {
    /* attributes */
    private Song mainSong;
    private Song patternSong;

    /* constructor */
    public Solver(Song mainSong, Song patternSong){
        this.mainSong = mainSong;
        this.patternSong = patternSong;
    }

    /* getter and setter */
    public Song getMainSong(){
        return mainSong;
    }
    public Song getPatternSong(){
        return patternSong;
    }
    public void setMainSong(Song mainSong){
        this.mainSong = mainSong;
    }
    public void setPatternSong(Song patternSong){
        this.patternSong = patternSong;
    }

    /* other methods */
    /**
     * Method findMinimumDurationGrouping to get the minimum duration grouping of the main song and the pattern song
     * @return the value of the minimum duration grouping
     */
    public Double findMinimumDurationGrouping(){
        return (mainSong.findMinimumDurationGrouping() < patternSong.findMinimumDurationGrouping()) ?
                mainSong.findMinimumDurationGrouping() : patternSong.findMinimumDurationGrouping();
    }

    /**
     * Method mainSongToString to convert the mainSong to a string,
     * according to the value of minimum duration grouping between the mainSong and the patternSong
     * The String represented by the notes' pitch, separated by a space ' '
     * @return a string represents the mainSong
     */
    public String mainSongToString(){
        return mainSong.toString(findMinimumDurationGrouping());
    }

    /**
     * Method patternSongToString to convert the patternSong to a string,
     * according to the value of minimum duration grouping between the mainSong and the patternSong
     * The String represented by the notes' pitch, separated by a space ' '
     * @return a string represents the patternSong
     */
    public String patternSongToString(){
        return patternSong.toString(findMinimumDurationGrouping());
    }

    /* Boyer-Moore Algorithm */

    /**
     * Methods boyerMooreMatch to find the index where the patternSong matches in the mainSong
     * Pattern matching Boyer Moore Algorithm
     * @return index where the patternSong starts in the mainSong (if pattern not found, returns -1)
     */
    public int boyerMooreMatch(){
        String[] mainSongArr = mainSongToString().split(" ");
        String[] patternSongArr = patternSongToString().split(" ");
        Map<String, Integer> lastOccurence = lastOccurence(mainSongArr, patternSongArr);

        int n = mainSongArr.length;
        int m = patternSongArr.length;
        int i = m-1;
        if (i > n-1){
            return -1;
        }
        int j = m-1;
        do {
            if (patternSongArr[j].equals(mainSongArr[i])){
                if (j==0){
                    return i; // matches
                }
                i--;
                j--;
            } else {
                int last = lastOccurence.get(mainSongArr[i]);
                i += m - Math.min(j, 1+last);
                j = m-1;
            }
        } while (i<=n-1);
        return -1;
    }

    /**
     * Method lastOccurence to create a map to find the last occurence of a note in the patternSong.
     * For each notes in the mainSong, search for its last occurence in the patternSong.
     * @param mainSongArr the mainSong (array of String)
     * @param patternSongArr the patternSong (array of String)
     * @return the Map with the pitch (String) as the key and the last index occurence as the value
     */
    public Map<String, Integer> lastOccurence(String[] mainSongArr, String[] patternSongArr){
        Map<String, Integer> last = new HashMap<String, Integer>();
        for (String note : mainSongArr){
            if (!last.containsKey(note)){
                last.put(note, searchLastIdx(note, patternSongArr));
            }
        }
        return last;
    }

    /**
     * Method searchLastIndex to search the last index of an element exists in the patternSong
     * @param note the note to be searched in the patternSong array
     * @param patternSongArr the patternSong
     * @return the last index of the note in the patternSong (returns -1 if not found)
     */
    public int searchLastIdx(String note, String[] patternSongArr){
        int idx = patternSongArr.length;
        while (idx>=0){
            if (patternSongArr[idx].equals(note)){
                return idx;
            } else {
                idx--;
            }
        }
        return -1;
    }
}
