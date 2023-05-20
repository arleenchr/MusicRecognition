public class Main {
    public static void main(String[] args) {
        Song song = new Song();
        song.addNote(new Note(6.,0.5,440.));
        song.addNote(new Note(1.,4.,440.));
        Note noteA = new Note(5.,1.,440.);
        song.addNote(noteA);
        song.addNote(new Note(6.5,0.75,440.));

        System.out.println(song.findMinimumDurationGrouping());

        song.removeNote(noteA);

        song.printSong();
    }
}