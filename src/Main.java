import java.util.List;

public class Main {
    public static void main(String[] args) {
        Song patternCinemaParadiso = new Song();
        patternCinemaParadiso.addNote(new Note(1.,1.5,466.2));      //Bb
        patternCinemaParadiso.addNote(new Note(2.5,0.5,293.6));     //D
        patternCinemaParadiso.addNote(new Note(3.,0.5,311.2));      //Eb
        patternCinemaParadiso.addNote(new Note(3.5,0.5,466.2));     //Bb
        patternCinemaParadiso.addNote(new Note(4.,0.5,440.));       //A
        patternCinemaParadiso.addNote(new Note(4.5,0.5,392.));      //G
        patternCinemaParadiso.addNote(new Note(5.,0.5,440.));       //A
        patternCinemaParadiso.addNote(new Note(5.5, 0.5, 523.2));   //C
        patternCinemaParadiso.addNote(new Note(6., 0.75, 698.5));   //F
        patternCinemaParadiso.addNote(new Note(6.75, 0.25, 311.2)); //Eb
        patternCinemaParadiso.addNote(new Note(7., 1., 311.2));     //Eb
        patternCinemaParadiso.addNote(new Note(8.,1.5, 293.6));     //D

        Song cinemaParadiso = new Song();
        cinemaParadiso.addNote(new Note(1.,2., 262.6));         //C
        cinemaParadiso.addNote(new Note(3.,1., 246.9));         //Bb
        cinemaParadiso.addNote(new Note(4.,0.25, 246.9));       //Bb
        cinemaParadiso.addNote(new Note(4.25,0.25, 293.6));     //D
        cinemaParadiso.addNote(new Note(4.5,0.25, 698.5));      //F
        cinemaParadiso.addNote(new Note(4.75,0.25, 440.));      //A
        cinemaParadiso.addNote(new Note(5.,1.5,466.2));         //Bb
        cinemaParadiso.addNote(new Note(6.5,0.5,293.6));        //D
        cinemaParadiso.addNote(new Note(7.,0.5,311.2));         //Eb
        cinemaParadiso.addNote(new Note(7.5,0.5,466.2));        //Bb
        cinemaParadiso.addNote(new Note(8.,0.5,440.));          //A
        cinemaParadiso.addNote(new Note(8.5,0.5,392.));         //G
        cinemaParadiso.addNote(new Note(9.,0.5,440.));          //A
        cinemaParadiso.addNote(new Note(9.5, 0.5, 523.2));      //C
        cinemaParadiso.addNote(new Note(10., 0.75, 698.5));     //F
        cinemaParadiso.addNote(new Note(10.75, 0.25, 311.2));   //Eb
        cinemaParadiso.addNote(new Note(11., 1., 311.2));       //Eb
        cinemaParadiso.addNote(new Note(12.,1.5, 293.6));       //D

        /*Matcher matcher = new Matcher(cinemaParadiso, patternCinemaParadiso);
        String[] s1 = {"j","o","h","n"};
        String[] s2 = {"j","o","n","n","y"};
        int levenstheinval = matcher.levensthein(s1,s2);
        Double similarity = Double.valueOf(5 - levenstheinval) / Double.valueOf(5);
        System.out.println(String.format("levensthein value = %d, similarity = %.2f", levenstheinval, similarity));*/

        Note A = new Note(0.,1.,440.);
        System.out.println(A.getPitch());
        A.transpose(-2);
        System.out.println(A.getPitch());

        Solver solver = new Solver(cinemaParadiso);
        solver.addSongDatabase(patternCinemaParadiso);
        List<Song> matchCinemaParadiso = solver.findMatch();
        for (Song match : matchCinemaParadiso){
            match.printSong();
        }
    }
}