import java.util.Map;

public interface IBoyerMoore {
    public int boyerMooreMatch();
    public Map<String, Integer> lastOccurence(String[] mainSong, String[] patternSong);
}
