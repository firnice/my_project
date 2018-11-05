package com.cornerstone.leetcode;

public class RobotReturnToOrigin {

    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
                case 'R':
                    x++;
                    break;
                case 'L':
                    x--;
                    break;
                default:
                    break;
            }
        }
        return (x == 0 && y == 0) ? true : false;
    }

    public static void main(String[] args) {
    }
}
