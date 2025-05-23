public class Player extends Item{
    private int x;
    private int y;

    public Player(){
        super(9, 18, 28);
        x = 20;
        y = 26;
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
}
