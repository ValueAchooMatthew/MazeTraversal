package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Storage {
    private BufferedReader reader;
    ArrayList<ArrayList<String>> maze = new ArrayList<ArrayList<String>>();
    private int[] start_coords = new int[2];
    private int[] end_coords = new int[2];

    public Storage(String path){
        try{
            reader = new BufferedReader(new FileReader(path));
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    public ArrayList<ArrayList<String>> StoreMaze(){
        try{
            String line;
            int line_count = 0;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> row = new ArrayList<String>();
                if(line.charAt(0) == ' '){
                    start_coords[0] = line_count;
                    start_coords[1] = 0;
                }
                if(line.charAt(line.length()-1) == ' '){
                    end_coords[0] = line_count;
                    end_coords[1] = line.length()-1;
                }
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        row.add("WALL");
                    } else if (line.charAt(idx) == ' ') {
                        row.add("PASS");
                    }
                }
                line_count++;
                maze.add(row);
            }
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return maze;
    }
    public int[] getStartCoords(){
        return start_coords;
    }
    public int[] getEndCoords(){
        return end_coords;
    }
}
