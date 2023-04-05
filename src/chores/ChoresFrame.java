package chores;

import javax.swing.*;
import java.awt.*;

class ChoresFrame extends JFrame {
    public ChoresFrame() {
        super("Chores");

        // Create components
        Font playfulFont = new Font("Comic Sans MS", Font.PLAIN, 14);
        JLabel label = new JLabel("Chores List:");
        label.setFont(playfulFont);

        String[] choresData = {"Chore 1", "Chore 2", "Chore 3", "Chore 4"}; // Replace with actual chores data

        // Create a panel with BoxLayout for checkboxes
        JPanel choresPanel = new JPanel();
        choresPanel.setLayout(new BoxLayout(choresPanel, BoxLayout.Y_AXIS));
        choresPanel.setBackground(new Color(255, 255, 153));

        // Create a JCheckBox for each chore and add it to the panel
        Font checkBoxFont = new Font("Comic Sans MS", Font.PLAIN, 24);
        for (String chore : choresData) {
            JCheckBox checkBox = new JCheckBox(chore);
            checkBox.setFont(checkBoxFont);
            checkBox.setBackground(new Color(255, 255, 153));
            choresPanel.add(checkBox);
        }

        // Wrap the chores panel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(choresPanel);
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


