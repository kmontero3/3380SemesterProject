package code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;

class ChoresFrame extends JFrame {
    public ChoresFrame(Child child) {
        super("Chores");

        // Create components
        Font playfulFont = new Font("Comic Sans MS", Font.PLAIN, 14);
        JLabel label = new JLabel("Chores List:");
        label.setFont(playfulFont);

        // Create a panel with BoxLayout for checkboxes
        JPanel choresPanel = new JPanel();
        choresPanel.setLayout(new BoxLayout(choresPanel, BoxLayout.Y_AXIS));
        choresPanel.setBackground(new Color(255, 255, 153));

        Set<Chore> selectedChores = new HashSet<>();
        // Define CheckBoxListener as an inner class
        class CheckBoxListener implements ItemListener {
            public void itemStateChanged(ItemEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                Chore chore = Main.getChoreByName(checkBox.getText().split("\t")[0], child.getChores());
                if (checkBox.isSelected()) {
                    selectedChores.add(chore);
                } else {
                    selectedChores.remove(chore);
                }
            }
        }

        CheckBoxListener myListener = new CheckBoxListener();

        // Create a JCheckBox for each chore and add it to the panel
        Font checkBoxFont = new Font("Comic Sans MS", Font.PLAIN, 24);
        for (Chore chore : child.getChores()) {
            if (chore != null) {
                JCheckBox checkBox = new JCheckBox(chore.getName() + "\t $" + chore.getRewardAmount());
                checkBox.setFont(checkBoxFont);
                checkBox.setBackground(new Color(255, 255, 153));
                checkBox.addItemListener(myListener);
                choresPanel.add(checkBox);
            }
        }
        // Wrap the chores panel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(choresPanel);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        // Layout components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 153));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;

        panel.add(label, constraints);

        constraints.gridy = 1;

        panel.add(scrollPane, constraints);

        JButton completeButton = new JButton("Complete Chores");
        completeButton.setFont(playfulFont);
        completeButton.setBackground(new Color(255, 153, 102));
        completeButton.setForeground(Color.WHITE);
        completeButton.setPreferredSize(new Dimension(170, 30));
        constraints.gridy = 2;

        panel.add(completeButton, constraints);

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Chore chore : selectedChores) {
                    System.out.println(child.getName() + " received " + chore.getRewardAmount() + " for completing " + chore.getName() + "!");
                    child.completedChore(child, chore);
                    System.out.println("Their new coin amount is $" + child.getCoinsAvailable());
                }

                // Refresh the chores list after completing chores
                ChoresFrame updatedChoresFrame = new ChoresFrame(child);
                System.out.println("Their updated incomplete chores are " + child.getChores());
                dispose(); // Close the current frame
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(playfulFont);
        backButton.setBackground(new Color(255, 153, 102));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(70, 30));

        GridBagConstraints sideButtonConstraints = new GridBagConstraints();
        sideButtonConstraints.gridx = 1;
        sideButtonConstraints.gridy = 0;
        sideButtonConstraints.anchor = GridBagConstraints.NORTHEAST;

        panel.add(backButton, sideButtonConstraints);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Going back to main screen...");
                new MainFrame(child);
            	//SelectAccountFrame.mainFrame.ShowMainFrame(child);
                dispose(); // Close the frame when the button is clicked
            }
        });

    //laurens update attempt
        // JProgressBar progBar = new JProgressBar();
 
        // // set initial value
        // progBar.setValue(child.getTotalChoresCompleted() / child.getTotalChoresAssigned());
 
        // progBar.setStringPainted(true);
        // progBar.progressString
 
        // // add progressbar
        // panel.add(progBar);





        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}