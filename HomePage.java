import javax.swing.*;
import java.awt.*;

public class HomePage {
    JFrame welcomeFrame;
    JTextField player1Field, player2Field;

    public HomePage() {
        welcomeFrame = new JFrame("Welcome to XandO game");
        welcomeFrame.setSize(400, 300);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setLayout(new GridLayout(4, 1));
        welcomeFrame.setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to XandO game", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeFrame.add(welcomeLabel);

        player1Field = new JTextField();
        player1Field.setToolTipText("Enter Player 1 Name");
        player2Field = new JTextField();
        player2Field.setToolTipText("Enter Player 2 Name");

        welcomeFrame.add(player1Field);
        welcomeFrame.add(player2Field);

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(e -> {
            String p1 = player1Field.getText().trim();
            String p2 = player2Field.getText().trim();
            GameStats.player1Name = p1.isEmpty() ? "Player 1" : p1;
            GameStats.player2Name = p2.isEmpty() ? "Player 2" : p2;
            GameStats.currentPlayer = GameStats.player1Name;

            welcomeFrame.dispose();
            new GamePage();
        });
        welcomeFrame.add(continueButton);
        welcomeFrame.setVisible(true);
    }
}
