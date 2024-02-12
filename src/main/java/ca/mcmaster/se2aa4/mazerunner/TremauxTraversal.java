package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Stack;

public class TremauxTraversal {
    private ArrayList<ArrayList<String>> maze;
    private int[] end_coords;
    private int[][] visited;
    private Stack<String> temp_path = new Stack<>();
    private String path = ""; 
    private Stack<int[]> stack = new Stack<>();

    public TremauxTraversal(int[] end_coords, int[] start_coords, ArrayList<ArrayList<String>> maze) {
        this.end_coords = end_coords;
        this.maze = maze;
        this.visited = new int[maze.size()][maze.get(0).size()];
        stack.push(start_coords);
    }

    public String Solve() {
        temp_path.push("");
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];
            if(x == end_coords[0] && y == end_coords[1]){
                return path;
            }else{
                if(x <= -1 || y <= -1 || x >= maze.size() || y >= maze.get(0).size() || maze.get(x).get(y) == "WALL" || visited[x][y] == 2){
                    temp_path.pop();
                    continue;
                }else{
                    path += temp_path.peek();
                    visited[x][y]++;
                }
                // Try moving in all four directions
                stack.push(new int[]{x - 1, y});
                temp_path.push("N");
                stack.push(new int[]{x + 1, y});
                temp_path.push("S");
                stack.push(new int[]{x, y - 1});
                temp_path.push("W");
                stack.push(new int[]{x, y + 1});
                temp_path.push("E");
            }
        }
            
        return "Could not find temp_path";
    }
}