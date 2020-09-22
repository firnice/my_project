package com.cornerstone.leetcode;

import java.util.Map;

public class SudokuSolver {

    public static void solveSudoku(char[][] board) {

        /**
         * 分为9个3*3
         * 每个3*3 理论上有 9
         */

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    /**
     *
     *
     *
     */

//    x,y xSet,ySet


    /**
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     *
     * 给定的数独序列只包含数字 1-9 和字符 '.' 。
     * 你可以假设给定的数独只有唯一解。
     * 给定数独永远是 9x9 形式的。
     *
     *
     * @param args
     */

    public static void main(String[] args) {

        char[][] a = new char[9][9];
        a[0][0] = '5';
        a[0][1] = '3';
        a[0][2] = '.';
        a[0][3] = '.';
        a[0][4] = '7';
        a[0][5] = '.';
        a[0][6] = '.';
        a[0][7] = '.';
        a[0][8] = '.';

        a[1][0] = '6';
        a[1][1] = '.';
        a[1][2] = '.';
        a[1][3] = '1';
        a[1][4] = '9';
        a[1][5] = '5';
        a[1][6] = '.';
        a[1][7] = '.';
        a[1][8] = '.';

        a[2][0] = '.';
        a[2][1] = '9';
        a[2][2] = '8';
        a[2][3] = '.';
        a[2][4] = '.';
        a[2][5] = '.';
        a[2][6] = '.';
        a[2][7] = '6';
        a[2][8] = '.';

        a[3][0] = '8';
        a[3][1] = '.';
        a[3][2] = '.';
        a[3][3] = '.';
        a[3][4] = '6';
        a[3][5] = '.';
        a[3][6] = '.';
        a[3][7] = '.';
        a[3][8] = '3';

        a[4][0] = '4';
        a[4][1] = '.';
        a[4][2] = '.';
        a[4][3] = '8';
        a[4][4] = '.';
        a[4][5] = '3';
        a[4][6] = '.';
        a[4][7] = '.';
        a[4][8] = '1';

        a[5][0] = '7';
        a[5][1] = '.';
        a[5][2] = '.';
        a[5][3] = '.';
        a[5][4] = '2';
        a[5][5] = '.';
        a[5][6] = '.';
        a[5][7] = '.';
        a[5][8] = '6';


        a[6][0] = '.';
        a[6][1] = '6';
        a[6][2] = '.';
        a[6][3] = '.';
        a[6][4] = '.';
        a[6][5] = '.';
        a[6][6] = '2';
        a[6][7] = '8';
        a[6][8] = '.';

        a[7][0] = '.';
        a[7][1] = '.';
        a[7][2] = '.';
        a[7][3] = '4';
        a[7][4] = '1';
        a[7][5] = '9';
        a[7][6] = '.';
        a[7][7] = '.';
        a[7][8] = '5';

        a[8][0] = '.';
        a[8][1] = '.';
        a[8][2] = '.';
        a[8][3] = '.';
        a[8][4] = '8';
        a[8][5] = '.';
        a[8][6] = '.';
        a[8][7] = '7';
        a[8][8] = '9';


        solveSudoku(a);

    }
}