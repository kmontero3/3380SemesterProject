package code;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class LeaderboardFrame extends JFrame {

    public LeaderboardFrame(Parent parent) {
        super("Leaderboard");
        System.out.println("Viewing the Leaderboard...");

        // Create components
        Font headerFont = new Font("Comic Sans MS", Font.BOLD, 36);
        JLabel headerLabel = new JLabel("Leaderboard");
        headerLabel.setFont(headerFont);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(248, 200, 255));
        panel.add(headerLabel);

        // Get children of the specific parent
        List<Child> childrenOfParent = Main.findChildrenByParent(Main.children, parent);

        // Sort children by their coins
        List<Child> sortedChildren = childrenOfParent.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getCoinsAvailable(), c1.getCoinsAvailable()))
                .collect(Collectors.toList());

        // Display rankings
        Font childFont = new Font("Comic Sans MS", Font.PLAIN, 24);
        int rank = 1;
        for (Child child : sortedChildren) {
            System.out.println(rank + ". " + child.getName() + " - " + child.getCoinsAvailable() + " coins");
            JLabel childLabel = new JLabel(rank + ". " + child.getName() + " - " + child.getCoinsAvailable() + " coins");
            childLabel.setFont(childFont);
            childLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            childLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            panel.add(childLabel);
            rank++;
        }

        // Add panel to frame
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}