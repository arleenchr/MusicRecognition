public class Main {
    public static void main(String[] args) {
        Song song = new Song();
        Note note1 = new Note(1.,4.,440.);
        song.AddNote(note1);
        song.PrintSong();
    }
}