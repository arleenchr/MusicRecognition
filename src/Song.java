import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Song {
    private List<Note> song;

    public Song(){
        song = new ArrayList<>();
    }
    public Song(List<Note> song){
        this.song = song;
    }

    public boolean containsNote(Note note){
        return song.contains(note);
    }

    public Note getNote(int idx){
        return song.get(idx);
    }

    public void addNote(Note note){
        song.add(note);
        song.sort((s1,s2)
                -> s1.getStart().compareTo(
                        s2.getStart()));
    }

    public void removeNote(Note note){
        song.remove(note);
    }

    public void removeNote(int idx){
        song.remove(idx);
    }

    public Double findMinimumDurationGrouping(){
        Double min = song.stream()
                .min(Comparator.comparing(Note::getDuration))
                .orElseThrow(NoSuchElementException::new)
                .getDuration();
        for (Note note : song) {
            if (note.getDuration() % min > 0 && note.getDuration() % min < min){
                min = note.getDuration() % min;
            }
        }
        return min;
    }

    public void printSong(){
        System.out.println("(start, duration, pitch)");
        for (Note note: song) {
            System.out.println(String.format("(%.2f, %.2f, %.2f)", note.getStart(), note.getDuration(), note.getPitch()));
        }
    }
}
