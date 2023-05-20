public class Note {
    private Double start;
    private Double duration;
    private Double pitch;

    public Note(Double start, Double duration, Double pitch){
        this.start = start;
        this.duration = duration;
        this.pitch = pitch;
    }

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
