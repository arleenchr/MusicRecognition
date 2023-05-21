import java.util.List;

public class Main {
    public static Solver initSolver(){
        /* Database song 1: Love Theme from Cinema Paradiso */
        Song patternCinemaParadiso = new Song("Love Theme from Cinema Paradiso");
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

        /* Database song 2: Salut d'Amour */
        Song patternSalutdAmour = new Song("Salut d'Amour");
        patternSalutdAmour.addNote(new Note(1.,1.,830.61));         //G#
        patternSalutdAmour.addNote(new Note(2.,0.5,493.88));        //B
        patternSalutdAmour.addNote(new Note(2.5,0.5,830.61));       //G#
        patternSalutdAmour.addNote(new Note(3.,0.5,739.99));        //F#
        patternSalutdAmour.addNote(new Note(3.5,0.5,659.25));       //E
        patternSalutdAmour.addNote(new Note(4.,0.5,622.25));        //D#
        patternSalutdAmour.addNote(new Note(4.5,0.5,659.25));       //E
        patternSalutdAmour.addNote(new Note(5.,1.,880.));           //A
        patternSalutdAmour.addNote(new Note(6.,1.,880.));           //A
        patternSalutdAmour.addNote(new Note(7.,1.,880.));           //A
        patternSalutdAmour.addNote(new Note(8.,1.,493.88));         //B

        Solver solver = new Solver();
        solver.addSongDatabase(patternCinemaParadiso);
        solver.addSongDatabase(patternSalutdAmour);
        return solver;
    }
    public static void main(String[] args) {
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

        Solver solver = initSolver();
        solver.setSongToBeMatched(cinemaParadiso);
        List<Song> matchCinemaParadiso = solver.findMatch();
        for (Song match : matchCinemaParadiso){
            match.printSong();
        }
    }
}