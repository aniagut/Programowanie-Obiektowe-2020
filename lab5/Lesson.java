package lab5;

public class Lesson {

    private Term term;
    private String name;
    private String teacherName;
    private int year;
    private boolean full_time;
    private ITimetable timetable;

    public void setterm(Term term) {
        this.term = term;
    }

    public Term getterm() {
        return term;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getname() {
        return name;
    }

    public void setteacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getteacherName() {

        return teacherName;
    }

    public void setyear(int year) {

        this.year = year;
    }

    public int getyear() {

        return year;
    }


    public void setTimetable(ITimetable timetable) {

        this.timetable = timetable;
    }

    public ITimetable getTimetable(){

        return timetable;
    }
    public void setFull_time(boolean full_time){

        this.full_time=full_time;
    }
    public boolean getfull_time(){

        return full_time;
    }


    public Lesson(ITimetable timetable, Term term, String name, String teacherName, int year) {
        this.timetable=timetable;
        this.term=term;
        this.name=name;
        this.teacherName=teacherName;
        this.year=year;
        this.full_time=true;

    }

    @Override
    public String toString() {

        String typ;
        String ktoryrok;

        if (this.full_time) {
            typ = "rok studiów stacjonarnych";
        } else typ = "rok studiów niestacjonarnych";

        ktoryrok = switch (this.year) {
            case 1 -> "Pierwszy";
            case 2 -> "Drugi";
            case 3 -> "Trzeci";
            case 4 -> "Czwarty";
            default -> "Piąty";
        };
        if (this.term.getminute() < 10 && this.term.endTerm().getminute() < 10) {
            return this.name + " (" + this.term.getday() + ", " +
                    this.term.gethour() + ":0" + this.term.getminute() +
                    "-" + this.term.endTerm().gethour() + ":0" +
                    this.term.endTerm().getminute() + ")\n" + ktoryrok + " " + typ + "\nProwadzący: " +
                    this.teacherName;
        } else if (this.term.getminute() < 10) {
            return this.name + " (" + this.term.getday() + ", " +
                    this.term.gethour() + ":0" + this.term.getminute() +
                    "-" + this.term.endTerm().gethour() + ":" +
                    this.term.endTerm().getminute() + ")\n" + ktoryrok + " " + typ + "\nProwadzący: " +
                    this.teacherName;
        } else if (this.term.endTerm().getminute() < 10) {
            return this.name + " (" + this.term.getday() + ", " +
                    this.term.gethour() + ":" + this.term.getminute() +
                    "-" + this.term.endTerm().gethour() + ":0" +
                    this.term.endTerm().getminute() + ")\n" + ktoryrok + " " + typ + "\nProwadzący: " +
                    this.teacherName;
        }

        return this.name + " (" + this.term.getday() + ", " +
                this.term.gethour() + ":" + this.term.getminute() +
                "-" + this.term.endTerm().gethour() + ":" +
                this.term.endTerm().getminute() + ")\n" + ktoryrok + " " + typ + "\nProwadzący: " +
                this.teacherName;
    }



    public boolean earlierDay() {
        Day[] array = Day.values();
        int i = this.term.getday().ordinal();
        int n;
        if(i==0){
            n=array.length-1;
        }
        else n=i-1;
        Term term1 = new Term(this.term.gethour(),this.term.getminute(),array[n]);
        return this.timetable.canBeTransferredTo(term1, this.full_time);
    }

    public boolean laterDay() {
        Day array[] = Day.values();
        int i = this.term.getday().ordinal();
        int n;
        if(i== array.length-1){
            n=0;
        }
        else n=i+1;
        Term term1 = new Term(this.term.gethour(),this.term.getminute(),array[n]);
        return this.timetable.canBeTransferredTo(term1, this.full_time);
    }

    public boolean earlierTime() {
        Term term=this.term;
        int godz = 0;
        int czas = term.inMinute();
        czas -= term.getduration();
        while (czas >= 60) {
            godz += 1;
            czas -= 60;
        }
        Term term1 = new Term(godz,czas,term.getday());

        return this.timetable.canBeTransferredTo(term1, this.full_time);
    }

    public boolean laterTime() {
        Term term=this.term;
        int godz = 0;
        int czas = term.inMinute();
        czas += term.getduration();
        while (czas >= 60) {
            godz += 1;
            czas -= 60;
        }
        Term term1 = new Term(godz,czas,term.getday());
        return this.timetable.canBeTransferredTo(term1, this.full_time);
    }
}
