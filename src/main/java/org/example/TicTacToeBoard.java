package org.example;

import lombok.Getter;

import static org.example.Player.NONE;

@Getter
public class TicTacToeBoard {
    private Player[][] gameBoard;

    public TicTacToeBoard() {
        gameBoard = new Player[3][3];
        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
                gameBoard[rowIndex][columnIndex] = NONE;
            }
        }
    }

    public void makeMove(int row, int column, Player player) {
        if (gameBoard[row][column] == NONE) {
            gameBoard[row][column] = player;
        } else {
            throw new IllegalArgumentException("Position already occupied");
        }
    }

    public Player determineWinner() {
        Player winner = checkRowsForWinner();
        if (winner != NONE) return winner;

        winner = checkColumnsForWinner();
        if (winner != NONE) return winner;

        winner = checkDiagonalsForWinner();
        return winner;
    }

    private Player checkRowsForWinner() {
        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
            Player playerInRow = gameBoard[rowIndex][0];
            if (playerInRow == gameBoard[rowIndex][1] && playerInRow == gameBoard[rowIndex][2] && playerInRow != NONE) {
                return playerInRow;
            }
        }
        return NONE;
    }

    private Player checkColumnsForWinner() {
        for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
            Player playerInColumn = gameBoard[0][columnIndex];
            if (playerInColumn == gameBoard[1][columnIndex] && playerInColumn == gameBoard[2][columnIndex] && playerInColumn != NONE) {
                return playerInColumn;
            }
        }
        return NONE;
    }

    private Player checkDiagonalsForWinner() {
        Player playerInDiagonal = gameBoard[0][0];
        if (playerInDiagonal == gameBoard[1][1] && playerInDiagonal == gameBoard[2][2] && playerInDiagonal != NONE) {
            return playerInDiagonal;
        }

        playerInDiagonal = gameBoard[0][2];
        if (playerInDiagonal == gameBoard[1][1] && playerInDiagonal == gameBoard[2][0] && playerInDiagonal != NONE) {
            return playerInDiagonal;
        }

        return NONE;
    }

    public void displayGameBoard() {
        StringBuilder gameBoardRepresentation = new StringBuilder("  0 1 2\n");
        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
            gameBoardRepresentation.append(rowIndex).append(" ");
            for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
                switch (gameBoard[rowIndex][columnIndex]) {
                    case X:
                        gameBoardRepresentation.append("X ");
                        break;
                    case O:
                        gameBoardRepresentation.append("O ");
                        break;
                    case NONE:
                        gameBoardRepresentation.append("- ");
                        break;
                }
            }
            gameBoardRepresentation.append("\n");
        }
        System.out.print(gameBoardRepresentation);
    }
}