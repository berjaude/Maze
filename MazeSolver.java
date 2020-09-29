package edu.ilstu;

import java.util.LinkedList;
import java.util.Queue;

public class MazeSolver {

    private char maze[][];

    //keeps track of position
    private int x;
    private int y;
    Queue<Node> queue= new LinkedList<>();

    //This constructor creates the maze to solve inside this class
    public MazeSolver(char[][] maze){
        this.maze = maze;
    }
    //returns the original starting point
    public int getX() {
        return x;
    }
    //sets the original starting point
    public void setX(int x) {
        this.x = x;
    }
    //returns the original starting point
    public int getY() {
        return y;
    }
    //sets the original starting point
    public void setY(int y) {
        this.y = y;
    }

    //start position 0,0 starts in upper left corner
    public void findStart(){
        char startPosition=' ';
        for (int i =0 ;i<maze.length;i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'o'){
                   startPosition = maze[i][j];
                   setX(j);
                   setY(i);
                }
            }
        }
        System.out.println(startPosition+" X:"+getX()+" Y:"+getY());

    }

    //prints the maze
    public void printMaze(char[][] maze){
        for (int i =0 ;i<maze.length;i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    //recursively call method with new x or y
    //
    public Node solveMaze(){
    	//Locate the start point
    	//findStart();
        //start node
        queue.add(new Node(getX(),getY(),maze[getY()][getX()],null));

        //queue that will add adjacent nodes, which are next to the current node, n
        //checks up, down, left, right
        //it will check if the node is not visited or is the finish node
        //if either of these cases it will add that node to the queue

        while(!queue.isEmpty()){
            Node n = queue.remove();
            //System.out.println(maze[n.y][n.x]+" "+n.x+" "+ n.y);
            //if end Print it is done.
            if (maze[n.y][n.x] == '*'){
                System.out.println("Reached the end of the maze.");
                return n;
            }
            //check up
            //if next to valid place to move
            //mark current node as visited and add the checked node to the queue
            if (maze[n.y-1][n.x]=='.'|| maze[n.y-1][n.x] == '*'){
                Node nextN = new Node(n.x,n.y-1,maze[n.y-1][n.x],n);
                maze[n.y][n.x]= 'V';
                queue.add(nextN);
            }
            //check down
            //repeat same
            if (maze[n.y+1][n.x]=='.'|| maze[n.y+1][n.x] == '*'){
                Node nextN = new Node(n.x,n.y+1,maze[n.y+1][n.x],n);
                maze[n.y][n.x]= 'V';
                queue.add(nextN);
            }
            //check left
            //repeat same
            if (maze[n.y][n.x-1]=='.'|| maze[n.y][n.x-1] == '*'){

                Node nextN = new Node(n.x-1,n.y,maze[n.y][n.x-1],n);
                maze[n.y][n.x]= 'V';
                queue.add(nextN);
            }

            //check right
            //repeat same
            if (maze[n.y][n.x+1]=='.'|| maze[n.y][n.x+1] == '*'){
                Node nextN = new Node(n.x+1,n.y,maze[n.y][n.x+1],n);
                maze[n.y][n.x]= 'V';
                queue.add(nextN);
            }

        }
        return null;
    }

    public void printPath(Node n){
        //print all nodes until start, backtracking
        while(n.getN() != null){
            System.out.println(n);
            n = n.getN();
        }
        //redo start
        maze[getY()][getX()] = 'o';
        //print start
        System.out.println(maze[getY()][getX()]+" X:"+x+" Y:"+ y);

    }

}
