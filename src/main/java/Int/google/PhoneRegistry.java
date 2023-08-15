package Int.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Given a phone number, implemement the Register, Unregister, and getRandom methods"
 * void register(phoneNumber)
 * void unregister(phoneNumber)
 * bool isRegisted(phoneNumber)
 * phoneNumber giveMeOne() //random unregistered
 */
public class PhoneRegistry {
    private PhoneNode root;
    private Random random;
    private int lengthOfPhoneNumber;

    public PhoneRegistry(int lengthOfPhoneNumber) {
        this.root = new PhoneNode();
        this.random = new Random();
        this.lengthOfPhoneNumber = lengthOfPhoneNumber;
    }

    public void register(String phoneNumber) {
        PhoneNode node = root;
        for (char c : phoneNumber.toCharArray()) {
            int digit = c - '0';
            if (node.children[digit] == null) {
                node.children[digit] = new PhoneNode();
            }
            node = node.children[digit];
        }
        node.isUnregistered = false;
    }

    public void unregister(String phoneNumber) {
        PhoneNode node = root;
        for (char c : phoneNumber.toCharArray()) {
            int digit = c - '0';
            if (node.children[digit] != null) {
                node = node.children[digit];
            } else {
                // This phone number was not registered.
                return;
            }
        }
        node.isUnregistered = true;
    }

    public boolean isRegistered(String phoneNumber) {
        PhoneNode node = root;
        for (char c : phoneNumber.toCharArray()) {
            int digit = c - '0';
            if (node.children[digit] != null) {
                node = node.children[digit];
            } else {
                // This phone number was not registered.
                return false;
            }
        }
        return !node.isUnregistered;
    }

    public String giveMeOne() {
        StringBuilder phoneNumber = new StringBuilder();
        PhoneNode node = root;
        for (int i = 0; i < lengthOfPhoneNumber; i++) {
            List<Integer> unregisteredDigits = new ArrayList<>();
            for (int digit = 0; digit <= 9; digit++) {
                if (node.children[digit] != null && node.children[digit].isUnregistered) {
                    unregisteredDigits.add(digit);
                }
            }
            if (unregisteredDigits.isEmpty()) {
                // No more unregistered phone numbers.
                return null;
            }
            int nextDigit = unregisteredDigits.get(random.nextInt(unregisteredDigits.size()));
            phoneNumber.append(nextDigit);
            node = node.children[nextDigit];
        }
        return phoneNumber.toString();
    }

    public static void main(String[] args) {
        // Create a new phone registry.
        // Assume phone numbers are 10 digits long.
        PhoneRegistry registry = new PhoneRegistry(10);

        // Register some phone numbers.
        registry.register("1234567890");
        registry.register("0987654321");

        // Check if a phone number is registered.
        System.out.println(registry.isRegistered("1234567890"));  // Output: true
        System.out.println(registry.isRegistered("0987654321"));  // Output: true
        System.out.println(registry.isRegistered("1111111111"));  // Output: false

        // Unregister a phone number.
        registry.unregister("1234567890");

        // Check if it was successfully unregistered.
        System.out.println(registry.isRegistered("1234567890"));  // Output: false

        // Try to get an unregistered phone number.
        // In this case, it should return the recently unregistered "1234567890".
        System.out.println(registry.giveMeOne());  // Output: 1234567890
    }
}
class PhoneNode {
    PhoneNode[] children = new PhoneNode[10];
    boolean isUnregistered = true;
}
