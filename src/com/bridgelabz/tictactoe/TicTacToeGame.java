
package com.bridgelabz.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {
	
	public static char[] createBoard() {
		char board[] = new char[10];
		for(int boardIndex = 1; boardIndex < 10; boardIndex++)
		{
			board[boardIndex] = ' ';
		}
		return board;
	}

	public static char[] playChance(char[] board) {
		System.out.println("Please enter the choice x or o");
		Scanner userInput = new Scanner(System.in);
		String userPlay = userInput.next();
		char userChance = userPlay.toCharArray()[0];
		char comPlay = 'x';
		if(userChance=='x')
			comPlay = 'o';
		return board;
	}
	public static void main(String [] args) {
		char[] board = createBoard();
		playChance(board);
	}
}
