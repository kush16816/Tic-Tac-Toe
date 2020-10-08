
package com.bridgelabz.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {

	/**
	 * @return returns
	 */

	public enum Players {
		Player, Computer
	};

	/**
	 * @return Returns Board after creating initial board
	 * UC 1
	 */
	public static char[] createBoard() {
		char board[] = new char[10];
		for (int boardIndex = 1; boardIndex < 10; boardIndex++) {
			board[boardIndex] = ' ';
		}
		return board;
	}

	/**
	 * @return Returns the Symbol chosen by user
	 * UC 2
	 */
	public static char chooseSymbol() {
		System.out.println("Please enter the choice x or o");
		Scanner userInput = new Scanner(System.in);
		String userPlay = userInput.next();
		char userSymbol = userPlay.toCharArray()[0];
		char compSymbol = 'x';
		if (userSymbol == 'x')
			compSymbol = 'o';
		return userSymbol;
	}

	/**
	 * @param board Contains Tic-Tac-Toe board
	 * UC 3
	 * Prints the board in 3X3 format
	 */
	public static void showBoard(char[] board) {
		for (int boardIndex = 1; boardIndex < 10; boardIndex += 3) {
			System.out.println(board[boardIndex] + " | " + board[boardIndex + 1] + " | " + board[boardIndex + 2]);
			if (boardIndex < 6) {
				System.out.println("---------");
			}
		}
		System.out.println("_________");
	}

	/**
	 * @param board Contains Tic-Tac-Toe board
	 * UC 4
	 * Function to ensures a free index is returned
	 */
	public static int ensureFreeSpace(char[] board) {
		System.out.println("Enter the Index you wish to occupy");
		Scanner userInput = new Scanner(System.in);
		int boardIndex = userInput.nextInt();
		if (0 > boardIndex || boardIndex > 9) {
			System.out.println("Your input is invalid");
		}
		boolean flag = true;
		while (flag) {
			if (board[boardIndex] == ' ') {
				System.out.println("The Index " + boardIndex + " is free");
				break;
			} else {
				System.out.println("The Index " + boardIndex + " is occupied");
				flag = false;
			}
		}
		userInput.close();
		return boardIndex;
	}

	/**
	 * @param board Contains Tic-Tac-Toe Board
	 * @param boardIndex Contains position to be checked
	 * @return Returns board or 0 if occupied
	 * Checks empty space when computer play randomly
	 */
	public static int ensureFreeSpaceComp(char[] board, int boardIndex) {
		if (board[boardIndex] == ' ') {
			return boardIndex;
		} else {
			return 0;
		}
	}

	/**
	 * @param board Contains Tic-Tac-Toe Board
	 * @param boardIndex Contains position to be filled
	 * @param Symbol Contains symbol to be filled
	 * @return Returns board after entering Symbol
	 *  UC 5
	 */
	public static char[] enterSymbol(char[] board, int boardIndex, char Symbol) {
		board[boardIndex] = Symbol;
		return board;
	}

	/**
	 * @return toss : This determines if player plays first
	 * UC 6
	 */
	public static Players tossForFirstChance() {
		int toss = (int) (Math.random() * 10) % 2;
		return (toss == 0) ? Players.Player : Players.Computer;
	}

	/**
	 * @param board Contains Tic-Tac-Toe Board
	 * @param playerTurn Contains who plays first user or computer
	 * @param userSymbol Contains user symbol
	 * @param compSymbol Contains computer symbol
	 * Determine the winner of game
	 */
	public static void determineWinner(char[] board, Players playerTurn, char userSymbol, char compSymbol) {
		for (int noOfTurns = 1; noOfTurns < 10; noOfTurns++) {
			int boardIndex = ensureFreeSpace(board);
			if (playerTurn == Players.Player) {
				board = enterSymbol(board, boardIndex, userSymbol);
				showBoard(board);
				if (winCheck(board, userSymbol) == true) {
					System.out.println("User Won");
					break;
				}
				playerTurn = Players.Computer;
			} else if (playerTurn == Players.Computer) {
				board = compPlaysToWin(board, compSymbol);
				showBoard(board);
				if (winCheck(board, compSymbol) == true) {
					System.out.println("Computer Won");
					break;
				}
				playerTurn = Players.Player;
			}
		}

	}

	/**
	 * @param board Contains Tic-Tac-Toe Board
	 * @param symbol Contains Symbol for which win condition is being checked
	 * @return Returns true if someone won or false if nobody won
	 * UC 7
	 */
	public static boolean winCheck(char[] board, char symbol) {
		if (board[1] == board[2] && board[1] == board[3] && board[1] == symbol)
			return true;
		else if (board[5] == board[2] && board[5] == board[8] && board[2] == symbol)
			return true;
		else if (board[1] == board[5] && board[1] == board[9] && board[1] == symbol)
			return true;
		else if (board[1] == board[4] && board[1] == board[7] && board[1] == symbol)
			return true;
		else if (board[4] == board[5] && board[4] == board[6] && board[5] == symbol)
			return true;
		else if (board[7] == board[8] && board[7] == board[9] && board[7] == symbol)
			return true;
		else if (board[3] == board[6] && board[6] == board[9] && board[3] == symbol)
			return true;
		else if (board[3] == board[5] && board[3] == board[7] && board[3] == symbol)
			return true;
		else
			return false;
	}

	/**
	 * @param board Contains Tic-Tac-Toe Board
	 * @param compSymbol Contains computer symbol
	 * @return Returns board after computer makes its move
	 * Computer plays to win only
	 * UC 8
	 */
	public static char[] compPlaysToWin(char[] board, char compSymbol) {
		if (checkNearCompleteLine(board, compSymbol) == 0) {
			boolean flag = true;
			int boardIndex = 0;
			while (flag) {
				boardIndex = ensureFreeSpaceComp(board, (int) (Math.random() * 10 % 9) + 1);
				if (boardIndex != 0)
					flag = false;
			}
			board = enterSymbol(board, boardIndex, compSymbol);
		} else {
			board = enterSymbol(board, checkNearCompleteLine(board, compSymbol), compSymbol);
		}
		return board;
	}

	/**
	 * @param board Contains Tic-Tac-Toe Board
	 * @param compSymbol Contain computer symbol
	 * @return Returns position where winning move is to be made
	 * or 0 if there is no winning move
	 * Checks which row, column or diagonal is about to be completed
	 */
	public static int checkNearCompleteLine(char[] board, char compSymbol) {
		int boardIndex;
		int[] checkBoard = new int[10];
		checkBoard[0] = 0;
		for (boardIndex = 1; boardIndex < 10; boardIndex++) {
			if (board[boardIndex] != ' ') {
				checkBoard[boardIndex] = (board[boardIndex] == compSymbol) ? 1 : -1;
			} else {
				checkBoard[boardIndex] = 0;
			}
		}
		if (checkBoard[1] + checkBoard[2] + checkBoard[3] == 2) {
			for (boardIndex = 1; boardIndex < 4; boardIndex++) {
				if (checkBoard[boardIndex] == 0)
					return boardIndex;
			}
		} else if (checkBoard[4] + checkBoard[5] + checkBoard[6] == 2) {
			for (boardIndex = 4; boardIndex < 7; boardIndex++) {
				if (checkBoard[boardIndex] == 0)
					return boardIndex;
			}
		} else if (checkBoard[7] + checkBoard[8] + checkBoard[9] == 2) {
			for (boardIndex = 7; boardIndex < 10; boardIndex++) {
				if (checkBoard[boardIndex] == 0)
					return boardIndex;
			}
		} else if (checkBoard[1] + checkBoard[4] + checkBoard[7] == 2) {
			for (boardIndex = 1; boardIndex < 8; boardIndex += 3) {
				if (checkBoard[boardIndex] == 0)
					return boardIndex;
			}
		} else if (checkBoard[2] + checkBoard[5] + checkBoard[8] == 2) {
			for (boardIndex = 2; boardIndex < 9; boardIndex += 3) {
				if (checkBoard[boardIndex] == 0)
					return boardIndex;
			}
		} else if (checkBoard[3] + checkBoard[6] + checkBoard[9] == 2) {
			for (boardIndex = 3; boardIndex < 10; boardIndex += 3) {
				if (checkBoard[boardIndex] == 0)
					return boardIndex;
			}
		} else if (checkBoard[1] + checkBoard[5] + checkBoard[9] == 2) {
			for (boardIndex = 1; boardIndex < 10; boardIndex += 4) {
				if (checkBoard[boardIndex] == 0)
					return boardIndex;
			}
		} else if (checkBoard[3] + checkBoard[5] + checkBoard[7] == 2) {
			for (boardIndex = 3; boardIndex < 8; boardIndex += 2) {
				if (checkBoard[boardIndex] == 0)
					return boardIndex;
			}
		}
		int key = 0;
		return key;
	}

	public static void main(String[] args) {
		char[] board = createBoard();
		Players playerTurn = tossForFirstChance();
		char userSymbol = chooseSymbol();
		char compSymbol = (userSymbol == 'o') ? 'x' : 'o';
		int boardIndex;
		showBoard(board);
		determineWinner(board, playerTurn, userSymbol, compSymbol);
	}
}
