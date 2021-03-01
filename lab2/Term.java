package lab2;

public class Term {
    final int hour;
    final int minute;
    int duration;

    public Term(int hour, int minute){
        this.hour=hour;
        this.minute=minute;
        this.duration=90;
    }
    public String toString() {
        return this.hour + ":" +
                this.minute +
                " [" + this.duration +
                "]";
    }
    public boolean earlierThan(Term term){
        if(this.hour< term.hour || (this.hour==term.hour && this.minute<term.minute)){
            return true;
        }
        return false;
    }
    public boolean laterThan(Term term){
        if(this.hour>term.hour || (this.hour==term.hour && this.minute>term.minute)){
            return true;
        }
        return false;
    }
    public Term endTerm(Term term){
        Term term1=new Term(this.hour, this.minute);
        int x=term.hour*60+term.minute;
        int y=this.hour*60+this.minute;
        term1.duration=x-y;
        return term1;
    }
    public Term endTerm(){
        int godz=this.hour;
        int min=this.minute+this.duration;
        while(min>=60){
            godz+=1;
            min-=60;
        }
        Term term1=new Term(godz,min);
        term1.duration=this.duration;
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
        if(this.hour!=term.hour){
            return false;
        }
        if(this.minute!=term.minute){
            return false;
        }
        if(this.duration!=term.duration){
            return false;
        }
        return true;
    }
}
