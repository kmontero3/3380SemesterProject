package code;

import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static Parent getParentByName(ArrayList<Parent> parents, String name, String password) {
        for (Parent parent : parents) {

            if (parent.getName().equals(name) && parent.getPassword().equals(password)) {
                return parent;

            }
        }

        return null;
    }

    public LoginFrame(ArrayList<Parent> parents) {

        super("Login Screen");

        // Set frame icon
        // JFrame f=new JFrame("Test");
        
        // JPanel j=new JPanel();
       
        // f.setSize(1000,500);
        // ImageIcon img = new ImageIcon("beecause.jpg");
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JLabel l=new JLabel(img);
        // j.add(l);
        // f.add(l);
        // f.setVisible(true);

        System.out.println("Log in:");
       
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        double scaleFactor = screenSize.getWidth() / 1920.0;
        // Create components
        JLabel usernameLabel = new JLabel("Username:");

        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField((int) (20 * scaleFactor));
        passwordField = new JPasswordField((int) (20 * scaleFactor));

        JButton loginButton = new JButton("Login");

        // Customize components

        int playfulFontSize = (int) (14 * scaleFactor);
        Font playfulFont = new Font("Comic Sans MS", Font.PLAIN, playfulFontSize);
        usernameLabel.setFont(playfulFont);
        passwordLabel.setFont(playfulFont);
        loginButton.setFont(playfulFont);
        loginButton.setBackground(new Color(255, 153, 102));
        loginButton.setForeground(Color.WHITE);
        Dimension buttonSize = new Dimension((int) (100 * scaleFactor), (int) (30 * scaleFactor));
        loginButton.setPreferredSize(buttonSize);
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
        
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(playfulFont);
        signUpButton.setBackground(new Color(255, 153, 102));
        signUpButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 2;

        panel.add(signUpButton, constraints);
        
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sign up: ");
                SignUpFrame signUpFrame = new SignUpFrame(parents);
            }
        });

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                //login
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);

                // Check if login credentials are valid
                if (getParentByName(parents, username, password) != null) {
                    System.out.println("Successful login!\n\n");
                    setVisible(false);
                    dispose();
                    Parent parent = getParentByName(parents, username, password);
                    SelectAccountFrame selectAccountFrame = new SelectAccountFrame(parent);

                } else {

                    System.out.println("Invalid login credentials \n\n");
                    JOptionPane.showMessageDialog(null, "Invalid login credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
        });

        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set frame size to screen size
        setSize(screenSize);

        // Set maximum frame size to screen size
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*public static void main(String[] args) {
        new LoginFrame();
    }*/
}

