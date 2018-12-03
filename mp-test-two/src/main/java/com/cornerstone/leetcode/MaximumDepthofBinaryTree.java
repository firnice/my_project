package com.cornerstone.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthofBinaryTree {

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            int ret = new Solution().maxDepth(root);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val; 值
     * TreeNode left; 左节点
     * TreeNode right; 右节点
     * TreeNode(int x) { val = x; } 构造函数
     * }
     */
    static class Solution {
//        public int maxDepth(TreeNode root) {
//            if (root == null) {
//                return 0;
//            }
//            int left = 0, right = 0;
//            if (root.left != null) {
//                left = maxDepth(root.left) + 1;
//            }
//            if (root.right != null) {
//                right = maxDepth(root.right) + 1;
//            }
//            if (root.left == null && root.right == null) {
//                return 1;
//            }
//            return left > right ? left : right;
//        }

        public int maxDepth(TreeNode root) {
            int leftMax = findDepth(root.left,0);
            int rightMax = findDepth(root.right,0);
            if(leftMax > rightMax){
                return leftMax;
            }else{
                return rightMax;
            }
        }
        public int findDepth(TreeNode root,int Depth){
            if(root == null){
                return Depth;
            }
            Depth++;
            return findDepth(root,Depth);
        }

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
