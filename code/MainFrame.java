package code;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

class MainFrame extends JFrame {
    private String selectedAccount;
    private Timer coinsAnimationTimer;
    
    public MainFrame(Child child) {
        super("Main Menu");
        this.selectedAccount = selectedAccount;
        ShowMainFrame(child);
    }

    public void ShowMainFrame(Child child) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        // Create components
        Parent currentParent = child.getParent();
        JLabel coinsLabel = new JLabel("Coins: " + child.getCoinsAvailable());
        JButton choresButton = new JButton("Chores");
        JButton shopButton = new JButton("Shop");
        JButton backButton = new JButton("Back");
        JButton leaderboardButton = new JButton("Show Leaderboard");
        JButton rewardsButton = new JButton("Rewards");
        JButton achievementsButton = new JButton("Achievements");

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

        leaderboardButton.setFont(playfulFont);
        leaderboardButton.setBackground(new Color(248, 200, 255));
        leaderboardButton.setForeground(Color.WHITE);
        leaderboardButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LeaderboardFrame(currentParent);  
            }
        });
        
        rewardsButton.setFont(playfulFont);
        rewardsButton.setBackground(new Color(102, 204, 255));
        rewardsButton.setForeground(Color.WHITE);
        rewardsButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        achievementsButton.setFont(playfulFont);
        achievementsButton.setBackground(new Color(255, 204, 102));
        achievementsButton.setForeground(Color.WHITE);
        achievementsButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        rewardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new RewardFrame(child);
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

        constraints    .gridx = 1;
        panel.add(choresButton, constraints);

        constraints.gridx = 2;
        panel.add(shopButton, constraints);

        constraints.gridx = 3;
        panel.add(rewardsButton, constraints);

        GridBagConstraints backButtonConstraints = new GridBagConstraints();
        backButtonConstraints.gridx = 0;
        backButtonConstraints.gridy = 1;
        backButtonConstraints.gridwidth = 4;
        backButtonConstraints.anchor = GridBagConstraints.CENTER;
        panel.add(backButton, backButtonConstraints);
        
        GridBagConstraints achievementsButtonConstraints = new GridBagConstraints();
        achievementsButtonConstraints.gridx = 4;
        achievementsButtonConstraints.gridy = 0;
        achievementsButtonConstraints.weighty = 1;
        achievementsButtonConstraints.weightx = 1;
        achievementsButtonConstraints.fill = GridBagConstraints.BOTH;
        
        panel.add(achievementsButton, achievementsButtonConstraints);
        panel.add(leaderboardButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SelectAccountFrame(child.getParent());
            }
        });
        
        coinsAnimationTimer = createCoinsAnimationTimer(child, coinsLabel);
        coinsAnimationTimer.start();

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

        achievementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AchievementsFrame(child);
            }
        });
        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();

        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Timer createCoinsAnimationTimer(Child child, JLabel coinsLabel) {
        int animationDelay = 100; // Update the coins label every 100ms
        if(child.getCoinsAvailable()>=100) {
            int scaled = child.getCoinsAvailable()/100;
            if (scaled >= 1) {
                animationDelay -= scaled * 100;
            }
        }
        ActionListener animationListener = new ActionListener() {
            int animationStep = 0;
            int numberOfSteps = child.getCoinsAvailable();
            int targetCoins = child.getCoinsAvailable();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (animationStep < numberOfSteps) {
                    animationStep++;
                    int coins = targetCoins - numberOfSteps + animationStep;
                    coinsLabel.setText("Coins: " + coins);
                } else {
                    coinsAnimationTimer.stop();
                }
            }
        };

        return new Timer(animationDelay, animationListener);
    }


    public void dispose() {
        if (coinsAnimationTimer != null) {
            coinsAnimationTimer.stop();
        }
        super.dispose();
    }
    
    
    class RewardFrame extends JFrame {
        private Child child;
        private JPanel rewardsListPanel;

        public RewardFrame(Child child) {
            super("Rewards");
            this.child = child;

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(255, 255, 153));

            rewardsListPanel = new JPanel();
            rewardsListPanel.setLayout(new BoxLayout(rewardsListPanel, BoxLayout.Y_AXIS));
            rewardsListPanel.setBackground(new Color(255, 255, 153));
            rewardsListPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            updateRewardsList();

            JScrollPane scrollPane = new JScrollPane(rewardsListPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            panel.add(scrollPane, BorderLayout.CENTER);

            JButton backButton = new JButton("Back");
            backButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new MainFrame(child);
                }
            });

            panel.add(backButton, BorderLayout.SOUTH);
            add(panel);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        private void updateRewardsList() {
            rewardsListPanel.removeAll();

            ArrayList<Reward> rewards = child.getRewards();

            if (rewards.size() > 0) {
                for (Reward reward : rewards) {
                    JPanel rewardPanel = createRewardPanel(reward);
                    rewardsListPanel.add(rewardPanel);
                    rewardsListPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            } else {
                JLabel noRewardsLabel = new JLabel("You have no rewards.");
                noRewardsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
                rewardsListPanel.add(noRewardsLabel);
            }

            rewardsListPanel.revalidate();
            rewardsListPanel.repaint();
        }

        private JPanel createRewardPanel(Reward reward) {
            JPanel rewardPanel = new JPanel();
            rewardPanel.setLayout(new BoxLayout(rewardPanel, BoxLayout.X_AXIS));
            rewardPanel.setBackground(new Color(255, 255, 153));
            rewardPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JLabel rewardLabel = new JLabel(reward.getName() + " - " + reward.getCoinAmount() + " coins");
            rewardLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

            JButton redeemButton = new JButton("Redeem");
            redeemButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            redeemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(RewardFrame.this, "Are you sure you want to redeem " + reward.getName() + "?", "Confirm Redemption", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        child.removeReward(reward);
                        JOptionPane.showMessageDialog(RewardFrame.this, "You have redeemed " + reward.getName() + "!", "Reward Redeemed", JOptionPane.INFORMATION_MESSAGE);
                        updateRewardsList();
                    }
                }
            });

            rewardPanel.add(rewardLabel);
            rewardPanel.add(Box.createHorizontalGlue());
            rewardPanel.add(redeemButton);

            return rewardPanel;
        }
    }
    
    class AchievementsFrame extends JFrame {
        private Child child;
        private JPanel achievementsListPanel;

        public AchievementsFrame(Child child) {
            super("Achievements");
            this.child = child;

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(255, 255, 153));

            achievementsListPanel = new JPanel();
            achievementsListPanel.setLayout(new BoxLayout(achievementsListPanel, BoxLayout.Y_AXIS));
            achievementsListPanel.setBackground(new Color(255, 255, 153));
            achievementsListPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            updateAchievementsList(this.child);

            JScrollPane scrollPane = new JScrollPane(achievementsListPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            panel.add(scrollPane, BorderLayout.CENTER);

            JButton backButton = new JButton("Back");
            backButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new MainFrame(child);
                }
            });

            panel.add(backButton, BorderLayout.SOUTH);
            add(panel);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        private void updateAchievementsList(Child child) {
            achievementsListPanel.removeAll();

            ArrayList<Achievement> achievements = child.getAchievements();

            if (achievements.size() > 0) {
                for (Achievement achievement : achievements) {
                	if(child.getTotalChoresCompleted() >= achievement.getRequiredChores())
                	{
                		child.completeAchievement(achievement);
                	}
                    JPanel achievementPanel = createAchievementPanel(achievement, child);
                    achievementsListPanel.add(achievementPanel);
                    achievementsListPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            } else {
                JLabel noAchievementsLabel = new JLabel("You have no achievements.");
                noAchievementsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
                achievementsListPanel.add(noAchievementsLabel);
            }

            achievementsListPanel.revalidate();
            achievementsListPanel.repaint();
        }

        private JPanel createAchievementPanel(Achievement achievement, Child child) {
            JPanel achievementPanel = new JPanel();
            achievementPanel.setLayout(new BoxLayout(achievementPanel, BoxLayout.Y_AXIS));
            achievementPanel.setBackground(new Color(255, 255, 153));
            achievementPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JLabel achievementLabel = new JLabel(achievement.getName() + " - " + achievement.getDescription());
            achievementLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            
            // Create the progress bar
            int progress = (int) (((double) child.getTotalChoresCompleted() / achievement.getRequiredChores()) * 100);
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setValue(progress);
            progressBar.setStringPainted(true);
            progressBar.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            progressBar.setString(child.getTotalChoresCompleted() + "/" + achievement.getRequiredChores() + " chores");

            JButton claimButton = new JButton("Claim");
            claimButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            claimButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (achievement.isCompleted()) {
                        int confirm = JOptionPane.showConfirmDialog(AchievementsFrame.this, "Are you sure you want to claim " + achievement.getName() + "?", "Confirm Claim", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            child.removeAchievement(achievement);
                            JOptionPane.showMessageDialog(AchievementsFrame.this, "You have claimed " + achievement.getName() + "!", "Achievement Claimed", JOptionPane.INFORMATION_MESSAGE);
                            updateAchievementsList(child);
                        }
                    } else {
                        JOptionPane.showMessageDialog(AchievementsFrame.this, "You haven't completed " + achievement.getName() + " yet!", "Achievement Not Completed", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            if (!achievement.isCompleted()) {
                claimButton.setEnabled(false);
            }

            achievementPanel.add(achievementLabel);
            achievementPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            achievementPanel.add(progressBar);
            achievementPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            achievementPanel.add(claimButton);

            return achievementPanel;
        }
    }
}
