package SystemDesign.RestaurantReservation;

/**
 * Created by venkatamunnangi on 4/29/17.
 */
public interface Command {
    public boolean execute();

    public void undo();

    public void redo();
}
