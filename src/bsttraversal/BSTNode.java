package bsttraversal;

public class BSTNode {
    //Store node information

    private int data;
    private int count;
    private BSTNode left;
    private BSTNode right;
    private BSTNode parent;
    private int level;
    private int order;

    public enum NodeType {
        LEFT_CHILD,
        RIGHT_CHILD
    }

    private static final int LEVEL_DY = 60;
    private int x;
    private int y;
    private int width;

    public BSTNode(int data) {
        this.data = data;
        this.count = 1;
        this.left = this.right = this.parent = null;
        this.level = this.order = 0;

        this.width = this.x = this.y = 0;
    }

    public BSTNode(int data, int yMin, int screenWidth) {
        this.data = data;
        this.count = 1;
        this.left = this.right = this.parent = null;
        this.level = this.order = 0;

        this.width = this.x = screenWidth / 2;
        this.y = yMin;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
        if (this.left != null) {
            this.left.setParent(this, NodeType.LEFT_CHILD);
        }
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
        if (this.right != null) {
            this.right.setParent(this, NodeType.RIGHT_CHILD);
        }
    }

    public BSTNode getParent() {
        return parent;
    }

    public void setParent(BSTNode parent, NodeType nodeType) {
        this.parent = parent;
        this.level = parent.getLevel() + 1;
        if (nodeType == NodeType.LEFT_CHILD) {
            this.order = parent.getOrder() * 2 + 1;
        } else {
            this.order = parent.getOrder() * 2 + 2;
        }

        this.y = parent.getY() + LEVEL_DY;
        this.width = parent.getWidth() / 2;
        if (nodeType == NodeType.LEFT_CHILD) {
            this.x = parent.getX() - this.width;
        } else {
            this.x = parent.getX() + this.width;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public boolean isChild() {
        return !isLeaf();
    }

    public boolean hasLeftChild() {
        return this.left != null;
    }

    public boolean hasRightChild() {
        return this.right != null;
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    public boolean isInside() {
        return !isLeaf() && !isRoot();
    }

    public BSTNode findMinNode() {
        BSTNode node = this;
        while (node.hasLeftChild()) {
            node = node.getLeft();
        }
        return node;
    }

    public BSTNode findMaxNode() {
        BSTNode node = this;
        while (node.hasRightChild()) {
            node = node.getRight();
        }
        return node;
    }

    public boolean removeLeafNode(BSTNode node) {
        if (node == null) {
            return false;
        }
        if (node.isLeaf()) {
            if (this.hasLeftChild()) {
                if (this.getLeft().getData() == node.getData()) {
                    this.setLeft(null);
                    return true;
                }
            }

            if (this.hasRightChild()) {
                if (this.getRight().getData() == node.getData()) {
                    this.setRight(null);
                    return true;
                }
            }
        }
        return false;
    }
}
