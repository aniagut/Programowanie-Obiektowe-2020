package lab5;

public class Term extends BasicTerm {

    private Day day;
    private boolean skipBreaks;

    public void setday(Day day){
        this.day=day;
    }
    public Day getday(){
        return day;
    }

    public void setSkipBreaks(boolean skipBreaks) {
        this.skipBreaks = skipBreaks;
    }
    public boolean getskipBreaks(){
        return skipBreaks;
    }

    public Term(int hour, int minute,Day day){
        super(hour,minute);
        this.duration=90;
        this.day=day;
        this.skipBreaks=true;

    }
    public String toString() {
        if(this.minute<10){
            return this.day+" "+ this.hour + ":0" +
                    this.minute +
                    " [" + this.duration +
                    "]";
        }
        return this.day+" "+ this.hour + ":" +
                this.minute +
                " [" + this.duration +
                "]";
    }

    public boolean earlierThan(Term term){
        if(this.day.ordinal()< term.getday().ordinal()){
            return true;
        }
        else if(this.day.ordinal()==term.getday().ordinal()){
            if(this.inMinute()<term.inMinute()) return true;
            else return false;
    }
        else return false;
    }

    public boolean laterThan(Term term){
        if(this.day.ordinal()>term.getday().ordinal()){
            return true;
        }
        else if(this.day.ordinal()==term.getday().ordinal()){
            if(this.inMinute()>term.inMinute()) return true;
            else return false;
        }
        else return false;
    }
    public Term endTerm(Term term){
        Term term1=new Term(this.hour,this.minute,this.day);
        int dur=term.inMinute()-this.inMinute();
        term1.setduration(dur);
        return term1;
    }
    public Term endTerm() {
        Term term1;
        int time=this.inMinute()+this.duration;
        int godz=0;
        while (time >= 60) {
            godz += 1;
            time-=60;
        }
        term1 = new Term(godz, time, this.day);
        term1.setduration(this.duration);

    return term1;
    }

    public boolean equals(Term term){
        if(this==term){
            return true;
        }
        if(term==null){
            return false;
        }
        if(getClass()!=term.getClass()){
            return false;
        }
        if(this.day!=term.getday()){
            return false;
        }
        if(this.hour!=term.gethour()){
            return false;
        }
        if(this.minute!=term.getminute()){
            return false;
        }
        if(this.duration!=term.getduration()){
            return false;
        }
        return true;
    }


}
