package lab7;

import org.junit.Test;

import static org.junit.Assert.*;

public class BasicTermTest {

    @Test
    public void compareTo() {
        BasicTerm term1=new BasicTerm(10,25);
        BasicTerm term2=new BasicTerm(10,25);
        BasicTerm term3=new BasicTerm(11,15);
        BasicTerm term4=new BasicTerm(9,0);
        assertEquals(term1.compareTo(term2),0);
        assertEquals(term1.compareTo(term3),-1);
        assertEquals(term1.compareTo(term4),1);

    }
}