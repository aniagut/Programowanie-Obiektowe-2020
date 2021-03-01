package lab7;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class StudentVisitor implements TimetableElementVisitor {
    private int year;
    private boolean full_time;
    private Day day;

    public void setYear(int year){
        this.year=year;
    }
    public int getYear(){
        return this.year;
    }
    public void setFull_time(boolean full_time){
        this.full_time=full_time;
    }
    public boolean getFull_time(){
        return this.full_time;
    }
    public void setDay(Day day){
        this.day=day;
    }
    public Day getDay(){
        return this.day;
    }

    public StudentVisitor(int year, boolean full_time, Day day){
        this.year=year;
        this.full_time=full_time;
        this.day=day;
    }
    @Override
    public void visit(Lesson lesson){

    }
    @Override
    public void visit(Break przerwa){

    }
    @Override
    public void visit(Timetable2 timetable){
        String typ;
        if(this.full_time==true) typ="stacjonarne";
        else typ="niestacjonarne";
        System.out.println("Rok studiów "+this.year+" "+typ);
        System.out.println(this.day);
        LinkedHashMap<Integer, Lesson> map = timetable.getMap();
        Lesson lekcje[] = map.values().toArray(new Lesson[0]);
        int len = map.size();
        Comparator<Lesson> comp1 = (Lesson e1, Lesson e2) -> (e1.compareTo1(e2));
        TreeSet<Lesson> lesson = new TreeSet<>(comp1);
        for (int i = 0; i < len; i++) {
            lesson.add(lekcje[i]);
        }
        Lesson tab[]=lesson.toArray(Lesson[]::new);
        BasicTerm przerwy[]=timetable.breaks.toArray(BasicTerm[]::new);
        int len2= przerwy.length;
        int j=0;
        int czasbezczynnosci=0;
        while(j<len && tab[j].getterm().getday()!=this.day) j+=1;
        while(j<len && tab[j].getterm().getday()==this.day && (tab[j].getyear()!=this.year||tab[j].getfull_time()!=this.full_time)) {
            j += 1;
        }
        if(tab[j].getfull_time()==this.full_time && tab[j].getyear()==this.year){
            System.out.println(tab[j].getterm().gethour()+":"+tab[j].getterm().getminute()+"-"+tab[j].getterm().endTerm().gethour()+":"+tab[j].getterm().endTerm().getminute()+"  "+tab[j].getname()+ " ("+tab[j].getteacherName()+")");
            Term last=tab[j].getterm();
            j+=1;
            int k=0;
            while(j<len && tab[j].getterm().getday()==this.day){
                if(tab[j].getyear()==this.year && tab[j].getfull_time()==this.full_time) {
                    System.out.println(tab[j].getterm().gethour() + ":" + tab[j].getterm().getminute() + "-" + tab[j].getterm().endTerm().gethour() + ":" + tab[j].getterm().endTerm().getminute() + "  " + tab[j].getname() + " (" + tab[j].getteacherName() + ")");
                    Term currterm = tab[j].getterm();
                    while (k < len2 && przerwy[k].inMinute() <= last.inMinute()) k += 1;
                    while (k < len2 && przerwy[k].inMinute() < currterm.inMinute()) {
                        czasbezczynnosci += przerwy[k].duration;
                        k += 1;
                    }
                    last = currterm;
                }
                j+=1;

            }
        }
        System.out.println("Czas bezczynnosci: "+czasbezczynnosci);
    }
}

