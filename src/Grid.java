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
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = 0;
            }
        }

        // FLOORING
        for (int x = 0; x < 10; x++) {
            double probability = Math.random();
//            System.out.println(probability);
            if (probability < 0.7 && probability > 0.4) { // concrete
                int startingRow = (int) Math.random() * 25;
                for (int i = startingRow; i < startingRow + (int) Math.random() * 4; i++) {
                    System.out.println(i);
                    for (int j = 0; j < grid[0].length; j++) {
                        grid[i][j] = 1;
                    }
                }
            } else if (probability < 0.85 && probability > 0.7) { // train tracks
                int startingRow = (int) Math.random() * 25;
                for (int i = startingRow; i < startingRow + (int) Math.random() * 2; i++) {
                    System.out.println(i);
                    for (int j = 0; j < grid[0].length; j++) {
                        grid[i][j] = 3;
                    }
                }
            } else if (probability < 1 && probability > 0.85) { // water
                int startingRow = (int) Math.random() * 25;
                for (int i = startingRow; i < startingRow + (int) Math.random() * 2; i++) {
                    System.out.println(i);
                    for (int j = 0; j < grid[0].length; j++) {
                        grid[i][j] = 2;
                    }
                }
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
