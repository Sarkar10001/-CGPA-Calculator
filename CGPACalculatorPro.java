import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CGPACalculatorPro extends JFrame {
    private JTextField semField, sgpaField, creditField;
    private JTextArea resultArea;
    private JButton addButton, calcButton, clearButton;

    private double totalPoints = 0.0;
    private double totalCredits = 0.0;
    private int semCount = 0;

    public CGPACalculatorPro() {
        // Frame Setup
        setTitle("CGPA Calculator");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center screen
        setLayout(new BorderLayout());

        // Heading
        JLabel heading = new JLabel("CGPA Calculator", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        heading.setForeground(new Color(25, 118, 210));
        heading.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(heading, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Semester Details"));

        JLabel semLabel = new JLabel("Semester Number:");
        semLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        semField = new JTextField();

        JLabel sgpaLabel = new JLabel("SGPA:");
        sgpaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sgpaField = new JTextField();

        JLabel creditLabel = new JLabel("Credits:");
        creditLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        creditField = new JTextField();

        inputPanel.add(semLabel); inputPanel.add(semField);
        inputPanel.add(sgpaLabel); inputPanel.add(sgpaField);
        inputPanel.add(creditLabel); inputPanel.add(creditField);

        add(inputPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addButton = new JButton("➕ Add Semester");
        calcButton = new JButton("📊 Calculate CGPA");
        clearButton = new JButton("🗑 Clear All");

        Font btnFont = new Font("Segoe UI", Font.BOLD, 14);
        addButton.setFont(btnFont);
        calcButton.setFont(btnFont);
        clearButton.setFont(btnFont);

        addButton.setBackground(new Color(56, 142, 60));
        addButton.setForeground(Color.WHITE);

        calcButton.setBackground(new Color(25, 118, 210));
        calcButton.setForeground(Color.WHITE);

        clearButton.setBackground(new Color(211, 47, 47));
        clearButton.setForeground(Color.WHITE);

        addButton.setFocusPainted(false);
        calcButton.setFocusPainted(false);
        clearButton.setFocusPainted(false);

        buttonPanel.add(addButton);
        buttonPanel.add(calcButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Result Area
        resultArea = new JTextArea(10, 40);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createTitledBorder("Results"));
        add(new JScrollPane(resultArea), BorderLayout.EAST);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double sgpa = Double.parseDouble(sgpaField.getText());
                    double credits = Double.parseDouble(creditField.getText());
                    semCount++;

                    totalPoints += sgpa * credits;
                    totalCredits += credits;

                    resultArea.append("Semester " + semField.getText() + ": SGPA=" + sgpa + ", Credits=" + credits + "\n");

                    // Reset fields
                    sgpaField.setText("");
                    creditField.setText("");
                    semField.setText("" + (semCount + 1));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "⚠ Please enter valid SGPA and Credits!");
                }
            }
        });

        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (totalCredits > 0) {
                    double cgpa = totalPoints / totalCredits;
                    double percentage = (cgpa - 0.5) * 10;
                    resultArea.append("\n✅ Final CGPA: " + String.format("%.2f", cgpa) +
                                      "\n📌 Equivalent Percentage: " + String.format("%.2f", percentage) + "%\n");
                } else {
                    JOptionPane.showMessageDialog(null, "⚠ No semesters added yet!");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                totalPoints = 0;
                totalCredits = 0;
                semCount = 0;
                resultArea.setText("");
                semField.setText("");
                sgpaField.setText("");
                creditField.setText("");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        // Modern Look
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        new CGPACalculatorPro();
    }
}
