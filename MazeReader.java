package edu.ilstu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MazeReader {

    private int width;
    private int height;
    private char[][] maze;


    public MazeReader() {
        String fileText = "";
        //read file and add it to the fileText string which will be processed to become the maze
        try {
            File file = new File("maze2.txt");
            Scanner input = new Scanner(file);
            //start at 0,0
            while (input.hasNextLine()) {
                fileText += input.nextLine() + "\n";
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println(fileText);

        //parse the file into rows
        String[] rows = fileText.split("\r?\n");

        //width of the array is the length of the row
        width = rows[0].length();

        //height is the number of lines
        height = rows.length;

        //new maze of type char which stores the character at each location
        maze = new char[height][width];

        //read array
        for (int i =0 ;i<height;i++){
            for (int j = 0;j<width;j++){
                maze[i][j] = rows[i].charAt(j);
            }
        }

       // System.out.println("Maze is a 2d array with height: "+height+" and a width of: "+width);

    }

    public char[][] getMaze(){
        return maze;
    }

    public static String fromChar(char ch) throws IllegalArgumentException {
        String a ="";
        switch (ch){
            case '#':
                a = "Wall";
                break;
            case '.':
                a = "Open space";
                break;
            case'o':
                a = "Start";
                break;
            case'*':
                a = "Finish";
                break;
                default:
                    throw new IllegalArgumentException();
        }

        return a;
    }

    @Override
    public String toString() {
        for (int i =0 ;i<height;i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        return "";
    }

    public char toString(int h, int w){
        return maze[h][w];
    }

    public static void main(String[] args) throws FileNotFoundException {
		MazeReader maz = new MazeReader();
		maz.toString();
		char s = maz.toString(3, 1);
		System.out.println("Char "+ s + " = " + fromChar(s));
	}
}

