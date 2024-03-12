package educative.lld.tennis;

public interface GameState {
    void pointWonBy(Player player);
    void checkGameWinner();
}

//Interview Q&A
//Q: Why did you choose the State Pattern for this problem?
//A: The State Pattern fits well for the tennis scoring system because the game's scoring rules lead to different
// behaviors based on the current score (e.g., Deuce, Advantage). The pattern allows us to encapsulate these varying
// behaviors into separate state objects, making the system more maintainable and extensible.
//
//Q: How does the State Pattern improve the design of the tennis scoring system?
//
//A: By applying the State Pattern, we encapsulate the scoring logic for different scenarios into their respective
// state classes. This reduces the complexity of the TennisMatch class, making it easier to understand, maintain,
// and extend. For example, adding a new scoring rule would simply involve creating a new state class without
// modifying the existing ones.
//
//Q: Can you explain the role of the changeState method in your implementation?
//A: The changeState method in the TennisMatch class allows the system to transition between different states. When a
// scoring event triggers a change in the game's state (e.g., moving from Regular scoring to Deuce), the changeState
// method is called to update the current state of the game. This method is central to the State Pattern's ability to
// dynamically change the behavior of the system based on its state.