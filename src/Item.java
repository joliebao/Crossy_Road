public class Item {
    protected int numAssociation;
    protected int start;
    protected int y;
    protected int x;

    public Item (int num, int x, int y){
        x = x;
        start = x;
        this.y = y;
        numAssociation = num;
    }

    public int getStart(){
        return start;
    }

    public int getY(){
        return y;
    }

    public int getX(){
        return x;
    }

    public int getNumAssociation(){
        return numAssociation;
    }

    @Override
    public String toString() {
        return numAssociation + "";
    }
}