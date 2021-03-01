package lab5;

import org.junit.Test;

import static org.junit.Assert.*;


public class LessonTest {
    ITimetable timetable=new Timetable1();
    Lesson lesson=new Lesson(timetable,new Term(9,25, Day.SUN),"PO","Stanisław Polak",2);
    Lesson lesson1=new Lesson(timetable,new Term(17,40,Day.MON),"coś","ktoś",2);
    @Test
    public void testToString() {
        lesson.setFull_time(false);
        assertEquals(lesson.toString(),"PO (Niedziela, 9:25-10:55)\n"+"Drugi" +
                " rok studiów niestacjonarnych\n"+"Prowadzący: Stanisław Polak");
    }


    @Test
    public void earlierDay() {
        lesson.setFull_time(false);
        assertTrue(lesson.earlierDay());
        assertFalse(lesson1.earlierDay());

    }

    @Test
    public void laterDay() {
        lesson.setFull_time(false);
        assertTrue(lesson1.laterDay());
        assertFalse(lesson.laterDay());
    }

    @Test
    public void earlierTime() {
        lesson.setFull_time(false);
        assertFalse(lesson.earlierTime());
        assertTrue(lesson1.earlierTime());

    }

    @Test
    public void laterTime() {
        lesson.setFull_time(false);
        assertFalse(lesson1.laterTime());
        assertTrue(lesson.laterTime());
    }
}