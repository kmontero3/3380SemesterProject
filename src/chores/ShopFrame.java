package chores;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ShopFrame extends JFrame {
    public ShopFrame() {
        super("Shop");

        // Create components
        Font playfulFont = new Font("Comic Sans MS", Font.PLAIN, 14);
        JLabel label = new JLabel("Welcome to the Shop!");
        label.setFont(playfulFont);

        // Create shop items
        JLabel item1Label = new JLabel("Item 1");
        item1Label.setFont(playfulFont);
        ImageIcon item1Icon = new ImageIcon("item1.png"); // Replace with actual image path
        JButton item1Button = new JButton(item1Icon);

        JLabel item2Label = new JLabel("Item 2");
        item2Label.setFont(playfulFont);
        ImageIcon item2Icon = new ImageIcon("item2.png"); // Replace with actual image path
        JButton item2Button = new JButton(item2Icon);

        JLabel item3Label = new JLabel("Item 3");
        item3Label.setFont(playfulFont);
        ImageIcon item3Icon = new ImageIcon("item3.png"); // Replace with actual image path
        JButton item3Button = new JButton(item3Icon);

        // Layout components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 153));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        constraints.gridy = 1;
        panel.add(item1Label, constraints);
        constraints.gridy = 2;
        panel.add(item1Button, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(item2Label, constraints);
        constraints.gridy = 2;
        panel.add(item2Button, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        panel.add(item3Label, constraints);
        constraints.gridy = 2;
        panel.add(item3Button, constraints);

        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

