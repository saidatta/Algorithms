package SystemDesign.RestaurantReservation;

import static java.lang.System.*;

/**
 * Created by venkatamunnangi on 4/29/17.
 */
public class ReserveCommand implements Command {

    private Restaurant restaurant;

    public ReserveCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean execute() {
        out.println("Reservation has been executed!");
        return false;
    }

    @Override
    public void undo() {
        out.println("Reservation has been undid!");
    }

    @Override
    public void redo() {
        out.println("Reservation undo has been redid!");
    }
}
