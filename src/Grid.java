import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    private Item[][] grid = new Item[30][40];
    private boolean train;
    private long time = System.currentTimeMillis();
    private Player p = new Player();

    // generate random grid -> needs numbers from 1-9 randomly generated
    public Grid(){
        for (int counter = 0; counter < grid.length; counter++) {
            double probability = Math.random();

            if (probability < 0.7 && probability > 0.4) {
                placeRoad(counter);
                for (int i = 0; i < 3; i++){
                    placeCar(counter);
                }
                placeTruck(counter);
            } else if (probability < 0.85 && probability > 0.7) {
                placeTracks(counter);
                if (train){
                    placeTrain(counter);
                }
            } else if (probability > 0.85) {
                placeWater(counter);
                for (int i = 0; i < 3; i++){
                    placeLog(counter);
                }
            } else {
                placeGrass(counter);
                for (int i = 0; i < 5; i ++) {
                    int col = 1 + (int) (Math.random() * 30);
                    placeTrees(counter, col);
                }
            }
        }

        System.out.println();
        printMapping();
    }

    // need to edit this to place as many roads as I input for each section
    public void placeRoad(int row){
        for (int col = 0; col < grid[0].length; col++) {
            grid[row][col] = new Item(1, col, row);
        }
    }

    public void placeCar(int row){
        for (int i = 0; i < 2; i++) {
            int locX = (int) (Math.random() * 30);
            for (int col = 0; col < 2; col++) {
                grid[row][locX + col] = new Vehicle(5, locX, row);
            }
        }
    }

    public void placeTruck(int row){
        for (int i = 0; i < 2; i++) {
            int locX = (int) (Math.random() * 30);
            for (int col = 0; col < 2; col++) {
                grid[row][locX + col] = new Vehicle(6, locX, row);
            }
        }
    }

    public void placeWater(int row){
        for (int col = 0; col < grid[0].length; col++) {
            grid[row][col] = new Item(2, col, row);
        }
    }

    public void placeLog(int row){
        int c = (int) (Math.random() * 32);
        for (int col = c; col < c + 5 + (int) (Math.random() * 2); col++) {
            grid[row][col] = new Vehicle(8, c, row);
        }
    }

    public void placeTracks(int row){
        for (int col = 0; col < grid[0].length; col++) {
            grid[row][col] = new Item(3, col, row);
        }
    }

    public void placeTrain(int row){
        for (int col = 0; col < 40; col++) {
            grid[row][col] = new Vehicle(7, col, row);
        }
    }

    public void placeGrass(int row){
        for (int col = 0; col < grid[0].length; col++) {
            grid[row][col] = new Item(0, col, row);
        }
    }

    public void placeTrees(int row, int col){
        grid[row][col] = new Item(4, row, col);
    }

    public void updateGrid(){
        moveVehicles();

        if (System.currentTimeMillis() - time == 600) {
            time = System.currentTimeMillis();
            // Moving everything down a row
            for (int r = grid.length - 2; r >= 0; r--) {
                for (int c = 0; c < grid[0].length; c++) {
                    grid[r + 1][c] = grid[r][c];
                }
            }

            double probability = Math.random();

            if (probability < 0.7 && probability > 0.4) { // 41-69
                placeRoad(0);
                for (int i = 0; i < 3; i++) {
                    placeCar(0);
                }
                placeTruck(0);
            } else if (probability < 0.85 && probability > 0.7) { // 71-84
                placeTracks(0);
                if (train) {
                    placeTrain(0);
                }
            } else if (probability > 0.85) { // 86
                placeWater(0);
                for (int i = 0; i < 3; i++) {
                    placeLog(0);
                }
            } else {
                placeGrass(0);
                for (int i = 0; i < 5; i++) {
                    int col = 1 + (int) (Math.random() * 30);
                    placeTrees(0, col);
                }
            }
        }
        System.out.println();
        printMapping();
    }

    public void moveVehicles(){
        int numAssociationFloor = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = grid[0].length - 1; c > 0; c--){
                if (grid[r][c] instanceof Vehicle){
                    int start = grid[r][c].getStart();
                    int numAssociation = grid[r][c].getNumAssociation();

                    // keeps only updating the start
                    if (c + 1 < grid[0].length) {
                        grid[r][c + 1] = new Vehicle(numAssociation, start + 1, r);
                    }
                    grid[r][c] = new Item(numAssociationFloor, c, r);

                } else {
                    numAssociationFloor = grid[r][c].getNumAssociation();
                }
            }
        }
    }

    private void printMapping(){
        for (int rows = 0; rows < grid.length; rows ++){
            System.out.println(Arrays.toString(grid[rows]));
        }
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

    public void movePlayer(boolean xChange, int changeBy, int x, int y){
        int numAssociation = -1;
        int i = 0;
        while (numAssociation == -1){
            if (!(grid[y][i] instanceof Vehicle)){
                numAssociation = grid[y][i].getNumAssociation();
            }
            i ++;
        }
        grid[y][x] = new Item(numAssociation, x, y);

        if (xChange){
            grid[y][x + changeBy] = p;
        } else {
            grid[y + changeBy][x] = p;
        }
    }
}