package code;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Toolkit;

public class SelectAccountFrame extends JFrame {

    private String[] accountNames = {"Child 1", "Child 2", "Child 3"}; // Replace with actual account names

    public static MainFrame mainFrame;
    private static SelectAccountFrame currentInstance;

    public SelectAccountFrame(Parent parent) {

        super("Select Account");
        if (currentInstance != null) {
            currentInstance.dispose();
        }
        currentInstance = this;

        // Create components
        JLabel titleLabel = new JLabel("Select an Account");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        JPanel accountPanel = new JPanel(new GridLayout(1, accountNames.length, 10, 0));
        accountPanel.setBackground(new Color(255, 255, 153));

        for (Child child : Main.findChildrenByParent(Main.children, parent)) {

            JButton accountButton = new JButton(child.getName());
            accountButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            accountButton.setBackground(new Color(255, 255, 102));

            accountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Add your code to handle account selection here
                    System.out.println("Selected account: " + child.getName());

                    // Add this code inside the actionPerformed method of the accountButton's ActionListener
                    mainFrame = new MainFrame(child);
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
                System.out.println("Opening Parent Settings...");
                ParentSettingsFrame parentSettingsFrame = new ParentSettingsFrame(parent);
            }
        });

        JButton customizeAllButton = new JButton("Customize Buttons");
        customizeAllButton.setFont(new Font("Arial", Font.PLAIN, 12));
        customizeAllButton.setBackground(new Color(200, 200, 200));
        customizeAllButton.setForeground(Color.BLACK);

        customizeAllButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Create custom dialog to select an account button
                JDialog selectButtonDialog = new JDialog((Frame) null, "Select Account Button", true);
                selectButtonDialog.setSize(400, 300);
                selectButtonDialog.setLocationRelativeTo(null);
                selectButtonDialog.setLayout(new BorderLayout());

                JPanel buttonSelectionPanel = new JPanel(new GridLayout(1, accountNames.length, 10, 0));
                buttonSelectionPanel.setBackground(new Color(255, 255, 153));

                ArrayList<JButton> accountButtons = new ArrayList<>();

                for (Component comp : accountPanel.getComponents()) {
                    if (comp instanceof JButton) {
                        accountButtons.add((JButton) comp);
                    }
                }

                for (JButton accountButton : accountButtons) {
                    JButton selectButton = new JButton(accountButton.getText());
                    selectButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            customizeButton(accountButton);
                            selectButtonDialog.dispose();
                        }
                    });
                    buttonSelectionPanel.add(selectButton); // Add this line
                }
                    selectButtonDialog.add(new JLabel("Select an account button to customize:"), BorderLayout.NORTH);
                    selectButtonDialog.add(buttonSelectionPanel, BorderLayout.CENTER);
                    selectButtonDialog.setVisible(true);
                }

            private void customizeButton(JButton accountButton) {
                // Customize the selected account button

                // Option to change button color
                Color newColor = JColorChooser.showDialog(null, "Choose a Button Color", accountButton.getBackground());
                if (newColor != null) {
                    accountButton.setBackground(newColor);
                }

                // Set the file chooser UI to the native system UI
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }

                // Option to set an avatar
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "jpeg", "gif");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    ImageIcon avatar = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
                    Image image = avatar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    accountButton.setIcon(new ImageIcon(image));
                }
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

            constraints.gridy = 2;
            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;

            panel.add(customizeAllButton, constraints);

            // Add panel to frame
            add(panel);

            // Set frame properties
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            // Set frame size to screen size
            setSize(screenSize);

            // Set maximum frame size to screen size
            setExtendedState(JFrame.MAXIMIZED_BOTH);;
            setLocationRelativeTo(null);
            setVisible(true);
        }
}

