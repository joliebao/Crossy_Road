public class Player extends Item{
    private int number;
    private int x;
    private int y;

    public Player(){
        super(9, 18, 28);
        number = 9;
        x = 19;
        y = 28;
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

    public void setX(int x) {
        this.x += x;
    }

    public void setY(int y) {
        this.y += y;
    }

    @Override
    public String toString() {
        return "Player is at " + x + ", " + y;
    }
}
