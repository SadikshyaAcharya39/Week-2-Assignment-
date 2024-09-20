package classes;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CustomerPanel extends JPanel {
    private int canCount = 0;
    private int bottleCount = 0;
    private int crateCount = 0;

    private static final int CAN_VALUE = 100;
    private static final int BOTTLE_VALUE = 200;
    private static final int CRATE_VALUE = 300;

    private JTextPane receiptArea;
    private JScrollPane scrollPane;
    private ArrayList<String> itemEntries = new ArrayList<>();

    public CustomerPanel() {
        createUI();
    }

    private void createUI() {
        setLayout(null); // Null layout for specific placement

        // Create buttons
        JButton addCanButton = new JButton("Add Can");
        JButton addBottleButton = new JButton("Add Bottle");
        JButton addCrateButton = new JButton("Add Crate");
        JButton printReceiptButton = new JButton("Print Receipt");

        // Set bounds for buttons
        addCanButton.setBounds(70, 70, 120, 30);
        addBottleButton.setBounds(70, 120, 120, 30);
        addCrateButton.setBounds(70, 170, 120, 30);
        printReceiptButton.setBounds(70, 220, 120, 30);

        // Create text pane for receipt
        receiptArea = new JTextPane();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        receiptArea.setBorder(new LineBorder(Color.BLACK, 2));
        receiptArea.setPreferredSize(new Dimension(400, 400));
//        receiptArea.setBounds(300, 30, 400, 400);

        // Create scroll pane
        scrollPane = new JScrollPane(receiptArea);
        scrollPane.setBounds(300, 30, 400, 400); // Adjust as needed
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add components to the panel
       add(scrollPane);
        add(addCanButton);
        add(addBottleButton);
        add(addCrateButton);
        add(printReceiptButton);

        // Add action listeners for buttons
        addCanButton.addActionListener(e -> {
            addCan();
            updateReceipt();
        });

        addBottleButton.addActionListener(e -> {
            addBottle();
            updateReceipt();
        });

        addCrateButton.addActionListener(e -> {
            addCrate();
            updateReceipt();
        });

        printReceiptButton.addActionListener(e -> printReceipt());

        // Display the initial receipt
        updateReceipt();
    }

    public void addCan() {
        canCount++;
        addItemEntry("Can");
    }

    public void addBottle() {
        bottleCount++;
        addItemEntry("Bottle");
    }

    public void addCrate() {
        crateCount++;
        addItemEntry("Crate");
    }

    private void addItemEntry(String itemType) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeStamp = now.format(timeFormatter);
        itemEntries.add(itemType + " added at " + timeStamp);
    }

    private void updateReceipt() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, yyyy-MM-dd");
        String currentDate = now.format(dateFormatter);

        StyledDocument doc = receiptArea.getStyledDocument();
        SimpleAttributeSet boldStyle = new SimpleAttributeSet();
        StyleConstants.setBold(boldStyle, true);
        SimpleAttributeSet rightAlign = new SimpleAttributeSet();
        StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);

        receiptArea.setText("");

        try {
            // Add the date at the top
            doc.setParagraphAttributes(doc.getLength(), 1, rightAlign, false);
            doc.insertString(doc.getLength(), "Date: " + currentDate + "\n\n", boldStyle);

            // Centered Receipt Title
            SimpleAttributeSet centerAlign = new SimpleAttributeSet();
            StyleConstants.setAlignment(centerAlign, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(doc.getLength(), 1, centerAlign, false);
            doc.insertString(doc.getLength(), "              Receipt          \n\n", boldStyle);

            // Left align the item entries
            SimpleAttributeSet leftAlign = new SimpleAttributeSet();
            StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);
            doc.setParagraphAttributes(doc.getLength(), 1, leftAlign, false);

            // Add the item entries
            for (String entry : itemEntries) {
                doc.insertString(doc.getLength(), entry + "\n", null);
            }

            // Add item counts
            doc.insertString(doc.getLength(), "\n   Cans: " + canCount + "\n", null);
            doc.insertString(doc.getLength(), "   Bottles: " + bottleCount + "\n", null);
            doc.insertString(doc.getLength(), "   Crates: " + crateCount + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }


    public void printReceipt() {
        // Update receipt to include totals and then print
        updateReceiptWithTotal();
        System.out.println(receiptArea.getText()); // This will print to console; replace with actual print logic if needed
    }

    private void updateReceiptWithTotal() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, yyyy-MM-dd");
        String currentDate = now.format(dateFormatter);

        StyledDocument doc = receiptArea.getStyledDocument();
        SimpleAttributeSet boldStyle = new SimpleAttributeSet();
        StyleConstants.setBold(boldStyle, true);
        SimpleAttributeSet rightAlign = new SimpleAttributeSet();
        StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);
        receiptArea.setText("");

        try {
            doc.setParagraphAttributes(doc.getLength(), 1, rightAlign, false);
            doc.insertString(doc.getLength(), "Date: " + currentDate + "\n\n", boldStyle);

            SimpleAttributeSet leftAlign = new SimpleAttributeSet();
            StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);
            doc.setParagraphAttributes(doc.getLength(), 1, leftAlign, false);

            for (String entry : itemEntries) {
                doc.insertString(doc.getLength(), entry + "\n", null);
            }

            // Add totals
            doc.insertString(doc.getLength(), "\n                 Receipt          \n\n", boldStyle);
            doc.insertString(doc.getLength(), "   Cans: " + canCount + "                 Total: " + (canCount * CAN_VALUE) + "\n", null);
            doc.insertString(doc.getLength(), "   Bottles: " + bottleCount + "              Total: " + (bottleCount * BOTTLE_VALUE) + "\n", null);
            doc.insertString(doc.getLength(), "   Crates: " + crateCount + "               Total: " + (crateCount * CRATE_VALUE) + "\n", null);
            doc.insertString(doc.getLength(), "---------------------------------------\n", null);
            doc.insertString(doc.getLength(), "            Total Amount : " + calculateTotalAmount() + "\n", boldStyle);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private int calculateTotalAmount() {
        return (canCount * CAN_VALUE) + (bottleCount * BOTTLE_VALUE) + (crateCount * CRATE_VALUE);
    }


    // Main method to run the application
    public static void main(String[] args) {
        JFrame frame = new JFrame("Item Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.add(new CustomerPanel());
        frame.setVisible(true);
    }
}


