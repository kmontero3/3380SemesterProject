package code;

import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame {
    
    public MainFrame(Child child) {
        super("Main Menu");
         ShowMainFrame(child);
    }

    public void ShowMainFrame(Child child) {
        // Create components

    	Parent currentParent = child.getParent();
        JLabel coinsLabel = new JLabel("Coins: " + child.getCoinsAvailable());
        JButton choresButton = new JButton("Chores");
        JButton shopButton = new JButton("Shop");
        JButton backButton = new JButton("Back");

        // Customize components
        Font playfulFont = new Font("Comic Sans MS", Font.BOLD, 40);
        coinsLabel.setFont(playfulFont);
        coinsLabel.setForeground(new Color(255, 153, 102));

        choresButton.setFont(playfulFont);
        choresButton.setBackground(new Color(153, 255, 153));
        choresButton.setForeground(Color.WHITE);
        choresButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        shopButton.setFont(playfulFont);
        shopButton.setBackground(new Color(153, 153, 255));
        shopButton.setForeground(Color.WHITE);
        shopButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        backButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        backButton.setBackground(new Color(255, 153, 102));
        backButton.setForeground(Color.WHITE);
        
        JButton leaderboardButton = new JButton("Show Leaderboard");
        leaderboardButton.setFont(playfulFont);
        leaderboardButton.setBackground(new Color(248, 200, 255));
        leaderboardButton.setForeground(Color.WHITE);
       // leaderboardButton.setPreferredSize(new Dimension(100, 300));
        leaderboardButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                new LeaderboardFrame(currentParent);  
                
            }
        });


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
        panel.add(coinsLabel, constraints);

        constraints.gridx = 1;
        panel.add(choresButton, constraints);

        constraints.gridx = 2;
        panel.add(shopButton, constraints);

        GridBagConstraints backButtonConstraints = new GridBagConstraints();
        backButtonConstraints.gridx = 0;
        backButtonConstraints.gridy = 1;
        backButtonConstraints.gridwidth = 3;
        backButtonConstraints.anchor = GridBagConstraints.CENTER;
        panel.add(backButton, backButtonConstraints);
        
        panel.add(leaderboardButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SelectAccountFrame(child.getParent());
            }
        });
        
        choresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new ChoresFrame(child);
            }
        });

        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new ShopFrame(child);
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
