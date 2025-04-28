public class Grid {
    private int[][] grid = new int[30][40];

    // generate random grid
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
