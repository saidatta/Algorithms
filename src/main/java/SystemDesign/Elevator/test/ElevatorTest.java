package SystemDesign.Elevator.test;

import SystemDesign.Elevator.Elevator;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by venkatamunnangi on 7/19/17.
 */
public class ElevatorTest {

    private Elevator elevator;

    @Before
    public void setup() {
        elevator = new Elevator();
    }

    @Test
    public void testUp() {
        elevator.goToLevel(4);
    }

    @Test
    public void testDown() {
        elevator.goToLevel(1);
    }

    @Test
    public void testCome() {
        elevator.comeToLevel(1);
    }
}
