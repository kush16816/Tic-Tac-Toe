
package com.bridgelabz.tictactoe;

public class TicTacToeGame {
	
	public static char[] createBoard() {
		char board[] = new char[10];
		for(int boardIndex = 1; boardIndex < 10; boardIndex++)
		{
			board[boardIndex] = ' ';
		}
		return board;
	}

	public static void main(String [] args) {
		char[] board = createBoard();
	}
}
