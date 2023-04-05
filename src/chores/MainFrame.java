package chores;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame {
    private String selectedAccount;

    public MainFrame(String selectedAccount) {
        super("Main Menu");
        this.selectedAccount = selectedAccount;

        // Create components
        JButton coinsButton = new JButton("Coins");
        JButton choresButton = new JButton("Chores");
        JButton shopButton = new JButton("Shop");

        // Customize components
        Font playfulFont = new Font("Comic Sans MS", Font.PLAIN, 14);
        coinsButton.setFont(playfulFont);
        coinsButton.setBackground(new Color(255, 153, 102));
        coinsButton.setForeground(Color.WHITE);

        choresButton.setFont(playfulFont);
        choresButton.setBackground(new Color(153, 255, 153));
        choresButton.setForeground(Color.WHITE);

        shopButton.setFont(playfulFont);
        shopButton.setBackground(new Color(153, 153, 255));
        shopButton.setForeground(Color.WHITE);

        // Layout components
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 153));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(coinsButton, constraints);

        constraints.gridx = 1;
        panel.add(choresButton, constraints);

        constraints.gridx = 2;
        panel.add(shopButton, constraints);

        // Add action listeners to buttons
        coinsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CoinsFrame();
            }
        });

        choresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChoresFrame();
            }
        });

        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShopFrame();
            }
        });

        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
