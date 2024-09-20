package customer;

import java.util.ArrayList;
import java.util.List;

public class ReceiptBasis {
    private List<DepositItem> items;

    public ReceiptBasis() {
        items = new ArrayList<>();
    }

    // Method to add items to the receipt basis
    public void addItem(DepositItem item) {
        items.add(item);
    }

    // Method to compute the sum of all items added
    public int computeSum() {
        int total = 0;

        // Loop through the items list and sum the values
        for (DepositItem item : items) {
            total += item.getValue();
        }

        return total;
    }
}
