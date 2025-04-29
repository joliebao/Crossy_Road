import java.util.Arrays;

public class Grid {
    private int[][] grid = new int[30][40];

    // generate random grid -> needs numbers from 1-9 randomly generated
    /*
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

        double probability = Math.random();
        System.out.println(probability);
        if (probability < 0.3){ // concrete
            System.out.println("CONCRETE");
            for (int i = (int) Math.random() * 30; i < Math.random() * 4; i++){
                for(int j = 0; j < grid[0].length; j++){
                    grid[i][j] = 1;
                }
            }
        } else if (probability < 0.2){ // train tracks
            System.out.println("TRAIN");
            for (int i = (int) Math.random() * 30; i < Math.random() * 2; i++){
                for (int j = 0; j < grid[0].length; j++){
                    grid[i][j] = 3;
                }
            }
        }

        printMapping();
    }

    public void UpdateGrid(){

    }

    private void printMapping(){
        for (int rows = 0; rows < grid.length; rows ++){
            System.out.println(Arrays.toString(grid[rows]));
        }
    }
}
