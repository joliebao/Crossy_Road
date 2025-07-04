import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    private Item[][] grid = new Item[30][40];
    private boolean train; // GET BACK TO THIS!!!
    private final Player p = new Player();
    private boolean lost;
    private static Item[] surroundings = new Item[8];
    private static int count = 0;
    private int trainCounter = 0;
    private static ArrayList<Integer> loadingVehicles = new ArrayList<Integer>();
    private Item savedItem;

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

            grid[p.getY()][p.getX()] = p;
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
        for (int col = 0; col < 2; col++) {
            int locX = (int) (Math.random() * 30);

            // avoid overlap
            if (getNumAssociation(row, locX) != 1 && getNumAssociation(row, locX + 2) != 1){
                locX = (int) (Math.random() * 30);
            }
            grid[row][locX + col] = new Vehicle(5, locX, row);
        }
    }

    public void placeTruck(int row){
        for (int col = 0; col < 3; col++) {
            int locX = (int) (Math.random() * 30);

            // avoid overlap
            if (getNumAssociation(row, locX) != 1 && getNumAssociation(row, locX + 3) != 1){
                locX = (int) (Math.random() * 30);
            }
            grid[row][locX + col] = new Vehicle(6, locX, row);
        }
    }

    public void placeWater(int row){
        for (int col = 0; col < grid[0].length; col++) {
            grid[row][col] = new Item(2, col, row);
        }
    }

    public void placeLog(int row){
        int c = (int) (Math.random() * 32);

        // avoid overlap
        if (getNumAssociation(row, c) != 2 && getNumAssociation(row, c + 6) != 2){
            c = (int) (Math.random() * 30);
        }

        for (int col = c; col < c + 5; col++) {
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
        if (!lost) {
            changePlayerLoc(false, 1, p.getX(), p.getY());
            moveVehicles();

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
            System.out.println();
            printMapping();
        }

        if (p.getY() >= 29){
            lost = true;
        }
    }

    public void moveVehicles(){
        int numAssociationFloor = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = grid[0].length - 1; c > 0; c--){
                if (grid[r][c] instanceof Vehicle && grid[r][c].getNumAssociation() != 7){
                    // load new vehicles
                    loadVehicles(r);
                    trainPassing();

                    int start = grid[r][c].getStart();
                    int numAssociation = grid[r][c].getNumAssociation();

                    // updates the start
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

    public void trainPassing(){
        if (trainCounter < 1000 && train){
            trainCounter++;
        }

        if (trainCounter == 1000){
            train = false;
            trainCounter = 0;
        }
        // train commands
        if (train){
            for (int r = 0; r < grid.length; r++){ // set to train
                if (grid[r][0].getNumAssociation() == 3){
                    for (int c = 0; c < grid[0].length; c++){
                        grid[r][c] = new Item(7, c, r);
                    }
                }
            }
        } else {
            for (int r = 0; r < grid.length; r++){
                if (grid[r][0].getNumAssociation() == 7){
                    for (int c = 0; c < grid[0].length; c++){
                        grid[r][c] = new Item(3, c, r);
                    }
                }
            }
        }
    }

    public void loadVehicles(int row){
        double random = Math.random();

        if (random < 0.25){
            loadingVehicles.add(2);
            for (int i = 0; i < 2; i++) { // only does this to one thing
                grid[row][0] = new Vehicle(5, 0, row);
            }
        } else if (random < 0.5 && random > 0.25){
            loadingVehicles.add(3);
            for (int i = 0; i < 3; i++) {
                grid[row][0] = new Vehicle(6, 0, row);
            }
        } else if (random < 0.85 && random > 0.5){
            loadingVehicles.add(6);
            for (int i = 0; i < 6; i++) {
                grid[row][0] = new Vehicle(8, 0, row);
            }
        } else if (random > 0.85 && !train){
            train = true;
        }

        for (int i = 0; i < loadingVehicles.size(); i ++){
            int num = loadingVehicles.get(i) - 1;
            if (num == 0){
                loadingVehicles.remove(i);
                i--;
            } else {
                loadingVehicles.set(i, num);
            }
        }
    }

    public void changePlayerLoc(boolean xChange, int changeBy, int x, int y) {
        if (xChange) { // need to change this
            savedItem = grid[y][x+changeBy];
        } else {
            savedItem = grid[y+changeBy][x];
        }

        int num = -1;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if (!(grid[r][c] instanceof Player)){
                    num = grid[r][c].getNumAssociation();
                } else{
                    grid[r][c] = new Item(num, c, r);
                }
            }
        }

        if (xChange) {
            p.setX(changeBy);
            grid[p.getY()][p.getX()] = p;
        } else {
            p.setY(changeBy);
            grid[p.getY()][p.getX()] = p;
        }
    }

    public boolean isLost(boolean pressed, boolean start){
        if (start) {
            // player bounds
            if (p.getY() >= 29) {
                return true;
            } else if (p.getX() >= 39) {
                return true;
            } else if (p.getX() <= 0) {
                return true;
            }

            if (pressed){
                count++;
            }

            surroundings[0] = new Item (grid[p.getY()][p.getX() - 1].getNumAssociation(), p.getX() - 1, p.getY());
            surroundings[1] = new Item (grid[p.getY()][p.getX() + 1].getNumAssociation(), p.getX() + 1, p.getY());
            surroundings[2] = new Item (grid[p.getY() - 1][p.getX()].getNumAssociation(), p.getX(), p.getY() - 1);
            surroundings[3] = new Item (grid[p.getY() + 1][p.getX()].getNumAssociation(), p.getX(), p.getY() + 1);

            for (int i = 0; i < 4; i++){
                Item collider = surroundings[i];
                int c;
                int r;

                if (count % 2 == 1) {
                    c = surroundings[i].getX();
                    r = surroundings[i].getY();
                } else {
                    c = surroundings[i + 4].getX();
                    r = surroundings[i + 4].getY();
                }

                if (c == p.getX() && r == p.getY()){
                    if (collider.getNumAssociation() == 2){
                        return true;
                    } else if (collider.getNumAssociation() == 4){
                        return true;
                    } else if (collider.getNumAssociation() == 5){
                        return true;
                    } else if (collider.getNumAssociation() == 7){
                        return true;
                    }
                }
            }

            surroundings[4] = new Item (grid[p.getY()][p.getX() - 1].getNumAssociation(), p.getX() - 1, p.getY());
            surroundings[5] = new Item (grid[p.getY()][p.getX() + 1].getNumAssociation(), p.getX() + 1, p.getY());
            surroundings[6] = new Item (grid[p.getY() - 1][p.getX()].getNumAssociation(), p.getX(), p.getY() - 1);
            surroundings[7] = new Item (grid[p.getY() + 1][p.getX()].getNumAssociation(), p.getX(), p.getY() + 1);
        }
        return lost;
    }

    // Need to save the type of item before it is changed to the player!
    private void printMapping(){
        for (int rows = 0; rows < grid.length; rows ++){
            System.out.println(Arrays.toString(grid[rows]));
        }
    }

    public int getRLength(){
        return grid.length;
    }

    public int getCLength(){
        return grid[0].length;
    }

    public int getNumAssociation(int r, int c){
        return grid[r][c].getNumAssociation();
    }

    public int getPlayerX(){
        return p.getX();
    }

    public int getPlayerY(){
        return p.getY();
    }

    public Item getItem(int r, int c){
        return grid[r][c];
    }
}