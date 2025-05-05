import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    private Item[][] grid = new Item[30][40];

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

        int y = 0;
        for (int i = 0; i < grid.length; i++){
            int x = 0;
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = new Item(0, x, y);
                x += 30;
            }
            y += 30;
        }

        // FLOORING
        for (int counter = 0; counter < 13; counter++) {
            double probability = Math.random();
            if (probability < 0.7 && probability > 0.4) { // concrete
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
            } else if (probability < 0.85 && probability > 0.7) { // train tracks
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
            } else if (probability > 0.85) { // water
                int startingRow = (int) (Math.random() * 22);
                while (usedRow.contains(startingRow)){
                    startingRow = (int) (Math.random() * 22);
                }
                for (int i = startingRow; i < startingRow + 1 + (int) (Math.random() * 4); i++) {
                    usedRow.add(i);
                    y = startingRow;
                    int x = 0;
                    for (int j = 0; j < grid[0].length; j++) {
                        grid[i][j] = new Item(2, x, y);
                        x += 30;
                    }
                }
            }
        }

        // objects
        for (int counter = 0; counter < 30; counter++){
            int xVal = (int) (Math.random() * 32);
            int yVal = (int) (Math.random() * 30);
            int objectNum = 4 + (int) (Math.random() * 5);

            if (objectNum > 4){ // vehicles
                int size = 2;
                if (objectNum == 5) { // car
                    size = 2 + (int) (Math.random() * 1);
                } else if (objectNum == 6){ // truck
                    size = 3 + (int) (Math.random() * 2);
                } else if (objectNum == 7){ // train
                    xVal = 0;
                    size = 40;
                } else if (objectNum == 8){ // log
                    size = 5 + (int) (Math.random() * 2);
                }

                for (int i = xVal; i < (xVal + size); i++) {
                    System.out.println(i);
                    grid[yVal][i] = new Vehicle(objectNum, xVal * 30, yVal * 30);
                }
            } else {    // boulders
                grid[yVal][xVal] = new Item(objectNum, xVal * 30, yVal * 30);
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
