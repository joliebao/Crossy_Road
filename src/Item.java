public class Item {
    protected int numAssociation;
    protected int start;
    protected int y;

    public Item (int num, int x, int y){
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

    public int getNumAssociation(){
        return numAssociation;
    }

    @Override
    public String toString() {
        return numAssociation + "";
    }
}