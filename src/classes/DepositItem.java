package classes;

// Base class for deposit items
public class DepositItem {
    private int number;
    private int value;

    // Constructor for DepositItem
    public DepositItem(int number, int value) {
        this.number = number;
        this.value = value;
    }

    // Getter for value
    public int getValue() {
        return value;
    }

    // Getter for number
    public int getNumber() {
        return number;
    }

    // Method to calculate the deposit, can be overridden by subclasses
    public float calculateDeposit() {
        return value;  // Default deposit calculation
    }
}

// Subclass for Can
class Can extends DepositItem {
    private float weightOfCan;
    private float sizeOfCan;

    // Constructor for Can that accepts all parameters
    public Can(int number, int value, float weightOfCan, float sizeOfCan) {
        super(number, value);  // Call the parent class constructor
        this.weightOfCan = weightOfCan;
        this.sizeOfCan = sizeOfCan;
    }

    // Getters for weight and size of the can
    public float getWeightOfCan() {
        return weightOfCan;
    }

    public float getSizeOfCan() {
        return sizeOfCan;
    }

    // Override calculateDeposit for Can-specific calculation
    @Override
    public float calculateDeposit() {
        return getValue() * sizeOfCan;  // Custom deposit calculation for cans
    }
}

// Subclass for Bottle
class Bottle extends DepositItem {
    private float weightOfBottle;
    private float sizeOfBottle;

    // Constructor for Bottle that accepts all parameters
    public Bottle(int number, int value, float weightOfBottle, float sizeOfBottle) {
        super(number, value);  // Call the parent class constructor
        this.weightOfBottle = weightOfBottle;
        this.sizeOfBottle = sizeOfBottle;
    }

    // Getters for weight and size of the bottle
    public float getWeightOfBottle() {
        return weightOfBottle;
    }

    public float getSizeOfBottle() {
        return sizeOfBottle;
    }

    // Override calculateDeposit for Bottle-specific calculation
    @Override
    public float calculateDeposit() {
        return getValue() * sizeOfBottle;  // Custom deposit calculation for bottles
    }
}

// Subclass for Crate
class Crate extends DepositItem {
    private float weightOfCrate;
    private float sizeOfCrate;

    // Constructor for Crate that accepts all parameters
    public Crate(int number, int value, float weightOfCrate, float sizeOfCrate) {
        super(number, value);  // Call the parent class constructor
        this.weightOfCrate = weightOfCrate;
        this.sizeOfCrate = sizeOfCrate;
    }

    // Getters for weight and size of the crate
    public float getWeightOfCrate() {
        return weightOfCrate;
    }

    public float getSizeOfCrate() {
        return sizeOfCrate;
    }

    // Override calculateDeposit for Crate-specific calculation
    @Override
    public float calculateDeposit() {
        return getValue() * sizeOfCrate;  // Custom deposit calculation for crates
    }
}
