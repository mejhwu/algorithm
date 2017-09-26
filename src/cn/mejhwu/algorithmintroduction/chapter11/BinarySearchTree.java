package cn.mejhwu.algorithmintroduction.chapter11;

import cn.mejhwu.utils.Utils;

/**
 * Created with IntelliJ IDEA.
 * Author: mejhwu
 * Date:   17-9-26
 * E-mail: mejhwu@163.com
 * Description:
 * 二叉搜索树
 */


class BinaryTreeNode {
    public int val;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode() {}

    public BinaryTreeNode(int val) {
        this.val = val;
    }
}

public class BinarySearchTree {

    public BinaryTreeNode root;

    /**
     * 插入节点
     * 插入的节点必定为叶子节点。
     * 如果插入节点比当前节点小，则将节点插入当前节点的左子树上，
     * 如果插入节点比当前节点大，则将节点插入当前节点的右子树上。
     * @param node
     */
    public void insertNode(BinaryTreeNode node) {
        if (root == null) {
            root = node;
            return;
        }

        BinaryTreeNode currentNode = root;
        BinaryTreeNode parentNode = null;

        while (currentNode != null) {
            parentNode = currentNode;
            if (currentNode.val > node.val) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        if (parentNode.val > node.val) {
            parentNode.left = node;
        } else {
            parentNode.right = node;
        }
    }

    /**
     * 遍历二叉搜索树
     */
    public void middleTravel() {
        if (this.root == null) {
            return;
        }
        middleTravel(this.root);
    }

    /**
     * 中序遍历二叉搜索树
     * @param node
     */
    public void middleTravel(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        middleTravel(node.left);
        System.out.print(node.val + " ");
        middleTravel(node.right);
    }

    /**
     * 查找节点
     * 返回-1表示未找到
     * 如果值比节点值小，表示在其左子树上
     * 如果值比节点值大，表示在其右子树上
     * @param val
     * @return
     */
    public int searchNode(int val) {
        if (this.root == null) {
            return -1;
        }
        BinaryTreeNode node = root;
        while (node != null) {
            if (node.val == val) {
                return node.val;
            } else if (node.val > val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return -1;
    }

    /**
     * 删除节点
     * 首先需要查找节点是否存在。
     * 删除节点可分为一下几种情况(设节点z为删除节点)：
     * 1. 如果z没有左孩子，则用右孩子替换z。
     * 2. 如果z没有右孩子，则用左孩子来替换z。
     * 3. 如果z有左右孩子，则查找z的后继y，后继y肯定在z的右子树中，且没有左孩子。将y移出替换z。
     * 4. 如果y是z的右孩子，那么用y替换z，并仅留下y的右孩子。
     * @param val
     */
    public void deleteNode(int val) {
        if (this.root == null) {
            return;
        }

        BinaryTreeNode deleteNode = root;
        BinaryTreeNode parentNode = null;

        //查找节点
        while (deleteNode != null) {

            if (deleteNode.val == val) {
                break;
            } else if (deleteNode.val > val) {
                parentNode = deleteNode;
                deleteNode = deleteNode.left;
            } else {
                parentNode = deleteNode;
                deleteNode = deleteNode.right;
            }
        }

        //如果节点为空，说明值为val的节点不存在
        if (deleteNode == null) {
            return;
        }

        //1.删除节点左子树为空，将删除节点的右子树替换删除节点。
        if (deleteNode.left == null) {
            if (parentNode.left == deleteNode) {
                parentNode.left = deleteNode.right;
            } else {
                parentNode.right = deleteNode.right;
            }
            return;
        }

        //2.删除节点右子树为空，将删除节点的左子树替换节点
        if (deleteNode.right == null) {
            if (parentNode.left == deleteNode) {
                parentNode.left = deleteNode.left;
            } else {
                parentNode.right = deleteNode.left;
            }
            return;
        }

        //3.删除节点的左右子树都不为空，则查找删除节点的后继，并替换删除节点。
        BinaryTreeNode nextNode = deleteNode.right;
        BinaryTreeNode parentNextNode = deleteNode;
        while (nextNode.left != null) {
            parentNextNode = nextNode;
            nextNode = nextNode.left;
        }

        //令后继节点的左子树为删除节点的左子树
        //如果后继节点是删除节点的右孩子，则将后继节点替换掉删除节点，
        nextNode.left = deleteNode.left;
        if (parentNode.left == deleteNode) {
            parentNode.left = nextNode;
        } else {
            parentNode.right = nextNode;
        }
        //如果后继节点不是删除节点的右孩子，则将后继节点的父节点的左孩子替换为后继节点的右孩子
        if (deleteNode.right != nextNode) {
            parentNextNode.left = nextNode.right;
        }


    }


    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        //int[] arr = Utils.randomArray(10, 200);
        //Utils.printArray(arr);
        int[] arr = {46, 42, 144, 49, 155, 178, 127, 75, 109, 104};
        for (int i = 0; i < arr.length; i++) {
            BinaryTreeNode node = new BinaryTreeNode(arr[i]);
            tree.insertNode(node);
        }
        System.out.println(tree.searchNode(56));
        tree.middleTravel();
        tree.deleteNode(49);
        System.out.println();
        tree.middleTravel();
    }

}
