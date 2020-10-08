
package com.bridgelabz.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {

	/**
	 * @return returns
	 */

	public enum Players {
		Player, Computer
	};

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
	 * @param board Prints the board in 3X3 format
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
	 * @param board Function to ensure index is free
	 */
	public static int ensureFreeSpace(char[] board) {
		System.out.println("Enter the Index you wish to occupy");
		Scanner userInput = new Scanner(System.in);
		int boardIndex = userInput.nextInt();
		if (0 > boardIndex || boardIndex > 9) {
			System.out.println("Your input is invalid");
		}
		if (board[boardIndex] == ' ') {
			System.out.println("The Index " + boardIndex + " is free");
			return boardIndex;
		} else {
			System.out.println("The Index " + boardIndex + " is occupied");
			return boardIndex;
		}
	}

	public static char[] enterSymbol(char[] board, int boardIndex, char letter) {
		board[boardIndex] = letter;
		return board;
	}

	/**
	 * @return toss : This determines if player plays first
	 */
	public static Players tossForFirstChance() {
		int toss = (int) (Math.random() * 10) % 2;
		return (toss == 0) ? Players.Player : Players.Computer;
	}

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
				board = enterSymbol(board, boardIndex, compSymbol);
				showBoard(board);
				if (winCheck(board, compSymbol) == true) {
					System.out.println("Computer Won");
					break;
				}
				playerTurn = Players.Player;
			}
		}

	}

	public static boolean winCheck(char[] board, char symbol) {
		if (board[1] == board[2] && board[1] == board[3] && board[1] == symbol)
			return true;
		else if (board[5] == board[2] && board[5] == board[8] && board[1] == symbol)
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
