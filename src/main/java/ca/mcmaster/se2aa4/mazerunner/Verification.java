package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class Verification {
    private ArrayList<ArrayList<String>> maze;
    private int[] current_coords;
    private int[] end_coords;
    private enum DIRECTION {
        NORTH, SOUTH, EAST, WEST
    }
    DIRECTION direction = DIRECTION.EAST;
    public Verification(ArrayList<ArrayList<String>> maze, int[] current_coords, int[] end_coords){
        this.maze = maze;
        this.current_coords = current_coords;
        this.end_coords = end_coords;
    }

    public boolean verify(String path){
        for(int i = 0; i < path.length(); i++){
            if(current_coords[0] == end_coords[0] && current_coords[1] == end_coords[1]){
                return true;
            }
            else{
                if(path.charAt(i) == 'F'){
                    if(direction == DIRECTION.EAST){
                        if(maze.get(current_coords[0]).get(current_coords[1] + 1).equals("PASS")){
                            current_coords[1]++;
                        }else{
                            return false;
                        }
                    }else if(direction == DIRECTION.WEST){
                        if(maze.get(current_coords[0]).get(current_coords[1] - 1).equals("PASS")){
                            current_coords[1]--;
                        }else{
                            return false;
                        }
                    }else if(direction == DIRECTION.NORTH){
                        if(maze.get(current_coords[0] - 1).get(current_coords[1]).equals("PASS")){
                            current_coords[0]--;
                        }else{
                            return false;
                        }
                    }else{
                        if(maze.get(current_coords[0] + 1).get(current_coords[1]).equals("PASS")) {
                            current_coords[0]++;
                        }else{
                            return false;
                        }
                    }
                }else if(path.charAt(i) == 'L'){
                    if(direction == DIRECTION.EAST){
                        direction = DIRECTION.NORTH;
                    }else if (direction == DIRECTION.SOUTH) {
                        direction = DIRECTION.EAST;
                    }else if (direction == DIRECTION.NORTH){
                        direction = DIRECTION.WEST;
                    }else{
                        direction = DIRECTION.SOUTH;
                    }
                }else if(path.charAt(i) == 'R'){
                    if(direction == DIRECTION.EAST){
                        direction = DIRECTION.SOUTH;
                    }else if (direction == DIRECTION.SOUTH) {
                        direction = DIRECTION.WEST;
                    }else if (direction == DIRECTION.NORTH){
                        direction = DIRECTION.EAST;
                    }else{
                        direction = DIRECTION.NORTH;
                    }
                }else{
                    return false;
                }
            }
        }
        if(current_coords[0] == end_coords[0] && current_coords[1] == end_coords[1]){
            return true;
        }else{
            return false;
        }
    }
}
