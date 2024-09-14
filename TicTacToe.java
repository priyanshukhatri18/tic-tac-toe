import java.util.Scanner;

public class TicTacToe {

    char[][] board;
    Scanner scanner;

    public TicTacToe() {
        board = new char[3][3];
        scanner = new Scanner(System.in);
        InitBoard();
    }

    // initializing the board;
    void InitBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Display board
    void DisplayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Mrking on the indeses;
    void PlaceMark() {
        int row, col;
        char mark;

        System.out.print("Enter row (0, 1, or 2): ");
        row = scanner.nextInt();
        System.out.print("Enter column (0, 1, or 2): ");
        col = scanner.nextInt();
        System.out.print("Enter mark (X or O): ");
        mark = scanner.next().toUpperCase().charAt(0);

        if (row >= 0 && row <= 2 && col >= 0 && col <= 2 && (mark == 'X' || mark == 'O')) {
            if (board[row][col] == ' ') {
                board[row][col] = mark;
            } else {
                System.out.println("Cell is already occupied. Try a different cell.");
            }
        } else {
            System.out.println("Invalid position or mark.");
        }
    }

    // Check for columns
    boolean CheckColWin() {
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != ' ' && board[0][col] == board[1][col] &&
                    board[1][col] == board[2][col]) {
                return true;
            }
        }
        return false;
    }

    // Check for rows
    boolean CheckRowWin() {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != ' ' && board[row][0] == board[row][1] &&
                    board[row][1] == board[row][2]) {
                return true;
            }
        }
        return false;
    }

    // Check for diagonal
    boolean CheckDiagonalWin() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    // Check for draw
    boolean CheckDraw() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        boolean gameEnded = false; // Game end intitially at false;
        char currentPlayer = 'X';

        while (!gameEnded) {
            game.DisplayBoard();
            System.out.println("Player " + currentPlayer + "'s turn.");
            game.PlaceMark();

            if (game.CheckRowWin() || game.CheckColWin() || game.CheckDiagonalWin()) {
                game.DisplayBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameEnded = true;
            } else if (game.CheckDraw()) {
                game.DisplayBoard();
                System.out.println("The game is a draw!");
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
            }
        }
        game.scanner.close();
    }
}
