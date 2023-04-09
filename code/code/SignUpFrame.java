package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import code.Parent;
import java.lang.String;

public class SignUpFrame extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField;

    public SignUpFrame(ArrayList<Parent> parents) {
        super("Sign Up");
        createComponents(parents);
        configureFrame();
    }

    private void createComponents(ArrayList<Parent> parents) {
        // Create components
        JLabel usernameLabel = createLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = createLabel("Password:");
        passwordField = new JTextField(20);
        JButton signUpButton = createButton("Sign Up");

        // Add action listener to sign up button
        signUpButton.addActionListener(e -> {
            Parent newParent = new Parent(UUID.randomUUID(), usernameField.getText(), passwordField.getText());
            parents.add(newParent);

            // Save new user's credentials
            // (You should use a proper method to store user credentials, e.g., hashing passwords, and saving them in a database)
            System.out.println("New user created: " + newParent.getName() + ", " + newParent.getPassword());

            setVisible(false);
            dispose();
        });

        // Layout components using GridBagLayout
        JPanel panel = createPanel(usernameLabel, usernameField, passwordLabel, passwordField, signUpButton);

        // Add panel to frame
        add(panel);
    }

    private JLabel createLabel(String labelText) {
        JLabel label = new JLabel(labelText);
        Font playfulFont = new Font("Comic Sans MS", Font.PLAIN, 14);
        label.setFont(playfulFont);
        return label;
    }

    private JButton createButton(String buttonText) {
        JButton button = new JButton(buttonText);
        Font playfulFont = new Font("Comic Sans MS", Font.PLAIN, 14);
        button.setFont(playfulFont);
        button.setBackground(new Color(255, 153, 102));
        button.setForeground(Color.WHITE);
        return button;
    }

    private JPanel createPanel(JLabel usernameLabel, JTextField usernameField, JLabel passwordLabel, JTextField passwordField, JButton signUpButton) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 153));
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(signUpButton, constraints);

        return panel;
    }

    private void configureFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

