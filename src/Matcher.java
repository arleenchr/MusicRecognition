import java.util.*;

/**
 * Class Matcher represents the Solver of the Music Recognition
 *
 * @mainSong the song to be checked
 * @patternSong the pattern to be compared with the mainSong
 */
public class Matcher implements IBoyerMoore, ILevensthein {
    /* attributes */
    private Song mainSong;
    private Song patternSong;

    /* constructor */
    public Matcher(Song mainSong, Song patternSong){
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
            if (patternSongArr[j].equals(mainSongArr[i]) ||
                    Math.abs(Double.parseDouble(mainSongArr[i]) - Double.parseDouble(patternSongArr[j])) / Double.parseDouble(patternSongArr[j]) >= 0.75){
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
        int idx = patternSongArr.length-1;
        while (idx>=0){
            if (patternSongArr[idx].equals(note)){
                return idx;
            } else {
                idx--;
            }
        }
        return -1;
    }

    /**
     * Method levensthein to create the Levensthein matrix used for calculating the similarity percentage between the mainSong and the patternSong
     * @param mainSongArr the mainSong (array of String)
     * @param patternSongArr the patternSong (array of String)
     * @return the value stored in the matrix at the last cell (last_row, last_col)
     */
    public int levensthein(String[] mainSongArr, String[] patternSongArr) {
        int mainSongLength = mainSongArr.length;
        int patternSongLength = patternSongArr.length;
        int[][] levenstheinMatrix = new int[mainSongLength+1][patternSongLength+1];
        Arrays.stream(levenstheinMatrix).forEach(elmt -> Arrays.fill(elmt, 0));

        for (int i=1; i<=mainSongLength; i++){
            levenstheinMatrix[i][0] = i;
        }
        for (int j=1; j<=patternSongLength; j++){
            levenstheinMatrix[0][j] = j;
        }

        for (int i=1; i<=mainSongLength; i++){
            for (int j=1; j<=patternSongLength; j++){
                if (mainSongArr[i-1] == patternSongArr[j-1] ||
                        Math.abs(Double.parseDouble(mainSongArr[i-1]) - Double.parseDouble(patternSongArr[j-1])) / Double.parseDouble(patternSongArr[j-1]) >= 0.75){
                    levenstheinMatrix[i][j] = levenstheinMatrix[i-1][j-1];
                } else {
                    levenstheinMatrix[i][j] = Math.min(levenstheinMatrix[i-1][j-1], Math.min(levenstheinMatrix[i-1][j], levenstheinMatrix[i][j-1])) + 1;
                }
            }
        }

        return levenstheinMatrix[mainSongLength][patternSongLength];
    }

    /**
     * Method calcSimilarityPercentage to calculate the similarity percentage between the mainSong and the patternSong.
     * Using the method levensthein to calculate the value
     * @return similarity percentage percentage with range [0..1]
     */
    public Double calcSimilarityPercentage() {
        String[] mainSongArr = mainSongToString().split(" ");
        String[] patternSongArr = patternSongToString().split(" ");

        int levenstheinValue = levensthein(mainSongArr, patternSongArr);
        Double similarity = 0.;
        int maxLength = Math.max(mainSongArr.length, patternSongArr.length);
        if (maxLength != 0){
            similarity = Double.valueOf(maxLength - levenstheinValue) / Double.valueOf(maxLength);
        } else {
            similarity = 1.;
        }
        return similarity;
    }


}
