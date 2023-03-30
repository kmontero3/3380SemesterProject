package chores;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ChoresFrame extends JFrame {
    public ChoresFrame() {
        super("Chores");

        // Create components
        Font playfulFont = new Font("Comic Sans MS", Font.PLAIN, 14);
        JLabel label = new JLabel("Chores List:");
        label.setFont(playfulFont);

        String[] choresData = {"Chore 1", "Chore 2", "Chore 3", "Chore 4"}; // Replace with actual chores data
        JList<String> choresList = new JList<>(choresData);
        choresList.setFont(playfulFont);
        JScrollPane scrollPane = new JScrollPane(choresList);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        // Layout components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 153));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        constraints.gridy = 1;
        panel.add(scrollPane, constraints);

        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}


