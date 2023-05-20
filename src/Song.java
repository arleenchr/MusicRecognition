import java.util.List;
import java.util.ArrayList;

public class Song {
    private List<Note> song;

    public Song(){
        song = new ArrayList<>();
    }
    public Song(List<Note> song){
        this.song = song;
    }

    public boolean ContainsNote(Note note){
        return song.contains(note);
    }

    public Note getNote(int idx){
        return song.get(idx);
    }

    public void AddNote(Note note){
        song.add(note);
        song.sort((s1,s2)
                -> s1.getStart().compareTo(
                        s2.getStart()));
    }

    public void RemoveNote(Note note){
        song.remove(note);
    }

    public void RemoveNote(int idx){
        song.remove(idx);
    }

    public void PrintSong(){
        System.out.println("(start, duration, pitch)");
        for (Note note: song) {
            System.out.println(String.format("(%.2f, %.2f, %.2f)", note.getStart(), note.getDuration(), note.getPitch()));
        }
    }
}
