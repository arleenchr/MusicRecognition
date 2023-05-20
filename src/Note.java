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
}
