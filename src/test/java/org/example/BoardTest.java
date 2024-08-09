package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void testMove() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();

        // Test moving to an empty spot
        assertDoesNotThrow(() -> ticTacToeBoard.makeMove(0, 0, Player.X));

        // Test moving to an occupied spot
        assertThrows(IllegalArgumentException.class, () -> ticTacToeBoard.makeMove(0, 0, Player.O));

        // Test moving out of bounds
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> ticTacToeBoard.makeMove(3, 3, Player.X));

        // Test moving with negative indices
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> ticTacToeBoard.makeMove(-1, -1, Player.X));
    }

    @Test
    public void testGetWinner() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();

        // Test with no winner
        assertEquals(Player.NONE, ticTacToeBoard.determineWinner());

        // Simulate a win for Player X in the first row
        ticTacToeBoard.makeMove(0, 0, Player.X);
        ticTacToeBoard.makeMove(0, 1, Player.X);
        ticTacToeBoard.makeMove(0, 2, Player.X);

        // Test with a winner
        assertEquals(Player.X, ticTacToeBoard.determineWinner());

        // Reset the board
        ticTacToeBoard = new TicTacToeBoard();

        // Simulate a win for Player O in the first column
        ticTacToeBoard.makeMove(0, 0, Player.O);
        ticTacToeBoard.makeMove(1, 0, Player.O);
        ticTacToeBoard.makeMove(2, 0, Player.O);

        // Test with a winner
        assertEquals(Player.O, ticTacToeBoard.determineWinner());

        // Reset the board
        ticTacToeBoard = new TicTacToeBoard();

        // Simulate a win for Player X in the main diagonal
        ticTacToeBoard.makeMove(0, 0, Player.X);
        ticTacToeBoard.makeMove(1, 1, Player.X);
        ticTacToeBoard.makeMove(2, 2, Player.X);

        // Test with a winner
        assertEquals(Player.X, ticTacToeBoard.determineWinner());
    }

    @Test
    public void testPrintBoard() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ticTacToeBoard.displayGameBoard();

        String expectedOutput = "  0 1 2\n0 - - - \n1 - - - \n2 - - - \n";
        assertEquals(expectedOutput, outContent.toString());

        // Reset the output stream
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Make some moves
        ticTacToeBoard.makeMove(0, 0, Player.X);
        ticTacToeBoard.makeMove(1, 1, Player.O);
        ticTacToeBoard.makeMove(2, 2, Player.X);

        // Print the board again
        ticTacToeBoard.displayGameBoard();

        expectedOutput = "  0 1 2\n0 X - - \n1 - O - \n2 - - X \n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testAlternatingPlayers() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();

        // Player X's turn
        assertDoesNotThrow(() -> ticTacToeBoard.makeMove(0, 0, Player.X));

        // Player O's turn
        assertDoesNotThrow(() -> ticTacToeBoard.makeMove(0, 1, Player.O));

        // Player X's turn again
        assertDoesNotThrow(() -> ticTacToeBoard.makeMove(0, 2, Player.X));

        // Check the board state
        assertEquals(Player.X, ticTacToeBoard.getGameBoard()[0][0]);
        assertEquals(Player.O, ticTacToeBoard.getGameBoard()[0][1]);
        assertEquals(Player.X, ticTacToeBoard.getGameBoard()[0][2]);
    }
}