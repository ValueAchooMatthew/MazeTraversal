package ca.mcmaster.se2aa4.mazerunner;

public class PathHandler {
    
    public boolean checkIfValid(String path){
        // Cannot have digit as the last character in the path
        if(Character.isDigit(path.charAt(path.length()-1))){
            return false;
        }
        for(int i = 0; i < path.length(); i++){
            Character currentChar = path.charAt(i);
            if(currentChar == 'F' || currentChar == 'L' || currentChar == 'R' || Character.isDigit(currentChar)){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
    public boolean checkIfCanonical(String path){
        if(this.checkIfValid(path) == false){
            return false;
        }else{
            for(int i = 0; i < path.length(); i++){
                Character currentChar = path.charAt(i);
                if(currentChar == 'F' || currentChar == 'L' || currentChar == 'R'){
                    continue;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkIfFactorized(String path){
        if(this.checkIfValid(path) && !this.checkIfCanonical(path)){
            return true;
        }else{
            return false;
        }
    }
    public String canonize(String path){
        String canonizedPath = "";
        int i = 0;
        while(i < path.length()){
            Character currentChar = path.charAt(i);
            // Remember to increment i by 2 when encountering a number
            if(Character.isDigit(currentChar)){
                for(int j = 0; j < Character.getNumericValue(currentChar); j++){
                    canonizedPath += path.charAt(i+1);
                }
                i += 2;
            }else{
                canonizedPath += currentChar;
                i += 1;
            }
        }
        return canonizedPath;
    }
    public String factorize(String path){
        String factorizedPath = "";
        int i = 0;
        while(i < path.length()){
            char currentChar = path.charAt(i);
            int count = 1;
            while(i + count < path.length() && path.charAt(i + count) == currentChar){
                count++;
            }
            if(count > 1){
                factorizedPath += count + "" + currentChar;
                i+=count;
            }else{
                factorizedPath += currentChar;
                i++;
            }
        }
        return factorizedPath;
    }
}