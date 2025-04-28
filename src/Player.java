public class Player {
    private int number;
    private int x;
    private int y;

    public Player(){
        number = 9;
        x = 15;
        y = 39;
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
}
