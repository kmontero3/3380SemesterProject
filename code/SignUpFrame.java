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

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JTextField(20);
        JButton signUpButton = new JButton("Sign Up");

        // Customize components
        Font playfulFont = new Font("Comic Sans MS", Font.PLAIN, 14);
        usernameLabel.setFont(playfulFont);
        passwordLabel.setFont(playfulFont);
        signUpButton.setFont(playfulFont);
        signUpButton.setBackground(new Color(255, 153, 102));
        signUpButton.setForeground(Color.WHITE);

        // Layout components using GridBagLayout
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

        // Add action listener to sign up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Parent newParent = new Parent(UUID.randomUUID(), usernameField.getText(), passwordField.getText());
                parents.add(newParent);

                // Save new user's credentials
                // (You should use a proper method to store user credentials, e.g., hashing passwords, and saving them in a database)
                System.out.println("New user created: " + newParent.getName() + ", " + newParent.getPassword());

                setVisible(false);
                dispose();
            }
        });

        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
