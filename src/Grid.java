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
    }

    public void UpdateGrid(){

    }

    private void printMapping(){
        for (int rows = 0; rows < grid.length; rows ++){
            System.out.println(grid[rows]);
        }
    }
}
