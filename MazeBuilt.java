package edu.ilstu;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import javafx.scene.input.KeyEvent;

public class MazeBuilt extends JFrame{

	private JFrame frmMaze;
	private JTextField textFieldStatus;
	MazeReader maz;
	MazeSolver mazSolv;
	
	//The maze to be displayed
	
	private char [][] maze;
		/*{ {1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,5,1,0,1,0,1,0,0,0,0,0,1}, //start point 5 at (1,1)
			{1,0,1,0,0,0,1,0,1,1,1,0,1},
			{1,0,0,0,1,1,1,0,0,0,0,0,1},
			{1,0,1,0,0,0,0,0,1,1,1,0,1},
			{1,0,1,0,1,1,1,0,1,0,0,0,1},
			{1,0,1,0,1,0,0,0,1,1,1,0,1},
			{1,0,1,0,1,1,1,0,1,0,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,1,9,1}, //end point 9 at (11,8)
			{1,1,1,1,1,1,1,1,1,1,1,1,1}

		};*/
	//Path
	private final ArrayList<Integer> path = new ArrayList<Integer>();
	private int pathIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeBuilt window = new MazeBuilt();
					window.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public MazeBuilt() {
		//initialize();
		setTitle("Maze");
		setBounds(100, 100, 750, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*DFS.searchPath(maze, 1, 1, path);
		pathIndex = path.size() - 2;
		System.out.println(path);*/
	}
	
	/**
	 * Draw the maze
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		maz = new MazeReader();
		maze = maz.getMaze();
		mazSolv = new MazeSolver(maze);
		
		mazSolv.findStart();
		mazSolv.solveMaze();
		
		g.translate(50, 50);
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[0].length; col++) {
				Color color;
				switch (maze[row][col]) {
					case '#': color = Color.BLACK; break;
					case 'o': color = Color.BLUE; break;
					case '*': color = Color.GREEN; break;
					default: color = Color.WHITE; 
				}
				g.setColor(color);
				g.fillRect(50 * col, 50 * row, 50, 50);
				//g.setColor(Color.WHITE);
				g.drawRect(50 * col, 50 * row, 50, 50);
			}
		}
		
		for (int p = 0; p < path.size();p += 2) {
			int pathX = path.get(p);
			int pathY = path.get(p + 1);
			g.setColor(Color.YELLOW);
			g.fillRect(pathX * 30, pathY * 30, 30, 30);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMaze = new JFrame();
		frmMaze.setTitle("Maze");
		frmMaze.setBounds(100, 100, 450, 300);
		frmMaze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMaze.getContentPane().setLayout(null);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLoad.setBounds(22, 11, 69, 23);
		frmMaze.getContentPane().add(btnLoad);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnStart.setBounds(101, 11, 69, 23);
		frmMaze.getContentPane().add(btnStart);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldStatus.setEditable(false);
		textFieldStatus.setBounds(195, 11, 206, 20);
		frmMaze.getContentPane().add(textFieldStatus);
		textFieldStatus.setColumns(10);
	}
}
