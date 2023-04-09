package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class CustomRewardFrame extends JFrame {

    public CustomRewardFrame(Parent parent) {
        super("Create Custom Reward");

        // Create components
        Font headerFont = new Font("Comic Sans MS", Font.BOLD, 24);
        JLabel headerLabel = new JLabel("Create Custom Reward");
        headerLabel.setFont(headerFont);

        JLabel titleLabel = new JLabel("Reward Title:");
        JTextField titleField = new JTextField(20);

        JLabel coinsLabel = new JLabel("Coin Amount:");
        JTextField coinsField = new JTextField(20);

        JButton createButton = new JButton("Create Reward");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rewardTitle = titleField.getText().trim();
                int coinAmount;
                try {
                    coinAmount = Integer.parseInt(coinsField.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CustomRewardFrame.this, "Invalid coin amount. Please enter a valid number.");
                    return;
                }
                if (rewardTitle.isEmpty()) {
                    JOptionPane.showMessageDialog(CustomRewardFrame.this, "Please enter a reward title.");
                    return;
                }
                Reward customReward = new Reward(UUID.randomUUID(), rewardTitle, coinAmount);
                Main.rewards.add(customReward);
                //Reward.saveRewardsToCSV();
                JOptionPane.showMessageDialog(CustomRewardFrame.this, "Custom reward created!");
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
