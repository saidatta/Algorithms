package educative.lld.parkinglot;

// https://www.educative.io/courses/grokking-the-low-level-design-interview-using-ood-principles/requirements-for-the-parking-lot-design
public class ParkingLot {
    private final int NUM_SLOTS = 40_000;


//    boolean accept() {}
//
//    boolean exit() {}
}

interface Role {}

class Admin {}

enum AUTOMOBILE_TYPE {

}


enum AccountStatus {
    ACTIVE,
    CLOSED,
    CANCELED,
    BLACKLISTED,
    NONE
}

// Custom Person data type class
class Person {
    private String name;
    private String address;
    private String phone;
    private String email;
}

// Custom Address data type class
class Address {
    private int zipCode;
    private String address;
    private String city;
    private String state;
    private String country;
}


// Roles {Admin, Driver, Agent}
// ParkingLot
// Parking slots
// accept -> token with timestamp
//
//
// Automobiles {car, motorbike}

// Payment {status}
// accept token with timestamp to figure out how long.