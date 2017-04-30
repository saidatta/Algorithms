package SystemDesign.RestaurantReservation;

/**
 * Created by venkatamunnangi on 4/29/17.
 */
public class MenuCommand implements Command{
    private Restaurant restaurant;

    public MenuCommand(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean execute() {
        System.out.println("Menu has been shown");
        return false;
    }

    @Override
    public void undo() {
        System.out.println("Menu command has been undone");
    }

    @Override
    public void redo() {
        System.out.println("Menu command has been redone");
    }
}
