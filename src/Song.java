import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class Song represents a song/piece constructed by a list of notes
 *
 * @notes a list of notes that builds up a song/piece
 */
public class Song {
    /* attributes */
    private List<Note> notes;

    /* constructor */
    public Song(){
        notes = new ArrayList<>();
    }
    public Song(List<Note> notes){
        this.notes = notes;
    }

    /* getter and setter */
    public List<Note> getNotes(){
        return notes;
    }
    public Note getNote(int idx){
        return notes.get(idx);
    }
    public void setNotes(List<Note> notes){
        this.notes = notes;
    }

    /* other methods */

    /**
     * Method containsNote to check whether a note exists in a song/piece
     * @param note note to be checked
     * @return true if the song/piece contains the note,
     * false if the song/piece does not contain the note
     */
    public boolean containsNote(Note note){
        return notes.contains(note);
    }

    /**
     * Method addNote to add a note to a song/piece,
     * while keeping the list of notes sorted by the start of each notes
     * @param note the note to be inserted
     */
    public void addNote(Note note){
        notes.add(note);
        notes.sort((s1,s2)
                -> s1.getStart().compareTo(
                        s2.getStart()));
    }

    /**
     * Method removeNote to remove a note from a song/piece
     * @param note the note to be removed
     */
    public void removeNote(Note note){
        notes.remove(note);
    }

    /**
     * Method removeNote to remove a note from a song/piece by its index in the list of notes
     * @param idx the index of the note to be removed
     */
    public void removeNote(int idx){
        notes.remove(idx);
    }

    /**
     * Method setNoteIdx to set a note by an index of the list of notes
     * @param idx the index of the note to be modified
     * @param note the new note
     */
    public void setNoteIdx(int idx, Note note){
        notes.set(idx, note);
    }

    /**
     * Method findMinimumDurationGrouping to find the minimum of the duration grouping of the song/piece
     * The minimum duration of grouping is the minimum value of beats in the song/piece, or
     * the minimum grouping value of beats in the song/piece.
     * @return the minimum duration grouping (in beats)
     * e.g.: if a song contains two notes with 0.5 beats and 0.75 beats, then the minimum duration grouping is 0.25
     */
    public Double findMinimumDurationGrouping(){
        Double min = notes.stream()
                .min(Comparator.comparing(Note::getDuration))
                .orElseThrow(NoSuchElementException::new)
                .getDuration();
        for (Note note : notes) {
            if (note.getDuration() % min > 0 && note.getDuration() % min < min){
                min = note.getDuration() % min;
            }
        }
        return min;
    }

    /**
     * Method toString to convert a song to a string, according to the value of minimum duration grouping
     * The String represented by the notes' pitch, separated by a space ' '
     * e.g.: a song that contains (1.,1.5,466.2), (2.5,0.5,293.6), (3.,0.5,311.2), (3.5,0.5,466.2), (4.,0.5,440.), (4.5,0.5,392.) with minimum duration grouping of 0.5
     * represented as "466.2 466.2 466.2 293.6 311.2 466.2 440.0 392.0"
     * @param minimumDurationGrouping the value of the minimum duration grouping
     * @return a string represents the song/piece
     */
    public String toString(Double minimumDurationGrouping){
        StringBuilder songStr = new StringBuilder();
        for (Note note : notes){
            for (int i = 0; i < note.getDuration()/minimumDurationGrouping; i++){
                songStr.append(String.format("%.1f ",note.getPitch()).toString());
            }
        }
        songStr.deleteCharAt(songStr.length()-1); // remove last whitespace
        return songStr.toString();
    }

    /**
     * Method transpose to transpose all notes in the song by a value of an interval number
     * @param numTranspose value of an interval number [...,-2,-1,0,1,2,...]
     */
    public void transpose(int numTranspose){
        for (Note note : notes){
            note.transpose(numTranspose);
        }
    }

    /**
     * Method printSong to print a song/piece
     * e.g.:
     * (start, duration, pitch)
     * (1.00, 1.50, 466.2)
     * (2.50, 0.50, 293.6)
     * (3.00, 0.50, 311.2)
     * (3.50, 0.50, 466.2)
     * (4.00, 0.50, 440.0)
     * (4.50, 0.50, 392.0)
     */
    public void printSong(){
        System.out.println("(start, duration, pitch)");
        for (Note note : notes) {
            System.out.println(String.format("(%.2f, %.2f, %.1f)", note.getStart(), note.getDuration(), note.getPitch()));
        }
    }
}
