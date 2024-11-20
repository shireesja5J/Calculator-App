import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    // JFrame to hold the components
    private JFrame frame;
    private JTextField display;

    public static void main(String[] args) {
        // Create an instance of the calculator
        Calculator calc = new Calculator();
        calc.createAndShowGUI();
    }

    // Constructor to initialize the components
    public Calculator() {
        frame = new JFrame("Calculator");
        display = new JTextField();
    }

    // Method to create and display the GUI
    private void createAndShowGUI() {
        // Set up the frame properties
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Set up the display area (TextField)
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        // Set up the panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Define the buttons
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        // Add buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Set the frame visibility
        frame.setVisible(true);
    }

    // Button click listener class to handle the logic
    private class ButtonClickListener implements ActionListener {

        private String operator = "";
        private double firstNumber = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            // Handle number and operator buttons
            if (command.matches("[0-9]")) {
                display.setText(display.getText() + command);
            } 
            else if (command.equals("C")) {
                display.setText("");
            } 
            else if (command.equals("=")) {
                try {
                    double result = evaluateExpression(display.getText());
                    display.setText(String.valueOf(result));
                } catch (Exception ex) {
                    display.setText("Error");
                }
            } 
            else { // operator buttons
                if (!display.getText().isEmpty()) {
                    firstNumber = Double.parseDouble(display.getText());
                    display.setText("");
                    operator = command;
                }
            }
        }

        // Simple method to evaluate the expression
        private double evaluateExpression(String expression) {
            double secondNumber = Double.parseDouble(expression);

            switch (operator) {
                case "+":
                    return firstNumber + secondNumber;
                case "-":
                    return firstNumber - secondNumber;
                case "*":
                    return firstNumber * secondNumber;
                case "/":
                    if (secondNumber != 0) {
                        return firstNumber / secondNumber;
                    } else {
                        throw new ArithmeticException("Cannot divide by zero");
                    }
                default:
                    return secondNumber;
            }
        }
    }
}
