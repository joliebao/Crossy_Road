import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

// NOTE* Need to use the usedRow arraylist to check if the row was used more than a certain amount of times
// (This will make sure the objects are well spread out)
// Doing it later :)
// Might also need to change some of this code because it's structure is not well done

public class Grid {
    private Item[][] grid = new Item[30][40];
    long time = System.currentTimeMillis();

    // generate random grid -> needs numbers from 1-9 randomly generated
    public Grid(){
        ArrayList<Integer> usedRow = new ArrayList<Integer>();
        // FLOORING: GRASS (0)
        placeGrass();

        for (int counter = 0; counter < 13; counter++) {
            double probability = Math.random();
            // FLOORING: ROAD (1)
            if (probability < 0.7 && probability > 0.4) {
                placeRoad(usedRow);
            // FLOORING: TRAIN TRACKS (3)
            } else if (probability < 0.85 && probability > 0.7) {
                placeTracks(usedRow);
            // FLOORING: WATER (2)
            } else if (probability > 0.85) {
                placeWater(usedRow);
            }
        }

        usedRow.clear();    // reset row
        System.out.println();
        printMapping();
    }

    public void placeRoad(ArrayList<Integer> usedRow){
        ArrayList<Integer> roads = new ArrayList<Integer>(); // prevent overlap of objects

        int startingRow = (int) (Math.random() * 22);
        while (usedRow.contains(startingRow)){
            startingRow = (int) (Math.random() * 22);
        }
        for (int i = startingRow; i < startingRow + 1 + (int) (Math.random() * 4); i++) {
            usedRow.add(i);
            roads.add(i);
            int x = 0;
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Item(1, x, startingRow);
                x += 30;
            }
        }

        // OBJECT: CAR (5)
        for (int road : roads) {
            for (int i = 0; i < 2; i++) {
                int locX = (int) (Math.random() * 30);
                for (int col = 0; col < 2; col++) {
                    grid[road][locX + col] = new Vehicle(5, col * 30, road * 30);
                }
                // OBJECT: TRUCK (6)
                locX = (int) (Math.random() * 30);
                for (int col = 0; col < 4; col++) {
                    grid[road][locX + col] = new Vehicle(6, col * 30, road * 30);
                }
            }
        }
    }

    public void placeWater(ArrayList<Integer> usedRow){
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

    public void placeTracks(ArrayList<Integer> usedRow){
        int startingRow = (int) (Math.random() * 22);
        while (usedRow.contains(startingRow)){
            startingRow = (int) (Math.random() * 22);
        }
        for (int i = startingRow; i < startingRow + 1 + (int) (Math.random() * 3); i++) {
            usedRow.add(i);
            int x = 0;
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Item(3, x, startingRow);
                x += 30;
            }
        }
        // OBJECT: TRAIN (7)
        for (int col = 0; col < 40; col++) {
            grid[startingRow][col] = new Vehicle(7, col * 30, startingRow * 30);
        }
    }

    public void placeGrass(){
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

        // OBJECT: TREES (4)
        for (int i = 0; i < 21; i ++) {
            int xVal = (int) (Math.random() * 32);
            int yVal = (int) (Math.random() * 30);
            grid[yVal][xVal] = new Item(4, xVal * 30, yVal * 30);
        }
    }

    public void UpdateGrid(){
        ArrayList<Integer> usedRow = new ArrayList<Integer>();

        if (System.currentTimeMillis() - time == 500){
            time = System.currentTimeMillis();

            for (int y = grid.length - 2; y > 0; y--){
                grid[y+1] = grid[y];
            }

            // Two new generated rows
            for (int r = 0; r < 2; r++){
                double probability = Math.random();

                // FLOORING: ROAD (1)
                if (probability < 0.7 && probability > 0.4) {
                    placeRoad(usedRow);
                    // FLOORING: TRAIN TRACKS (3)
                } else if (probability < 0.85 && probability > 0.7) {
                    placeTracks(usedRow);
                    // FLOORING: WATER (2)
                } else if (probability > 0.85) {
                    placeWater(usedRow);
                } else {
                    placeGrass();
                }
            }

            // ADD VEHICLE MOVEMENTS_________

            System.out.println();
            printMapping();
        }
    }

    private void printMapping(){
        for (int rows = 0; rows < grid.length; rows ++){
            System.out.println(Arrays.toString(grid[rows]));
        }
    }

    public Item checkGrid(int r, int c){
        return grid[r][c];
    }

    // NEED TO FINISH THIS AND APPLY TO EVERYTHING
    public boolean isSafe(int r, int c){
        int num = grid[r][c+1].getNumAssociation();
        if (num == 2){ // water
            return false;
        } else if (num == 5){ // car
            return false;
        } else if (num == 6){ // truck
            return false;
        } else if (num == 7){ // train
            return false;
        }
        return true;
    }

    public void updatePlayerLoc(int x, int y){

    }
}