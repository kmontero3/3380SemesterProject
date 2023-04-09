package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class CustomChoreFrame extends JFrame {

    public CustomChoreFrame() {
        super("Create Custom Chore");
        setAlwaysOnTop(true);
        System.out.println("Creating a custom chore...");

        // Create components
        Font headerFont = new Font("Comic Sans MS", Font.BOLD, 24);
        JLabel headerLabel = new JLabel("Create Custom Chore");
        headerLabel.setFont(headerFont);

        JLabel titleLabel = new JLabel("Chore Title:");
        JTextField titleField = new JTextField(20);

        JLabel coinsLabel = new JLabel("Earned Coins:");
        JTextField coinsField = new JTextField(20);

        JButton createButton = new JButton("Create Chore");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String choreTitle = titleField.getText().trim();
                int earnedCoins;

                try {
                    earnedCoins = Integer.parseInt(coinsField.getText().trim());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CustomChoreFrame.this, "Invalid earned coins amount. Please enter a valid number.");
                    return;
                }

                if (choreTitle.isEmpty()) {
                    JOptionPane.showMessageDialog(CustomChoreFrame.this, "Please enter a chore title.");
                    return;
                }

                Chore customChore = new Chore(UUID.randomUUID(), choreTitle, earnedCoins, false, 1);
                Main.chores.add(customChore);
                Chore.saveChoresToCSV();
                JOptionPane.showMessageDialog(CustomChoreFrame.this, "Custom chore created!");
                System.out.println("Successfully created the chore '" + customChore.getName() +"' worth $" +customChore.getRewardAmount());
                dispose();
            }
        });

        // Layout components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 153));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        panel.add(headerLabel, constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(titleLabel, constraints);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;

        panel.add(titleField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.LINE_END;

        panel.add(coinsLabel, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;

        panel.add(coinsField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;

        panel.add(createButton, constraints);

        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}