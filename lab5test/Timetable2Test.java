package lab5;


import org.junit.Test;

import static org.junit.Assert.*;

public class Timetable2Test {


    @Test
    public void canBeTransferredTo() {
        Break przerwa1=new Break(new BasicTerm(9,30));
        przerwa1.getterm().setduration(5);
        Break przerwa2=new Break(new BasicTerm(11,5));
        przerwa2.getterm().setduration(10);
        Break przerwa3=new Break(new BasicTerm(12,45));
        przerwa3.getterm().setduration(5);
        Break przerwa4=new Break(new BasicTerm(14,20));
        przerwa4.getterm().setduration(20);
        Break przerwa5=new Break(new BasicTerm(16,10));
        przerwa5.getterm().setduration(5);
        Break przerwa6=new Break(new BasicTerm(17,45));
        przerwa6.getterm().setduration(5);
        Break[] breaks={przerwa1,przerwa2,przerwa3,przerwa4,przerwa5,przerwa6};
        ITimetable timetable=new Timetable2(breaks);
        Term term1=new Term(8, 0,Day.MON);

        Lesson l1=new Lesson(timetable,term1,"Fizyka-W","Kąkol",2);
        timetable.put(l1);
        Term term2=new Term(9,30,l1.getterm().getday());
        assertTrue(l1.getTimetable().canBeTransferredTo(term2,l1.getfull_time()));
        assertFalse(l1.getTimetable().canBeTransferredTo(new Term(7,50,Day.TUE),l1.getfull_time()));
        assertTrue(l1.getTimetable().canBeTransferredTo(term2,l1.getfull_time()));

    }

    @Test
    public void busy() {
        Break przerwa1=new Break(new BasicTerm(9,30));
        przerwa1.getterm().setduration(5);
        Break przerwa2=new Break(new BasicTerm(11,5));
        przerwa2.getterm().setduration(10);
        Break przerwa3=new Break(new BasicTerm(12,45));
        przerwa3.getterm().setduration(5);
        Break przerwa4=new Break(new BasicTerm(14,20));
        przerwa4.getterm().setduration(20);
        Break przerwa5=new Break(new BasicTerm(16,10));
        przerwa5.getterm().setduration(5);
        Break przerwa6=new Break(new BasicTerm(17,45));
        przerwa6.getterm().setduration(5);
        Break[] breaks={przerwa1,przerwa2,przerwa3,przerwa4,przerwa5,przerwa6};
        ITimetable timetable=new Timetable2(breaks);
        Term term1=new Term(8, 0,Day.MON);
        Lesson l1=new Lesson(timetable,term1,"Fizyka-W","Kąkol",2);
        timetable.put(l1);
        Term term2=new Term(9,30,l1.getterm().getday());
        assertTrue(timetable.busy(term1));
        assertFalse(timetable.busy(term2));
        assertFalse(timetable.busy(term2));

    }

    @Test
    public void put() {
        Break przerwa1=new Break(new BasicTerm(9,30));
        przerwa1.getterm().setduration(5);
        Break przerwa2=new Break(new BasicTerm(11,5));
        przerwa2.getterm().setduration(10);
        Break przerwa3=new Break(new BasicTerm(12,45));
        przerwa3.getterm().setduration(5);
        Break przerwa4=new Break(new BasicTerm(14,20));
        przerwa4.getterm().setduration(20);
        Break przerwa5=new Break(new BasicTerm(16,10));
        przerwa5.getterm().setduration(5);
        Break przerwa6=new Break(new BasicTerm(17,45));
        przerwa6.getterm().setduration(5);
        Break[] breaks={przerwa1,przerwa2,przerwa3,przerwa4,przerwa5,przerwa6};
        ITimetable timetable=new Timetable2(breaks);
        Term term1=new Term(9, 30,Day.MON);
        Lesson l1=new Lesson(timetable,term1,"Fizyka-W","Kąkol",2);
        timetable.put(l1);
        assertEquals(timetable.get(new Term(9,35,Day.MON)),"Fizyka-W");
        Term term2=new Term(11,5,Day.MON);
        term2.setSkipBreaks(false);
        Lesson l2=new Lesson(timetable,term2,"","",2);
        assertFalse(timetable.put(l2));
    }

    @Test
    public void get() {
        Break przerwa1=new Break(new BasicTerm(9,30));
        przerwa1.getterm().setduration(5);
        Break przerwa2=new Break(new BasicTerm(11,5));
        przerwa2.getterm().setduration(10);
        Break przerwa3=new Break(new BasicTerm(12,45));
        przerwa3.getterm().setduration(5);
        Break przerwa4=new Break(new BasicTerm(14,20));
        przerwa4.getterm().setduration(20);
        Break przerwa5=new Break(new BasicTerm(16,10));
        przerwa5.getterm().setduration(5);
        Break przerwa6=new Break(new BasicTerm(17,45));
        przerwa6.getterm().setduration(5);
        Break[] breaks={przerwa1,przerwa2,przerwa3,przerwa4,przerwa5,przerwa6};
        ITimetable timetable=new Timetable2(breaks);
        Term term2=new Term(12,45,Day.TUE);
        Lesson lekcja=new Lesson(timetable,term2,"abc","",4);
        timetable.put(lekcja);
        Term term3=new Term(12,50,Day.TUE);
        assertEquals(timetable.get(term3),"abc");
        term2.setSkipBreaks(false);
        Term term4=new Term(12,45,Day.THU);
        term4.setSkipBreaks(false);
        Lesson lekcja1=new Lesson(timetable,term4,"abcd","",3);
        timetable.put(lekcja1);
        Term term5=new Term(12,50,Day.THU);
        assertEquals(timetable.get(term5),null);
        assertEquals(timetable.get(term4),"Przerwa");

    }
}