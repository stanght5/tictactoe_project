public class GameStats {
    public static String player1Name = "Player 1";
    public static String player2Name = "Player 2";
    public static String currentPlayer;
    public static int player1Score = 0;
    public static int player2Score = 0;
    public static int turns = 0;
    public static boolean gameOver = false;

    public static void resetScores() {
        player1Score = 0;
        player2Score = 0;
    }

    public static String getScoreText() {
        return player1Name + " (X): " + player1Score + " | " + player2Name + " (O): " + player2Score;
    }

    public static void switchPlayer() {
        currentPlayer = currentPlayer.equals(player1Name) ? player2Name : player1Name;
    }
}
