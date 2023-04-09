package code;

// import java.io.*;
import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

	class CoinsFrame extends JFrame {
//     private int currentImageIndex = 0;
//     private JLabel coinImage;

//     public CoinsFrame() {
//         super("Coins");

//         // Create components
//         Font playfulFont = new Font("Comic Sans MS", Font.PLAIN, 14);
//         JLabel label = new JLabel("Your Coins:");
//         label.setFont(playfulFont);

//         int numberOfCoins = 100; // Replace with the actual number of coins
//         JLabel coinsLabel = new JLabel(String.valueOf(numberOfCoins));
//         coinsLabel.setFont(playfulFont);

//         ImageIcon coinIcon = new ImageIcon("star coin rotate 1.png"); // Replace with the actual coin image path
//         coinImage = new JLabel(coinIcon);

//         // Layout components
//         JPanel panel = new JPanel(new GridBagLayout());
//         panel.setBackground(new Color(255, 255, 153));
//         GridBagConstraints constraints = new GridBagConstraints();
//         constraints.insets = new Insets(10, 10, 10, 10);

//         constraints.gridx = 0;
//         constraints.gridy = 0;
//         panel.add(label, constraints);

//         constraints.gridy = 1;
//         panel.add(coinsLabel, constraints);
        
//         JButton backButton = new JButton("Back");
//         backButton.setFont(playfulFont);
//         backButton.setBackground(new Color(255, 153, 102));
//         backButton.setForeground(Color.WHITE);

//         // Set the preferred size of the button to make it smaller
//         backButton.setPreferredSize(new Dimension(70, 30));

//         // Set the constraints to position the button in the top right corner
//         GridBagConstraints backButtonConstraints = new GridBagConstraints();
//         backButtonConstraints.gridx = 1;
//         backButtonConstraints.gridy = 0;
//         backButtonConstraints.anchor = GridBagConstraints.NORTHEAST;
//         backButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(java.awt.event.ActionEvent e) {
//                 SelectAccountFrame.mainFrame.ShowMainFrame();
//                 dispose(); // Close the frame when the button is clicked
//             }
//         });
//         panel.add(backButton, backButtonConstraints);

//         // Add panel to frame
//         JLayeredPane layeredPane = new JLayeredPane();
//         layeredPane.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
//         panel.setBounds(0, 0, layeredPane.getPreferredSize().width, layeredPane.getPreferredSize().height);
//         layeredPane.add(panel, new Integer(0));
//         coinImage.setBounds(layeredPane.getPreferredSize().width - coinIcon.getIconWidth(), 0, coinIcon.getIconWidth(), coinIcon.getIconHeight());
//         layeredPane.add(coinImage, new Integer(1));
//         add(layeredPane);

//         // Set frame properties
//         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//         setSize(Toolkit.getDefaultToolkit().getScreenSize());
//         setLocationRelativeTo(null);
//         setVisible(true);

//         // Animate the coin image
//         animateCoinImage();
//     }

//     private void animateCoinImage() {
//         String[] coinImages = {"star coin rotate 1.png", "star coin rotate 2.png", "star coin rotate 3.png", "star coin rotate 4.png",
//                 "star coin rotate 5.png", "star coin rotate 6.png"}; // Replace with the actual coin image paths
//         int delay = 100; // Time between frames in milliseconds

//         Timer timer = new Timer(delay, new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 currentImageIndex = (currentImageIndex + 1) % coinImages.length;
//                 ImageIcon newIcon = new ImageIcon(coinImages[currentImageIndex]);
//                 coinImage.setIcon(newIcon);
//             }
//         });

//         timer.start();
//     }
 }