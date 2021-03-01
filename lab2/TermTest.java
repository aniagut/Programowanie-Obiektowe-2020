package lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class TermTest {

    Term term1=new Term(9,45);
    Term term2=new Term(10,15);
    @Test
    public void testToString() {
        assertEquals(term1.toString(),"9:45 [90]");
        assertEquals(term2.toString(),"10:15 [90]");
    }

    @Test
    public void earlierThan() {
        assertTrue(term1.earlierThan(term2));
    }

    @Test
    public void laterThan() {
        assertFalse(term1.laterThan(term2));
    }

    @Test
    public void endTerm() {
        assertEquals(term1.endTerm(term2).toString(),"9:45 [30]");
    }

    @Test
    public void testEndTerm() {
        assertEquals(term1.endTerm().toString(),"11:15 [90]");
    }
}