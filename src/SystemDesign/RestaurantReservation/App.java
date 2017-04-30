package SystemDesign.RestaurantReservation;

/**
 * Created by venkatamunnangi on 4/29/17.
 */
public class App {

    public static void main(String... args) {
        Restaurant restaurant = new Restaurant();

        Command reserveCommand = new ReserveCommand(restaurant);
        Command menuCommand = new MenuCommand(restaurant);

        Customer customerInvoker = new Customer();
        customerInvoker.submit(reserveCommand);
    }
}
