import javax.swing.*;

public class ScorePage {

    public ScorePage(String winner, GamePage gamePage) {
        if (gamePage == null || gamePage.getGameFrame() == null) {
            throw new IllegalArgumentException("GamePage or its frame cannot be null.");
        }

        JFrame frame = gamePage.getGameFrame();

        // Display result message
        if (winner.equalsIgnoreCase("Draw")) {
            JOptionPane.showMessageDialog(
                    frame,
                    "It's a tie! No one wins this round.",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    winner + " wins this round!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Update scores safely
            if (winner.equalsIgnoreCase(GameStats.player1Name)) {
                GameStats.player1Score++;
            } else if (winner.equalsIgnoreCase(GameStats.player2Name)) {
                GameStats.player2Score++;
            }
        }

        // Lock board and refresh score display
        gamePage.disableGame();

        gamePage.updateScoreLabel();

        // Ask to restart
        int response = JOptionPane.showConfirmDialog(
                frame,
                "Would you like to play another round?",
                "Play Again?",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            gamePage.restartGame();

        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    "Thanks for playing!",
                    "Exit",
                    JOptionPane.INFORMATION_MESSAGE
            );
            System.exit(0);
        }
    }
}

