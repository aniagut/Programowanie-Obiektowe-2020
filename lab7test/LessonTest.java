package lab7;

import org.junit.Test;

import java.util.TreeSet;

import static org.junit.Assert.*;

public class LessonTest {

    @Test
    public void compareTo1() {
        TreeSet<BasicTerm> breaks=new TreeSet<BasicTerm>();
        Timetable2 timetable=new Timetable2(breaks);
        Lesson lesson1=new Lesson(timetable,new Term(10,30,Day.MON),"coś","ktoś",1);
        Lesson lesson2=new Lesson(timetable,new Term(12,10,Day.MON),"coś","ktoś",1);
        assertEquals(lesson1.compareTo1(lesson2),-1);
        assertEquals(lesson2.compareTo1(lesson1),1);
    }
}