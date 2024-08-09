package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicTacToeBoard board = new TicTacToeBoard();
        Scanner scanner = new Scanner(System.in);

        board.displayGameBoard();

        Player currentPlayer = Player.X; // Start with Player X

        while (true) {
            try {
                System.out.println("Enter the coordinates for the next move for player " + currentPlayer + " (format: x y):");
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                board.makeMove(x, y, currentPlayer);

                board.displayGameBoard();

                Player winner = board.determineWinner();
                if (winner != Player.NONE) {
                    System.out.println("The winner is: " + winner);
                    break;
                }

                // Switch to the other player for the next move
                currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter two integers for the coordinates.");
                scanner.nextLine(); // discard the rest of the line
            }
        }

        scanner.close();
    }
}


//public class Main {
//    public static void main(String[] args) {
//        Board board = new Board();
//
//        // Simulate a game
//        board.move(0, 0, Player.X);
//        board.move(0, 1, Player.O);
//        board.move(1, 1, Player.X);
//        board.move(0, 2, Player.O);
//        board.move(2, 2, Player.X);
//
//        // Print the winner
//        Player winner = board.getWinner();
//        if (winner != Player.NONE) {
//            System.out.println("The winner is: " + winner);
//        } else {
//            System.out.println("The game is a draw.");
//        }
//    }
//}
