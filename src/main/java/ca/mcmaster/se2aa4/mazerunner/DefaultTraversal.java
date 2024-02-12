package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList; // Add the import statement for ArrayList

public class DefaultTraversal {
    private int[] end_coords;
    private ArrayList<ArrayList<String>> maze;
    private String solution = "";
    private DIRECTION direction = DIRECTION.EAST;
    private enum DIRECTION {
        NORTH, SOUTH, EAST, WEST
    }
    public DefaultTraversal(int[] end_coords, ArrayList<ArrayList<String>> maze) {
        this.end_coords = end_coords;
        this.maze = maze;
    }
    public String solve(int[] current_coords){
        while(true){
            if(current_coords[0] == end_coords[0] && current_coords[1] == end_coords[1]){
                return solution;
            }else{
                if(direction == DIRECTION.EAST){
                    if(maze.get(current_coords[0] + 1).get(current_coords[1]).equals("PASS")){
                        current_coords[0]++;
                        direction = DIRECTION.SOUTH;
                        solution += "R";
                        solution += "F";
                    }else if(maze.get(current_coords[0]).get(current_coords[1] + 1).equals("PASS")){
                        current_coords[1]++;
                        solution += "F";
                    }else if(maze.get(current_coords[0] - 1).get(current_coords[1]).equals("PASS")){
                        current_coords[0]--;
                        direction = DIRECTION.NORTH;
                        solution += "L";
                        solution += "F";
                    }else{
                        direction = DIRECTION.SOUTH;
                        solution += "R";
                    }
                }else if(direction == DIRECTION.SOUTH){
                    if(maze.get(current_coords[0]).get(current_coords[1] - 1).equals("PASS")){
                        current_coords[1]--;
                        direction = DIRECTION.WEST;
                        solution += "R";
                        solution += "F";
                    }else if(maze.get(current_coords[0] + 1).get(current_coords[1]).equals("PASS")){
                        current_coords[0]++;
                        solution += "F";
                    }else if(maze.get(current_coords[0]).get(current_coords[1] + 1).equals("PASS")){
                        current_coords[1]++;
                        direction = DIRECTION.EAST;
                        solution += "L";
                        solution += "F";
                    }else{
                        direction = DIRECTION.WEST;
                        solution += "R";
                    }
                }else if(direction == DIRECTION.WEST){
                    if(maze.get(current_coords[0] - 1).get(current_coords[1]).equals("PASS")){
                        current_coords[0]--;
                        direction = DIRECTION.NORTH;
                        solution += "R";
                        solution +="F";
                    }else if(maze.get(current_coords[0]).get(current_coords[1] - 1).equals("PASS")){
                        current_coords[1]--;
                        solution += "F";
                    }else if(maze.get(current_coords[0] + 1).get(current_coords[1]).equals("PASS")){
                        current_coords[0]++;
                        direction = DIRECTION.SOUTH;
                        solution += "L";
                        solution += "F";
                    }else{
                        direction = DIRECTION.NORTH;
                        solution += "R";
                    }
                }else{
                    if(maze.get(current_coords[0]).get(current_coords[1] + 1).equals("PASS")){
                        current_coords[1]++;
                        direction = DIRECTION.EAST;
                        solution += "R";
                        solution += "F";
                    }else if(maze.get(current_coords[0] - 1).get(current_coords[1]).equals("PASS")){
                        current_coords[0]--;
                        solution += "F";
                    }else if(maze.get(current_coords[0]).get(current_coords[1] - 1).equals("PASS")){
                        current_coords[1]--;
                        direction = DIRECTION.WEST;
                        solution += "L";
                        solution += "F";
                    }else{
                        direction = DIRECTION.EAST;
                        solution += "R";
                    }
                }
            }
        }
    }
}