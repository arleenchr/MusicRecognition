/**
 * Class Note represents a note
 *
 * @start the start of the note (in beats), starts from 1
 * e.g.: 1. for a note on the beginning of a song/piece,
 *       1.5 for a note after a half of a beat rest on the beginning of a song/piece
 * @duration the duration of the note (in beats)
 * e.g.: 4. for a semibreve note,
 *       2. for a minim note,
 *       1. for a crotchet note,
 *       0.5 for a quaver note, or
 *       0.25 for a semiquaver note
 * @pitch the pitch of the note (in Hz)
 * e.g.: 440. for standard A (pitch standard)
 */
public class Note {
    /* attributes */
    private Double start;
    private Double duration;
    private Double pitch;

    /* constructor */
    public Note(Double start, Double duration, Double pitch){
        this.start = start;
        this.duration = duration;
        this.pitch = pitch;
    }

    /* getter and setter */
    public Double getStart(){
        return start;
    }
    public Double getDuration(){
        return duration;
    }
    public Double getPitch(){
        return pitch;
    }

    public void setStart(Double start){
        this.start = start;
    }
    public void setDuration(Double duration){
        this.duration = duration;
    }
    public void setPitch(Double pitch){
        this.pitch = pitch;
    }

    /* other methods */
    /**
     * Method transpose to transpose a note by a value of an interval number
     * Calculation based on how to calculate pitch and frequency.
     * frequency = base_frequency * 2^(interval_from_base / 12)
     * In this program, A (440 Hz) used as the base frequency
     * e.g.: A (440 Hz) transposed by +2 will result B (440 * 2^(2/12) = 493.88 Hz)
     * @param numTranspose value of an interval number [...,-2,-1,0,1,2,...]
     */
    public void transpose(int numTranspose){
        pitch *= Math.pow(2., (Double.valueOf(numTranspose) / 12.));
    }
}
