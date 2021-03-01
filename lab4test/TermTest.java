package lab5;


import org.junit.Test;

import static org.junit.Assert.*;

public class TermTest {

    @Test
    public void testToString() {
        assertEquals(new Term(18,20, Day.TUE).toString(),"Wtorek 18:20 [90]");
    }

    @Test
    public void earlierThan() {
        Term term=new Term(9,30,Day.TUE);
        Term term1=new Term(11,30,Day.MON);
        assertTrue(term1.earlierThan(term));
        assertFalse(term.earlierThan(term1));
    }

    @Test
    public void laterThan() {
        Term term=new Term(9,30,Day.TUE);
        Term term1=new Term(11,30,Day.MON);
        assertFalse(term1.laterThan(term));
        assertTrue(term.laterThan(term1));
    }

    @Test
    public void endTerm() {
        Term term = new Term(9, 30, Day.TUE);
        Term term1 = new Term(11, 30, Day.TUE);
        assertEquals(term.endTerm(term1).toString(),"Wtorek 9:30 [120]");
    }

    @Test
    public void testEndTerm() {
        Term term1 = new Term(11, 30, Day.TUE);
        assertEquals(term1.endTerm().toString(),"Wtorek 13:00 [90]");
    }

    @Test
    public void testEquals() {
        Term term1 = new Term(11, 30, Day.TUE);
        assertTrue(term1.equals(new Term(11,30,Day.TUE)));
        assertFalse(term1.equals(new Term(11,25,Day.TUE)));
    }
}