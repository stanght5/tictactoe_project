import javax.swing.*;
import java.awt.*;

public class BoardManager {
    private JButton[][] board = new JButton[3][3];
    private JPanel boardPanel;
    private GamePage gamePage;

    public BoardManager(GamePage page) {
        this.gamePage = page;
        boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        createBoard();
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    private void createBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton btn = new JButton();
                board[r][c] = btn;
                btn.setFont(new Font("Arial", Font.BOLD, 120));
                btn.setFocusable(false);
                btn.setBackground(Color.darkGray);
                btn.setForeground(Color.white);

                final int row = r;
                final int col = c;

                btn.addActionListener(e -> {
                    if (GameStats.gameOver || !btn.getText().equals("")) return;

                    btn.setText(GameStats.currentPlayer.equals(GameStats.player1Name) ? "X" : "O");
                    GameStats.turns++;
                    checkWinner();
                    if (!GameStats.gameOver) {
                        GameStats.switchPlayer();
                        gamePage.updateStatus(GameStats.currentPlayer + "'s Turn");
                    }
                });

                boardPanel.add(btn);
            }
        }
    }

    private void checkWinner() {
        JButton[][] b = board;
        for (int i = 0; i < 3; i++) {
            // Rows
            if (!b[i][0].getText().isEmpty() &&
                    b[i][0].getText().equals(b[i][1].getText()) &&
                    b[i][1].getText().equals(b[i][2].getText())) {
                highlight(b[i][0], b[i][1], b[i][2]);
                new ScorePage(GameStats.currentPlayer, gamePage);
                return;
            }

            // Columns
            if (!b[0][i].getText().isEmpty() &&
                    b[0][i].getText().equals(b[1][i].getText()) &&
                    b[1][i].getText().equals(b[2][i].getText())) {
                highlight(b[0][i], b[1][i], b[2][i]);
                new ScorePage(GameStats.currentPlayer, gamePage);
                return;
            }
        }

        // Diagonal 1
        if (!b[0][0].getText().isEmpty() &&
                b[0][0].getText().equals(b[1][1].getText()) &&
                b[1][1].getText().equals(b[2][2].getText())) {
            highlight(b[0][0], b[1][1], b[2][2]);
            new ScorePage(GameStats.currentPlayer, gamePage);
            return;
        }

        // Diagonal 2
        if (!b[0][2].getText().isEmpty() &&
                b[0][2].getText().equals(b[1][1].getText()) &&
                b[1][1].getText().equals(b[2][0].getText())) {
            highlight(b[0][2], b[1][1], b[2][0]);
            new ScorePage(GameStats.currentPlayer, gamePage);
            return;
        }

        // Draw
        if (GameStats.turns == 9) {
            new ScorePage("Draw", gamePage);
        }
    }

    private void highlight(JButton a, JButton b, JButton c) {
        a.setBackground(Color.green);
        b.setBackground(Color.green);
        c.setBackground(Color.green);
    }

    public void disableBoard() {
        for (JButton[] row : board) {
            for (JButton btn : row) {
                btn.setEnabled(false);
            }
        }
    }

    public void resetBoard() {
        GameStats.turns = 0;
        GameStats.gameOver = false;
        GameStats.currentPlayer = GameStats.player1Name;

        for (JButton[] row : board) {
            for (JButton btn : row) {
                btn.setText("");
                btn.setEnabled(true);
                btn.setBackground(Color.darkGray);
            }
        }

        gamePage.updateStatus(GameStats.currentPlayer + "'s Turn");
        gamePage.updateScoreLabel();
    }
}
