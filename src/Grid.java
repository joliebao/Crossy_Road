import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    private int[][] grid = new int[30][40];

    // generate random grid -> needs numbers from 1-9 randomly generated
    /* Below is the key used to represent each item ↓
            Grass: 0
            Concrete/Road: 1
            Water: 2
            Train track: 3
            Boulder: 4
            Car: 5
            Truck: 6
            Train: 7
            Logs: 8
            Character: 9
    */
    public Grid(){
        ArrayList<Integer> usedRow = new ArrayList<Integer>();

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = 0;
            }
        }

        // FLOORING
        for (int x = 0; x < 13; x++) {
            double probability = Math.random();
            if (probability < 0.7 && probability > 0.4) { // concrete
                int startingRow = (int) (Math.random() * 22);
                while (usedRow.contains(startingRow)){
                    startingRow = (int) (Math.random() * 22);
                }
                for (int i = startingRow; i < startingRow + 1 + (int) (Math.random() * 4); i++) {
                    usedRow.add(i);
                    for (int j = 0; j < grid[0].length; j++) {
                        grid[i][j] = 1;
                    }
                }
            } else if (probability < 0.85 && probability > 0.7) { // train tracks
                int startingRow = (int) (Math.random() * 22);
                while (usedRow.contains(startingRow)){
                    startingRow = (int) (Math.random() * 22);
                }
                for (int i = startingRow; i < startingRow + 1 + (int) (Math.random() * 3); i++) {
                    usedRow.add(i);
                    for (int j = 0; j < grid[0].length; j++) {
                        grid[i][j] = 3;
                    }
                }
            } else if (probability > 0.85) { // water
                int startingRow = (int) (Math.random() * 22);
                while (usedRow.contains(startingRow)){
                    startingRow = (int) (Math.random() * 22);
                }
                for (int i = startingRow; i < startingRow + 1 + (int) (Math.random() * 4); i++) {
                    usedRow.add(i);
                    for (int j = 0; j < grid[0].length; j++) {
                        grid[i][j] = 2;
                    }
                }
            }
        }

        // objects
        for (int x = 0; x < 30; x++){
            int xVal = (int) (Math.random() * 40);
            int yVal = (int) (Math.random() * 30);
            int objectNum = 4 + (int) (Math.random() * 4);
            if (objectNum == 4){

            }
        }
        System.out.println();
        printMapping();

        // NEED TO MAKE OBJECTS TOO; NOT SURE IF THEY SHOULD INDIVIDUALLY HAVE A CLASS;
        // MIGHT NOT BE NECESSARY FOR COLLISIONS THOUGH
        // COLLISIONS COULD BE DONE BY LOCATION BASED THINGS
        // ALSO ONLY CERTAIN OBJECTS MOVE...
    }

    public void UpdateGrid(){

    }

    private void printMapping(){
        for (int rows = 0; rows < grid.length; rows ++){
            System.out.println(Arrays.toString(grid[rows]));
        }
    }
}
