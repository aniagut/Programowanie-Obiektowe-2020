package lab6;

public class BasicTerm {

    protected int hour;
    protected void sethour(int hour){

        this.hour=hour;
    }
    protected int gethour(){

        return hour;
    }
    protected int minute;
    protected void setminute(int minute){
        this.minute=minute;
    }
    protected int getminute(){
        return minute;
    }
    protected int duration;
    protected void setduration(int duration){
        this.duration=duration;
    }
    protected int getduration(){
        return duration;
    }
    protected BasicTerm(int hour,int minute){
        this.hour=hour;
        this.minute=minute;
        this.duration=5;
    }
    protected int inMinute(){
        int min=this.hour*60+this.minute;
        return min;
    }
    protected BasicTerm endTerm() {
        BasicTerm term1;
        int time=this.inMinute()+this.duration;
        int godz=0;
        while (time >= 60) {
            godz += 1;
            time-=60;
        }
        term1 = new BasicTerm(godz,time);
        term1.setduration(this.duration);

        return term1;
    }
}
