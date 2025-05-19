import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    private Item[][] grid = new Item[30][40];
    long time = System.currentTimeMillis();

    // generate random grid -> needs numbers from 1-9 randomly generated
    public Grid(){
        ArrayList<Integer> usedRow = new ArrayList<Integer>();
        // FLOORING: GRASS (0)
        placeGrass(grid.length);

        for (int counter = 0; counter < 13; counter++) {
            double probability = Math.random();

            // FLOORING: ROAD (1)
            if (probability < 0.7 && probability > 0.4) {
                int startingRow = (int) (Math.random() * 22);
                while (usedRow.contains(startingRow)){
                    startingRow = (int) (Math.random() * 22);
                }
                for (int i = startingRow; i < startingRow + 1 + (int) (Math.random() * 4); i++) {
                    placeRoad(startingRow);
                }

            // FLOORING: TRAIN TRACKS (3)
            } else if (probability < 0.85 && probability > 0.7) {
                int startingRow = (int) (Math.random() * 22);
                while (usedRow.contains(startingRow)){
                    startingRow = (int) (Math.random() * 22);
                }

                placeTracks(usedRow, 22);
            // FLOORING: WATER (2)
            } else if (probability > 0.85) {
                placeWater(usedRow, 22);
            }
        }

        usedRow.clear();    // reset row
        System.out.println();
        printMapping();
    }

    // need to edit this to place as many roads as I input for each section
    public void placeRoad(int row){
        for (int j = 0; j < grid[0].length; j++) {
            int x = j * 30;
            grid[row][j] = new Item(1, x, row);
            x += 30;
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

    public void placeCar(){

    }

    public void placeTruck(){

    }

    public void placeWater(ArrayList<Integer> usedRow, int numRows){
        int startingRow = (int) (Math.random() * numRows);
        while (usedRow.contains(startingRow)){
            startingRow = (int) (Math.random() * numRows);

            // OBJECT: LOGS (8)
            int c = (int) (Math.random() * 32);
            for (int col = c; col < c + 5 + (int) (Math.random() * 2); col++) {
                grid[startingRow][col] = new Vehicle(7, col * 30, startingRow * 30);
            }
        }
    }

    public void placeLog(){}

    public void placeTracks(ArrayList<Integer> usedRow, int numRows){
        int startingRow = (int) (Math.random() * numRows);
        while (usedRow.contains(startingRow)){
            startingRow = (int) (Math.random() * numRows);
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

    public void placeTrain(){

    }

    public void placeGrass(int numRows){
        // FLOORING: GRASS (0)
        int y = 0;
        for (int i = 0; i < numRows; i++){
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

        if (System.currentTimeMillis() - time == 300){
            time = System.currentTimeMillis();

            for (int y = grid.length - 2; y >= 0; y--){
                grid[y+1] = grid[y];
            }

            // I have to refactor all this code so that I can generate two rows normally later

            // Two new generated rows
//            for (int r = 0; r < 2; r++){
//                double probability = Math.random();
//
//                if (probability < 0.7 && probability > 0.4) {
//                    placeRoad(usedRow, 1);
//                } else if (probability < 0.85 && probability > 0.7) {
//                    placeTracks(usedRow, 1);
//                } else if (probability > 0.85) {
//                    placeWater(usedRow, 1);
//                } else {
//                    placeGrass(1);
//                }
//            }

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