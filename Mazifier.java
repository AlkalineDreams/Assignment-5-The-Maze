/****************************************************************************
 *
 * Created by: Craig
 * Created on: Nov 2016
 * This program solves a maze.
 * 
 ****************************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Mazifier {

	public static void main(String[] args) throws IOException {
		
		FileReader fr = null;
		String mazeFile = "Maze3.txt"; //CHANGE THIS CODE TO CHANGE WHICH MAZE YOU'RE SOLVING
	    BufferedReader br = null;
	    String line = "";
	    
	    fr = new FileReader(mazeFile);
	    br = new BufferedReader(fr);
	    
	    line = br.readLine(); //reads first line of maze
        int i = (int) (line.length()); //array width should be the width of the first line
        System.out.println(i); //for debugging
        br.close();
        
        BufferedReader br2 = new BufferedReader(new FileReader(mazeFile));
        int j = 0;
        while (br2.readLine() != null) j++;
        br2.close();
        System.out.println(j);
        
		char[][] maze = new char[j][i]; //2d maze array creation
	    char[] lineArray = new char[line.length()];
        
	    BufferedReader br3 = new BufferedReader(new FileReader(mazeFile)); //new line reader so it starts at the beginning again

	    //these nested loops populate the "maze[][]" array.
        for(int x = 0; x < maze.length; x++) {
        	
                line = br3.readLine(); //selects next line
                lineArray = line.toCharArray(); //turn line into char array
                
            for(int y = 0; y < maze[0].length; y++) {
                maze[x][y] = lineArray[y]; //shoves line array into maze array at row[x]
            }
            
        }
        br3.close();

        //now that the maze has been created:
        int[] startPosition = new int[2];
        startPosition = FindStart(maze);
        
        if (startPosition[0] == -1) { //error handler
        	System.out.println("Your maze needs to have a starting point marked by the letter 'S'.");
        	System.out.println("Please run the program with a valid maze next time.");
        } else {
        	
        	//this occurs when everything worked correctly, we have a valid starting position, and the maze is ready to be solved.
        	System.out.println("Start Position: Maze Array at " + startPosition[0] + ", " + startPosition[1]); //debugging
        	
        	boolean mazeSolved = FindPath(maze, startPosition[0], startPosition[1]);
        	
        	if(mazeSolved){System.out.println("A solution was found!");}
        	else{System.out.println("A solution was not found :(");}
        	
        }

	}
	
	public static int[] FindStart(char[][] maze) {
		
		int[] startAt = new int[2];
		
		for(int x = 0; x < maze.length; x++) {
			for(int y = 0; y < maze[0].length; y++) {
				
                if (maze[x][y] == ('S')){
                	startAt[0] = x;
                	startAt[1] = y;
                	
                	return startAt;
                } else {
                	startAt[0] = -1;
                }
                
            }
		}
		
		return startAt;
		
	}
	
	public static boolean FindPath(char[][] currentMaze, int x, int y){
		
		boolean solved = false;
		
		if (0 <= x && x < currentMaze.length && 0 <= y && y < currentMaze[0].length && solved == false){
			
			if(currentMaze[x][y] == 'G'){ //if goal found
				solved = true;
        		
        		for(int i = 0; i < currentMaze.length; i++) {
        			
        			String line = ""; //resets "line" variable
        			
        			for(int j = 0; j < currentMaze[0].length; j++) {
        				
                        line = line + currentMaze[i][j]; 
                        
                    }
        			System.out.println(line);
        		}
				
				return solved;
				
			}
			else if (currentMaze[x][y] == '#'){ //when you hit a wall
				return solved;
			}
			else if (currentMaze[x][y] == '+'){ //when you've already tried this route
				return solved;
			}
			else if (currentMaze[x][y] == '.' || currentMaze[x][y] == 'S'){ //when there's an open path
				
				if (currentMaze[x][y] == 'S'){
				} else {currentMaze[x][y] = '+';} //marks this as part of the tested path if it's not the starting point
				
				if (FindPath(currentMaze, x, y+1) == true){ //TEST NORTH
					solved = true;
					return solved;
				}
				else if (FindPath(currentMaze, x+1, y) == true){ //TEST EAST
					solved = true;
					return solved;
				}
				else if (FindPath(currentMaze, x, y-1) == true){ //TEST SOUTH
					solved = true;
					return solved;
				}
				else if (FindPath(currentMaze, x-1, y) == true){ //TEST WEST
					solved = true;
					return solved;
				}
				else if (solved == false) {
					if (currentMaze[x][y] == 'S'){
					} else {currentMaze[x][y] = '.';} //deletes item from solution path if it isn't the starting position
				}
				return solved;
			}
			
		} else {
			//when out of bounds, do nothing.
		}

		return solved;
		
	}

}
