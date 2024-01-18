package com.shpp.cs.a.lectures.lec10;
/* A simple program showing off multidimensional arrays in Java. */

import com.shpp.cs.a.console.TextProgram;

public class MultidimensionalArrays extends TextProgram {
	public void run() {
		int[][] arr = new int[3][5];
		
		/* Set each element equal to 10 times its row plus its columns.
		 * This helps make clearer which digit corresponds to each
		 * dimension.
		 */
		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) {
				arr[row][col] = 10 * row + col;
			}
		}
		
		/* Print out the array one row at a time. */
		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) {
				print(arr[row][col] + " ");
			}
			println();
		}
	}
}
