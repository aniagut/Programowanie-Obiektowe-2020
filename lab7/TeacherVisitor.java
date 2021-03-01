package lab7;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class TeacherVisitor implements TimetableElementVisitor {
    private String name;
    private Day day;

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setDay(Day day){
        this.day=day;
    }
    public Day getDay(){
        return this.day;
    }

    public TeacherVisitor(String name, Day day){
        this.name=name;
        this.day=day;
    }
    @Override
    public void visit(Lesson lesson){

    }
    @Override
    public void visit(Break przerwa){

    }
    @Override
    public void visit(Timetable2 timetable) {
        System.out.println(this.name);
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
        while (j<len && tab[j].getterm().getday()==this.day && tab[j].getteacherName()!=this.name) {
                j += 1;
        }
        if(tab[j].getteacherName()==this.name){
            System.out.println(tab[j].getterm().gethour()+":"+tab[j].getterm().getminute()+"-"+tab[j].getterm().endTerm().gethour()+":"+tab[j].getterm().endTerm().getminute()+ "   "+tab[j].getname());
            Term last=tab[j].getterm();
            j+=1;
            int k=0;
            while(j<len && tab[j].getterm().getday()==this.day){
                if(tab[j].getteacherName()==this.name){
                    System.out.println(tab[j].getterm().gethour()+":"+tab[j].getterm().getminute()+"-"+tab[j].getterm().endTerm().gethour()+":"+tab[j].getterm().endTerm().getminute()+"   "+tab[j].getname());
                    Term currterm=tab[j].getterm();
                    while(k<len2 && przerwy[k].inMinute()<=last.inMinute()) k+=1;
                    while (k<len2 && przerwy[k].inMinute()<currterm.inMinute()){
                        czasbezczynnosci+=przerwy[k].duration;
                        k+=1;
                    }
                    last=currterm;
                }
                j+=1;
            }
        }
        System.out.println("Czas bezczynnosci: "+czasbezczynnosci);
    }
}
