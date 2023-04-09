package code;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class ShopFrame extends JFrame {
    public ShopFrame(Child child) {
        super("Shop");

        // Create components
        Font playfulFont = new Font("Comic Sans MS", Font.BOLD, 24);
        JLabel label = new JLabel("Welcome to the Shop!");
        label.setFont(playfulFont);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 255, 153));
        panel.add(label);

        // ActionListener for item buttons
        ActionListener itemButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                Reward reward = (Reward) button.getClientProperty("reward");
                handlePurchase(child, reward);
            }
        };

        // Create shop items
        Font itemFont = new Font("Comic Sans MS", Font.PLAIN, 18);
        
        for (Reward reward : Main.rewards) {
            panel.add(createShopItem(itemFont, reward, "item1.png", itemButtonListener));
        }

        JButton backButton = new JButton("Back");
        backButton.setFont(playfulFont);
        backButton.setBackground(new Color(255, 153, 102));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(70, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectAccountFrame.mainFrame.ShowMainFrame(child);
                dispose();
            }
        });
        panel.add(backButton);

        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createShopItem(Font itemFont, Reward reward, String itemImagePath, ActionListener itemButtonListener) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemPanel.setBackground(new Color(255, 255, 153));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JLabel itemLabel = new JLabel(reward.getName() + " - " + reward.getCoinAmount() + " coins");
        itemLabel.setFont(itemFont);

        ImageIcon itemIcon = new ImageIcon(itemImagePath); // Replace with actual image path
        JButton itemButton = new JButton(itemIcon);
        itemButton.addActionListener(itemButtonListener);
        itemButton.putClientProperty("reward", reward);

        itemPanel.add(itemButton);
        itemPanel.add(itemLabel);

        return itemPanel;
    }

    private void handlePurchase(Child child, Reward reward) {
        if (child.getCoinsAvailable() >= reward.getCoinAmount()) {
            System.out.println(child.getName() + " has successfully purchased " + reward.getName() + "!");
            child.addReward(child, reward);
            System.out.println("They now have " + child.getCoinsAvailable() + " available coins.");
            JOptionPane.showMessageDialog(this, "You have successfully purchased " + reward.getName() + "!", "Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
            new MainFrame(child);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "You do not have enough coins to purchase " + reward.getName() + ".", "Not Enough Coins", JOptionPane.ERROR_MESSAGE);
        }
    }
}

