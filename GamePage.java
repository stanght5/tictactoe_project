import javax.swing.*;
import java.awt.*;

public class GamePage {
    JFrame gameFrame;
    JLabel statusLabel, scoreLabel;
    BoardManager boardManager;

    public GamePage() {
        gameFrame = new JFrame("XandO Game");
        gameFrame.setSize(600, 700);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridLayout(3, 1));

        statusLabel = new JLabel(GameStats.currentPlayer + "'s Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 32));
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.darkGray);
        statusLabel.setForeground(Color.white);
        topPanel.add(statusLabel);

        scoreLabel = new JLabel(GameStats.getScoreText(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(Color.lightGray);
        scoreLabel.setForeground(Color.black);
        topPanel.add(scoreLabel);

        JButton resetButton = new JButton("Reset Scores");
        resetButton.addActionListener(e -> {
            GameStats.resetScores();
            scoreLabel.setText(GameStats.getScoreText());
        });
        topPanel.add(resetButton);

        gameFrame.add(topPanel, BorderLayout.NORTH);

        // BoardManager handles grid & game logic
        boardManager = new BoardManager(this);
        gameFrame.add(boardManager.getBoardPanel(), BorderLayout.CENTER);

        gameFrame.setVisible(true);
    }

    public void updateStatus(String text) {
        statusLabel.setText(text);
    }

    public void updateScoreLabel() {
        scoreLabel.setText(GameStats.getScoreText());
    }

    public JFrame getGameFrame() {
        return gameFrame;
    }

    public void restartGame() {
        boardManager.resetBoard();
    }

    public void disableGame() {
        GameStats.gameOver = true;         // ✅ Correct assignment
        boardManager.disableBoard();       // ✅ Disable all buttons
    }
}
