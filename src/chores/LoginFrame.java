package chores;

import java.io.*;
import javax.swing.*;
import java.awt.*;
package chores;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        super("Login Screen");

        // Set frame icon
        ImageIcon icon = new ImageIcon("C:\\Users\\12253\\Pictures\\Saved Pictures\\png-transparent-logo-brand-product-pattern-letter-b-blue-text-rectangle.png");
        setIconImage(icon.getImage());

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        // Customize components
        Font playfulFont = new Font("Comic Sans MS", Font.PLAIN, 14);
        usernameLabel.setFont(playfulFont);
        passwordLabel.setFont(playfulFont);
        loginButton.setFont(playfulFont);
        loginButton.setBackground(new Color(255, 153, 102));
        loginButton.setForeground(Color.WHITE);

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
        panel.add(loginButton, constraints);

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if login credentials are valid
                if (username.equals("no") && password.equals("you")) {
                    setVisible(false);
                    dispose();
                    SelectAccountFrame selectAccountFrame = new SelectAccountFrame();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
