package edu.ilstu;

public class Node {

    public Node n;
    public int x;
    public int y;
    public char c;
    
    public Node(int x, int y, char c, Node n){
        this.x = x;
        this.y = y;
        this.c = c;
        this.n = n;
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

    public char getC() {
        return c;
    }
    public void setC(char c) {
        this.c = c;
    }

    public Node getN() {
        return n;
    }
    public void setN(Node n) {
        this.n = n;
    }

    public String toString() {
        return c +" X:"+ x +" Y:"+ y;
    }

}
