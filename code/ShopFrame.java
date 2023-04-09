package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ShopFrame extends JFrame {
    private JLabel coinsLabel;
    private AppFactory appFactory;
    
    public ShopFrame(Child child, AppFactory appFactory) {
        super("Shop");
     
        this.appFactory = appFactory;
        
        coinsLabel = createCoinsLabel(child);
        JPanel headerPanel = createHeaderPanel(coinsLabel);
        ActionListener itemButtonListener = e -> handlePurchase(child, (Reward) ((JButton) e.getSource()).getClientProperty("reward"));
        ActionListener sellButtonListener = e -> handleSale(child, (Reward) ((JButton) e.getSource()).getClientProperty("reward"));
        JPanel itemsPanel = createItemsPanel(itemButtonListener, sellButtonListener, child);
        JButton backButton = createBackButton(child);
        JPanel footerPanel = createFooterPanel(backButton);

        // Add panels to frame
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(itemsPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        configureFrame();
    }

    private JLabel createCoinsLabel(Child child) {
        JLabel coinsLabel = new JLabel("Coins: " + child.getCoinsAvailable());
        coinsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        return coinsLabel;
    }

    private JPanel createHeaderPanel(JLabel coinsLabel) {
        Font playfulFont = new Font("Comic Sans MS", Font.BOLD, 24);
        JLabel label = new JLabel("Welcome to the Shop!");
        label.setFont(playfulFont);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(255, 255, 153));
        headerPanel.add(label, BorderLayout.WEST);
        headerPanel.add(coinsLabel, BorderLayout.EAST);
        return headerPanel;
    }

    private JPanel createItemsPanel(ActionListener itemButtonListener, ActionListener sellButtonListener, Child child) {
        Font itemFont = new Font("Comic Sans MS", Font.PLAIN, 18);
        JPanel itemsPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        itemsPanel.setBackground(new Color(255, 255, 153));
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Reward reward : Main.rewards) {
            JPanel itemPanel = createShopItem(itemFont, reward, "item1.png", itemButtonListener, sellButtonListener, child);
            itemsPanel.add(itemPanel);
        }
        return itemsPanel;
    }

    private JButton createBackButton(Child child) {
        Font playfulFont = new Font("Comic Sans MS", Font.BOLD, 24);
        JButton backButton = new JButton("Back");
        backButton.setFont(playfulFont);
        backButton.setBackground(new Color(255, 153, 102));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            SelectAccountFrame.mainFrame.ShowMainFrame(child);
            dispose();
        });
        return backButton;
    }

    private JPanel createFooterPanel(JButton backButton) {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(255, 255, 153));
        footerPanel.add(backButton, BorderLayout.WEST);
        return footerPanel;
    }

    private JPanel createShopItem(Font itemFont, Reward reward, String itemImagePath, ActionListener itemButtonListener, ActionListener sellButtonListener, Child child) {
        JPanel itemPanel = new JPanel(new BorderLayout(10, 10));
        itemPanel.setBackground(new Color(255, 255, 153));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JLabel itemLabel = new JLabel(reward.getName() + " - " + reward.getCoinAmount() + "coins");
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
            Reward newReward = appFactory.createReward(reward.getId(), reward.getName(), reward.getCoinAmount());
            child.addReward(child, newReward);
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
            child.updateCoins(child.getCoinsAvailable() + salePrice);
            System.out.println("They now have " + child.getCoinsAvailable() + " available coins.");
            JOptionPane.showMessageDialog(this, "You have successfully sold " + reward.getName() + " for " + salePrice + " coins!", "Sale Successful", JOptionPane.INFORMATION_MESSAGE);
            coinsLabel.setText("Coins: " + child.getCoinsAvailable());
        } else {
            JOptionPane.showMessageDialog(this, "You do not have " + reward.getName() + " to sell.", "Item Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configureFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


    
    

