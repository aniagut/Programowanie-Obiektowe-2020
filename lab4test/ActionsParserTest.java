package lab5;


import org.junit.Test;

import static org.junit.Assert.*;

public class ActionsParserTest {

    @Test
    public void parse() {
        String tab[]={"t+","t-","d+","d-","ssss"};
        Action[] actions = new ActionsParser().parse(tab);
        assertEquals(actions[0],Action.TIME_LATER);
        assertEquals(actions[1],Action.TIME_EARLIER);
        assertEquals(actions[2],Action.DAY_LATER);
        assertEquals(actions[3],Action.DAY_EARLIER);
        assertEquals(actions[4],null);
    }
}