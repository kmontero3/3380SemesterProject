package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.UUID;
import code.Main;
import java.util.ArrayList;

public class ParentSettingsFrame extends JFrame {
    private String[] settingsOptions = {
            "Add a Child",
            "Assign a chore",
            "Set custom rewards",
            "See Child chore, coin, or reward status",
            "Select Account",
            "Log out"
    };

    public ParentSettingsFrame(Parent parent) {
        super("Parent Settings");
        // Create components
        JLabel titleLabel = new JLabel("Parent Settings");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        JPanel settingsPanel = new JPanel(new GridLayout(settingsOptions.length, 1, 0, 10));
        settingsPanel.setBackground(new Color(255, 255, 153));
        for (String settingsOption : settingsOptions) {
            JButton settingsButton = new JButton(settingsOption);
            settingsButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            settingsButton.setBackground(new Color(255, 255, 102));
            settingsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(parent, settingsOption);
                }
            });
            settingsPanel.add(settingsButton);
        }

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

        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(settingsPanel, constraints);

        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleButtonClick(Parent parent, String settingsOption) {
        switch (settingsOption) {

            case "Add a Child":
                System.out.println("Adding a child...");
                addChild(parent);
                break;
                
            case "Assign a chore":
                System.out.println("Assigning a chore...");
                assignChore(parent);
                // Assign a chore logic here
                break;
                
            case "Set custom rewards":
                System.out.println("Customizing reward...");
            	new CustomRewardFrame(parent);
            	break;
                
            case "See Child chore, coin, or reward status":
                System.out.println("Checking Child's chore, coin, and reward status");
                for (Child i : Main.findChildrenByParent(Main.children, parent)) {
                    System.out.println(i.getName());
                    System.out.println("\t Chores:");
                    Main.printChoresByChild(i);
                    System.out.println("\t Coins Available:" + i.getCoinsAvailable());
                    System.out.println("\t \t Available Rewards:");
                    Main.printRewardsByChild(i);
                }
                showChildStatus(parent);
                break;

            case "Select Account":
                //Main.saveCSVData();
                dispose();
                System.out.println("Select a child Account!");
                SelectAccountFrame selectAccountFrame = new SelectAccountFrame(parent);
                break;

            case "Log out":
                System.out.println("Logging out...");
                System.out.println("Good bye!");
                dispose();
                System.exit(0);
                Main.parentMode();
                break;
                
            default:
                throw new IllegalStateException("Unexpected value: " + settingsOption);
        }
    }

    private void addChild(Parent parent) {
        AddChildDialog dialog = new AddChildDialog(this);
        dialog.setVisible(true);
        String childName = dialog.getChildName();

        if (childName == null || childName.trim().isEmpty()) {
            // The user canceled the dialog or entered an empty name, don't create a new
            // profile
            return;
        }

        // Here, you can create the new child profile using the childName variable
        // For example, if you have a class representing a child, you can instantiate
        // it:
        Child child = new Child(UUID.randomUUID(), childName, 0, parent, new ArrayList<Reward>(), new ArrayList<Chore>());
        Main.children.add(child);
        Child.saveChildrentoCSV();
        // Then you may add this child to a data structure, for example:
        // children.add(child);

        JOptionPane.showMessageDialog(this, "Child profile for '" + child.getName() + "' has been created.");
    }

    private class AddChildDialog extends JDialog {
        private JTextField childNameTextField;
        private String childName;

        public AddChildDialog(JFrame parent) {
            super(parent, "Add a Child", true);

            // Create components
            System.out.println("Enter New Child's Name: ");
            JLabel titleLabel = new JLabel("Enter New Child's Name");
            titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

            childNameTextField = new JTextField(20);

            JButton addButton = new JButton("Add");
            addButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    childName = childNameTextField.getText();
                    System.out.println(childName + " has successfully been added as a child!");

                    dispose();
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

            constraints.gridy = 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            panel.add(childNameTextField, constraints);

            constraints.gridy = 2;
            constraints.fill = GridBagConstraints.NONE;
            panel.add(addButton, constraints);

            // Add panel to dialog
            add(panel);

            // Set dialog properties
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 200);
            setLocationRelativeTo(parent);
        }

        public String getChildName() {
            return childName;
        }
    }

    
    private void assignChore(Parent parent) {
        AssignChoreDialog dialog = new AssignChoreDialog(Main.findChildrenByParent(Main.children, parent), this);
        dialog.setVisible(true);
    
        String selectedChild = dialog.getChildName();
        String selectedChore = dialog.getChoreName();

        Child child = Main.getChildByName(Main.findChildrenByParent(Main.children, parent), selectedChild);
        
        Chore chore = Main.getChoreByName(selectedChore, Main.chores);
        
    
        if (selectedChild == null || selectedChore == null) {
            // The user canceled the dialog, don't assign a chore
            return;
        }
        System.out.println("Successfully assigned " + chore.getName() + " to " + child.getName());
        child.addChore(chore);

    
        JOptionPane.showMessageDialog(this, "Chore Assigned!");
    }
    

    private class AssignChoreDialog extends JDialog {
        private String childName;
        private String choreName;

        public AssignChoreDialog(ArrayList<Child> childrenOfParent, JFrame parent) {
            
            super(parent, "Assign a Chore", true);
            // Create components
            JLabel titleLabel = new JLabel("Assign Chore");
            titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));

            // Create child combo box
            JComboBox<String> childComboBox = new JComboBox<String>();
            childComboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
            childComboBox.setBackground(new Color(255, 255, 153));
            
            for (Child child : childrenOfParent) {
                childComboBox.addItem(child.getName());
            }

            // Create chore combo box
            JComboBox<String> choreComboBox = new JComboBox<String>();
            choreComboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
            choreComboBox.setBackground(new Color(255, 255, 153));
            
            for (Chore chore : Main.chores) {
                choreComboBox.addItem(chore.getName());
            }

            JButton addButton = new JButton("Assign");
            JButton customButton = new JButton("Custom Chore");
            
            customButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            customButton.setBackground(new Color(102, 255, 102));
           
            addButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            addButton.setBackground(new Color(102, 255, 102));
            
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    childName = (String) childComboBox.getSelectedItem();
                    choreName = (String) choreComboBox.getSelectedItem();
                    dispose();
                }
            });
            
            customButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CustomChoreFrame();
                    dispose();
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
            constraints.gridwidth = 2;
            constraints.anchor = GridBagConstraints.CENTER;
            panel.add(titleLabel, constraints);

            constraints.gridy = 1;
            constraints.gridx = 0;
            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            panel.add(new JLabel("Child:"), constraints);
            constraints.gridx = 1;
            panel.add(childComboBox, constraints);

            constraints.gridy = 2;
            constraints.gridx = 0;
            panel.add(new JLabel("Chore:"), constraints);
            constraints.gridx = 1;
            panel.add(choreComboBox, constraints);

            constraints.gridy = 3;
            constraints.gridx = 0;
            constraints.gridwidth = 2;
            constraints.fill = GridBagConstraints.NONE;
            panel.add(addButton, constraints);

            constraints.gridy = 3;
            constraints.gridx = 3;
            constraints.gridwidth = 2;
            constraints.fill = GridBagConstraints.NONE;
            panel.add(customButton, constraints);
            // Add panel to dialog
            add(panel);

            // Set dialog properties
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(500, 300);
            setLocationRelativeTo(parent);
            
        }
    
        public String getChoreName() {
            return choreName;
        }
    
        public String getChildName() {
            return childName;
        }
    }
    

    private void showChildStatus(Parent parent) {
        ChildStatusDialog dialog = new ChildStatusDialog(this, Main.findChildrenByParent(Main.children, parent));
        dialog.setVisible(true);
    }

    class ChildStatusDialog extends JDialog {
        public ChildStatusDialog(JFrame parent, ArrayList<Child> childrenOfParent) {
            super(parent, "Child Chore, Coin, and Reward Status", true);

            // Create components
            JLabel titleLabel = new JLabel("Child Chore, Coin, and Reward Status");
            titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

            JPanel childStatusPanel = new JPanel();
            childStatusPanel.setLayout(new BoxLayout(childStatusPanel, BoxLayout.Y_AXIS));
            childStatusPanel.setBackground(new Color(245, 245, 245));

            Font childFont = new Font("Comic Sans MS", Font.BOLD, 18);
            for (Child child : childrenOfParent) {
                JPanel childBubble = new JPanel();
                childBubble.setLayout(new BorderLayout());
                childBubble.setBackground(new Color(255, 255, 204));
                childBubble.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 2),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

                JLabel childLabel = new JLabel(child.getName());
                childLabel.setFont(childFont);
                childLabel.setHorizontalAlignment(SwingConstants.CENTER);
                childBubble.add(childLabel, BorderLayout.NORTH);

                JPanel statsPanel = new JPanel();
                statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
                statsPanel.setOpaque(false);

                JLabel choresLabel = new JLabel("Chores: " + Main.choresByChildToSting(child));
                choresLabel.setFont(childFont);
                choresLabel.setForeground(new Color(0, 128, 0));
                statsPanel.add(choresLabel);

                JLabel coinsLabel = new JLabel("Coins: " + child.getCoinsAvailable());
                coinsLabel.setFont(childFont);
                coinsLabel.setForeground(new Color(0, 0, 255));
                statsPanel.add(coinsLabel);

                JLabel rewardsLabel = new JLabel("Rewards: " + Main.rewardsByChildToString(child));
                rewardsLabel.setFont(childFont);
                rewardsLabel.setForeground(new Color(255, 0, 0));
                statsPanel.add(rewardsLabel);

                childBubble.add(statsPanel, BorderLayout.CENTER);
                childStatusPanel.add(childBubble);
                childStatusPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            // Layout components
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBackground(new Color(245, 245, 245));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(10, 10, 10, 10);

            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.anchor = GridBagConstraints.CENTER;
            panel.add(titleLabel, constraints);

            constraints.gridy = 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            panel.add(childStatusPanel, constraints);
            
            add(panel);

            // Set dialog properties
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(900, 900);
            setLocationRelativeTo(parent);
        }
    }
}