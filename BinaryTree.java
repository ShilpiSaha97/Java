package com.cerner.careaware.sbm_build_automation.config;/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class StackClass
{
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }

    static class BinaryTree {
        static int idx = -1;
        public static Node buildTree(int nodes[]) {
            idx++;
            if(nodes[idx] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static void preorder(Node root){
        if(root==null){
            System.out.print(-1+" ");
            return;
        }
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(Node root){
        if(root==null){
            System.out.print(-1+" ");
            return;
        }
        preorder(root.left);
        System.out.print(root.data+" ");
        preorder(root.right);
    }

    public static void postorder(Node root){
        if(root==null){
            System.out.print(-1+" ");
            return;
        }
        preorder(root.left);
        preorder(root.right);
        System.out.print(root.data+" ");
    }

    public static void levelorder(Node root){
        if(root==null) return;

        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while(!queue.isEmpty()){
            Node currNode=queue.remove();
            if(currNode==null){
                System.out.println();
                if(queue.isEmpty()){
                    break;
                }
                else{
                    queue.add(null);
                }
            }
            else{
                System.out.print(currNode.data+" ");
                if(currNode.left!=null){
                    queue.add(currNode.left);
                }
                if(currNode.right!=null){
                    queue.add(currNode.right);
                }
            }
        }
    }

    public static int countNodes(Node root){
        if(root==null) return 0;

        int leftNodes=countNodes(root.left);
        int rightNodes=countNodes(root.right);
        return leftNodes+rightNodes+1;
    }

    public static int sumNodes(Node root){
        if(root==null) return 0;

        int leftNodes=sumNodes(root.left);
        int rightNodes=sumNodes(root.right);
        return leftNodes+rightNodes+root.data;
    }

    public static int height(Node root){
        if(root==null) return 0;

        int leftNodes=height(root.left);
        int rightNodes=height(root.right);

        int maxHeight=Math.max(leftNodes,rightNodes);
        return maxHeight+1;
    }

    public static int diameter(Node root){
        if(root==null) return 0;

        int dia1=diameter(root.left);
        int dia2=diameter(root.right);
        int dia3=height(root.left)+height(root.right)+1;

        return Math.max(dia3, Math.max(dia2,dia1));
    }

    static class TreeInfo{
        int height;
        int diam;

        TreeInfo(int height, int diam){
            this.height=height;
            this.diam=diam;
        }
    }

    public static TreeInfo diameter2(Node root) {
        if(root == null) {
            return new TreeInfo(0, 0);
        }


        TreeInfo leftTI = diameter2(root.left);
        TreeInfo rightTI = diameter2(root.right);


        int myHeight = Math.max(leftTI.height, rightTI.height) + 1;


        int diam1 = leftTI.height + rightTI.height + 1;
        int diam2 = leftTI.diam;
        int diam3 = rightTI.diam;


        int myDiam = Math.max(diam1, Math.max(diam2, diam3));


        return new TreeInfo(myHeight, myDiam);
    }



    public static void main (String[] args) throws java.lang.Exception{
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        BinaryTree tree = new BinaryTree();

        Node root = tree.buildTree(nodes);
        //System.out.println(root.data);

        preorder(root);
        System.out.println("\n");
        inorder(root);
        System.out.println("\n");
        postorder(root);

    }
}
