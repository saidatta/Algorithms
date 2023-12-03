package leetcode.math.numbertheory;

// https://leetcode.com/problems/bulb-switcher/
public class BulbSwitcher {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }

    public static void main(String[] args) {
        BulbSwitcher solution = new BulbSwitcher();
        System.out.println(solution.bulbSwitch(3)); // Output: 1
        System.out.println(solution.bulbSwitch(0)); // Output: 0
        System.out.println(solution.bulbSwitch(1)); // Output: 1
    }
}
