
package com.bridgelabz.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {

	/**
	 * @return returns
	 */
	public static char[] createBoard() {
		char board[] = new char[10];
		for (int boardIndex = 1; boardIndex < 10; boardIndex++) {
			board[boardIndex] = ' ';
		}
		return board;
	}

	/**
	 * @param board
	 * @return : Returns board.
	 */
	public static char[] chooseSymbol(char[] board) {
		System.out.println("Please enter the choice x or o");
		Scanner userInput = new Scanner(System.in);
		String userPlay = userInput.next();
		char userSymbol = userPlay.toCharArray()[0];
		char compSymbol = 'x';
		if (userSymbol == 'x')
			compSymbol = 'o';
		return board;
	}

	/**
	 * @param board
	 * Prints the board in 3X3 format
	 */
	public static void showBoard(char[] board) {
		for (int boardIndex = 1; boardIndex < 10; boardIndex += 3) {
			System.out.println(board[boardIndex] + " | " + board[boardIndex + 1] + " | " + board[boardIndex + 2]);
			if (boardIndex < 6) {
				System.out.println("---------");
			}
		}
	}
	
	/**
	 * @param board
	 * Function to ensure index is free
	 */
	public static void ensureFreeSpace (char[] board) {
		System.out.println("Enter the Index you wish to occupy");
		Scanner userInput = new Scanner(System.in);
		int boardIndex = userInput.nextInt();
		if(board[boardIndex]==' ') {
			System.out.println("The Index is free");
		} else {
			System.out.println("The Index is occupied");
		}
	}

	public static void main(String[] args) {
		char[] board = createBoard();
		board = chooseSymbol(board);
		showBoard(board);
		ensureFreeSpace(board);
	}
}
