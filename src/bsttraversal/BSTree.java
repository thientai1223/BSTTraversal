package bsttraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BSTree {

    BSTNode root;
    String result;
    ArrayList<BSTNode> path;
    ArrayList<BSTNode> ascendingArray;
    String fileData;
    int countData;
    BSTNode minNode, maxNode;

    public String getFileData() {
        return fileData;
    }

    public int getCountData() {
        return countData;
    }

    int screenWidth;
    int yMin;

    public BSTree() {
        root = null;
        this.result = "";
        this.screenWidth = 0;
        this.yMin = 0;
        path = new ArrayList<BSTNode>();
        ascendingArray = new ArrayList<>();
    }

    public BSTree(int screenWidth, int yMin) {
        this.root = null;
        this.result = "";
        this.screenWidth = screenWidth;
        this.yMin = yMin;
        path = new ArrayList<BSTNode>();
        ascendingArray = new ArrayList<>();
    }

    public BSTNode getRoot() {
        return this.root;
    }

    public String getTraversalResult() {
        return this.result;
    }

    public void addNode(int data) {
        if (root == null) {
            root = new BSTNode(data, yMin, screenWidth);
        } else {
            BSTNode node = root;
            boolean isAdded = false;
            while (!isAdded) {
                if (data < node.getData()) {
                    if (node.hasLeftChild()) {
                        node = node.getLeft();
                    } else {
                        node.setLeft(new BSTNode(data));
                        isAdded = true;
                    }
                } else if (data > node.getData()) {
                    if (node.hasRightChild()) {
                        node = node.getRight();
                    } else {
                        node.setRight(new BSTNode(data));
                        isAdded = true;
                    }
                } else {
                    node.setCount(node.getCount() + 1);
                    isAdded = true;
                }
            }

        }
    }

    public void preOrder() {
        this.result = "";
        this.fileData = "";
        this.countData = 0;
        preOrder(this.root);
    }

    public void preOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.getCount(); i++) {
            result += node.getData() + ", ";
            this.fileData += node.getData() + " ";
            ++this.countData;
        }
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void inOrder() {
        this.result = "";
        this.ascendingArray.clear();
        inOrder(this.root);
    }

    public void inOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        for (int i = 0; i < node.getCount(); i++) {
            result += node.getData() + ",";
        }
        this.ascendingArray.add(node);
        inOrder(node.getRight());
    }

    public void postOrder() {
        this.result = "";
        postOrder(this.root);
    }

    public void postOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft());
        postOrder(node.getRight());
        for (int i = 0; i < node.getCount(); i++) {
            result += node.getData() + ",";
        }
    }

    public ArrayList<BSTNode> getPath() {
        return this.path;
    }

    public BSTNode findNode(int data) {
        BSTNode node = this.root;
        result = "";
        path.clear();
        while (node != null) {
            result += node.getData() + " -> ";
            path.add(node);
            if (data == node.getData()) {
                return node;
            } else if (data < node.getData()) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        path.clear();
        return null;
    }

    public boolean removeNode(int data) {
        BSTNode node = findNode(data);
        return removeNode(node);
    }

    public boolean removeNode(BSTNode node) {
        if (node == null) {
            return false;
        }
        node.setCount(node.getCount() - 1);
        if (node.getCount() == 0) {
            if (node.isLeaf()) {
                node.getParent().removeLeafNode(node);
                return true;
            } else {
                BSTNode incomer = null;
                if (node.hasLeftChild()) {
                    incomer = node.getLeft().findMaxNode();
                } else {
                    incomer = node.getRight().findMinNode();
                }
                node.setData(incomer.getData());
                node.setCount(incomer.getCount());

                incomer.setCount(1);
                return removeNode(incomer);
            }
        } else {
            return true;
        }
    }

    public void clear() {
        clearNode(root);
        this.root = null;
    }

    public void clearNode(BSTNode node) {
        if (node != null) {
            if (node.hasLeftChild()) {
                clearNode(node.getLeft());
            }
            if (node.hasRightChild()) {
                clearNode(node.getRight());
            }
            if (!node.isRoot()) {
                node.getParent().removeLeafNode(node);
            }
        }
    }

    public void balancing() {
        inOrder();
        clear();
        balancing(0, this.ascendingArray.size() - 1);
    }

    public void balancing(int leftIndex, int rightIndex) {
        if (leftIndex <= rightIndex) {
            int middleIndex = (leftIndex + rightIndex) / 2;
            int nodeData = this.ascendingArray.get(middleIndex).getData();
            int nodeCount = this.ascendingArray.get(middleIndex).getCount();
            for (int i = 0; i < nodeCount; i++) {
                this.addNode(nodeData);
            }
            balancing(leftIndex, middleIndex - 1);
            balancing(middleIndex + 1, rightIndex);
        }
    }

    public void BFS() {
        this.result = "";
        Queue<BSTNode> q = new LinkedList<>();
        q.add(root);
        BSTNode node;
        while (!q.isEmpty()) {
            node = q.poll();
            if (node != null) {
                for (int i = 0; i < node.getCount(); i++) {
                    result += "," + node.getData();
                }
                q.add(node.getLeft());
                q.add(node.getRight());
            }
        }
    }

    public void DFS() {
        this.result = "";
        Stack<BSTNode> s = new Stack<>();
        s.add(root);
        BSTNode node;
        while (!s.isEmpty()) {
            node = s.pop();
            if (node != null) {
                for (int i = 0; i < node.getCount(); i++) {
                    result += "," + node.getData();
                }
                s.add(node.getRight());
                s.add(node.getLeft());
            }
        }
    }
}
