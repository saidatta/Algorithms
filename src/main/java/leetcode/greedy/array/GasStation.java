package leetcode.greedy.array;

// https://leetcode.com/problems/gas-station/
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // It will record the total remaining gas when you travel around the circuit.
        int totalTank = 0;
        // It will record the current remaining gas when you try to start from a station.
        int currTank = 0;
        // It will keep the index of a potential starting station.
        int startingStation = 0;

        for (int i = 0; i < gas.length; i++) {
            totalTank += gas[i] - cost[i];
            currTank += gas[i] - cost[i];

            // If you can't reach the next station from the current station, reset currTank and set startingStation to
            // the next one.
            if (currTank < 0) {
                startingStation = i + 1;
                currTank = 0;
            }
        }

        // If the total remaining gas is negative, then a solution is not possible.
        return totalTank >= 0 ? startingStation : -1;
    }

    public static void main(String[] args) {
        GasStation solution = new GasStation();
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(solution.canCompleteCircuit(gas, cost)); // Expected output: 3

        int[] gas2 = {2,3,4};
        int[] cost2 = {3,4,3};
        System.out.println(solution.canCompleteCircuit(gas2, cost2)); // Expected output: -1
    }
}
