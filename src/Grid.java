import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    private Item[][] grid = new Item[30][40];

    // generate random grid -> needs numbers from 1-9 randomly generated
    public Grid(){
        ArrayList<Integer> usedRow = new ArrayList<Integer>();

        // FLOORING: GRASS (0)
        int y = 0;
        for (int i = 0; i < grid.length; i++){
            int x = 0;
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = new Item(0, x, y);
                x += 30;
            }
            y += 30;
        }

        // OBJECT: BOULDERS (4)
        for (int i = 0; i < 21; i ++) {
            int xVal = (int) (Math.random() * 32);
            int yVal = (int) (Math.random() * 30);
            grid[yVal][xVal] = new Item(4, xVal * 30, yVal * 30);
        }

        for (int counter = 0; counter < 13; counter++) {
            double probability = Math.random();

            // FLOORING: ROAD (1)
            if (probability < 0.7 && probability > 0.4) {
                int startingRow = (int) (Math.random() * 22);
                while (usedRow.contains(startingRow)){
                    startingRow = (int) (Math.random() * 22);
                }
                for (int i = startingRow; i < startingRow + 1 + (int) (Math.random() * 4); i++) {
                    usedRow.add(i);
                    y = startingRow;
                    int x = 0;
                    for (int j = 0; j < grid[0].length; j++) {
                        grid[i][j] = new Item(1, x, y);
                        x += 30;
                    }
                }

                // OBJECT: CAR (5)
                for (int i = 0; i < 7; i ++) {
                    int locX = (int) (Math.random() * 35);
                    for (int col = 0; col < ((int) (Math.random() * 2)) + 2; col++) {
                        grid[startingRow][locX + col] = new Vehicle(5, col * 30, startingRow * 30);
                    }
                    // OBJECT: TRUCK (6)
                    locX = (int) (Math.random() * 35);
                    for (int col = 0; col < ((int) (Math.random() * 2)) + 4; col++) {
                        grid[startingRow][locX + col] = new Vehicle(6, col * 30, startingRow * 30);
                    }
                }

            // FLOORING: TRAIN TRACKS (3)
            } else if (probability < 0.85 && probability > 0.7) {
                int startingRow = (int) (Math.random() * 22);
                while (usedRow.contains(startingRow)){
                    startingRow = (int) (Math.random() * 22);
                }
                for (int i = startingRow; i < startingRow + 1 + (int) (Math.random() * 3); i++) {
                    usedRow.add(i);
                    y = startingRow;
                    int x = 0;
                    for (int j = 0; j < grid[0].length; j++) {
                        grid[i][j] = new Item(3, x, y);
                        x += 30;
                    }
                }
                // OBJECT: TRAIN (7)
                for (int col = 0; col < 40; col++) {
                    grid[startingRow][col] = new Vehicle(7, col * 30, startingRow * 30);
                }

            // FLOORING: WATER (2)
            } else if (probability > 0.85) {
                int startingRow = (int) (Math.random() * 22);
                while (usedRow.contains(startingRow)){
                    startingRow = (int) (Math.random() * 22);

                    // OBJECT: LOGS (8)
                    int c = (int) (Math.random() * 32);
                    for (int col = c; col < c + 5 + (int) (Math.random() * 2); col++) {
                        grid[startingRow][col] = new Vehicle(7, col * 30, startingRow * 30);
                    }
                }
            }
        }
        System.out.println();
        printMapping();
    }

    public void UpdateGrid(){
        long time = System.currentTimeMillis();
        // delete row, move everything down :( ; generate new row
        if (System.currentTimeMillis() - time == 500){
            for (int x = 0; x < grid[0].length; x++){
                for (int y = 0; y < grid.length; y++){

                }
            }
        }
    }

    private void printMapping(){
        for (int rows = 0; rows < grid.length; rows ++){
            System.out.println(Arrays.toString(grid[rows]));
        }
    }
}
