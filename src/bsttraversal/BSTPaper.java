/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bsttraversal;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class BSTPaper extends JPanel {

    BSTree tree;
    int screenWidth;
    int yMin;
    Graphics2D g;

    /**
     *
     */
    int r = 16;

    public BSTPaper(BSTree tree, int screenWidth, int yMin) {
        this.tree = tree;
        this.screenWidth = screenWidth;
        this.yMin = yMin;
    }

    public void drawPath() {
        repaint();
    }

    public void addNode(int data) {
        tree.addNode(data);
        repaint();
    }

    public void deleteNode(int data) {
        this.tree.removeNode(data);
        this.tree.path.clear();
        repaint();
    }

    public void findMax() {
        tree.maxNode = tree.minNode = null;
        tree.maxNode = tree.root.getRight().findMaxNode();
        int x = tree.maxNode.getX();
        int y = tree.maxNode.getY();
        g.setColor(Color.red);
        g.fillOval(x - r, y - r, r * 2, r * 2);

        g.setColor(Color.YELLOW);
        g.drawOval(x - r, y - r, r * 2, r * 2);
        drawCenterString(g, tree.maxNode.getData() + "",
                new Rectangle(x - r, y - r, r * 2, r * 2),
                new Font("Arial", Font.PLAIN, 12));
        repaint();

    }
        public void findMin() {
        tree.maxNode = tree.minNode = null;
        tree.minNode = tree.root.getLeft().findMinNode();
        int x = tree.minNode.getX();
        int y = tree.minNode.getY();
        g.setColor(Color.red);
        g.fillOval(x - r, y - r, r * 2, r * 2);

        g.setColor(Color.YELLOW);
        g.drawOval(x - r, y - r, r * 2, r * 2);
        drawCenterString(g, tree.minNode.getData() + "",
                new Rectangle(x - r, y - r, r * 2, r * 2),
                new Font("Arial", Font.PLAIN, 12));
        repaint();

    }
    public void drawCenterString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);

    }

    public void clear1() {

        tree.root = null;
        repaint();
    }

    public void drawNode(BSTNode node) {
        if (node == null) {
            return;
        }
        int x = node.getX();
        int y = node.getY();

        g.setColor(Color.BLACK);
        if (node.hasLeftChild()) {
            g.drawLine(x, y, node.getLeft().getX(), node.getLeft().getY());
        }
        if (node.hasRightChild()) {
            g.drawLine(x, y, node.getRight().getX(), node.getRight().getY());
        }

        g.setColor(Color.WHITE);
        g.fillOval(x - r, y - r, r * 2, r * 2);

        g.setColor(Color.BLACK);
        g.drawOval(x - r, y - r, r * 2, r * 2);

        drawCenterString(g, node.getData() + "",
                new Rectangle(x - r, y - r, r * 2, r * 2),
                new Font("Arial", Font.PLAIN, 12));

        drawCenterString(g, "c=" + node.getCount(),
                new Rectangle(x - r, y - 25  - r, r * 2, r * 2),
                new Font("Arial", Font.PLAIN, 12));
        drawNode(node.getLeft());
        drawNode(node.getRight());

    }

//    public void refesh() {
//        repaint();
//    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.g = (Graphics2D) g;
        drawNode(this.tree.getRoot());

        ArrayList<BSTNode> path = this.tree.getPath();
        if (path.size() > 0) {
            BSTNode n1, n2;
            g.setColor(Color.red);
            for (int i = 1; i < path.size(); i++) {
                n1 = path.get(i - 1);
                n2 = path.get(i);
                g.drawLine(n1.getX(), n1.getY(), n2.getX(), n2.getY());
            }

            int x, y;

            for (int i = 0; i < path.size(); i++) {
                n1 = path.get(i);
                x = n1.getX();
                y = n1.getY();

                g.setColor(Color.YELLOW);
                g.fillOval(x - r, y - r, r * 2, r * 2);

                g.setColor(Color.red);
                g.drawOval(x - r, y - r, r * 2, r * 2);

                drawCenterString(g, n1.getData() + "",
                        new Rectangle(x - r, y - r, r * 2, r * 2),
                        new Font("Arial", Font.PLAIN, 12));

            }

        }
        if (tree.maxNode != null) {
            findMax();
        }
         if (tree.minNode != null) {
            findMin();
        }

    }

    void refesh() {
        repaint();
    }
}
