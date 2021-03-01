package lab7;

import java.util.LinkedHashMap;
import java.util.TreeSet;
import java.util.Comparator;
public class DeanerySystem {
    public static void main(String[] args) {
        Comparator<BasicTerm> comp = (BasicTerm o1, BasicTerm o2) -> (o1.compareTo(o2));
        TreeSet<BasicTerm> breaks = new TreeSet<>(comp);

        BasicTerm przerwa1=new BasicTerm(11,5);
        BasicTerm przerwa2=new BasicTerm(14,20);
        przerwa1.setduration(10);
        przerwa2.setduration(20);
        breaks.add(przerwa1);
        breaks.add(new BasicTerm(12, 45));
        breaks.add(new BasicTerm(9,30));
        breaks.add(przerwa2);
        breaks.add(new BasicTerm(16,10));
        breaks.add(new BasicTerm(17,45));
        BasicTerm przerwy[]=breaks.toArray(BasicTerm[]::new);
        int n=breaks.size();
        for(int i=0;i<n;i++){
            System.out.println(przerwy[i].gethour()+":"+przerwy[i].getminute()+"-"+przerwy[i].endTerm().gethour()+":"+przerwy[i].endTerm().getminute());
        }

        Timetable2 timetable=new Timetable2(breaks);
        Lesson l1=new Lesson(timetable,new Term(8,0,Day.MON),"Fizyka-W","Kąkol",2);
        Lesson l2=new Lesson(timetable,new Term(17,50,Day.MON),"PO-W","Smywiński-Pohl",2);
        Lesson l3=new Lesson(timetable,new Term(8,0,Day.TUE),"RPIS-W","Smołka",2);
        Lesson l4=new Lesson(timetable,new Term(9,35,Day.TUE),"RRIR-W","Schaefer",2);
        Lesson l5=new Lesson(timetable,new Term(11,15,Day.TUE),"RPIS","Smołka",2);
        Lesson l6=new Lesson(timetable,new Term(16,15,Day.TUE),"Geometryczne-W","Głut",2);
        Lesson l7=new Lesson(timetable,new Term(8,0,Day.WED),"Funkcyjne-W","Dębski",2);
        Lesson l8=new Lesson(timetable,new Term(9,35,Day.WED),"Bazy Danych-W","Zygmunt",2);
        Lesson l9=new Lesson(timetable,new Term(12,50,Day.WED),"PO","Polak",2);
        Lesson l11=new Lesson(timetable,new Term(17,50,Day.WED),"Grafowe-W","Meszka",2);
        Lesson l12=new Lesson(timetable,new Term(9,35,Day.THU),"RRIR","Woźniak",2);
        Lesson l13=new Lesson(timetable,new Term(11,15,Day.THU),"Funkcyjne","Łoś",2);
        Lesson l14=new Lesson(timetable,new Term(14,40,Day.THU),"Bazy danych","Marcjan",2);
        Lesson l15=new Lesson(timetable,new Term(16,15,Day.THU),"Geometryczne","Głut",2);
        Lesson l16=new Lesson(timetable,new Term(17,50,Day.THU),"Grafowe","Łoś",2);
        Lesson l17=new Lesson(timetable,new Term(12,45,Day.FRI),"Fizyka-L","Armatys",2);
        timetable.put(l1);
        timetable.put(l2);
        timetable.put(l3);
        timetable.put(l4);
        timetable.put(l5);
        timetable.put(l6);
        timetable.put(l7);
        timetable.put(l8);
        timetable.put(l9);
        timetable.put(l11);
        timetable.put(l12);
        timetable.put(l13);
        timetable.put(l14);
        timetable.put(l15);
        timetable.put(l16);
        timetable.put(l17);

        LinkedHashMap<Integer,Lesson> lesson_map=timetable.getMap();
        Lesson lekcje[]=lesson_map.values().toArray(new Lesson[0]);
        int len= lesson_map.size();

        Comparator<Lesson> comp1 = (Lesson e1, Lesson e2) -> (e1.compareTo1(e2));
        TreeSet<Lesson> lesson= new TreeSet<>(comp1);
        for(int i=0;i<len;i++){
            lesson.add(lekcje[i]);
        }
        Lesson tab[]=lesson.toArray(Lesson[]::new);
        int n1= tab.length;
        for(int i=0;i<n1;i++){
            System.out.println(tab[i]);
        }

        System.out.println(timetable);
        TeacherVisitor nauczyciel1=new TeacherVisitor("Smywiński-Pohl",Day.MON);
        TeacherVisitor nauczyciel2=new TeacherVisitor("Smołka",Day.TUE);
        TeacherVisitor nauczyciel3=new TeacherVisitor("Głut",Day.WED);
        timetable.accept(nauczyciel1);
        timetable.accept(nauczyciel2);
        timetable.accept(nauczyciel3);
        StudentVisitor student1=new StudentVisitor(2,true,Day.TUE);
        StudentVisitor student2=new StudentVisitor(2,true,Day.THU);
        timetable.accept(student1);
        timetable.accept(student2);

        Action[] actions = new ActionsParser().parse(args);
        timetable.perform(actions);
        System.out.println(timetable);
    }
}
