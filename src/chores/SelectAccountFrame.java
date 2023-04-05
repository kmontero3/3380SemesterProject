package chores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectAccountFrame extends JFrame {
    private String[] accountNames = {"Child 1", "Child 2", "Child 3"}; // Replace with actual account names

    public SelectAccountFrame() {
        super("Select Account");

        // Create components
        JLabel titleLabel = new JLabel("Select an Account");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        JPanel accountPanel = new JPanel(new GridLayout(1, accountNames.length, 10, 0));
        accountPanel.setBackground(new Color(255, 255, 153));
        for (String accountName : accountNames) {
            JButton accountButton = new JButton(accountName);
            accountButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            accountButton.setBackground(new Color(255, 255, 102));
            accountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Add your code to handle account selection here
                    System.out.println("Selected account: " + accountName);

                    // Add this code inside the actionPerformed method of the accountButton's ActionListener
                    MainFrame mainFrame = new MainFrame(accountName);
                    dispose(); // Close the SelectAccountFrame
                }
            });
            accountPanel.add(accountButton);
        }

        // Create Parent Settings button
        JButton parentSettingsButton = new JButton("Parent Settings");
        parentSettingsButton.setFont(new Font("Arial", Font.PLAIN, 12));
        parentSettingsButton.setBackground(new Color(200, 200, 200));
        parentSettingsButton.setForeground(Color.BLACK);
        parentSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open ParentSettingsFrame
                ParentSettingsFrame parentSettingsFrame = new ParentSettingsFrame();
            }
        });

        // Layout components
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 153));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        panel.add(emptyPanel, constraints);

        constraints.gridx = 2;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        panel.add(parentSettingsButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(accountPanel, constraints);

        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

