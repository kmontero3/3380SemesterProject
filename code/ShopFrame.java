package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ShopFrame extends JFrame {
    private JLabel coinsLabel;

    public ShopFrame(Child child) {
        super("Shop");

        // Create components
        Font playfulFont = new Font("Comic Sans MS", Font.BOLD, 24);
        JLabel label = new JLabel("Welcome to the Shop!");
        label.setFont(playfulFont);
        coinsLabel = new JLabel("Coins: " + child.getCoinsAvailable());
        coinsLabel.setFont(playfulFont);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(255, 255, 153));
        headerPanel.add(label, BorderLayout.WEST);
        headerPanel.add(coinsLabel, BorderLayout.EAST);

        // ActionListener for item buttons
        ActionListener itemButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                Reward reward = (Reward) button.getClientProperty("reward");
                handlePurchase(child, reward);
            }
        };
        
        // ActionListener for sell buttons
        ActionListener sellButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                Reward reward = (Reward) button.getClientProperty("reward");
                handleSale(child, reward);
            }
        };

        // Create shop items
        Font itemFont = new Font("Comic Sans MS", Font.PLAIN, 18);
        JPanel itemsPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        itemsPanel.setBackground(new Color(255, 255, 153));
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Reward reward : Main.rewards) {
            JPanel itemPanel = createShopItem(itemFont, reward, "item1.png", itemButtonListener, sellButtonListener, child);
            itemsPanel.add(itemPanel);
        }

        JButton backButton = new JButton("Back");
        backButton.setFont(playfulFont);
        backButton.setBackground(new Color(255, 153, 102));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectAccountFrame.mainFrame.ShowMainFrame(child);
                dispose();
            }
        });

        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(255, 255, 153));
        footerPanel.add(backButton, BorderLayout.WEST);

        // Add panels to frame
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(itemsPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createShopItem(Font itemFont, Reward reward, String itemImagePath, ActionListener itemButtonListener, ActionListener sellButtonListener, Child child) {
        JPanel itemPanel = new JPanel(new BorderLayout(10, 10));
        itemPanel.setBackground(new Color(255, 255, 153));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JLabel itemLabel = new JLabel(reward.getName() + " - " + reward.getCoinAmount() + " coins");
        itemLabel.setFont(itemFont);

        ImageIcon itemIcon = new ImageIcon(itemImagePath); // Replace with actual image path
        JButton itemButton = new JButton(itemIcon);
        itemButton.addActionListener(itemButtonListener);
        itemButton.putClientProperty("reward", reward);

        boolean hasItem = child.hasReward(reward);

        JButton sellButton = new JButton("Sell");
        sellButton.setFont(itemFont);
        sellButton.setBackground(new Color(255, 153, 102));
        sellButton.setForeground(Color.WHITE);
        sellButton.addActionListener(sellButtonListener);
        sellButton.putClientProperty("reward", reward);
        sellButton.setEnabled(hasItem);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        buttonPanel.setBackground(new Color(255, 255, 153));
        buttonPanel.add(itemButton);
        buttonPanel.add(sellButton);

        itemPanel.add(buttonPanel, BorderLayout.CENTER);
        itemPanel.add(itemLabel, BorderLayout.SOUTH);

        return itemPanel;
    }

    private void handlePurchase(Child child, Reward reward) {
        if (child.getCoinsAvailable() >= reward.getCoinAmount()) {
            System.out.println(child.getName() + " has successfully purchased " + reward.getName() + "!");
            child.addReward(child, reward);
            System.out.println("They now have " + child.getCoinsAvailable() + " available coins.");
            JOptionPane.showMessageDialog(this, "You have successfully purchased " + reward.getName() + "!", "Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
            coinsLabel.setText("Coins: " + child.getCoinsAvailable());
        } else {
            JOptionPane.showMessageDialog(this, "You do not have enough coins to purchase " + reward.getName() + ".", "Not Enough Coins", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSale(Child child, Reward reward) {
        if (child.hasReward(reward)) {
            int salePrice = reward.getCoinAmount() / 2;
            System.out.println(child.getName() + " has successfully sold " + reward.getName() + " for " + salePrice + " coins.");
            child.removeReward(reward);
            child.updateCoins(child.getCoinsAvailable()+salePrice);
            System.out.println("They now have " + child.getCoinsAvailable() + " available coins.");
            JOptionPane.showMessageDialog(this, "You have successfully sold " + reward.getName() + " for " + salePrice + " coins!", "Sale Successful", JOptionPane.INFORMATION_MESSAGE);
            coinsLabel.setText("Coins: " + child.getCoinsAvailable());
        } else {
            JOptionPane.showMessageDialog(this, "You do not have " + reward.getName() + " to sell.", "Item Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }
}
