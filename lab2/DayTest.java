package lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class DayTest {

    @Test
    public void nextDay() {
        assertEquals(Day.MON.nextDay(),Day.TUE);
        assertEquals(Day.TUE.nextDay(),Day.WED);
        assertEquals(Day.WED.nextDay(),Day.THU);
        assertEquals(Day.THU.nextDay(),Day.FRI);
        assertEquals(Day.FRI.nextDay(),Day.SAT);
        assertEquals(Day.SAT.nextDay(),Day.SUN);
        assertEquals(Day.SUN.nextDay(),Day.MON);
    }

    @Test
    public void prevDay() {
        assertEquals(Day.MON.prevDay(),Day.SUN);
        assertEquals(Day.TUE.prevDay(),Day.MON);
        assertEquals(Day.WED.prevDay(),Day.TUE);
        assertEquals(Day.THU.prevDay(),Day.WED);
        assertEquals(Day.FRI.prevDay(),Day.THU);
        assertEquals(Day.SAT.prevDay(),Day.FRI);
        assertEquals(Day.SUN.prevDay(),Day.SAT);
    }
}