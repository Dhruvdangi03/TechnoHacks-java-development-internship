import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }

        playGame(board);
    }

    private static void playGame(char[][] board) {
        Scanner sc = new Scanner(System.in);
        char playerOne = ' ', playerSec = ' ';
        System.out.println("Enter the Name of Player 1 : ");
        String First = sc.next();
        sc.nextLine();
        System.out.println("Enter the Name of Player 2 : ");
        String Sec = sc.next();
        System.out.println("Enter Numbers from 1 to 9 to play.");
        Random rd = new Random();
        if(rd.nextBoolean()){
            playerOne = 'X';
            playerSec = 'O';
        }else{
            playerOne = 'O';
            playerSec = 'X';
        }

        char turn = playerOne;
        String player = First;

        boolean gameOver = false;
        while (!gameOver){
            System.out.println(player + "'s turn : ");
            printBoard(board);
            int place = sc.nextInt() -1;
            if(board[place / 3][place % 3] == ' '){
                board[place / 3][place % 3] = turn;
                gameOver = haveWon(board, turn);
                if (gameOver){
                    System.out.println(player + " has won 🎉✨");
                }

                turn = (turn == playerOne) ? playerSec : playerOne;
                player = (player.equals(First)) ? Sec : First;
            }else {
                System.out.println("Invalid Move!");
            }
        }
        printBoard(board);
    }

    private static Boolean haveWon(char[][] board, char turn) {
        // check rows
        for (int row = 0; row < 3; row++) {
            if(board[row][0] == turn && board[row][1] == turn && board[row][2] == turn) return true;
        }
        // check columns
        for (int col = 0; col < 3; col++) {
            if(board[0][col] == turn && board[1][col] == turn && board[2][col] == turn) return true;
        }
        // for diagonals
        if(board[0][0] == turn && board[1][1] == turn && board[2][2] == turn) return true;

        else return board[0][2] == turn && board[1][1] == turn && board[2][0] == turn;
    }

    private static void printBoard(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col]);
                if(col < 2) System.out.print(" | ");
            }
            System.out.println();
        }
    }
}