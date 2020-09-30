package edu.ilstu;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;

public class MazeBuilt {

	private JFrame frame;
	private JTextField textField;
	private JButton btnLoad;
	private JButton btnStart;
	private MazePanel panel;
	private Timer timer;
	MazeReader maz;
	MazeSolver mazSolv;
	Maze passedMaze;
	private char [][] maze;
	//Path
	private ArrayList<Node> path, prevPath;
	private Node n;
	private JButton btnAdvance;
	private int pathIndex;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeBuilt window = new MazeBuilt();
					window.frame.setVisible(true);
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
		initialize();
	
		btnLoad.addActionListener(new ActionListener(){///btn
            @Override
            public void actionPerformed(ActionEvent e) {
            	panel = new MazePanel();
        		panel.setBounds(10, 42, 750, 650);
        		frame.getContentPane().add(panel);
        		
            	maz = new MazeReader();
            	maze = maz.getMaze();
    			passedMaze = new Maze(maze);
                panel.repaint();
                maz.toString();
                
                path = new ArrayList<Node>();
                prevPath = new ArrayList<Node>();
                
                textField.setText("Maze loaded");
            }
           
        });/////btn
		
		btnStart.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	btnAdvance.setVisible(true);
            	
            	mazSolv = new MazeSolver(maze);
        		mazSolv.findStart();
        		n = mazSolv.solveMaze(path);
        		if(n != null) {
        			pathIndex = 0;
                    panel.repaint();
                    textField.setText("Solution in progress");
        		} else {
        			textField.setText("Solution Complete: finish not reachable");
        		}
            }
        });
		
		btnAdvance.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (pathIndex < path.size()) {
            		pathIndex++;
            	} 
            	panel.repaint();
            
            	if(pathIndex == path.size() -1 && n != null) {
            		textField.setText("Solution Complete: finish at " + Integer.toString(0) + 
            		" in "  + Integer.toString(path.size()) + " moves");
                }
            }
        });
	}

	/**
	 * Panel to call maze loader
	 */
		
	private class MazePanel extends JPanel {
		@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            passedMaze.drawMaze(g);
            
            if(!path.isEmpty() && n != null) {
            	passedMaze.searchPath(pathIndex, g);
            }
            
            if(pathIndex == path.size() -1 && n != null) {
            	passedMaze.shortestPath(g);
            }
        }
	
	}
	
	/**
	 * Draw maze
	 */
	
	private class Maze {
		char mz[][];
		
		public Maze(char mz[][]) {
			this.mz = mz;
		}
		
		public void drawMaze(Graphics g) {
			g.translate(50, 50);
			for (int row = 0; row < mz.length; row++) {
				for (int col = 0; col < mz[0].length; col++) {
					Color color;
					switch (mz[row][col]) {
						case '#': color = Color.BLACK; break;
						default: color = Color.WHITE; 
					}
					g.setColor(color);
					g.fillRect(50 * col, 50 * row, 50, 50);
					g.drawRect(50 * col, 50 * row, 50, 50);
				}
			}
		}
		
		public void searchPath(int p, Graphics g) {
			int pathX = path.get(p).x;
			int pathY = path.get(p).y;
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(pathX * 50, pathY * 50, 50, 50);
			
			for (int i=0; i < prevPath.size(); i++) {
				int prvPathX = prevPath.get(i).x;
				int prvPathY = prevPath.get(i).y;
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(50 * prvPathX, 50 * prvPathY, 50, 50);
			}
			
			prevPath.add(path.get(p));
		}
		
		public void shortestPath(Graphics g) {
			while(n.getN() != null) {
				n = n.getN();
				int pathX = n.x;
				int pathY = n.y;
				g.setColor(Color.RED);
				g.fillOval(50 * pathX, 50 * pathY, 50, 50);
			}
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(207, 11, 272, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText("No Maze");
		
		btnLoad = new JButton("Load");
		btnLoad.setBounds(29, 10, 79, 23);
		frame.getContentPane().add(btnLoad);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(118, 10, 79, 23);
		frame.getContentPane().add(btnStart);
		
		btnAdvance = new JButton("Advance");
		btnAdvance.setBounds(519, 10, 79, 23);
		frame.getContentPane().add(btnAdvance);
		btnAdvance.setVisible(false);
	
	}
}
