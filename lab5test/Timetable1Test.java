package lab5;


import org.junit.Test;

import static org.junit.Assert.*;

public class Timetable1Test {
    ITimetable timetable=new Timetable1();

    @Test
    public void canBeTransferredTo() {
        Lesson lekcja1=new Lesson(timetable,new Term(9,30, Day.MON),"PO","Stanisław Polak",2);
        Lesson lekcja2=new Lesson(timetable,new Term(11,0,Day.MON),"PO","Stanisław Polak",2);
        timetable.put(lekcja1);
        timetable.put(lekcja2);
        assertFalse(lekcja1.getTimetable().canBeTransferredTo(new Term(11,0,Day.MON),true));
        assertTrue(lekcja2.getTimetable().canBeTransferredTo(new Term(14,0,Day.FRI),true));
        assertFalse(lekcja1.getTimetable().canBeTransferredTo(new Term(12,35,Day.SAT),true));

    }

    @Test
    public void busy() {
        Lesson lekcja1=new Lesson(timetable,new Term(9,30,Day.MON),"PO","Stanisław Polak",2);
        Lesson lekcja2=new Lesson(timetable,new Term(11,0,Day.MON),"PO","Stanisław Polak",2);
        timetable.put(lekcja1);
        timetable.put(lekcja2);
        assertTrue(timetable.busy(new Term(11,0,Day.MON)));
        assertFalse(timetable.busy(new Term(8,0,Day.MON)));

    }

    @Test
    public void put() {
        Lesson lekcja=new Lesson(timetable,new Term(14,0,Day.TUE),"abc","",4);
        timetable.put(lekcja);
        assertEquals(timetable.get(new Term(14,0,Day.TUE)),"abc");

    }

    @Test
    public void perform() {
        Lesson lekcja=new Lesson(timetable,new Term(14,0,Day.TUE),"abc","",4);
        Lesson lekcja1=new Lesson(timetable,new Term(11,0,Day.MON),"abc","",4);
        timetable.put(lekcja);
        timetable.put(lekcja1);
        String args[]={"t+","d+","d-","t-"};
        Action actions[]=new ActionsParser().parse(args);
        timetable.perform(actions);
        assertEquals(lekcja.getterm().toString(),"Poniedziałek 15:30 [90]");
        assertEquals(lekcja1.getterm().toString(),"Wtorek 9:30 [90]");

    }

    @Test
    public void get() {
        Lesson lekcja=new Lesson(timetable,new Term(14,0,Day.TUE),"abc","",4);
        Lesson lekcja1=new Lesson(timetable,new Term(11,0,Day.MON),"efg","",4);
        timetable.put(lekcja);
        timetable.put(lekcja1);
        assertEquals(timetable.get(new Term(14,0,Day.TUE)),"abc");
        assertEquals(timetable.get(new Term(11,0,Day.MON)),"efg");
        assertEquals(timetable.get(new Term(14,0,Day.SAT)),null);
    }

}