package classes;

public class DepositItemReceiver {

    // Method to classify item based on slot
    public DepositItem classifyItem(int slot) {
        switch (slot) {
            case 1:
                return new Can(1, 100, 0.5f, 0.75f);  // Create a Can with the given parameters
            case 2:
                return new Bottle(2, 150, 0.7f, 1.0f);  // Create a Bottle
            case 3:
                return new Crate(3, 200, 2.5f, 1.5f);  // Create a Crate
            default:
                return null;
        }
    }

    // Method to create the receipt basis
    public ReceiptBasis createReceiptBasis() {
        return new ReceiptBasis();
    }

    // Method to print the receipt
    public void printReceipt(int total) {
        System.out.println("Total Value: " + total);
    }
}
