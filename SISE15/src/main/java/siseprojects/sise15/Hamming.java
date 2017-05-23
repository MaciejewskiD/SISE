/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siseprojects.sise15;

/**
 *
 * @author user
 */
public class Hamming {

    public static int calculateDistance(int[][] board) {
        int sum = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != Solution.resultArray[i][j]) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
