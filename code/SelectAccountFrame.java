package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelectAccountFrame extends JFrame {

    private String[] accountNames = {"Child 1", "Child 2", "Child 3"}; // Replace with actual account names

    public static MainFrame mainFrame;
    private static SelectAccountFrame currentInstance;

    public SelectAccountFrame(Parent parent) {

        super("Select Account");
        disposeCurrentInstance();
        currentInstance = this;

        JPanel panel = createPanel();
        addComponentsToPanel(panel, parent);
        add(panel);

        configureFrame();
    }

    private void disposeCurrentInstance() {
        if (currentInstance != null) {
            currentInstance.dispose();
        }
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 153));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return panel;
    }

    private void addComponentsToPanel(JPanel panel, Parent parent) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = createTitleLabel();
        JButton parentSettingsButton = createParentSettingsButton(parent);
        JPanel accountPanel = createAccountPanel(parent);
        JButton customizeAllButton = createCustomizeAllButton(accountPanel);

        // Add components with constraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(createEmptyPanel(), constraints);

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
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Select an Account");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        return titleLabel;
    }

    private JButton createParentSettingsButton(Parent parent) {
        JButton parentSettingsButton = new JButton("Parent Settings");
        parentSettingsButton.setFont(new Font("Arial", Font.PLAIN, 12));
        parentSettingsButton.setBackground(new Color(200, 200, 200));
        parentSettingsButton.setForeground(Color.BLACK);
        parentSettingsButton.addActionListener(e -> {
            System.out.println("Opening Parent Settings...");
            ParentSettingsFrame parentSettingsFrame = new ParentSettingsFrame(parent);
        });
        return parentSettingsButton;
    }

    private JPanel createAccountPanel(Parent parent) {
        JPanel accountPanel = new JPanel(new GridLayout(1, accountNames.length, 10, 0));
        accountPanel.setBackground(new Color(255, 255, 153));

        for (Child child : Main.findChildrenByParent(Main.children, parent)) {
            JButton accountButton = new JButton(child.getName());
            accountButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            accountButton.setBackground(new Color(255, 255, 102));
            accountButton.addActionListener(e -> {
                System.out.println("Selected account: " + child.getName());

                mainFrame = new MainFrame(child);
                dispose(); // Close the SelectAccountFrame
            });

            accountPanel.add(accountButton);
        }

        return accountPanel;
    }

    private JButton createCustomizeAllButton(JPanel accountPanel) {
        JButton customizeAllButton = new JButton("Customize Buttons");
        customizeAllButton.setFont(new Font("Arial", Font.PLAIN, 12));
        customizeAllButton.setBackground(new Color(200, 200, 200));
        customizeAllButton.setForeground(Color.BLACK);
        customizeAllButton.addActionListener(e -> {
            JDialog selectButtonDialog = createSelectButtonDialog(accountPanel);
            selectButtonDialog.setVisible(true);
        });
        return customizeAllButton;
    }

    private JDialog createSelectButtonDialog(JPanel accountPanel) {
        JDialog selectButtonDialog = new JDialog((Frame) null, "Select Account Button", true);
        selectButtonDialog.setSize(400, 300);
        selectButtonDialog.setLocationRelativeTo(null);
        selectButtonDialog.setLayout(new BorderLayout());

        JPanel buttonSelectionPanel = createButtonSelectionPanel(accountPanel, selectButtonDialog);

        selectButtonDialog.add(new JLabel("Select an account button to customize:"), BorderLayout.NORTH);
        selectButtonDialog.add(buttonSelectionPanel, BorderLayout.CENTER);

        return selectButtonDialog;
    }

    private JPanel createButtonSelectionPanel(JPanel accountPanel, JDialog selectButtonDialog) {
        JPanel buttonSelectionPanel = new JPanel(new GridLayout(1, accountNames.length, 10, 0));
        buttonSelectionPanel.setBackground(new Color(255, 255, 153));

        ArrayList<JButton> accountButtons = getAccountButtons(accountPanel);

        for (JButton accountButton : accountButtons) {
            JButton selectButton = new JButton(accountButton.getText());
            selectButton.addActionListener(e -> {
                customizeButton(accountButton);
                selectButtonDialog.dispose();
            });
            buttonSelectionPanel.add(selectButton);
        }

        return buttonSelectionPanel;
    }
    
    private ArrayList<JButton> getAccountButtons(JPanel accountPanel) {
        ArrayList<JButton> accountButtons = new ArrayList<>();

        for (Component comp : accountPanel.getComponents()) {
            if (comp instanceof JButton) {
                accountButtons.add((JButton) comp);
            }
        }

        return accountButtons;
    }

    private void customizeButton(JButton accountButton) {
        customizeButtonColor(accountButton);
        customizeButtonAvatar(accountButton);
    }

    private void customizeButtonColor(JButton accountButton) {
        Color newColor = JColorChooser.showDialog(null, "Choose a Button Color", accountButton.getBackground());
        if (newColor != null) {
            accountButton.setBackground(newColor);
        }
    }

    private void customizeButtonAvatar(JButton accountButton) {
        setSystemLookAndFeel();

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

    private void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }
    }

    private JPanel createEmptyPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        return emptyPanel;
        }
    private void configureFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Set frame size to screen size
        setSize(screenSize);

        // Set maximum frame size to screen size
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
