public class Item {
    protected int numAssociation;
    protected int x;
    protected int y;

    public Item (int num, int x, int y){
        this.x = x;
        this.y = y;
        numAssociation = num;
    }

    public int start(){
        return x;
    }

    public int place(){
        return y;
    }

    public int getNumAssociation(){
        return numAssociation;
    }
}
