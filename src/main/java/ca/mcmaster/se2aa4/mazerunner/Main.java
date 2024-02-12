package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        Options options = new Options();
        Option input = Option.builder("i")
                            .longOpt("input")
                            .desc("input file path")
                            .hasArg().argName("FILE")
                            .required()
                            .build();
        Option path = Option.builder("p")
                            .longOpt("path")
                            .desc("path to check against maze")
                            .hasArg()
                            .argName("PATH")
                            .build();
        
        Option method = Option.builder(null)
                            .longOpt("method")
                            .desc("method to use for solving maze")
                            .hasArg()
                            .argName("METHOD")
                            .build();

        options.addOption(method);
        options.addOption(input);
        options.addOption(path);
        
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("i")){
                PathHandler pathHandler = new PathHandler();
                logger.info("**** Reading the maze from file " + cmd.getOptionValue("i"));
                String filePath = cmd.getOptionValue("i");
                Storage storage = new Storage(filePath);
                int[] start_coords = storage.getStartCoords();
                int[] end_coords = storage.getEndCoords();
                ArrayList<ArrayList<String>> maze = storage.StoreMaze();

                logger.info("Start location: " + Arrays.toString(start_coords));
                logger.info("End location: " + Arrays.toString(end_coords));
                
                Verification verifier = new Verification(maze, start_coords, end_coords);
                if(cmd.hasOption("p")){
                    pathHandler.checkIfValid(filePath);
                    String providedPath = cmd.getOptionValue("p");
                    if(pathHandler.checkIfValid(providedPath) && pathHandler.checkIfFactorized(providedPath)){
                        String canonizedPath = pathHandler.canonize(providedPath);
                        if(verifier.verify(canonizedPath)){
                            logger.info("The provided path was correct and solved the maze!");
                        }else{
                            logger.info("The provided path was incorrect as it either did not reach the exit or passed through a wall.");
                        }
                    }else if(pathHandler.checkIfValid(providedPath) && pathHandler.checkIfCanonical(providedPath)){
                        if(verifier.verify(providedPath)){
                            logger.info("The provided path was correct and solved the maze!");
                        }else{
                            logger.info("The provided path was incorrect as it either did not reach the exit or passed through a wall.");
                        }
                    }else{
                        logger.error("The provided path was not valid. Make sure the path consists of only the F, L, R, characters or numbers," 
                        +"and a number does not appear at the end of the path.");
                    }
                
                }else{
                    if(cmd.hasOption("method") && cmd.getOptionValue("method").equals("tremaux")){
                        TremauxTraversal traverser = new TremauxTraversal(end_coords, start_coords, maze);
                        String solution = traverser.Solve();
                        logger.info(solution);

                    }else{
                        DefaultTraversal traverser = new DefaultTraversal(end_coords, maze);
                        String solution = traverser.solve(start_coords);
                        logger.info(pathHandler.factorize(solution));
                    }
                }
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\" +e.getMessage());
            logger.error("Make sure you included a -i flag and a path to a file");
        }
        logger.info("** End of MazeRunner");
    }
}
