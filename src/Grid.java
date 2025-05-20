import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    private Item[][] grid = new Item[30][40];
    long time = System.currentTimeMillis();

    // generate random grid -> needs numbers from 1-9 randomly generated
    public Grid(){
        // FLOORING: GRASS (0)

        for (int counter = 0; counter < grid.length; counter++) {
            double probability = Math.random();

            // FLOORING: ROAD (1)
            if (probability < 0.7 && probability > 0.4) {
                placeRoad(counter);
            // FLOORING: TRAIN TRACKS (3)
            } else if (probability < 0.85 && probability > 0.7) {
                placeTracks(counter);
            // FLOORING: WATER (2)
            } else if (probability > 0.85) {
                placeWater(counter);
            } else {
                placeGrass(counter);
            }
        }

        System.out.println();
        printMapping();
    }

    // need to edit this to place as many roads as I input for each section
    public void placeRoad(int row){
        for (int j = 0; j < grid[0].length; j++) {
            grid[row][j] = new Item(1, j * 30, row * 30);
        }
    }

    public void placeCar(int row){
        for (int i = 0; i < 2; i++) {
            int locX = (int) (Math.random() * 30);
            for (int col = 0; col < 2; col++) {
                grid[row][locX + col] = new Vehicle(5, col * 30, row * 30);
            }
        }
    }

    public void placeTruck(int row){
        for (int i = 0; i < 2; i++) {
            int locX = (int) (Math.random() * 30);
            for (int col = 0; col < 2; col++) {
                grid[row][locX + col] = new Vehicle(6, col * 30, row * 30);
            }
        }
    }

    public void placeWater(int row){
        for (int j = 0; j < grid[0].length; j++) {
            grid[row][j] = new Item(2, j * 30, row * 30);
        }
    }

    public void placeLog(int row){
        int c = (int) (Math.random() * 32);
        for (int col = c; col < c + 5 + (int) (Math.random() * 2); col++) {
            grid[row][col] = new Vehicle(7, col * 30, row * 30);
        }
    }

    public void placeTracks(int row){
        for (int j = 0; j < grid[0].length; j++) {
            grid[row][j] = new Item(3, j * 30, row * 30);
        }
    }

    public void placeTrain(int row){
        for (int col = 0; col < 40; col++) {
            grid[row][col] = new Vehicle(7, col * 30, row * 30);
        }
    }

    public void placeGrass(int row){
        for (int j = 0; j < grid[0].length; j++) {
            grid[row][j] = new Item(0, j * 30, row * 30);
        }
    }

    public void placeTrees(int row, int col){
        grid[row][col] = new Item(4, row * 30, col * 30);
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
            for (int r = 0; r < 2; r++){
                double probability = Math.random();

                if (probability < 0.7 && probability > 0.4) {
                    placeRoad(r);
                } else if (probability < 0.85 && probability > 0.7) {
                    placeTracks(r);
                } else if (probability > 0.85) {
                    placeWater(r);
                } else {
                    placeGrass(r);
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
        } else if (num == 4){ // tree
            return false;
        }
        return true;
    }

    public void updatePlayerLoc(int x, int y){

    }
}