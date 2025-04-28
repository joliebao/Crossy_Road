public class Player {
    private int number;
    private int x;
    private int y;

    public Player(){
        number = 9;
        x = 20;
        y = 40;
    }

    public int getNum(){
        return number;
    }

    // Get location
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
