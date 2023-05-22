import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        Song inputSong = new Song();
        boolean isInputValid = false;
        List<Note> notes = new ArrayList<Note>();

        while (!isInputValid){
            System.out.println("Please input the song you want to search!");
            System.out.println("<start> <duration> <pitch>");
            System.out.println("Input -1 to stop");

            String note = "";
            notes.clear();
            do {
                Scanner inputScan = new Scanner(System.in);
                note = inputScan.nextLine();
                if (note.equals("-1")){
                    isInputValid = true;
                    break;
                }
                String[] noteComponent = note.split(" ");
                if (noteComponent.length != 3){
                    break;
                }
                Double start = Double.parseDouble(noteComponent[0]);
                Double duration = Double.parseDouble(noteComponent[1]);
                Double pitch = Double.parseDouble(noteComponent[2]);
                if (start<1. || duration<0. || pitch<0.){
                    break;
                }
                notes.add(new Note(start, duration, pitch));
            } while (!note.equals("-1"));
        }

        inputSong.setNotes(notes);

        if (notes.size() > 0){
            Solver solver = initSolver();
            solver.setSongToBeMatched(inputSong);
            List<Song> matchInputSong = solver.findMatch();
            if (matchInputSong.size()>0){
                System.out.println(String.format("%d matches found!", matchInputSong.size()));
                for (Song match : matchInputSong){
                    System.out.println(match.getTitle());
                }
            } else {
                System.out.println("No matches found!");
            }
        }
    }
}