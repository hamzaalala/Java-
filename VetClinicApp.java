/**
 * Name: Hamza Alala
 * Date: April 17, 2025
 * Description: A simple veterinary clinic registration form
 *              built using Java Swing. Validates user input
 *              and writes to a text file using JFileChooser.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class VetClinicApp extends JFrame {
    // Declare UI components
    private JTextField txtPatientName, txtOwnerName, txtEmail;
    private JLabel lblMessage;
    private JRadioButton vet1, vet2, vet3;
    private ButtonGroup vetGroup;

    // Constructor for the GUI
    public VetClinicApp() {
        setTitle("Veterinary Clinic Registration");
        setSize(600, 450);
        setLayout(new GridLayout(8, 2)); // Layout with 8 rows, 2 columns
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Properly closes the application

        // Create and add labels and text fields
        add(new JLabel("Patient Name:"));
        txtPatientName = new JTextField();
        add(txtPatientName);

        add(new JLabel("Owner Name:"));
        txtOwnerName = new JTextField();
        add(txtOwnerName);

        add(new JLabel("Email Address:"));
        txtEmail = new JTextField();
        add(txtEmail);

        // Create and add radio buttons for vet selection
        add(new JLabel("Assign a Vet:"));
        JPanel vetPanel = new JPanel();
        vet1 = new JRadioButton("Dr. Smith", true);
        vet2 = new JRadioButton("Dr. Lee");
        vet3 = new JRadioButton("Dr. Gomez");
        vetGroup = new ButtonGroup();
        vetGroup.add(vet1);
        vetGroup.add(vet2);
        vetGroup.add(vet3);
        vetPanel.add(vet1);
        vetPanel.add(vet2);
        vetPanel.add(vet3);
        add(vetPanel);

        // Create and add buttons
        JButton btnRegister = new JButton("Register");
        JButton btnClear = new JButton("Clear");
        JButton btnExit = new JButton("Exit");

        // Add button action listeners
        btnRegister.addActionListener(e -> registerPatient());
        btnClear.addActionListener(e -> clearForm());
        btnExit.addActionListener(e -> System.exit(0));

        add(btnRegister);
        add(btnClear);
        add(btnExit);

        // Label to display messages
        lblMessage = new JLabel("", JLabel.CENTER);
        add(lblMessage);
    }

    // Method to validate input and register a patient
    private void registerPatient() {
        String patient = txtPatientName.getText().trim();
        String owner = txtOwnerName.getText().trim();
        String email = txtEmail.getText().trim();

        // Validate each field
        if (patient.isEmpty()) {
            lblMessage.setText("Patient name is required.");
            return;
        }
        if (owner.isEmpty()) {
            lblMessage.setText("Owner name is required.");
            return;
        }
        if (email.isEmpty() || !isValidEmail(email)) {
            lblMessage.setText("Valid email is required.");
            return;
        }

        // Get selected vet and current date
        String selectedVet = vet1.isSelected() ? vet1.getText() :
                vet2.isSelected() ? vet2.getText() : vet3.getText();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // Open file and save
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Write patient details to the file
                writer.write("**Patient Registration Document**\n");
                writer.write("Patient Name: " + patient + "\n");
                writer.write("Owner Name: " + owner + "\n");
                writer.write("Email: " + email + "\n");
                writer.write("Assigned Vet: " + selectedVet + "\n");
                writer.write("Date: " + date + "\n");
                lblMessage.setText("Patient registered successfully.");
            } catch (IOException ex) {
                lblMessage.setText("Error writing to file.");
            }
        }
    }

    // Method to validate email using regex
    private boolean isValidEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    // Method to reset form fields to default state
    private void clearForm() {
        txtPatientName.setText("");
        txtOwnerName.setText("");
        txtEmail.setText("");
        vet1.setSelected(true);
        lblMessage.setText("");
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VetClinicApp().setVisible(true));
    }
}
